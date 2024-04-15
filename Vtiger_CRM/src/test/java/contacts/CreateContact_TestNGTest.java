package contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.GenericUtilities.BaseClass;
import com.GenericUtilities.ExcelFileUtility;
import com.GenericUtilities.JavaUtility;
import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;
import com.ObjectRepository.ContactInfoPage;
import com.ObjectRepository.CreateContactPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LoginPage;
import com.ObjectRepository.VerifyContactPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContact_TestNGTest extends BaseClass {

	@Test(retryAnalyzer = com.GenericUtilities.RetryimplementationClass.class)
	public void createContact_TestNGTest() throws Throwable {

	  	        /* Read data from Excel File */ 
		        String LASTNAME = eUtil.readDataFromExcel("Contact", 0, 1)+ jUtil.getRandomNo();
		
				//Step:3 Navigate to Contacts link
				HomePage homePage = new HomePage(driver);
				homePage.clickOnContactLnk();
				
				//Assert.fail(); - used for retryAnalyser to check if it is running or not
				//Step:4 Click on Create contact look Up Image
				ContactInfoPage contactInfoPage = new ContactInfoPage(driver);
				contactInfoPage.clickOnCreateConLookUpImg();
								
				//Step:5 Create Contact with Mandatory fields
				CreateContactPage createContactPage = new CreateContactPage(driver);
				createContactPage.createContact(LASTNAME);
				
				//Step:6 Verify or Validate
				VerifyContactPage verifyContactPage = new VerifyContactPage(driver);
				String contactHeader = verifyContactPage.getContactHeader();
				
				Assert.assertTrue(contactHeader.contains(LASTNAME));
				System.out.println(contactHeader);
//				
//				if(contactHeader.contains(LASTNAME))
//				{
//					System.out.println("PASS");
//					System.out.println(contactHeader);
//				}
//				else
//				{
//					System.out.println("FAIL");
//				}		
	}
}
