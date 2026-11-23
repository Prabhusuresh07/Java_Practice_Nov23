package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends BasePage {
	
	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	By profileicon = By.xpath("//span[@class='oxd-userdropdown-tab']");
	By logoutbtn = By.xpath("//a[normalize-space()='Logout']");
	
	//Action Methods

	public void profileclick() 
	{
		driver.findElement(profileicon).click();	
		
	}
	
	public void logoutclick() 
	{
		driver.findElement(logoutbtn).click();
	}
	

}
