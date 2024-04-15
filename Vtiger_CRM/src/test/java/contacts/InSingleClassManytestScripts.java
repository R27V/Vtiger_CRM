package contacts;

import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;
import com.ObjectRepository.ContactInfoPage;
import com.ObjectRepository.CreateContactPage;
import com.ObjectRepository.CreateLeadPage;
import com.ObjectRepository.CreateOrganizationPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LeadInfoPage;
import com.ObjectRepository.OrganizationInfoPage;
import com.ObjectRepository.VerifyContactPage;
import com.ObjectRepository.VerifyLeadPage;
import com.ObjectRepository.VerifyOrganizationPage;

public class InSingleClassManytestScripts extends BaseClass{

	@Test
	public void createContact_TestNGTest() throws Throwable {

	  	        /* Read data from Excel File */ 
		        String LASTNAME = eUtil.readDataFromExcel("Contact", 0, 1)+ jUtil.getRandomNo();
		
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
	}
	
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
		   if(orgHeader.contains(ORGNAME))
		        {
		        	System.out.println("Organization created");
		        	System.out.println(orgHeader);
		        }
		        else
		        {
		        	System.out.println("FAIL");
		        }
				
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
		        if(contactHeader.contains(LASTNAME))
		        {
		        	System.out.println("PASS");
		        	System.out.println(contactHeader);
		        }
		        else
		        {
		        	System.out.println("FAIL");
		        }

	}
	
	@Test
	public void createLead_TestNGTest() throws Throwable  {
	
		
		/* Read data from Excel File */
		String LASTNAME = eUtil.readDataFromExcel("Leads", 0, 1)+ jUtil.getRandomNo();
		String COMPANY = eUtil.readDataFromExcel("Leads", 1, 1);
				
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

	}

	
	
	
}
