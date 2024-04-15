package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {

	//Declaration
	@FindBy(xpath="//a[text()='Calendar']")
	private WebElement calendarLnk;
	
	@FindBy(linkText = "Leads")
	private WebElement leadLnk;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLnk;
	
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesLnk;
	
	@FindBy(linkText = "Products")
	private WebElement productLnk;
	
	@FindBy(linkText = "Documents")
	private WebElement documentLnk;
	
	@FindBy(linkText = "Email")
	private WebElement emailLnk;
	
	@FindBy(linkText = "Trouble Tickets")
	private WebElement ticketLnk;
	
	@FindBy(linkText = "Dashboard")
	private WebElement darhboardLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	//Initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
		
	//Utilization
	public WebElement getCalendarLnk() {
		return calendarLnk;
	}

	public WebElement getLeadLnk() {
		return leadLnk;
	}

	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getContactLnk() {
		return contactLnk;
	}

	public WebElement getOpportunitiesLnk() {
		return opportunitiesLnk;
	}

	public WebElement getProductLnk() {
		return productLnk;
	}

	public WebElement getDocumentLnk() {
		return documentLnk;
	}

	public WebElement getEmailLnk() {
		return emailLnk;
	}

	public WebElement getTicketLnk() {
		return ticketLnk;
	}

	public WebElement getDarhboardLnk() {
		return darhboardLnk;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}

	
	//Business Library
	/**
	 * This method will click on contacts link in home page
	 */
	public void clickOnLeadlnk()
	{
		leadLnk.click();
	}
	
	/**
	 * This method will click on Organizations link in home page
	 */
	public void clickOnOrgLnk()
	{
		organizationLnk.click();
	}
	
	/**
	 * This method will click on contacts link in home page
	 */
	public void clickOnContactLnk()
	{
		contactLnk.click();
	}
	/**
	 * This method will click on Products link in home page
	 */
	public void clickOnProductsLnk()
	{
		productLnk.click();
	}
	
	/**
	 * This method will perform Logout operation
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void logOutFromApp(WebDriver driver) throws Throwable
	{
		mouseHoverAction(driver, administratorImg);
		Thread.sleep(1000);
		signOutLnk.click();
	}
}
