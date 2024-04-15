package contacts;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
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

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws Throwable {
				
		
		Random r = new Random();
		int random = r.nextInt(1000);
		
		WebDriver driver = null;
		
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
		Sheet sh = wb.getSheet("Contact");
		String LASTNAME = sh.getRow(0).getCell(1).getStringCellValue()+random;
		String ORGNAME = sh.getRow(2).getCell(1).getStringCellValue()+random;
		
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
		  
		       
		    		//Step 1: Capture all the window IDs
		    		 Set<String> allWinIDs = driver.getWindowHandles();
		    		 
		    		//Step 2: Navigate through each window
		    		 for(String id:allWinIDs)
		    		 {
		    			//Step 3: Switch to each window and capture the title
		    			 String actTitle = driver.switchTo().window(id).getTitle();
		    			
		    			//Step 4: Compare title with required
		    			 if(actTitle.contains("Accounts"))
		    			 {
		    				 break;
		    			 }
		    		 }
		    		
		        
		        //Step 16: Search for Organization
		        driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		        
		        driver.findElement(By.name("search")).click();
		        
		       // driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click(); //dynamic Xpath, run time data  & (.) represent the text()
		        //here xpath itself generated during run time     
		        // OR
		        driver.findElement(By.linkText(ORGNAME)).click(); 
		        
		        //Step 17: Switch the control back to the parent window
		        
	    		//Step 1: Capture all the window IDs
	    		 Set<String> allWinID = driver.getWindowHandles();
	    		 
	    		//Step 2: Navigate through each window
	    		 for(String id:allWinID)
	    		 {
	    			//Step 3: Switch to each window and capture the title
	    			 String actTitle = driver.switchTo().window(id).getTitle();
	    			
	    			//Step 4: Compare title with required
	    			 if(actTitle.contains("Contacts"))
	    			 {
	    				 break;
	    			 }
	    		 }
		        
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
				  Actions act = new Actions(driver);
				  act.moveToElement(adImg).perform();
				  Thread.sleep(1000);
				  driver.findElement(By.linkText("Sign Out")).click();
				  Thread.sleep(1000);
				  
		        
		        //Step 21: Close the browser
		        driver.quit();

	}
}
