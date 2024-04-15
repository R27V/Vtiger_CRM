package testNGpractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TakeDataFromExcel {

	@Test(dataProviderClass = DataProviderdata.class , dataProvider = "readDatafromExcel")
	public void getOrgDataTest(String Org, String Loc, String course,String Amount)
	{
		System.out.println("Organization------------>"+Org+"is located in ------>"+Loc+" with Course Name"+course+"with Fees"+Amount);
	    
	}
}
