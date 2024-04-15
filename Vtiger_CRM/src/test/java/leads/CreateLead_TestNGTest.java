package leads;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;
import com.GenericUtilities.ListenerImplimentationClass;
import com.ObjectRepository.CreateLeadPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LeadInfoPage;
import com.ObjectRepository.VerifyLeadPage;

@Listeners(com.GenericUtilities.ListenerImplimentationClass.class)
public class CreateLead_TestNGTest extends BaseClass {

	@Test
	public void createLead_TestNGTest() throws Throwable  {
	
		
		/* Read data from Excel File */
		String LASTNAME = eUtil.readDataFromExcel("Leads", 0, 1)+ jUtil.getRandomNo();
		String COMPANY = eUtil.readDataFromExcel("Leads", 1, 1);
				
				//Step:3 Navigate to Lead link
				HomePage homePage=new HomePage(driver);
				homePage.clickOnLeadlnk();
				Assert.fail();
				//Step:4 Click on Create Lead look Up Image
				LeadInfoPage leadInfoPage = new LeadInfoPage(driver);
				leadInfoPage.clickOnCreateLeadLookUpImg();
				
				//Step:5 Create Lead with Mandatory fields
				CreateLeadPage createLP = new CreateLeadPage(driver);
				createLP.CreateLead(LASTNAME, COMPANY);
				
				//Step:7 Verify or Validate
				VerifyLeadPage verifyLeadPage = new VerifyLeadPage(driver);
				String contactHeader = verifyLeadPage.getLeadHeader();
				
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
