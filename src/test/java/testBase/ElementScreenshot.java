package testBase;

import java.util.*;
import java.io.File;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementScreenshot {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label ='India']")));
		
		File Source = img.getScreenshotAs(OutputType.FILE);
		
		String fileName = "IndiaFlag_"+ System.currentTimeMillis() + ".png";

        File target = new File(System.getProperty("user.dir")+File.separator + "screenshots"
                + File.separator + fileName);
		
		Source.renameTo(target);
		
		driver.quit();

	}

}
