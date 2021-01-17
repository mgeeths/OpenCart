package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constants.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class RegisterPageTest extends BaseTest {

    @BeforeMethod
    public void goToRegisterPage(){
        homePage.navToRegisterPage();
    }

    @Test
    @Description("Verify register page title")
    @Severity(SeverityLevel.MINOR)
    public void getRegisterPageTitle(){
        String title = registerPage.getRegisterPageTitle();
        Assert.assertEquals(title, Constants.REGISTER_PAGE_TITLE);
    }

    @DataProvider(name= "getRegnTestData")
    public Iterator<Object[]> getRegnTestData(){
        ArrayList<Object[]> myTestDataArr = testDataProvider.getRegistrationData();
        System.out.println((myTestDataArr).size());
        return myTestDataArr.iterator();
    }
    @Description("Verify creating multiple accounts")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getRegnTestData")
    public void registerNewAccounts(String fName, String lName, String telephone, String pwd, String subscribe){
        registerPage.doRegister(fName, lName, telephone, pwd, subscribe);
    }
}
