package testCases;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

import com.automation.util.MyScreenRecorder;
import com.automation.util.TestUtils;
public class LoginPageTest  extends TestBase {

LoginPage loginPage;
HomePage homePage;
LoginPage samePage;


    //expected result String


    public LoginPageTest() throws IOException {
        super();
    }

    @Parameters("browser")
    @BeforeMethod
    public void loginPrecondtions(String browser ,Method method) throws IOException {
        logger= extent.startTest(method.getName());
        initializtions(browser);
        loginPage= new LoginPage();


    }

    @Test(priority = 2)
    public  void login(Method method) throws Exception {
        SoftAssert softAssert= new SoftAssert();
        // start video Recorder
        MyScreenRecorder.startRecording(method.getName());



        homePage=   loginPage.performLogin();

        String loginURL= loginPage.getUrl();
        String expectedURL= prop.getProperty("expectedURL");

        TestUtils.takeSnapShot(method.getName());// method to screen shot
        softAssert.assertEquals(loginURL,expectedURL);
        TestUtils.takeSnapShot(method.getName());
    }

    @Test(priority = 3,dataProvider = "data")
    public void loginWithWrongData(String username,String password,Method method) throws Exception {
        SoftAssert softAssert= new SoftAssert();


        MyScreenRecorder.startRecording(method.getName());
        samePage = loginPage.loginWithinvalidData(username,password);


        String prove= loginPage.getUrl();
        String actualURL=prop.getProperty("actualURL");
        softAssert.assertEquals(prove,actualURL);
         WebElement element= loginPage.getLoginButton();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    @DataProvider
    public Object[][] data() throws IOException {

        Object dataFromExcel[][]=TestUtils.getDataFromExcelSheet("Sheet1");
        return dataFromExcel;
    }



    @AfterMethod
    public void tearDown(ITestResult result,Method method) throws Exception {
        MyScreenRecorder.stopRecording();

        TestUtils.takeSnapShot(method.getName());// method to take screenshot
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
        driver.close();

    }
}
