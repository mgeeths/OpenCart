package com.qa.opencart.driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    //class variables: driver and prop, No Need for any constructor
    private WebDriver driver;
    private Properties prop;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    OptionsManager optionsManager;

    //Class Methods
    public Properties init_prop(){
        prop = new Properties();
        try {
            FileInputStream ip =
                    new FileInputStream("C:\\Users\\browse\\Automation\\AutomateOpencart\\src\\resources\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public WebDriver init_driver(Properties prop) throws Exception {
        String browserName = prop.getProperty("browserName");
        highlight = prop.getProperty("highlight").trim();

        optionsManager = new OptionsManager(prop);
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver(optionsManager.chromeOptionsManager());
            tlDriver.set(new ChromeDriver(optionsManager.chromeOptionsManager()));
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            //driver = new EdgeDriver();
            tlDriver.set(new EdgeDriver());
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver(optionsManager.firefoxOptionsManager());
            tlDriver.set(new FirefoxDriver(optionsManager.firefoxOptionsManager()));
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
