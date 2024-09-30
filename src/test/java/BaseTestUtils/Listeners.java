package BaseTestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.henrynFrameworks.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends AppiumUtils implements ITestListener
{
    ExtentTest test;
    ExtentReports extent = ExtentReportNG.getReporterObject();
    AppiumDriver driver;
// add implementation for ITestListener methods

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        //surround with try catch
        try {
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {

            test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(), driver), (result.getMethod().getMethodName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
     //  System.out.println("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //System.out.println("Test failed but within success percentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
       // System.out.println("Test failed with timeout");
    }

    @Override
    public void onStart(ITestContext context) {
        //System.out.println("Test started");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }



}
