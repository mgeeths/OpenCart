package com.qa.opencart.baseTest;

import com.qa.opencart.driverFactory.DriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utils.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    //It is the starting point of execution, hence needs to instantiate driver, create class variables driver & prop
    private WebDriver driver;
    public Properties prop;
    DriverFactory df;
    public HomePage homePage;
    public LoginPage loginPage;
    public AccountPage accountPage;
    public ProductsPage productsPage;
    public ProductInfoPage productInfoPage;
    public RegisterPage registerPage;
    public TestDataProvider testDataProvider;
    public NewAddressPage newAddressPage;
    public AddressPage addressPage;

    @BeforeTest
    public void setUp() throws Exception {
        df = new DriverFactory();
        prop = df.init_prop();
        driver = df.init_driver(prop);

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        productsPage = new ProductsPage(driver);
        productInfoPage = new ProductInfoPage(driver);
        registerPage = new RegisterPage(driver);
        testDataProvider = new TestDataProvider();
        newAddressPage = new NewAddressPage(driver);
        addressPage = new AddressPage(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
