package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyLeadPage {


    // Declaration
    @FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement leadHeaderText;
    
	// Initialization
    public VerifyLeadPage(WebDriver driver)
    {
   	 PageFactory.initElements(driver, this);
    }
    
	// Utilization
    public String getLeadHeader()
	{
		return leadHeaderText.getText();
	}
    
	// Business Library
    public WebElement getLeadHeaderText() {
		return leadHeaderText;
	}
}
