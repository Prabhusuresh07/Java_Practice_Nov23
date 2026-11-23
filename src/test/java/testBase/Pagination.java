package testBase;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Pagination {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo3x.opencartreports.com/admin/index.php?route=common/login");
		driver.manage().window().maximize();
		// login page operations
		WebElement username = driver.findElement(By.name("username"));
		username.clear();
		username.sendKeys("demo");
		WebElement password = driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys("demo");
		WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.click();
		
		// dashboard page operations
		
		WebElement sales = driver.findElement(By.id("menu-sale"));
		sales.click();
		WebElement orders = driver.findElement(By.xpath("//a[text()='Orders']"));
		orders.click();
		
		//Table Oprations
		
		
		String page=driver.findElement(By.xpath("//div[@class='col-sm-6 text-right']")).getText();
		int pagenum = Integer.parseInt(page.substring(page.indexOf("(")+1, page.indexOf("Pages")-1));
		int shippeditemscount=0;
		Set<String> CusName = new HashSet<>();
		
		for(int p=1;p<=pagenum;p++)
		{
			if(p>1)
			{
				driver.findElement(By.xpath("//ul[@class='pagination']//*[text()="+p+"]")).click();
				
			}
			
			List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr"));
			
			for(int i=1;i<=rows.size();i++)
			{
				String custname= driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+i+"]//td[3]")).getText();
				//System.out.println(custname);
				String status= driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+i+"]//td[4]")).getText();
				
				if(status.equalsIgnoreCase("shipped")) 
				{
					
					//Set<String> CusName = new HashSet<>();
					CusName.add(custname);
					shippeditemscount++;
				}
			}
			
		}
		
		// Print unique customer names only once
		for (String n : CusName) 
		{
		    System.out.println(n);
		}

		System.out.println("Unique shipped customer count: " + CusName.size());
		System.out.println("Total shipped records: " + shippeditemscount);

		driver.quit();

	}

}
