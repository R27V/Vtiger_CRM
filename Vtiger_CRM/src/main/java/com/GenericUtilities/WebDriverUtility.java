package com.GenericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * @param driver
	 * here driver is parameterize because to get updated driver other wise if globally initialize the driver then it takes NULL value
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will open the window in full screen mode
	 * @param driver
	 */
	public void fullScreenWindow(WebDriver driver)
	{
		driver.manage().window().fullscreen();
	}
	
	/**
	 * This method will wait 10 seconds for all web element to load
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait until page get load
	 * @param driver
	 * @param sec
	 */	
	public void waitForPageLoad(WebDriver driver, int sec)
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
	}
	
	/**
	 * This method is the creating the object for explicit wait
	 * @param driver
	 * @param sec
	 * @return
	 */
	public WebDriverWait webDriverWaitObj(WebDriver driver, int sec)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		return wait;
	}
	
	/**
	 * This method will wait until particular element to be Visible
	 * @param driver
	 * @param sec
	 * @param element
	 */
	public void waitUntilEleToBeVisible(WebDriver driver,int sec, WebElement element)
	{
		webDriverWaitObj(driver, sec).until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait until particular element to be Clickable
	 * @param driver
	 * @param sec
	 * @param element
	 */
	public void waitUntilEleToBeClickable(WebDriver driver,int sec, WebElement element)
	{
		webDriverWaitObj(driver, sec).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait till to get Title
	 * @param driver
	 * @param sec
	 * @param title
	 */
	public void waitUntilTogetTitle(WebDriver driver, int sec, String title)
	{
		webDriverWaitObj(driver, sec).until(ExpectedConditions.titleContains(title));
	}
	
	/**
	 * This method is the creating the object for Select class
	 * @param driver
	 * @param element
	 * @return
	 */
	public Select dropdownObject(WebDriver driver, WebElement element)
	{
		Select sel = new Select(element);
		return sel;
	}
	
	/*
	 * 
	 */
	public void handleDropDown(WebDriver driver,WebElement element,String text)
	{
		dropdownObject(driver,element).selectByVisibleText(text);
	}
	
	public void handleDropDown(WebDriver driver, WebElement element, int index)
	{
		dropdownObject(driver,element).selectByIndex(index);
	}
	
	public void handleDropDown(WebDriver driver,String value, WebElement element)
	{
		dropdownObject(driver,element).selectByValue(value);
	}
	
	/**
	 * This method is the creating the object for Action class
	 * @param driver
	 * @return
	 */
	public Actions performActions(WebDriver driver)
	{
		Actions act = new Actions(driver);
		return act;
	}
	
	/**
	 * This method will perform mouse hover action on a web element
	 * @param driver
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element)
	{
		performActions(driver).moveToElement(element).perform();
	//	 performActions(driver).click(element).perform();
	}
	
	/**
	 * This method will perform right click anywhere on the web page 
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver)
	{
		performActions(driver).contextClick().perform();
	}
	
	/**
	 * This method will perform right click on a particular web element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element)
	{
		performActions(driver).contextClick(element).perform();
	}
	
	/**
	 * This method will perform double click anywhere on the web page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		performActions(driver).doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on a particular Web element
	 * @param driver
	 * @param element
	 */
	public void doubleCLickAction(WebDriver driver, WebElement element)
	{
		performActions(driver).doubleClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop operation
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void dragAndDropAction(WebDriver driver, WebElement src, WebElement target)
	{
		performActions(driver).dragAndDrop(src, target).perform();
	}
	
	/**
	 * This method will perform drag and drop operation on the particular place
	 * @param driver
	 * @param element
	 * @param x
	 * @param y
	 */
	public void dropAndDrop(WebDriver driver, WebElement element, int x, int y)
	{
		
		performActions(driver).dragAndDropBy(element, x, y);
	}
	/**
	 * This method will click and hold the cursor anywhere on the webpage
	 * @param driver
	 */
	public void clickAndHold(WebDriver driver)
	{
		performActions(driver).clickAndHold().perform();
	}
	/**
	 * This method will click and hold the cursor on a particular Web element
	 * @param driver
	 * @param element
	 */
	public void clickAndHold(WebDriver driver,WebElement element)
	{
		performActions(driver).clickAndHold(element).perform();
	}
	/**
	 * This method will move the cursor by offset and click
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void moveTheCursorAndClick(WebDriver driver, int x, int y)
	{
		performActions(driver).moveByOffset(x, y).click().perform();
	}
	
	/**
	 * This method will press Enter key
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		performActions(driver).sendKeys(Keys.ENTER).perform();
	}
	
	/**
	 * his method will help to switch the selenium control from parent to child or 
	 * child to parent based on partial window title
	 * @param driver
	 * @param expWin
	 */
	public  void switchToWindow(WebDriver driver, String expWin)
	{
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		while(it.hasNext())
		{
			String win = it.next();
			String currentTitle = driver.switchTo().window(win).getTitle();
			
			if(currentTitle.contains(expWin))
			{
				break;
			}
		}
	}
	
	public Robot robotObj() throws AWTException
	{
		Robot rb =new Robot();
		return rb;
	}
	
	/**
	 * This method will press Enter key
	 * @throws Throwable
	 */
	public void enterKey() throws Throwable
	{
		robotObj().keyPress(KeyEvent.VK_ENTER);
	}
	
	/**
	 *  This method is used to release the key
	 * @throws Throwable
	 */
	public void enterRelease() throws Throwable
	{
		robotObj().keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will switch the frame based on nameOrID
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
	}
	
	/**
	 * This method will switch the frame based on address
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, WebElement address)
	{
		driver.switchTo().frame(address);
	}
	
	/**
	 * This method will accept alert popUp
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel alert popUp
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will get the Alert Text and return it to caller
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will take screenshot and store it in folder called as screenshot
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws Throwable
	 */
	public String getScreenShot(WebDriver driver, String screenShotName) throws Throwable
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = "./ScreenShot/"+screenShotName+".png";
		File dst = new File(path);
		String srcpath = dst.getAbsolutePath();
		FileUtils.copyFile(src, dst);
		return srcpath;
	}
	
	/**
	 * This method will take screenshot and store it in folder called as screenshot using for ITestListeners
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws Throwable
	 */
	public static String getScreenshot(WebDriver driver, String screenShotName) throws Throwable
	{
		JavaUtility jUtil = new JavaUtility();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = "./ScreenShot/"+screenShotName+""+jUtil.getSystemDateAndTime()+".png";
		File dst = new File(path);
		String srcpath = dst.getAbsolutePath();
		FileUtils.copyFile(src, dst);
		return srcpath;
	}
	
	/**
	 * This method will perform random scroll
	 * @param driver
	 */
	public void scrollBarAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)", "");
	}
	
	/**
	 * This method will scroll until specified element is found
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	/**
	 * This method will scroll till bottom of the page
	 * @param driver
	 */
	public void scrollDownTillBottomOfThePage(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}
	
	/**
	 * This method will scroll till Element to be Visible
	 * @param driver
	 * @param element
	 */
	public void scrollDownTillEleToBeVisisble(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].scrollIntoView()", element);
	}
	
	/**
	 * This method will Click on element
	 * @param driver
	 * @param element
	 */
	public void clickOnElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].click()", element);
	}
	
	/**
	 * this method is used to give input 
	 * @param driver
	 * @param element
	 * @param expData
	 */
	public void jseUsingSendKeys(WebDriver driver, WebElement element, String expData)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].value=argument[1]", element, expData);
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollUpTillEleToBeVisisble(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Point loc = element.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		jse.executeScript("window.scrollBy("+x+","+y+")");
	}
}
