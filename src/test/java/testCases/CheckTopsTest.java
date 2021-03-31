package testCases;

import com.automation.pages.CheckTopsPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.automation.testBase.TestBase;
import com.automation.util.MyScreenRecorder;
import com.automation.util.TestUtils;

import java.io.IOException;
import java.lang.reflect.Method;

public class CheckTopsTest extends TestBase{
    LoginPage loginPage;
    HomePage homePage;
    CheckTopsPage checkTopsPage;
    public CheckTopsTest() throws IOException {
        super();
    }

    @Parameters("browser")
    @BeforeMethod
    public void checkTopsPrecondtions(String browser , Method method ) throws Exception {


        logger= extent.startTest(method.getName());
        initializtions(browser);

        loginPage= new LoginPage();
        checkTopsPage= new CheckTopsPage();


    }

    @Test(priority = 5)
    public void checkTops(Method method ) throws Exception {
        SoftAssert softAssert= new SoftAssert();
        MyScreenRecorder.startRecording(method.getName());

        homePage=   loginPage.performLogin();
        checkTopsPage= homePage.clickONTops();

        //Check Tops

        WebElement proveTops= driver.findElement(By.xpath("//p[@class='subcategory-heading']"));
      boolean prove=  checkTopsPage.isTopsDisplayed();
        softAssert.assertTrue(prove );
        TestUtils.takeSnapShot(method.getName());

    }

    @AfterMethod
    public void tearDown(ITestResult result , Method method) throws Exception {
        MyScreenRecorder.stopRecording();
        TestUtils.takeSnapShot(method.getName());
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS,"Test Passed");
            logger.log(LogStatus.PASS,"<a href='../snapShots/" + TestUtils.snapShotName+ ".png" + "'>"+"<img src='../snapShots/" + TestUtils.snapShotName+ ".png'   height='100'>"+"</a>");
            logger.log(LogStatus.PASS,"<a href='../recordings/" + MyScreenRecorder.name + "'><span class='lable info'>Download the video</span></a>");

        }else if (result.getStatus()== ITestResult.FAILURE){
            logger.log(LogStatus.FAIL,"Test Failed");

        }else
        {
            logger.log(LogStatus.SKIP,"Test Skipped");
        }
        driver.quit();
    }

}
