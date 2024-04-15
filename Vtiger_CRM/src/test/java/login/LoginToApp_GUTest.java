package login;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.GenericUtilities.DatabaseUtility;
import com.GenericUtilities.ExcelFileUtility;
import com.GenericUtilities.JavaUtility;
import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;

public class LoginToApp_GUTest {

	public static void main(String[] args) throws Throwable {
		
		
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility    wUtil = new WebDriverUtility();
		
		WebDriver driver = null;
		
		//Step 1: Read all data required
		
		/* Read data from property file */
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//Step 2: Launch the browser - //driver exhibit that is Run-Time Polymorphism
				if(BROWSER.equalsIgnoreCase("chrome"))
				{
					driver = new ChromeDriver();
					System.out.println(BROWSER+"....browser launched");
				}
				else if(BROWSER.equalsIgnoreCase("firefox"))
				{
					driver = new FirefoxDriver();
					System.out.println(BROWSER+"....browser launched");
				}
				else if(BROWSER.equalsIgnoreCase("edge"))
				{
					driver = new EdgeDriver();
					System.out.println(BROWSER+"....browser launched");
				}
				else
				{
					System.out.println("Invalid browser name");
				}
				
				wUtil.maximizeWindow(driver);
				wUtil.waitForPageLoad(driver, 10);
				driver.get(URL);
				
				//Step 3: Login to the Application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
		
	}
}
