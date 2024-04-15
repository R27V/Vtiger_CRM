package contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;
import com.GenericUtilities.DatabaseUtility;
import com.GenericUtilities.ExcelFileUtility;
import com.GenericUtilities.JavaUtility;
import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;
import com.ObjectRepository.ContactInfoPage;
import com.ObjectRepository.CreateContactPage;
import com.ObjectRepository.CreateOrganizationPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LoginPage;
import com.ObjectRepository.OrganizationInfoPage;
import com.ObjectRepository.VerifyContactPage;
import com.ObjectRepository.VerifyOrganizationPage;

public class CreateContactWitOrg_TestNGTest extends BaseClass{

	@Test
	public void createContactWitOrg_TestNGTest() throws Throwable {


		/* Read data from Excel File */
		String LASTNAME = eUtil.readDataFromExcel("Contact", 0, 1)+ jUtil.getRandomNo();
		String ORGNAME = eUtil.readDataFromExcel("Contact", 2, 1)+jUtil.getRandomNo();

		//Step 6: Navigate to the Organization link
		HomePage homePage = new HomePage(driver);
		homePage.clickOnOrgLnk();
				
		//Step 7: Click on create Organization Look-Up Image
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		organizationInfoPage.clickOnOrgLookUpImg();
				
		//Step 8: Create Organization with mandatory field
		CreateOrganizationPage createOrganizationPage = new CreateOrganizationPage(driver);
		createOrganizationPage.CreateOrganization(ORGNAME);
				
		Thread.sleep(1000);
		//Step 9: Validate
		VerifyOrganizationPage verifyOrganizationPage = new VerifyOrganizationPage(driver);
	    String orgHeader = verifyOrganizationPage.getHeadertext();
	    
	    Assert.assertTrue(orgHeader.contains(ORGNAME));// this is going to validate
	    System.out.println(orgHeader);
	    
//		   if(orgHeader.contains(ORGNAME))
//		        {
//		        	System.out.println("Organization created");
//		        	System.out.println(orgHeader);
//		        }
//		        else
//		        {
//		        	System.out.println("FAIL");
//		        }
				
				/* Create contact using Organization*/
		        //Step 11: Click on Contacts Link
		        homePage.clickOnContactLnk();
		        
		        //Step 12: Navigate to Create Contact Look-up Image
		        ContactInfoPage contactInfoPage = new ContactInfoPage(driver);
				contactInfoPage.clickOnCreateConLookUpImg();
		        
		        //Step 13: Create contact with mandatory field
				CreateContactPage createContactPage = new CreateContactPage(driver);
				createContactPage.createContact(driver, LASTNAME, ORGNAME);
		        
		  
		        
		       //Step 19: Validate for Contact 
				VerifyContactPage verifyContactPage = new VerifyContactPage(driver);
		        String contactHeader = verifyContactPage.getContactHeader();
		        
		        Assert.assertTrue(contactHeader.contains(LASTNAME));
		        System.out.println(contactHeader);
		        
//		        if(contactHeader.contains(LASTNAME))
//		        {
//		        	System.out.println("PASS");
//		        	System.out.println(contactHeader);
//		        }
//		        else
//		        {
//		        	System.out.println("FAIL");
//		        }

	}
}

