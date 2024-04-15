package contacts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.GenericUtilities.ExcelFileUtility;
import com.GenericUtilities.JavaUtility;
import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;
import com.ObjectRepository.ContactInfoPage;
import com.ObjectRepository.CreateContactPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LoginPage;
import com.ObjectRepository.VerifyContactPage;

public class CreateContact_POMTest {

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
				
				//Step 3: Login the Application
				LoginPage loginPage = new LoginPage(driver);
				loginPage.loginToApp(USERNAME, PASSWORD);
				
				
				//Step:3 Navigate to Contacts link
				HomePage homePage = new HomePage(driver);
				homePage.clickOnContactLnk();
				
				//Step:4 Click on Create contact look Up Image
				ContactInfoPage contactInfoPage = new ContactInfoPage(driver);
				contactInfoPage.clickOnCreateConLookUpImg();
								
				//Step:5 Create Contact with Mandatory fields
				CreateContactPage createContactPage = new CreateContactPage(driver);
				createContactPage.createContact(LASTNAME);
				
				//Step:6 Verify or Validate
				VerifyContactPage verifyContactPage = new VerifyContactPage(driver);
				String contactHeader = verifyContactPage.getContactHeader();
				
				if(contactHeader.contains(LASTNAME))
				{
					System.out.println("PASS");
					System.out.println(contactHeader);
				}
				else
				{
					System.out.println("FAIL");
				}
				
				//Step:7 logout of Application
				 homePage.logOutFromApp(driver);
				  
				 //Step:8 Close the browser
				  driver.quit();

		
		
	}
}
