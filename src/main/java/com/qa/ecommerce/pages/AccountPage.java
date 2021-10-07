package com.qa.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ecommerce.base.BasePage;

public class AccountPage extends BasePage{

	
	private WebDriver driver;
	private By AccountLabel = By.cssSelector("div>h2");
	private By homePageIcon = By.cssSelector("div[id='account-account']>ul>li>a");
	
	public AccountPage (WebDriver driver) {
		this.driver =driver;
	}
	
	public String getHomePageTitle() {	
		return driver.getTitle();
	}
	
	public boolean checkHomepageIcon() {
		//return driver.findElement(homePageIcon).isDisplayed();
		if (driver.findElements(homePageIcon).size()>0) {			
			return true;
		}
		return false;
	}	
	
	public String getHomepageAccountLabel() {
		return driver.findElement(AccountLabel).getText();
	}
}
