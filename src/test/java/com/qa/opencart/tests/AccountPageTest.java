package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constants.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Feature Name: Accounts Page Epic number:12458")
@Story("User story :356878 - Check the Accounts page sections")
public class AccountPageTest extends BaseTest {
    @BeforeClass
    public void setUpAccPageTest() throws InterruptedException {
        homePage.goToLoginPage();
        loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    @Description("Get the account page URL")
    @Severity(SeverityLevel.MINOR)
    public void getAccPageUrl(){
        String url = accountPage.getAccountPageUrl();
        Assert.assertTrue(url.equals(Constants.ACCOUNT_PAGE_URL));
    }

    @Test
    @Description("Check the number of sections in Accounts Page")
    @Severity(SeverityLevel.NORMAL)
    public void getAccSectionHeadersCountTest(){
        int count = accountPage.getAllAccSectionHeaders();
        Assert.assertTrue(count == Constants.ACC_SECTION_HEADERS_COUNT);
    }


}
