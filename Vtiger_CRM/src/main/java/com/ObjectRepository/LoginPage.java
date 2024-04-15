package com.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;

/**
 * @author RENU
 */
public class LoginPage {

	//Declaration
	@FindBy(name = "user_name")
	private WebElement userNameEdtTxt;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdtTxt;
	
	@FindAll({@FindBy(id="submitButton"), @FindBy(xpath = "//input[@value='Login']")})
	private WebElement loginBtnClk;
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getUserNameEdtTxt() {
		return userNameEdtTxt;
	}

	public WebElement getPasswordEdtTxt() {
		return passwordEdtTxt;
	}

	public WebElement getLoginBtnClk() {
		return loginBtnClk;
	}
	
	//Business Libraries
	/**
	 * This method will perform login to application
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username, String password)
	{
		userNameEdtTxt.sendKeys(username);
		passwordEdtTxt.sendKeys(password);
		loginBtnClk.click();
	}
	
	
}
