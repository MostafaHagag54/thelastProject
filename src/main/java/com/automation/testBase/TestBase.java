package com.automation.testBase;

import com.automation.util.WebListner;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop; // property File
    public static WebListner webListner;
    public static EventFiringWebDriver e_driver; // Debuger

    public static ExtentReports extent; // Extent Report Obj
    public static ExtentTest logger; // extent report objet

    public TestBase() throws IOException {
        prop= new Properties();
        FileInputStream fis= new FileInputStream("src/main/java/com/automation/config/config.properties");
        prop.load(fis);
    }

    public void initializtions(String browser)
    {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().clearDriverCache();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().clearDriverCache();
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }
        webListner= new WebListner();
        e_driver=new EventFiringWebDriver(driver);
        e_driver.register(webListner);
        driver=e_driver;





        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(prop.getProperty("URL"));
    }

}
