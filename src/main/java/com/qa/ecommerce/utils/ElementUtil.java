package com.qa.ecommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.ecommerce.base.BasePage;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public  ElementUtil (WebDriver driver) {
		this.driver=driver;
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	public String pageTitleInfo(String titleValue,int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (BasePage.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
}
