package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductsPageTest extends BaseTest {

    @Test
    public void getProductsPageTitleTest(){
        homePage.searchFor("Mac");
        String title = productsPage.getProductsPageTitle();
        Assert.assertEquals(title,"Search - Mac");
    }

    @Test
    public void getTotalCountOfSearchResults(){
        int count = productsPage.getCountOfResultProducts();
        Assert.assertEquals(count, 4);
    }

    @Test
    public void goToRespProductInfoPageTest() throws InterruptedException {
        productsPage.goToRespectiveProductPage("MacBook Air");
        String title = productInfoPage.getInfoPageTitle();
        Assert.assertEquals(title, "MacBook Air");
    }
}
