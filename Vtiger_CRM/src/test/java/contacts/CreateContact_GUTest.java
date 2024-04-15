package contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.GenericUtilities.ExcelFileUtility;
import com.GenericUtilities.JavaUtility;
import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;

public class CreateContact_GUTest {

	public static void main(String[] args) throws Throwable {
	
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility   jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		WebDriver driver = null;
		
		/* Read data from property file */
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/* Read data from Excel File */
		String LASTNAME = eUtil.readDataFromExcel("Contact", 0, 1)+ jUtil.getRandomNo();
		
		
		
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
				wUtil.waitForPageLoad(driver);
				driver.get(URL);
				
				//Step 3: Login to the Application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				
				//Step:3 Navigate to Contacts link
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				
				//Step:4 Click on Create contact look Up Image
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				
				//Step:5 Create Contact with Mandatory fields
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				
				
				
				//Step:6 Save 
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Step:7 Verify or Validate
				String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
				if(contactHeader.contains(LASTNAME))
				{
					System.out.println("PASS");
					System.out.println(contactHeader);
				}
				else
				{
					System.out.println("FAIL");
				}
				
				//Step:8 logout of Application
				 WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				  wUtil.mouseHoverAction(driver, adImg);
				  Thread.sleep(1000);
				  driver.findElement(By.linkText("Sign Out")).click();
				  Thread.sleep(1000);
				  
				 //Step:9 Close the browser
				  driver.quit();

		
		
		
	}
}
