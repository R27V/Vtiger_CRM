package batchExecutionTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;

public class SampleTest extends BaseClass {

	@Test(groups = {"Smoke"})
	public void sampleTest1()
	{
		System.out.println("--- SampleTest - 1 ---");
		Assert.fail();
	}
	
	@Test(groups = {"Regression"})
	public void sampleTest2()
	{
		System.out.println("--- SampleTest - 2 ---");
	}
	
	@Test
	public void sampleTest3()
	{
		System.out.println("--- SampleTest - 3 ---");
	}
}
