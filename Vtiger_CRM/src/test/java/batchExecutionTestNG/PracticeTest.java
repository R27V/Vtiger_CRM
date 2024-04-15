package batchExecutionTestNG;

import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;

public class PracticeTest extends BaseClass {

	@Test(groups= {"Smoke"})
	public void createTest()
	{
		System.out.println("--- Created Test ---");
	}
	
	@Test(groups = {"Regression"})
	public void editTest()
	{
		System.out.println("--- Edited Test ---");
	}
	
	@Test
	public void deleteTest()
	{
		System.out.println("--- Deleted Test ---");
	}
}
