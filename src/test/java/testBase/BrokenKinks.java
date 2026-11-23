package testBase;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenKinks {
public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
		List <WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total number of links present inside the webpage: " + links.size());
		int noOfBrokenLinks=0;
		for(WebElement link : links) 
		{ 
			String linkAttVal = link.getAttribute("href");
			if(linkAttVal==null || linkAttVal.isEmpty()) 
			{
			 System.out.println("Href Attribute value is Null or Empty. So unable check whether the given link is broken or not");
			 continue;
			}
			
			
			try {
				URL urlconn = new URL(linkAttVal);
				HttpURLConnection conn = (HttpURLConnection) urlconn.openConnection();
				conn.connect();
				if(conn.getResponseCode()>=400) {
					System.out.println(linkAttVal+" It is a broken link");	
					noOfBrokenLinks++;
				}
				else
				{
					System.out.println(linkAttVal+" It is not a broken link");
				}
			}
			catch(Exception e) {}
		}
		System.out.println("Total number of Broken Links: "+ noOfBrokenLinks);
			
		}
	

}
