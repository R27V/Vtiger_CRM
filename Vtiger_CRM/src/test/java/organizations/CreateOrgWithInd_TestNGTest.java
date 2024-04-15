package organizations;

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
import com.ObjectRepository.CreateOrganizationPage;
import com.ObjectRepository.HomePage;
import com.ObjectRepository.LoginPage;
import com.ObjectRepository.OrganizationInfoPage;
import com.ObjectRepository.VerifyOrganizationPage;

public class CreateOrgWithInd_TestNGTest extends BaseClass{

	@Test
	public void createOrgWithInd_TestNGTest() throws Throwable {

		/* Read data from Excel File */
		String ORGNAME = eUtil.readDataFromExcel("Organization", 0, 1) + jUtil.getRandomNo();
		String INDUSTRY = eUtil.readDataFromExcel("Organization", 1, 4);
		
		//Step 4: Navigate to the Organization link
		HomePage homePage = new HomePage(driver);
		homePage.clickOnOrgLnk();
		
		//Step 5: Click on create organization Look-up image
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		organizationInfoPage.clickOnOrgLookUpImg();
		
		//Step 6: Create organization with mandatory fields
		CreateOrganizationPage createOrganizationPage = new CreateOrganizationPage(driver);
		createOrganizationPage.CreateOrganization(driver, ORGNAME, INDUSTRY);
		
		Thread.sleep(1000);
		//Step 8: Validate
		VerifyOrganizationPage verifyOrganizationPage = new VerifyOrganizationPage(driver);
	    String orgHeader = verifyOrganizationPage.getHeadertext();
	    
	    Assert.assertTrue(orgHeader.contains(ORGNAME));
	    System.out.println(orgHeader);
	    
//	    if(orgHeader.contains(ORGNAME))
//	    {
//	    	System.out.println("PASS");
//	    	System.out.println(orgHeader);
//	    }
//	    else
//	    {
//	    	System.out.println("FAIL");
//	    }
	    
	}
}

