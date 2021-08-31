package com.qa.opencart.utils;

import com.qa.opencart.driverFactory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ElemUtils {

    //class var
    private WebDriver driver;
    private JSUtils jsUtils;
    public static WebDriverWait wait ;


    //Constructor
    public ElemUtils(WebDriver driver){
        this.driver = driver;
        jsUtils = new JSUtils(driver);
    }

    //Page Actions
    public WebElement getElement(By loc){
        WebElement element = driver.findElement(loc);
        if(DriverFactory.highlight.equals("true")){
            jsUtils.flash(element);
        }
        return element;
    }

    public List<WebElement>
    getAllElements(By loc){
        return driver.findElements(loc);
    }

    //this method will always clear the field before it enter the text
    public void doSendKeys(By loc, String text){
        getElement(loc).clear();
        getElement(loc).sendKeys(text);
    }

    public String getTextOfEle(By loc){
        return getElement(loc).getText();
    }
    public void doClick(By loc){
        getElement(loc).click();
    }

    public void doClear(By loc){
        getElement(loc).clear();
    }

    public void selectDropdownByVisibleText(By loc, String text){
        Select select = new Select(getElement(loc));
        select.selectByVisibleText(text);
    }

    public void selectDropdownByIndex(By loc, int index){
        Select select = new Select(getElement(loc));
        select.selectByIndex(index);
    }

    public void selectDropdownByValue(By loc, String value){
        Select select = new Select(getElement(loc));
        select.selectByValue(value);
    }

    public void mouseOver(By loc){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(loc)).perform();
    }

    public void mouseOverAndClick(By loc){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(loc)).perform();
    }

    public void rightClick(By loc){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(loc)).contextClick().build().perform();
    }

    public void doubleClick(By loc){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(loc)).doubleClick().build().perform();
    }

    public void dragAndDrop(By srcLoc, By tarLoc){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getElement(srcLoc),getElement(tarLoc)).build().perform();
    }

    public void switchToFrameById(String idOrName){
        driver.switchTo().frame(idOrName);
    }

    public void switchToFrameByIndex(int index){
        driver.switchTo().frame(index);
    }

    //Alert/ Popup Handlers
    public Alert switchToAlert(){
        return driver.switchTo().alert();
    }

    public void switchToWindow(String handle){
        driver.switchTo().window(handle);
    }

    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    public void getAllLinksFromASectionInArray(List<WebElement> allLinks){
       String[] allLinksStaticArr = new String[allLinks.size()];
       int i = 0;
       for(WebElement e:allLinks){
           allLinksStaticArr[i++] = e.getText();
       }
    }

    public void doSelectFromJQuerry(List<WebElement> allOptionsList, String... value){
        String flag = value[0];
        if(!flag.equalsIgnoreCase("all")) {
            for (WebElement e : allOptionsList) {
                for (int i = 0; i < value.length; i++)
                    if (e.getText().trim().equalsIgnoreCase(value[i])) {
                        e.click();
                        break;
                    }
            }
        }
        else{
            for(WebElement e:allOptionsList){
                e.click();
            }
        }
    }


    //Explicit Wait Methods
    public void waitForPageTitle(String title){
        wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void waitForPresenceOfElement(By loc){
        wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(loc));
    }

    public void waitForInvisibiltyOfElement(By loc){
        wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOf(getElement(loc)));
    }

    public void waitForInvisibiltyOfElement1(String partUrl){
        wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlContains(partUrl));
    }
}
