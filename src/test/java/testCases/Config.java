package testCases;

import com.automation.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class Config extends TestBase {
    public Config() throws IOException {
    }

@BeforeSuite
    public void Start()
    {
// config report and add file location
extent = new ExtentReports("Reports\\myReport.html",true);

// add info to the report
extent.addSystemInfo("OS","Windows");
extent.addSystemInfo("Owner" ,"Mostafa Hagag");
extent.addSystemInfo("Lang", "java");
extent.addSystemInfo("Test Name ", "Automation practise ");
extent.addSystemInfo("FrameWork Design", "Page Object ");






    }

    @AfterSuite
    public void end(){
        extent.flush();// create the report

    }
}
