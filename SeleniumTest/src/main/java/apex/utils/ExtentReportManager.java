/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extentReports;
	private static ExtentSparkReporter sparkReporter;

	public static void initializeReport(String reportFilePath) {

		sparkReporter = new ExtentSparkReporter(reportFilePath);
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setReportName("Functional Test Report");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);

		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Tester", "Seph");
	}

	public static ExtentTest createTest(String testName) {
		ExtentTest test = extentReports.createTest(testName);
		ThreadUtils.setExtentTest(test); // Store in ThreadLocal
		return test;
	}

	public static ExtentTest getTest() {
		return ThreadUtils.getExtentTest(); // Retrieve from ThreadLocal
	}

	public static void flushReport() {
		if (extentReports != null) {
			extentReports.flush();
		}
	}
}
