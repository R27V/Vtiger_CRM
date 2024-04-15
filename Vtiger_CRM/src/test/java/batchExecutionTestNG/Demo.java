package batchExecutionTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.GenericUtilities.BaseClass;

public class Demo extends BaseClass {

	@Test(groups = {"Smoke"})
	public void demo1()
	{
		System.out.println("--- Demo - 1 ---");
	}
	
	@Test(groups = {"Regression"})
	public void demo2()
	{
		System.out.println("--- Demo - 2 ---");
	}
	
	@Test
	public void demo3()
	{
		System.out.println("--- Demo - 3 ---");
		Assert.fail();
	}
}
