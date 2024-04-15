package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLeadPage {

	//Declaration
	@FindBy(name= "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(name = "company")
	private WebElement companyNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;
	
	//Initialization
	public CreateLeadPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getCompanyNameEdt() {
		return companyNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method will create Lead with mandatory fields
	 * @param ORGNAME
	 */
	public void CreateLead(String LASTNAME, String COMPANY)
	{
		lastNameEdt.sendKeys(LASTNAME);
		companyNameEdt.sendKeys(COMPANY);
		saveBtn.click();
	}
	
	
}
