package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericUtilities.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility {

	//Declaration
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdt;
    
    @FindBy(name = "industry")
    private WebElement industryDropDown;
    
    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;
    
	//Initialization
    public CreateOrganizationPage(WebDriver driver)
    {
    	PageFactory.initElements(driver, this);
    }

    //Utilization
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
       
	//Business Library
	/**
	 * This method will create organization with mandatory fields
	 * @param ORGNAME
	 */
	public void CreateOrganization(String ORGNAME)
	{
		orgNameEdt.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create organization with industry drop down.
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void CreateOrganization(WebDriver driver,String ORGNAME, String INDUSTRY)
	{
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(driver, industryDropDown, INDUSTRY);
		saveBtn.click();
	}
	
	
}
