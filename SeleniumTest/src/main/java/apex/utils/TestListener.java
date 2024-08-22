package apex.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    private static Logger log = ThreadUtils.getLogger();

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.initializeReport("test-output/extent-report.html");
        log.info("Test Suite started!");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        log.info(result.getMethod().getMethodName() + " started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " passed.");
        captureScreenshot(result, "PASS");
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        log.error(result.getMethod().getMethodName() + " failed.");
        captureScreenshot(result, "FAIL");
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn(result.getMethod().getMethodName() + " skipped.");
        captureScreenshot(result, "SKIP");
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }
    
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
        log.info("Test Suite finished!");
    }

    private void captureScreenshot(ITestResult result, String status) {
        WebDriver driver = ThreadUtils.getDriverRef();
        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            String destPath = "screenshots/" + result.getMethod().getMethodName() + "_" + status + ".png";
            File destFile = new File(destPath);

            try {
                FileUtils.copyFile(srcFile, destFile);
                ExtentReportManager.getTest().addScreenCaptureFromPath(destPath);
            } catch (IOException e) {
                log.error("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
}
