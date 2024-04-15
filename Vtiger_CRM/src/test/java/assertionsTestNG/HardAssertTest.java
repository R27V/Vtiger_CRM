package assertionsTestNG;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert.*;
import org.testng.annotations.Test;

public class HardAssertTest {

	@Test
	public void hardAssert()
	{
		
		String expectedData = "vtiger CRM 5 - Commercial Open Source CRM";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
	   // System.out.println(driver.getTitle()); 
		String actualData = driver.getTitle();
		//assertEquals(actualData, expectedData);		
		assertNotEquals(actualData, expectedData);
		
		driver.quit();
	}
	
	@Test
	public void demo()
	{
		int a = 5;
		//assertNull(a);
		assertNotNull(a);
		System.out.println(a);
	}
	
	@Test
	public void localBranch()
	{
		//creating to check with local branch - git hub
		int a = 5;
		//assertNull(a);
		assertNotNull(a);
		System.out.println(a);
	}
	
}
