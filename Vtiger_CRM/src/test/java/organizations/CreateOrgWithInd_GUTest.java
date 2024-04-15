package organizations;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class CreateOrgWithInd_GUTest {

	public static void main(String[] args) throws Throwable {
		
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility    eUtil = new ExcelFileUtility();
		JavaUtility         jUtil = new JavaUtility();
		WebDriverUtility    wUtil = new WebDriverUtility();
		DatabaseUtility     dUtil = new DatabaseUtility();
		
		WebDriver driver = null;
		
		//Step 1: Read all data required
		
		/* Read data from property file */
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/* Read data from Excel File */
		String ORGNAME = eUtil.readDataFromExcel("Organization", 0, 1) + jUtil.getRandomNo();
		String INDUSTRY = eUtil.readDataFromExcel("Organization", 1, 1);
		
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
		
		//Step 4: Navigate to the Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5: Click on create organization Look-up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 6: Create organization with mandatory fields
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);
		
		//Step 7: Select 'chemical' in industry dropDown
		WebElement ind = driver.findElement(By.name("industry"));
		wUtil.handleDropDown(driver, ind, INDUSTRY);
//		Select sel = new Select(ind);
//		sel.selectByVisibleText(INDUSTRY);
//		
		//Step 8: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		System.out.println("Organization name is saved.");
		
		Thread.sleep(1000);
		//Step 9: Validate
	    String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if(orgHeader.contains(ORGNAME))
	    {
	    	System.out.println("PASS");
	    	System.out.println(orgHeader);
	    }
	    else
	    {
	    	System.out.println("FAIL");
	    }
		
	    Thread.sleep(1000);
		//Step 10: Logout of the application
	    WebElement adminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions act = new Actions(driver);
	    act.moveToElement(adminImg).perform();
	    Thread.sleep(1000);
	    driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 11: Close the browser
		driver.close();
		
	}
}
