package com.qa.opencart.pages;

import com.qa.opencart.driverFactory.DriverFactory;
import com.qa.opencart.utils.ElemUtils;
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

    public ProductsPage searchFor(String prodName){
        elemUtils.doSendKeys(searchFieldLoc, prodName);
        elemUtils.doClick(searchIconBtn);
        return new ProductsPage(driver);
    }

    public LoginPage goToLoginPage(){
        elemUtils.doClick(myAccountLinkLoc);
        elemUtils.doClick(loginTabLoc);
        return new LoginPage(driver);
    }

    public void doLogout(){
        elemUtils.doClick(logoutLoc);
    }

    public RegisterPage navToRegisterPage(){
        elemUtils.doClick(myAccountLinkLoc);
        elemUtils.doClick(registerTabLoc);
        return new RegisterPage(driver);
    }
}
