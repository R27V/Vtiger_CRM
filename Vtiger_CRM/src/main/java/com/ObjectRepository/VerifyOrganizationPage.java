package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyOrganizationPage {

	 // Declaration
    @FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement orgHeaderText;
    
	// Initialization
    public VerifyOrganizationPage(WebDriver driver) //test script
    {
    	PageFactory.initElements(driver, this);
    }
	// Utilization

	public WebElement getOrgHeaderText() {
		return orgHeaderText;
	}
    
	// Business Library
	/**
	 * This method will capture the header and return it to caller
	 * @return
	 */
	public String getHeadertext()
	{
		return orgHeaderText.getText();
	}
}
