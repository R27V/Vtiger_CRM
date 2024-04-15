package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage {

	//Declaration
	@FindBy(xpath= "//img[@title='Create Lead...']")
	private WebElement createLeadLookUpImg;
	
	//Initialization
	public LeadInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateLeadLookUpImg() {
		return createLeadLookUpImg;
	}
	
	//Business Library
	/**
	 * This method will click on create Lead look up image
	 */
	public void clickOnCreateLeadLookUpImg()
	{
		createLeadLookUpImg.click();
	}
	
	
	
}
