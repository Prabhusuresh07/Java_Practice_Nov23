package utilities;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testCases.BaseTest;

public class Listeners implements ITestListener {
	
	
	// Executes before the TestNG test execution starts
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution Started: "
                + context.getName());
    }

    // Executes when a test case starts
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TEST STARTED: " + result.getName());
    }

    // Executes when a test case passes
    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("TEST PASSED: " + result.getName());

        
    }

    // Executes when a test case fails
    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("TEST FAILED: " + result.getName());

        System.out.println("Failure Reason: "
                + result.getThrowable().getMessage());

        WebDriver driver = BaseTest.driver;

        if (driver != null) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

            File destination = new File(
                "./screenshots/Failed/" + result.getName() + ".png"
            );

            try {
                FileUtils.copyFile(source, destination);
                System.out.println("Failed Screenshot Captured");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Executes when a test case is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("TEST SKIPPED: " + result.getName());
    }

    

    // Executes after the TestNG test execution finishes
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Execution Finished: "
                + context.getName());
    }
}
