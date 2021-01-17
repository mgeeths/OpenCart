package com.qa.opencart.pages;

import com.qa.opencart.driverFactory.DriverFactory;
import com.qa.opencart.utils.ElemUtils;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class HomePage {

    //Class Variables
    private DriverFactory df = new DriverFactory();
    private WebDriver driver;
    //private Properties prop;
    private ElemUtils elemUtils;

    private final Logger logger = LogManager.getLogger(HomePage.class);

    //Page Locators
    private By searchFieldLoc = By.name("search");
    private By searchIconBtn = By.cssSelector("span.input-group-btn button");
    private By myAccountLinkLoc = By.linkText("My Account");
    private By loginTabLoc = By.linkText("Login");
    private By registerTabLoc = By.linkText("Register");
    private By logoutLoc = By.linkText("Logout");

    //constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        //this.prop = df.init_prop();
        this.elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public String getHomePageTitle(){
        return driver.getTitle();
    }

    public String getHomePageUrl(){
        return driver.getCurrentUrl();
    }

    @Step("Search for the product name {0}")
    public ProductsPage searchFor(String prodName){
        elemUtils.doSendKeys(searchFieldLoc, prodName);
        logger.info("Entered the product name in the search Field");
        elemUtils.doClick(searchIconBtn);
        return new ProductsPage(driver);
    }

    @Step("Navigate to login page")
    public LoginPage goToLoginPage(){
        elemUtils.doClick(myAccountLinkLoc);
        logger.info("Nav to my account page done");
        elemUtils.doClick(loginTabLoc);
        logger.info("Nav to my home page done");
        return new LoginPage(driver);
    }

    public void doLogout(){
        elemUtils.doClick(logoutLoc);
    }
    @Step("Navigate to registration page")
    public RegisterPage navToRegisterPage(){
        elemUtils.doClick(myAccountLinkLoc);
        logger.info("Nav to my account page done");
        elemUtils.doClick(registerTabLoc);
        logger.info("Nav to my register page done");
        return new RegisterPage(driver);
    }
}
