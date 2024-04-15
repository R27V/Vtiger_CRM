package leads;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.ObjectRepository.CreateLeadPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LeadInfoPage;
import com.ObjectRepository.LoginPage;
import com.ObjectRepository.VerifyLeadPage;

public class CreateLead_POMTest {

	public static void main(String[] args) throws Throwable  {
		
		
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
		String LASTNAME = eUtil.readDataFromExcel("Leads", 0, 1)+ jUtil.getRandomNo();
		String COMPANY = eUtil.readDataFromExcel("Leads", 1, 1);
		
		
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
				wUtil.waitForPageLoad(driver, 20);
				driver.get(URL);
				
				//Step 3: Login to the Application
				LoginPage loginPage = new LoginPage(driver);
				loginPage.loginToApp(USERNAME, PASSWORD);
								
				//Step:3 Navigate to Lead link
				HomePage homePage=new HomePage(driver);
				homePage.clickOnLeadlnk();
				
				//Step:4 Click on Create Lead look Up Image
				LeadInfoPage leadInfoPage = new LeadInfoPage(driver);
				leadInfoPage.clickOnCreateLeadLookUpImg();
				
				//Step:5 Create Lead with Mandatory fields
				CreateLeadPage createLP = new CreateLeadPage(driver);
				createLP.CreateLead(LASTNAME, COMPANY);
				
				//Step:7 Verify or Validate
				VerifyLeadPage verifyLeadPage = new VerifyLeadPage(driver);
				String contactHeader = verifyLeadPage.getLeadHeader();
				
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
				 homePage.logOutFromApp(driver);
				 
				 //Step:9 Close the browser
				  driver.quit();
	}

	

}
