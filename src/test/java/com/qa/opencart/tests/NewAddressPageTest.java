package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewAddressPageTest extends BaseTest {

    @BeforeClass(enabled = false)
    public void setUpAddressPage() {
        homePage.goToLoginPage();
        loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        loginPage.goToAddressPage();
        loginPage.goToNewAddressPage();
    }

    @Test(enabled = false)
    public void createNewAddressTest() throws InterruptedException {
        newAddressPage.createNewAddress();
    }
}
