package apex.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    
    private static ExtentReports extentReports;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;

    // Method to initialize and configure ExtentReports
    public static void initializeReport(String reportFilePath) {
        // Create a Spark reporter and set its location
        sparkReporter = new ExtentSparkReporter(reportFilePath);
        
        // Customize the report appearance
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Functional Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        // Create ExtentReports instance and attach the Spark reporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // You can add system or environment information to the report
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Tester", "Your Name");
    }

    // Method to create a new test in the report
    public static ExtentTest createTest(String testName) {
        test = extentReports.createTest(testName);
        return test;
    }

    // Method to get the current ExtentTest instance
    public static ExtentTest getTest() {
        return test;
    }

    // Method to finalize and flush the report
    public static void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
