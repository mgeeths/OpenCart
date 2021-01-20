package com.qa.opencart.pages;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.DoubleBuffer;
import java.time.Duration;
import java.util.Random;

public class RegisterPage {

    //Class var
    private WebDriver driver;
    private ElemUtils elemUtils;

    //By locators
    private By firstNameLoc = By.id("input-firstname");
    private By lastNameLoc = By.id("input-lastname");
    private By emailIdLoc = By.id("input-email");
    private By telephoneLoc = By.id("input-telephone");
    private By passwordLoc = By.id("input-password");
    private By confirmPwdLoc = By.id("input-confirm");
    private By yesSubscribeLoc = By.xpath("(//label[@class='radio-inline']/ input[@type='radio'])[1]");
    private By noSubscribeLoc = By.xpath("(//label[@class='radio-inline']/ input[@type='radio'])[2]");
    private By agreePolicy = By.cssSelector("input[name='agree']");
    private By continueBtn = By.cssSelector("input[value='Continue']");

    private By successMsg = By.cssSelector("#content h1");
    private By myAccountLinkLoc = By.linkText("My Account");
    private By logoutLoc = By.linkText("Logout");

    //Constructor
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }
    //Page Actions
    public String getRegisterPageTitle(){
        return driver.getTitle();
    }

    public void doRegister(String fName, String lName, String telephone, String pwd, String subscribe) {

        elemUtils.doSendKeys(firstNameLoc, fName);
        elemUtils.doSendKeys(lastNameLoc, lName);
        Random rand = new Random();
        String eMail = "auto" + String.format("%04d", rand.nextInt(10000)) + "@aol.com";

        elemUtils.doSendKeys(emailIdLoc, eMail);
        elemUtils.doSendKeys(telephoneLoc,telephone);
        elemUtils.doSendKeys(passwordLoc,pwd);
        elemUtils.doSendKeys(confirmPwdLoc, pwd);
        if(subscribe.equals("true")){
            elemUtils.doClick(yesSubscribeLoc);
        }
        else{
            elemUtils.doClick(noSubscribeLoc);
        }
        elemUtils.doClick(agreePolicy);
        elemUtils.doClick(continueBtn);

        elemUtils.doClick(myAccountLinkLoc);
        elemUtils.doClick(logoutLoc);
        elemUtils.waitForPageTitle(Constants.LOGOUT_PAGE_TITLE);
        elemUtils.mouseOver(myAccountLinkLoc);
    }

}
