package com.GenericUtilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.ObjectRepository.HomePage;
import com.ObjectRepository.LoginPage;

public class BaseClass {

	public DatabaseUtility dUtil = new DatabaseUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	
	public WebDriver driver = null; 
	
	public static WebDriver sdriver;
	
@BeforeSuite(alwaysRun = true)
public void connectToDB()
{
	System.out.println("-- DB Connected --");
	Reporter.log("-- DB Connected --", true);
}

//@Parameters("BROWSER") - to run on different browser, same time we use this
//@BeforeClass(alwaysRun = true)
//public void launchBrowser(String BROWSER) throws Throwable
//{
	//String BROWSER = pUtil.readDataFromPropertyFile("browser");

@BeforeClass(alwaysRun = true)
public void launchBrowser() throws Throwable
{
	String BROWSER = pUtil.readDataFromPropertyFile("browser");
	
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		Reporter.log("-- Launched Chrome  Browser --", true);
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		Reporter.log("-- Launched Firefox Browser --");
	}
	else if(BROWSER.equalsIgnoreCase("edge"))
	{
		driver = new EdgeDriver();
		Reporter.log("-- Launched Edge Browser --");
	}
	else
	{
		Reporter.log("-- Invalid Browser --");
	}
	
	sdriver = driver;
	wUtil.maximizeWindow(driver);
	wUtil.waitForPageLoad(driver);
}

@BeforeMethod(alwaysRun = true)
public void loginToApp() throws Throwable 
{
	String URL = pUtil.readDataFromPropertyFile("url");
	String USERNAME = pUtil.readDataFromPropertyFile("username");
	String PASSWORD = pUtil.readDataFromPropertyFile("password");
	
	driver.get(URL);
	
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	Reporter.log("-- Logged in to Application --", true);
}

@AfterMethod(alwaysRun = true)
public void logoutToApp() throws Throwable
{
	HomePage hp = new HomePage(driver);
	hp.logOutFromApp(driver);
	Reporter.log("-- Logged out from Application --", true);
}

@AfterClass(alwaysRun = true)
public void closeBrowser()
{
	driver.quit();
	Reporter.log("-- Close the Browser --");
}

@AfterSuite(alwaysRun = true)
public void disConnectToDB()
{
	Reporter.log("-- Close DB Connection --", true);
}
}
