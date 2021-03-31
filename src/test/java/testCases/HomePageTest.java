package testCases;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.automation.util.MyScreenRecorder;
import com.automation.util.TestUtils;
import java.io.IOException;
import java.lang.reflect.Method;

public class HomePageTest extends TestBase {
LoginPage loginPage;
HomePage homePage;

    public HomePageTest() throws IOException {
    }

    @Parameters("browser")
    @BeforeMethod
    public void HomePagePrecondtions(String browser , Method method ) throws IOException {

        logger= extent.startTest(method.getName());
        initializtions(browser);
        loginPage=new LoginPage();



    }

    @Test(priority = 4)
    public void confirmHoMePage(Method method) throws Exception {
        MyScreenRecorder.startRecording(method.getName());

        SoftAssert softAssert= new SoftAssert();
        homePage=  loginPage.performLogin();
        boolean userIsDisplayed= homePage.IsUserDisplayed();
        softAssert.assertTrue(userIsDisplayed);
        TestUtils.takeSnapShot(method.getName());


    }


    @AfterMethod
    public void tearDown(ITestResult result,Method method) throws Exception {
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
