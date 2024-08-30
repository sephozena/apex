/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		/*
		 * ExtentReportManager.initializeReport("test-output/extent-report.html"); is
		 * handled in our BaseClass for cleaner code
		 */
	}

	@Override
	public void onTestStart(ITestResult result) {
//		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        String browser = ThreadUtils.getBrowserName(); // Get browser from ThreadUtils
		if (browser == null) {
			browser = getBrowserFromResult(result); // Fallback to the original method
		}
		ExtentTest test = ExtentReportManager.createTest(result.getMethod().getMethodName() + " [" + browser + "] ");
		ThreadUtils.setExtentTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logTestResult(result, Status.PASS, "Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logTestResult(result, Status.FAIL, "Failure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logTestResult(result, Status.SKIP, "Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		/*
		 * ExtentReportManager.flushReport(); is handled in our BaseClass for cleaner
		 * code
		 */
	}

	private String extractCustomErrorMessage(String errorMessage) {
		// Customize this method to extract and return
		// the specific part of the error message
		// Example implementation: Extract the first line of the error message
		if (errorMessage != null) {
			String[] lines = errorMessage.split("\n");
			if (lines.length > 0) {
				return lines[0];
			}
		}
		return errorMessage;
	}

	private void logTestResult(ITestResult result, Status status, String resultType) {
		ExtentTest test = ThreadUtils.getExtentTest();
		String browser = ThreadUtils.getBrowserName(); 
		
		String screenshotPath = ScreenshotUtils.captureAndAttachScreenshot(ThreadUtils.getDriverRef(),
				result.getMethod().getMethodName() + "_" + resultType);
		String customErrorMessage = result.getThrowable() != null
				? extractCustomErrorMessage(result.getThrowable().getMessage())
				: "";

		try {
			test.log(status, "Test " + resultType + ": [" + browser + "] " + customErrorMessage,
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (Exception e) {
			e.printStackTrace();
			ThreadUtils.getLogger().error("Failed to log test result: " + e.getMessage());
		}
	}

	private String getBrowserFromResult(ITestResult result) {
//		return result.getTestContext().getCurrentXmlTest().getParameter("browser");
        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        return browser != null ? browser : "Unknown Browser";

	}
}
