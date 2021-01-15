package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class ProdInfoPageTest extends BaseTest {


    @Test
    public void getAllMataDataTest() throws InterruptedException {
        homePage.searchFor("Mac");
        productsPage.goToRespectiveProductPage("MacBook Air");
        HashMap<String, String> prodMap = productInfoPage.getAllMetaData();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(prodMap.get("Brand").trim(), "Apple");
        softAssert.assertEquals(prodMap.get("Availability").trim(), "Out Of Stock");
        softAssert.assertEquals(prodMap.get("Product Price:").trim(), "$1,202.00");
        softAssert.assertEquals(prodMap.get("Name"), "MacBook Air");
        softAssert.assertEquals(prodMap.get("Product Code").trim(), "Product 17");
        softAssert.assertEquals(prodMap.get("Price with Tax :").trim(), "$1,000.00");
        softAssert.assertEquals(prodMap.get("Reward Points").trim(), "700");

        softAssert.assertAll();
    }

    @Test
    public void totalImagesCountTest(){
        int count = productInfoPage.getImageCount();
        Assert.assertEquals(count,3);
    }

    @Test(enabled = false)
    public void verifyEnteringAllInputFields() throws InterruptedException {
        homePage.searchFor("Apple");
        productsPage.goToRespectiveProductPage("Apple Cinema 30");
        productInfoPage.enterAllFields();
    }
}
