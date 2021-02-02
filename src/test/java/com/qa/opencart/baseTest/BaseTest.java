package com.qa.opencart.baseTest;

import com.qa.opencart.driverFactory.DriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utils.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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

    /**
     * @param browserName
     * @param browserVersion
     * To take the value passed thro @paramaeters, we should first create a holind paramerter in the method
     * So create these holding params in the method name, as String browserName and String browserVersion
     * The holding param name can be something other than the var name given in the @parameter annotation
     * But those params come into play only when selenium grid remote execution is done.
     * To make the code work for local execution as well, we have to access the browser value mentioned in
     * config.prop file, so inside the setup method, access this value and store it in a browser var
     * First check if this value from testNG_FF_83.xml file(i.e the value of the holding param is not null,
     * (i.e some browser value is passed at the testNG level) then pass this value (i.e override the
     * browser value given at the config level) by assigning browser = browserName
     * @throws Exception
     */


    @BeforeTest
    @Parameters({"browser", "version"})
    public void setUp(String browserName, String browserVersion) throws Exception {
        df = new DriverFactory();
        prop = df.init_prop();
        String browser = prop.getProperty("browser");
        if(browserName != null){
            browser = browserName;
        }

        driver = df.init_driver(browser, browserVersion);

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
