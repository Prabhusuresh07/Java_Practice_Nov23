package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginpageHRM extends BasePage
{
	
	public loginpageHRM(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	
	By UsernameTxt = By.xpath("//input[@placeholder='Username']");
	By PasswordTxt = By.xpath("//input[@placeholder='Password']");
	By Btn = By.xpath("//button[normalize-space()='Login']");
	
	//Action Methods
	
	public void setusername(String name) 
	{
		driver.findElement(UsernameTxt).sendKeys(name);
	}
	
	public void setpassword(String password) 
	{
		driver.findElement(PasswordTxt).sendKeys(password);
	}
	

	public void loginclick() 
	{
		driver.findElement(Btn).click();
	}
}
