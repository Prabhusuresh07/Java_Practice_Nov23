
package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testCases.BaseTest;

public class Listeners implements ITestListener {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    // Initialize Extent Report before test execution
    static {

        // Generate dynamic timestamp
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        );

        // Create report directory
        File reportFolder = new File("./reports");
        reportFolder.mkdirs();

        // Generate dynamic report filename
        String reportPath =
                "./reports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Project Name", "Selenium Automation");
        extent.setSystemInfo("Testing Framework", "TestNG");
        extent.setSystemInfo("Language", "Java");

        System.out.println("Extent Report Path: " + reportPath);
    }

    // Executes before TestNG test execution starts
    @Override
    public void onStart(ITestContext context) {

        System.out.println("Test Execution Started: "
                + context.getName());
    }

    // Executes when a test case starts
    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("TEST STARTED: " + result.getName());

        ExtentTest test =
                extent.createTest(result.getName());

        extentTest.set(test);

        extentTest.get().log(Status.INFO,
                "Test case execution started");
    }

    // Executes when a test case passes
    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("TEST PASSED: " + result.getName());

        extentTest.get().log(Status.PASS,
                "Test case passed successfully");
    }

    // Executes when a test case fails
    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("TEST FAILED: " + result.getName());

        System.out.println("Failure Reason: "
                + result.getThrowable().getMessage());

        extentTest.get().log(Status.FAIL,
                "Test case failed");

        extentTest.get().fail(result.getThrowable());

        WebDriver driver = BaseTest.driver;

        if (driver != null) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

            File screenshotFolder =
                    new File("./screenshots/Failed");

            screenshotFolder.mkdirs();

            File destination = new File(
                    screenshotFolder,
                    result.getName() + "_" +
                    System.currentTimeMillis() + ".png"
            );

            try {

                FileUtils.copyFile(source, destination);

                System.out.println("Failed Screenshot Captured");

                // Attach screenshot to Extent Report
                extentTest.get().addScreenCaptureFromPath(
                        destination.getAbsolutePath());

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Executes when a test case is skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("TEST SKIPPED: " + result.getName());

        extentTest.get().log(Status.SKIP,
                "Test case skipped");

        if (result.getThrowable() != null) {

            extentTest.get().skip(result.getThrowable());
        }
    }

    // Executes after TestNG test execution finishes
    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test Execution Finished: "
                + context.getName());

        // Generate and save HTML report
        extent.flush();

        // Remove ThreadLocal value
        extentTest.remove();

        System.out.println("Extent Report Generated Successfully");
    }
}
