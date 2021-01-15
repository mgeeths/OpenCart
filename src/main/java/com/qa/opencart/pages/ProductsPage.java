package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariOptions;

public class ProductsPage {

    //Class var
    private WebDriver driver;
    private ElemUtils elemUtils;

    private By searchProductsList = By.cssSelector("div.product-layout div.product-thumb");
    private By productsPageHeader = By.cssSelector("#content h1");


    //Constructor
    public ProductsPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }

   //Page Actions

    public String getProductPageUrl(){
       return driver.getCurrentUrl();
    }

    public String getProductsPageTitle(){
        return driver.getTitle();
    }

    public String getProductsPageHeader(){
        return elemUtils.getTextOfEle(productsPageHeader);
    }

    public int getCountOfResultProducts(){
        int count = elemUtils.getAllElements(searchProductsList).size();
        System.out.println("Total no. of items for the search are : " + count);
        return count;
    }

    public ProductInfoPage goToRespectiveProductPage(String prodName) throws InterruptedException {

        String formattedLoc = "//div[@class='product-thumb']//h4/a[contains(text(),'"+ prodName + "')]";
        driver.findElement(By.xpath(formattedLoc)).click();
        Thread.sleep(500);
        return new ProductInfoPage(driver);
    }

}
