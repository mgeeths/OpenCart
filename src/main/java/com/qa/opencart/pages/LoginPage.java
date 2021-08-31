package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import io.qameta.allure.Step;
import org.apache.commons.math3.analysis.function.Add;
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
    private By addressBookLink = By.linkText("Address Book");
    private By newAddressLink = By.linkText("New Address");

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

    @Step("Login with username {0} and password {1}")
    public AccountPage doLogin(String uName, String pwd) {
        elemUtils.doSendKeys(uNameLoc,uName);
        elemUtils.doSendKeys(passwordLoc,pwd);
        elemUtils.doClick(loginBtnLoc);
        return new AccountPage(driver);
    }

    @Step("Navigate to forgot pwd page")
    public  ForgotPwdPage goToForgotPwdPage(){
        elemUtils.doClick(forgotPwdLinkLoc);
        return new ForgotPwdPage(driver);
    }

    public AddressPage goToAddressPage(){
        elemUtils.doClick(addressBookLink);
        return new AddressPage(driver);
    }

    public NewAddressPage goToNewAddressPage(){

        elemUtils.doClick(newAddressLink);
        return new NewAddressPage(driver);
    }


}
