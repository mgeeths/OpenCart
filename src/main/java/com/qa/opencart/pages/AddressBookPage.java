package com.qa.opencart.pages;

import com.qa.opencart.utils.ElemUtils;
import org.openqa.selenium.WebDriver;

public class AddressBookPage {
    //class var
    private WebDriver driver;
    private ElemUtils elemUtils;

    //Constructor
    public AddressBookPage(WebDriver driver){
        this.driver = driver;
        this.elemUtils = new ElemUtils(driver);
    }

    //Page Actions
    public String getPageTitle(){
        return driver.getTitle();
    }
}
