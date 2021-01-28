package com.qa.opencart.driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    //class variables: driver and prop, No Need for any constructor
    //private WebDriver driver;
    private Properties prop;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverFactory.class));
    OptionsManager optionsManager;

    //Class Methods
    public Properties init_prop() {
        prop = new Properties();
        FileInputStream ip = null;
        String env = System.getProperty("env");
        System.out.println("Test ran on environment : " + env);
        LOGGER.info("Test ran on environment : " + env);
        if(env == null){
            try {
                ip = new FileInputStream(".\\src\\resources\\config\\config.qa.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                switch (env) {
                    case "qa":
                        ip = new FileInputStream(".\\src\\resources\\config\\config.qa.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream(".\\src\\resources\\config\\config.stage.properties");
                        break;
                    case "preProd":
                        ip = new FileInputStream(".\\src\\resources\\config\\config.preProd.properties");
                        break;
                    default:
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LOGGER.info("File not found at the given location");
            }
        }
        try {
            prop.load(ip);
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public WebDriver init_driver(String browserName, String browserVersion) throws Exception {
        //String browserName = prop.getProperty("browser");
        System.out.println("Test will be executed on " + browserName + " browser.");
        highlight = prop.getProperty("highlight").trim();
        optionsManager = new OptionsManager(prop);
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver(optionsManager.chromeOptionsManager());
            if(Boolean.parseBoolean(prop.getProperty("remote"))){
                init_remoteDriver(browserName, browserVersion);
            }else {
                tlDriver.set(new ChromeDriver(optionsManager.chromeOptionsManager()));
            }
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            //driver = new EdgeDriver();
            tlDriver.set(new EdgeDriver());
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver(optionsManager.firefoxOptionsManager());
            if(Boolean.parseBoolean(prop.getProperty("remote"))){
                init_remoteDriver(browserName, browserVersion);
            }else {
                tlDriver.set(new FirefoxDriver(optionsManager.firefoxOptionsManager()));
            }
        }
        else if(browserName.equalsIgnoreCase("safari")){
            //driver = new SafariDriver();
            tlDriver.set(new SafariDriver());
        }
        else{
            throw new Exception ("BrowserException ---- Enter valid browser name");
        }
        //Use the getDriver method to the tlDriver's copy
    return getDriver();
    }

    /**
    creates the instance of the remote webdriver. With the use of Desired Capabilities, we can set the
    browser options (like headless/incognito/ any other options needed). Then this cap has to be passed
     while creating the new remote webDriver (i.e.calling the constructor of remote webdriver that takes
     remote address and capabilities. Remote address should be in the form of URL not a string, so we have
     to create the new object of URL class and pass the huburl as the param

     Instead of thread local driver, we can also use :
     RemoteWebDriver driver = new RemoteWebDriver
     */
    private void init_remoteDriver(String browser, String browserVersion){
        System.out.println("Running tests on remote grid server : " + browser);
        if (browser.equals("chrome")) {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability("browserName", "chrome");
            cap.setCapability("browserVersion", browserVersion);
            cap.setCapability("enableVNC",true);
            cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.chromeOptionsManager());
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if (browser.equals("firefox")){
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setCapability("browserName","firefox");
            cap.setCapability("browserVersion", browserVersion);
            cap.setCapability("enableVNC", true);
            cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.firefoxOptionsManager() );

            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public Properties getProp(){
        return prop;
    }

    //This wrapper method returns the thread local copy of a WebDDriver
    //ie. It converts the tlDriver obj to a WebDriver obj, as we declared the thread local obj with WebDriver generics
    //It is made static, so we can call this inside init_driver to return the tlDriver as a WebDriver, so finally
    // the init_driver returns only a WebDriver obj which will be used everywhere

    public static synchronized WebDriver getDriver(){
        return tlDriver.get();
    }

    /**
     * Typecast the driver to TakeScreenshot , so that the getScreenshot method can be used
     * create the path to dest file in the current project dir with the current time
     * Use fileUtils to copy the file from source to dest file
     * @return the path where the screenshot is saved
     */
    public String getScreenshot(){
        File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png" ;
        File destinaton = new File(path);

        try {
            FileUtils.copyFile(src, destinaton);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }


}
