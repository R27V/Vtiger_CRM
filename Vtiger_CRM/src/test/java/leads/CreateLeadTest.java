package leads;

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

public class CreateLeadTest {

	public static void main(String[] args) throws Throwable {
		
		
		Random r = new Random();
		int random = r.nextInt(1000);
		
		WebDriver driver = null;
		
		//Step 1: Read all data required
		
		/* Read data from property file */
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		/* Read data from Excel File */
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\VTIGER_Testcases.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet("Leads");
		String LASTNAME = sh.getRow(0).getCell(1).getStringCellValue()+ random;
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
