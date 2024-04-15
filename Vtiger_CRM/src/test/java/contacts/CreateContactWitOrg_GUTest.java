package contacts;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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

import com.GenericUtilities.DatabaseUtility;
import com.GenericUtilities.ExcelFileUtility;
import com.GenericUtilities.JavaUtility;
import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;

public class CreateContactWitOrg_GUTest {

	public static void main(String[] args) throws Throwable {
		
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		DatabaseUtility dUtil = new DatabaseUtility();
		
		WebDriver driver = null;
		
		/* Read data from property file */
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/* Read data from Excel File */
		String LASTNAME = eUtil.readDataFromExcel("Contact", 0, 1)+ jUtil.getRandomNo();
		String ORGNAME = eUtil.readDataFromExcel("Contact", 2, 1)+jUtil.getRandomNo();
		
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
		
		//Step 6: Navigate to the Organization link
		driver.findElement(By.linkText("Organizations")).click();
				
		//Step 7: Click on create Organization Look-Up Image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
		//Step 8: Create Organization with mandatory field
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
				
		//Step 9: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Step 10: Validate for Organization
		 String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		   if(OrgHeader.contains(ORGNAME))
		        {
		        	System.out.println("Organization created");
		        	System.out.println(OrgHeader);
		        }
		        else
		        {
		        	System.out.println("FAIL");
		        }
				
				/* Create contact using Organization*/
		        //Step 11: Click on Contacts Link
		        driver.findElement(By.linkText("Contacts")).click();
		        
		        //Step 12: Navigate to Create Contact Look-up Image
		        driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		        
		        //Step 13: Create contact with mandatory field
		        driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		        
		        //Step 14: Click on create organization look-up image
		        driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		        
		        //Step 15: Switch control to child window
		  
		        wUtil.switchToWindow(driver, "Accounts");
		       
		    		
		        
		        //Step 16: Search for Organization
		        driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		        
		        driver.findElement(By.name("search")).click();
		        
		       // driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click(); //dynamic Xpath, run time data  & (.) represent the text()
		        //here xpath itself generated during run time     
		        // OR
		        driver.findElement(By.linkText(ORGNAME)).click(); 
		        
		        //Step 17: Switch the control back to the parent window
		        wUtil.switchToWindow(driver, "Contacts");
	   
		        
		       //Step 18: Save
		        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		        
		       //Step 19: Validate for Contact 
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
		        
		       //Step 20: Logout from application
		        WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				 wUtil.mouseHoverAction(driver, adImg);
				  Thread.sleep(1000);
				  driver.findElement(By.linkText("Sign Out")).click();
				  Thread.sleep(1000);
				  
		        
		        //Step 21: Close the browser
		        driver.quit();
		
	}
}
