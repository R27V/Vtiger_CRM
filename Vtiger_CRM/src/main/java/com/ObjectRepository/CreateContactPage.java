package com.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility{

		    // Declaration
		    @FindBy(name = "lastname")
		    private WebElement lastNameEdt;
		    
		    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
		    private WebElement saveBtn;
		    
		    @FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
		    private WebElement orgLookUpImg;
		    
		    @FindBy(name = "search_text")
		    private WebElement orgSearchEdt;
		    
		    @FindBy(name = "search")
		    private WebElement orgSearchBtn;
		    
			// Initialization
		    public CreateContactPage(WebDriver driver)
		    {
		    	PageFactory.initElements(driver, this);
		    }

			// Utilization
			public WebElement getLastNameEdt() {
				return lastNameEdt;
			}

			public WebElement getSaveBtn() {
				return saveBtn;
			}

			public WebElement getOrgLookUpImg() {
				return orgLookUpImg;
			}

			public WebElement getOrgSearchEdt() {
				return orgSearchEdt;
			}

			public WebElement getOrgSearchBtn() {
				return orgSearchBtn;
			}
		    	    
			// Business Library
			/**
			 * This method will create contact with mandatory information
			 * @param LASTNAME
			 */
			public void createContact(String LASTNAME)
			{
				lastNameEdt.sendKeys(LASTNAME);
				saveBtn.click();
			}
			
			/**
			 * This method will create contact with relevant organization
			 * @param driver
			 * @param LASTNAME
			 * @param ORGNAME
			 * @throws Throwable 
			 * @throws Throwable 
			 */
			public void createContact(WebDriver driver, String LASTNAME, String ORGNAME) throws Throwable 
			{
				lastNameEdt.sendKeys(LASTNAME);
				orgLookUpImg.click();
				switchToWindow(driver, "Accounts");
				orgSearchEdt.sendKeys(ORGNAME);
				orgSearchBtn.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click(); //dynamic xpath
				switchToWindow(driver, "Contacts");
				saveBtn.click();
			}
	

}
