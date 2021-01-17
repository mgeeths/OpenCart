package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountPage {

    //Class Var
    private static final Logger logger = LogManager.getLogger(AccountPage.class);
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

    @Step("Get all the section in the Account page")
    public int getAllAccSectionHeaders(){
        List<WebElement> allHeaders = elemUtils.getAllElements(accSectionHeadersLoc);
        logger.info("Entered the accounts page and collected the sections web elements");
        int count = allHeaders.size();
        for(WebElement e:allHeaders){
            System.out.println(e.getText());
        }
        return count;
    }

}
