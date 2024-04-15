package login;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.GenericUtilities.PropertyFileUtility;
import com.GenericUtilities.WebDriverUtility;
import com.ObjectRepository.LoginPage;

public class LoginToApp_POMTest {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility    wUtil = new WebDriverUtility();
		
		WebDriver driver = null;
		
		//Step 1: Read all data required
		
		/* Read data from property file */
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//Step 2: Launch the browser - //driver exhibit that is Run-Time Polymorphism
				if(BROWSER.equalsIgnoreCase("chrome"))
				{
					driver = new ChromeDriver();
					System.out.println(BROWSER+"....browser launched");
				}
				else if(BROWSER.equalsIgnoreCase("firefox"))
				{
					driver = new FirefoxDriver();
					System.out.println(BROWSER+"....browser launched");
				}
				else if(BROWSER.equalsIgnoreCase("edge"))
				{
					driver = new EdgeDriver();
					System.out.println(BROWSER+"....browser launched");
				}
				else
				{
					System.out.println("Invalid browser name");
				}
				
				wUtil.maximizeWindow(driver);
				wUtil.waitForPageLoad(driver, 10);
				driver.get(URL);
				
				//Step 3: Login to the Application
				LoginPage loginPage = new LoginPage(driver); 
				loginPage.loginToApp(USERNAME, PASSWORD);
	}
}
