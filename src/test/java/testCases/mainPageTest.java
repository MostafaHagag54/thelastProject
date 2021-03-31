package testCases;

import com.automation.pages.MainPageObj;
import com.automation.testBase.TestBase;
import com.automation.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.automation.util.MyScreenRecorder;

import java.io.IOException;
import java.lang.reflect.Method;

public class mainPageTest extends TestBase {

MainPageObj mainPageObj;
    public mainPageTest() throws IOException {
    }
    @Parameters("browser")
    @BeforeMethod
    public void mainPagePrecondtions(String browser ,Method method) throws Exception {

        logger= extent.startTest(method.getName());

        initializtions(browser);
        mainPageObj= new MainPageObj();


    }

    @Parameters("browser")
    @Test(priority = 1)
    public void confirmMainPage(String browser , Method method) throws Exception {
        MyScreenRecorder.startRecording(method.getName());
        SoftAssert softAssert= new SoftAssert();


        String title=mainPageObj.getTitle();
        String expectedTitle=prop.getProperty("expectedTitle");
        softAssert.assertEquals(title,expectedTitle);

    }

    @AfterMethod
    public void tesDown(ITestResult result,Method method) throws Exception {

        TestUtils.takeSnapShot(method.getName());
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS,"Test Passed");
        //    System.out.println(TestUtils.snapShotName);
            //      System.out.println(result.getName());
            //            System.out.println(TestUtils.snapShotName);
            logger.log(LogStatus.PASS,"<a href='../snapShots/" + TestUtils.snapShotName+ ".png" + "'>"+"<img src='../snapShots/" + TestUtils.snapShotName+ ".png'   height='100'>"+"</a>");
          logger.log(LogStatus.PASS,"<a href='../recordings/" + MyScreenRecorder.name + "'><span class='lable info'>Download the video</span></a>");

            System.out.println();

        }else if (result.getStatus()== ITestResult.FAILURE){
            logger.log(LogStatus.FAIL,result.getThrowable());
        }else
        {
            logger.log(LogStatus.SKIP,"Test Skipped");
        }
        MyScreenRecorder.stopRecording();
        driver.quit();
    }
}
