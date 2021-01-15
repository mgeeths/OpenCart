package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class ProductInfoPage {

    //Class var
    private WebDriver driver;
    private ElemUtils elemUtils;

    //By Locators
    private By prodMetaDataListLoc = By.cssSelector("#content ul.list-unstyled:nth-of-type(1) li");
    private By prodPriceListLoc = By.cssSelector("#content ul.list-unstyled:nth-of-type(2) li");
    private By prodNameHeader = By.cssSelector("#content  h1");
    private By imagesListLoc = By.cssSelector(".thumbnails .image-additional");
    private By qtyFieldLoc = By.id("input-quantity");
    private By addToCartBtn = By.id("button-cart");

    private By chkBox1Loc = By.cssSelector(".checkbox input[value='10']");
    private By chkBox2Loc = By.cssSelector(".checkbox input[value='11']");
    private By textInputBox = By.cssSelector("#input-option208");
    private By dropdownLoc = By.id("input-option217");
    private By selectOptions =  By.xpath("//select[@id=\"input-option217\"]/option");
    private By textBox = By.id("input-option209");
    private By fileUploadLoc = By.id("button-upload222");
    private By qtyLoc = By.id("input-quantity");

    //Constructor
    public ProductInfoPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public String getInfoPageTitle(){
        return driver.getTitle();
    }

    public HashMap<String, String> getAllMetaData(){
   List<WebElement> metaDataList = elemUtils.getAllElements(prodMetaDataListLoc);
        System.out.println(metaDataList.size());
        HashMap<String, String> prodMetaDataMap = new HashMap<>();
        prodMetaDataMap.put("Name",elemUtils.getTextOfEle(prodNameHeader));
        for(WebElement e:metaDataList) {
            String mataKey = e.getText().split(":")[0];
            String mataValue = e.getText().split(":")[1];
            prodMetaDataMap.put(mataKey, mataValue);
        }

        List<WebElement> prodPriceList = elemUtils.getAllElements(prodPriceListLoc);
            String prodPrice = prodPriceList.get(0).getText();
            String withTaxPrice = prodPriceList.get(1).getText().split(":")[1].trim();
            prodMetaDataMap.put("Product Price:", prodPrice);
            prodMetaDataMap.put("Price with Tax :", withTaxPrice);

        prodMetaDataMap.forEach((k,v)->System.out.println(k + "  " + v));
        return prodMetaDataMap;
    }

    public void setQuantity(String num){
        elemUtils.doSendKeys(qtyFieldLoc,num);
    }

    public void addToCart(){
        elemUtils.doClick(addToCartBtn);
    }

    public int getImageCount(){
        return elemUtils.getAllElements(imagesListLoc).size();
    }

    public void enterAllFields() throws InterruptedException {
        elemUtils.doClick(chkBox2Loc);
        elemUtils.doSendKeys(textBox,"Hello World");
        elemUtils.doClick(dropdownLoc);
        elemUtils.selectDropdownByVisibleText(selectOptions,"Green");
        elemUtils.doSendKeys(textInputBox, "Starting ChromeDriver 87.0.4280.88 (89e2380a3e36c3464b5dd1302349b1382549290d-refs/branch-heads/4280@{#1761}) on port 28440");
        elemUtils.doSendKeys(fileUploadLoc,"C:\\Users\\browse\\Pictures\\Painting\\fall landscape painting.png");
        elemUtils.doSendKeys(qtyLoc,"3");
        Thread.sleep(2000);

    }
}
