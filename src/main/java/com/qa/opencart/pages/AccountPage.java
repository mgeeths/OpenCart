package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountPage {

    //Class Var
    private WebDriver driver;
    ElemUtils elemUtils;

    //By Locators
    private By accSectionHeadersLoc = By.cssSelector("div#content h2");

    //Constructor
    public AccountPage(WebDriver driver){
        this.driver = driver;
        elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public String getAccountPageUrl(){
        return driver.getCurrentUrl();
    }

    public int getAllAccSectionHeaders(){
        List<WebElement> allHeaders = elemUtils.getAllElements(accSectionHeadersLoc);
        int count = allHeaders.size();
        for(WebElement e:allHeaders){
            System.out.println(e.getText());
        }
        return count;
    }

}
