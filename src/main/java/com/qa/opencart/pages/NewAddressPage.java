package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import com.qa.opencart.utils.JSUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAddressPage {
    //class var
    private WebDriver driver;
    private ElemUtils elemUtils;
    private JSUtils jsUtils;

    //By locators
    private By firstNameField = By.id("input-firstname");
    private By lastNameField = By.id("input-lastname");
    private By companyField = By.id("input-company");
    private By address1Field = By.id("input-address-1");
    private By address2Field = By.id("input-address-2");
    private By cityField = By.id("input-city");
    private By countryField = By.id("input-country");
    private By countryFieldDropdown = By.xpath("//select[@id='input-country']");
    private By postCodeField = By.id("input-postcode");
    private By regionField = By.id("input-zone");
    private By regionDropdown = By.xpath("//select[@id='input-zone']");
    private By defaultYesBtn = By.cssSelector("input[type='radio'][value='1']");
    private By defaultNoBtn = By.cssSelector("input[type='radio'][value='0']");
    private By continueBtn =  By.cssSelector("input[type='submit'][value='Continue']");
    private By successMsgAlert = By.cssSelector("div.alert-success");

    //Constructor
    public NewAddressPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
        this.jsUtils = new JSUtils(driver);
    }

    //Page Actions
    public String getPageTitle(){
        return driver.getTitle();
    }

    public Boolean createNewAddress() throws InterruptedException {
        elemUtils.doSendKeys(firstNameField, "Adam");
        elemUtils.doSendKeys(lastNameField,"Fields");
        elemUtils.doSendKeys(companyField,"Amazon");
        elemUtils.doSendKeys(address1Field,"252, Elsa ln");
        elemUtils.doSendKeys(cityField,"Ontorio");
        elemUtils.doClick(countryField);
        elemUtils.selectDropdownByValue(countryFieldDropdown,"1");
        elemUtils.doSendKeys(postCodeField,"356987");
        elemUtils.doClick(regionField);
        elemUtils.selectDropdownByVisibleText(regionDropdown,"Balkh");
        elemUtils.doClick(defaultNoBtn);
        elemUtils.doClick(continueBtn);
        if(elemUtils.getTextOfEle(successMsgAlert).contains("successfully added")){
            return true;
        }
        else{return false;}
    }

    public void editAddress(){
        System.out.println("Edit the address line 1 data");
    }


}
