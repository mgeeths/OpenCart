package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddressPageTest extends BaseTest {

    @BeforeClass
    public void addressPageSetup() throws InterruptedException {
        homePage.goToLoginPage();
        loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        loginPage.goToAddressPage();
        loginPage.goToNewAddressPage();
        newAddressPage.createNewAddress();

    }

    @Test(priority = 1)
    public void editAddressTest() throws InterruptedException {
        addressPage.goToEditRowWithCity1("Balkh");
        addressPage.editCityInAddress("Balkh");
    }

    @Test(priority = 2)
    public void deleteSpecificAddressTest() throws InterruptedException {
        addressPage.deleteRowWithCity("Paris");
    }
}
