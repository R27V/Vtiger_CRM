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
import org.openqa.selenium.support.ui.Select;


public class CreateOrganizationWithIndustryTest {

	public static void main(String[] args) throws Throwable  {
		
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
		Sheet sh = wb.getSheet("Organization");
		String ORGNAME = sh.getRow(0).getCell(1).getStringCellValue()+random;
		String INDUSTRY = sh.getRow(1).getCell(1).getStringCellValue();
		
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
		
		//Step 4: Navigate to the Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5: Click on create organization Look-up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 6: Create organization with mandatory fields
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);
		
		//Step 7: Select 'chemical' in industry dropDown
		WebElement ind = driver.findElement(By.name("industry"));
		Select sel = new Select(ind);
		sel.selectByVisibleText(INDUSTRY);
		
		//Step 8: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
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

