package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountPageTest extends BaseTest {
    @BeforeClass
    public void setUpAccPageTest() throws InterruptedException {
        homePage.goToLoginPage();
        loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void getAccPageUrl(){
        String url = accountPage.getAccountPageUrl();
        Assert.assertTrue(url.equals(Constants.ACCOUNT_PAGE_URL));
    }

    @Test
    public void getAccSectionHeadersCountTest(){
        int count = accountPage.getAllAccSectionHeaders();
        Assert.assertTrue(count == Constants.ACC_SECTION_HEADERS_COUNT);
    }


}
