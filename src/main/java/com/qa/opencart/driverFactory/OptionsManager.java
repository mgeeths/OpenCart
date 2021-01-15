package com.qa.opencart.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Properties;

import static java.lang.Boolean.parseBoolean;

public class
OptionsManager {
    //Class Var
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;


    //Constructor
    public OptionsManager(Properties prop) {
        this.prop = prop;

    }

    public ChromeOptions chromeOptionsManager() {
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");

        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("--incognito");
        return co;
    }

    public FirefoxOptions firefoxOptionsManager() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");
        return fo;
    }

}
