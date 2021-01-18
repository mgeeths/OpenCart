package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewAddressPageTest extends BaseTest {

    @BeforeClass
    public void setUpAddressPage() throws InterruptedException {
        homePage.goToLoginPage();
        loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        loginPage.goToAddressPage();
        loginPage.goToNewAddressPage();
    }

    @Test
    public void createNewAddressTest() throws InterruptedException {
        newAddressPage.createNewAddress();
    }
}
