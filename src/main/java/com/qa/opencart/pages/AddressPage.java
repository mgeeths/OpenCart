package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddressPage {

    //class var
    private WebDriver driver;
    private ElemUtils elemUtils;

    //By locators
    private By newAddressLink = By.linkText("New Address");
    private By allRowsOfAddressTable = By.xpath("//div[@class='table-responsive']//tbody/tr");
    private By deleteSuccessMsg = By.xpath("//div[@class='alert']");
    private By cityField = By.id("input-city");
    private By countryField = By.id("input-country");
    private By countryFieldDropdown = By.xpath("//select[@id='input-country']");
    private By regionField = By.id("input-zone");
    private By regionDropdown = By.xpath("//select[@id='input-zone']");
    private By continueBtn =  By.cssSelector("input[type='submit'][value='Continue']");
    private By successMsgAlert = By.cssSelector("div.alert-success");


    //Constructor
    public AddressPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public String getPageTitle(){
        return driver.getTitle();
    }

    public NewAddressPage goToNewAddressPage(){
        elemUtils.doClick(newAddressLink);
        return new NewAddressPage(driver);
    }

    public AddressPage editRowWithCity(String city) throws InterruptedException {
        List<WebElement> allRows = elemUtils.getAllElements(allRowsOfAddressTable);
        String formattedXpath = "//div[@class='table-responsive']//td[@class='text-left' and contains(.,'" + city + "')]";

        for (WebElement e:allRows){
            if(e.findElement(By.xpath(formattedXpath)).getText().contains(city)){
                System.out.println(e.findElement(By.xpath(formattedXpath)).getText());
                e.findElement(By.xpath(formattedXpath+"/parent::tr//following-sibling::td[@class='text-right']/a[text()='Edit']")).click();
                Thread.sleep(2000);
                break;
            }
        }return new AddressPage(driver);
    }

    public AddressPage goToEditRowWithCity1(String city) throws InterruptedException {
        List<WebElement> allRows = elemUtils.getAllElements(allRowsOfAddressTable);
        String formattedXpath = "//div[@class='table-responsive']//td[@class='text-left' and contains(.,'" + city + "')]";

        for (WebElement e:allRows){
            if(e.findElement(By.xpath(formattedXpath)).getText().contains(city)){
                WebElement specificAddEle = e.findElement(By.xpath(formattedXpath));
                System.out.println(specificAddEle.getText());
                specificAddEle.findElement(By.xpath("./parent::tr//following-sibling::td[@class='text-right']/a[text()='Edit']")).click();
                Thread.sleep(2000);
                break;
            }
        }return new AddressPage(driver);
    }

    public Boolean editCityInAddress(String newCity) throws InterruptedException {
        //goToEditRowWithCity1("Balkh");
        //Thread.sleep(1000);
        elemUtils.doSendKeys(cityField,"Paris");
        elemUtils.doClick(countryField);
        elemUtils.selectDropdownByVisibleText(countryFieldDropdown,"France, Metropolitan");
        Thread.sleep(500);
        elemUtils.doClick(regionField);
        elemUtils.selectDropdownByVisibleText(regionDropdown,"Paris");
        elemUtils.doClick(continueBtn);
        if(elemUtils.getTextOfEle(successMsgAlert).contains("successfully updated")){
            return true;
        }
        return false;
    }

    public Boolean deleteRowWithCity(String city) throws InterruptedException {
        List<WebElement> allRows = elemUtils.getAllElements(allRowsOfAddressTable);
        String formattedXpath = "//div[@class='table-responsive']//td[@class='text-left' and contains(.,'" + city + "')]";

        for (WebElement e : allRows) {
            if (e.findElement(By.xpath(formattedXpath)).getText().contains(city)) {
                WebElement specificAddEle = e.findElement(By.xpath(formattedXpath));
                System.out.println(specificAddEle.getText());
                specificAddEle.findElement(By.xpath("./parent::tr//following-sibling::td[@class='text-right']/a[text()='Delete']")).click();
                Thread.sleep(2000);
                break;
            }
            if (elemUtils.getTextOfEle(deleteSuccessMsg).contains("successfully deleted")) {
                return true;
            }
        }
        return false;

    }
}
