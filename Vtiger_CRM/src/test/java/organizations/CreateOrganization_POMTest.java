package organizations;

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
import com.ObjectRepository.CreateOrganizationPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LoginPage;
import com.ObjectRepository.OrganizationInfoPage;
import com.ObjectRepository.VerifyOrganizationPage;


public class CreateOrganization_POMTest {

	public static void main(String[] args) throws Throwable {
		
	
	        PropertyFileUtility pUtil = new PropertyFileUtility();
			ExcelFileUtility eUtil = new ExcelFileUtility();
			JavaUtility jUtil = new JavaUtility();
			WebDriverUtility wUtil = new WebDriverUtility();
			DatabaseUtility dUtil = new DatabaseUtility();
			
			
			WebDriver driver = null;
			
			//Step 1: Read all data required
			
			/* Read data from property file */
			String BROWSER = pUtil.readDataFromPropertyFile("browser");
			String URL = pUtil.readDataFromPropertyFile("url");
			String USERNAME = pUtil.readDataFromPropertyFile("username");
			String PASSWORD = pUtil.readDataFromPropertyFile("password");
			
			/* Read data from Excel File */
			String ORGNAME = eUtil.readDataFromExcel("Organization", 0, 1) + jUtil.getRandomNo();
		
			
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
			
			//Step 4: Navigate to the Organization link
			HomePage homePage = new HomePage(driver);
			homePage.clickOnOrgLnk();
			
			//Step 5: Click on create organization Look-up image
			OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
			organizationInfoPage.clickOnOrgLookUpImg();
			
			//Step 6: Create organization with mandatory fields
			CreateOrganizationPage createOrganizationPage = new CreateOrganizationPage(driver);
			createOrganizationPage.CreateOrganization(ORGNAME);
			
			Thread.sleep(1000);
			//Step 8: Validate
			VerifyOrganizationPage verifyOrganizationPage = new VerifyOrganizationPage(driver);
		    String orgHeader = verifyOrganizationPage.getHeadertext();
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
			//Step 9: Logout of the application
			homePage.logOutFromApp(driver);
			
			
			//Step 10: Close the browser
			driver.close();
			
	
	}

}
