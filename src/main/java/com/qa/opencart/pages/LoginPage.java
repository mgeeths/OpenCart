package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //Class var
    private WebDriver driver;
    ElemUtils elemUtils;

    private By loginPageHeaderLoc = By.cssSelector("div#logo a");
    private By uNameLoc = By.id("input-email");
    private By passwordLoc = By.id("input-password");
    private By forgotPwdLinkLoc = By.cssSelector(".form-group>a");
    private By loginBtnLoc = By.cssSelector("input.btn-primary");

    //Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public boolean checkLoginPageHeader(){
        return elemUtils.getElement(loginPageHeaderLoc).isDisplayed();
    }

    public String getLoginPageUrl(){
        return driver.getCurrentUrl();
    }

    public AccountPage doLogin(String uName, String pwd) throws InterruptedException {
        elemUtils.doSendKeys(uNameLoc,uName);
        elemUtils.doSendKeys(passwordLoc,pwd);
        elemUtils.doClick(loginBtnLoc);
        Thread.sleep(2000);
        return new AccountPage(driver);
    }

    public  ForgotPwdPage goToForgotPwdPage(){
        elemUtils.doClick(forgotPwdLinkLoc);
        return new ForgotPwdPage(driver);
    }
}
