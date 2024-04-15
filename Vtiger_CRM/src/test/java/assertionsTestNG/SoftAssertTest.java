package assertionsTestNG;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertTest {

	@Test
	public void stringValue()
	{
		SoftAssert sa = new SoftAssert();
		System.out.println("-- line-1 --");
		System.out.println("-- line-2 --");
		
		sa.assertNotEquals("A", "B", "Both the condition are same");
	//	sa.assertEquals("A", "B", "Both the condition are same");
		System.out.println("-- line-3 --");
		
		int a=5;
	//	sa.assertNull(a);
		sa.assertNotNull(a);
		System.out.println(a); //in both null and not null it get executed
		
		sa.assertAll();
		
	}
}
