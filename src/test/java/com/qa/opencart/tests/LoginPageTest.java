package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constants.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Feature Name: Login Page Epic number:12458")
@Story("User story :356878 - Login to appln ")
public class LoginPageTest extends BaseTest {

    @BeforeClass
    public void setUpLoginPage(){
        homePage.goToLoginPage();

    }

    @Test(priority = 1)
    @Description("Get the login page URL")
    @Severity(SeverityLevel.MINOR)
    public void loginPageUrlTest(){
        String url = loginPage.getLoginPageUrl();
        Assert.assertTrue(url.equals(Constants.LOGIN_PAGE_URL));
    }

    @Test(priority = 2)
    @Description("Verify login page header")
    @Severity(SeverityLevel.MINOR)
    public void loginPageHeaderTest(){
        Assert.assertTrue(loginPage.checkLoginPageHeader());
    }

    @Test(priority = 3)
    @Description("Verify valid login")
    @Severity(SeverityLevel.BLOCKER)
    public void doValidLoginTest() throws InterruptedException {
        loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accountPage.getAccountPageUrl().equals(Constants.ACCOUNT_PAGE_URL));
    }
}
