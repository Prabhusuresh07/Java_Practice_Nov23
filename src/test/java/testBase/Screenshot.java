package testBase;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Screenshot {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File ss = ts.getScreenshotAs(OutputType.FILE);
		
		File targetfile = new File(System.getProperty("user.dir")+"\\screenshots\\Amozon1Page.png");
		
		ss.renameTo(targetfile);
		
		driver.quit();
		
		

	}

}
