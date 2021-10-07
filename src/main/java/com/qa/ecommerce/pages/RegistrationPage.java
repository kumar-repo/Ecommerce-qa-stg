package com.qa.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ecommerce.base.BasePage;
import com.qa.ecommerce.utils.ElementUtil;

public class RegistrationPage extends BasePage{
	
	ElementUtil elementUtil;
	private WebDriver driver;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	private By agreeCheckbox = By.xpath("//input[@type='checkbox']");
	private By clickOnContinue =By.xpath("//input[@class='btn btn-primary' and @type='submit']");
	private By actMsg = By.cssSelector("#content h1");
	private By logoutLink = By.linkText("Logout");
	  private By registartionLink =By.linkText("Register");

	public  RegistrationPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	public boolean accountRegistration(String firstname, String lastname,String email, 
			String telephone,String password, String subscribe) {
		elementUtil.getElement(this.firstName).clear();
		elementUtil.getElement(this.lastName).clear();
		elementUtil.getElement(this.emailId).clear();
		elementUtil.getElement(this.telephone).clear();
		elementUtil.getElement(this.password).clear();
		elementUtil.getElement(this.confirmPassword).clear();
		
		elementUtil.getElement(firstName).clear();
		elementUtil.doSendKeys(this.firstName, firstname);
		elementUtil.doSendKeys(this.lastName, lastname);
		elementUtil.doSendKeys(this.emailId, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);
		
		if (subscribe.equals("yes")) {
			elementUtil.doClick(subscribeYes);
		}else {
			elementUtil.doClick(subscribeNo);
		}
		elementUtil.doClick(agreeCheckbox);
		elementUtil.doClick(clickOnContinue);
		
		String text= elementUtil.doGetText(actMsg);
		if (text.contains("Your Account Has Been Created"))
				{
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registartionLink);
			return true;
		}
		return false;
	}
	
}
