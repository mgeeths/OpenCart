package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPwdPage {

    //Class Var
    private WebDriver driver;
    private ElemUtils elemUtils;

    private By emailFieldLoc = By.id("id=input-email");
    private By continueBtn = By.cssSelector("input[value='Continue'][type='submit']");

    //Constructor
    public ForgotPwdPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public String getForgotPwdPageUrl(){
        return driver.getCurrentUrl();
    }

    public LoginPage enterPwdRecoveryEmail(String email) throws InterruptedException {
        elemUtils.doSendKeys(emailFieldLoc,email);
        elemUtils.doClick(continueBtn);
        Thread.sleep(2000);
        return new LoginPage(driver);
    }


}
