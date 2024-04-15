package testNGpractice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AnnotationTest {

	@Test
	public void oneTest()
	{
		System.out.println("----- Test Script - 1 -----");
	}
	
	@BeforeMethod
	public void beforMethod()
	{
		System.out.println("----- Login To App -----");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("----- Launch the Browser -----");
	}
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("----- Connect to the Database -----");
	}
	
	@Test
	public void executeTest()
	{
		System.out.println("----- Test Script - 2 -----");
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("----- Disconnect to the Database -----");
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("----- Logout from App -----");
	}
	
	@AfterClass 
	public void afterClass()
	{
		System.out.println("----- Close the Browser -----");
	}

}

// the order of test script to run is based on ACSII value
