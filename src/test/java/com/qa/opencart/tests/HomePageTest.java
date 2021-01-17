package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constants.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    @Description("Verify home page title")
    @Severity(SeverityLevel.MINOR)
    public void homePageTitleTest(){
        String title = homePage.getHomePageTitle();
        Assert.assertTrue(title.equals(Constants.HOME_PAGE_TITLE));
    }

    @Test
    @Description("Verify search result for product")
    @Severity(SeverityLevel.CRITICAL)
    public void searchForTest_MacBook() {
        homePage.searchFor("MacBook");
        String title = productsPage.getProductsPageTitle();
        System.out.println(title);
        Assert.assertTrue(title.equalsIgnoreCase("Search - MacBook"));
    }
    @Test
    @Description("Verify navigating to register page")
    @Severity(SeverityLevel.NORMAL)
    public void navToRegisterPageTest(){
        homePage.navToRegisterPage();
        String title = registerPage.getRegisterPageTitle();
        Assert.assertEquals(title, Constants.REGISTER_PAGE_TITLE);
        }

}
