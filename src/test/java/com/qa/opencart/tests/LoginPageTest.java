package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @BeforeClass
    public void setUpLoginPage(){
        homePage.goToLoginPage();

    }

    @Test(priority = 1)
    public void loginPageUrlTest(){
        String url = loginPage.getLoginPageUrl();
        Assert.assertTrue(url.equals(Constants.LOGIN_PAGE_URL));
    }

    @Test(priority = 2)
    public void loginPageHeaderTest(){
        Assert.assertTrue(loginPage.checkLoginPageHeader());
    }

    @Test(priority = 3)
    public void doValidLoginTest() throws InterruptedException {
        loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accountPage.getAccountPageUrl().equals(Constants.ACCOUNT_PAGE_URL));
    }
}
