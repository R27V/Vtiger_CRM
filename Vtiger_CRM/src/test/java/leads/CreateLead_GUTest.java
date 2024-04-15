package leads;

import java.io.FileInputStream;
import java.time.Duration;

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

public class CreateLead_GUTest {

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
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\VTIGER_Testcases.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet("Leads");
		String LASTNAME = sh.getRow(0).getCell(1).getStringCellValue()+ jUtil.getRandomNo();
		String COMPANY = sh.getRow(1).getCell(1).getStringCellValue();
		
		
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
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);
				
				//Step 3: Login to the Application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				
				//Step:3 Navigate to Lead link
				driver.findElement(By.xpath("//a[text()='Leads']")).click();
				
				//Step:4 Click on Create Lead look Up Image
				driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
				
				
				//Step:5 Create Lead with Mandatory fields
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				driver.findElement(By.name("company")).sendKeys(COMPANY);
					
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
				  Actions act = new Actions(driver);
				  act.moveToElement(adImg).perform();
				  Thread.sleep(1000);
				  driver.findElement(By.linkText("Sign Out")).click();
				  Thread.sleep(1000);
				  
				 //Step:9 Close the browser
				  driver.quit();
        }
}
