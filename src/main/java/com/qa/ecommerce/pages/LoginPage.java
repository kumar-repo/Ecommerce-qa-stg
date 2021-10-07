package com.qa.ecommerce.pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.ecommerce.base.BasePage;
import com.qa.ecommerce.utils.Constants;
import com.qa.ecommerce.utils.ElementUtil;
//import com.qa.hubspot.pages.HomePage;

import io.qameta.allure.Step;

import com.qa.ecommerce.pages.RegistrationPage;

public class LoginPage extends BasePage {

/*
 * By locators
 */
	private WebDriver driver;
	private By emailId= By.id("input-email");
    private By password= By.id("input-password");
    private By loginButton= By.xpath("//input[@value='Login' and @class='btn btn-primary']");
    private By singUpLink= By.linkText("Forgotten Password");
    private By searchBox =By.xpath("//input[@name='search' and @placeholder='Search']");
    private By menuItems= By.xpath("//aside/div/a[@href]");
    private ElementUtil elementUtil;
    private By registartionLink =By.linkText("Register");
    /*
     *Constructor of the page class
     */
    
    public LoginPage (WebDriver driver) {
    	this.driver=driver;
    	elementUtil = new ElementUtil(driver);
    }
    
    /*
     * Page actions 
     */
    // Step annotation is used for allure report
   @Step("getting login page title")
   public String getLoginPageTile() {

	   return elementUtil.pageTitleInfo(Constants.LOGIN_PAGE_TITLE, 05);
	   
	   //Thread.sleep(5000);
//	   WebDriverWait wait = new WebDriverWait(driver,05);
//	   wait.until(ExpectedConditions.titleContains("Account")) ;
//	   System.out.println("Page title is :" +driver.getTitle());
//	   return driver.getTitle();
   }
   
   public boolean isForgotPassTextLinkExists() {
	   return driver.findElement(singUpLink).isDisplayed();
   }
   
//   public AccountPage doLogin(String un,String pwd) {
//	   System.out.println("username :"+un+"password :"+pwd);
//	   driver.findElement(emailId).sendKeys(un);
//	   driver.findElement(password).sendKeys(pwd);
//	   driver.findElement(loginButton).click(); 
//	   return new AccountPage(driver);
//	   
//   }
	@Step("login to app with username: {0} and password: {1}")
	public AccountPage doLogin(String username, String password) {
		//elementUtil.waitForElementPresent(this.emailId, 10);
		elementUtil.doSendKeys(this.emailId, username);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doClick(this.loginButton);

		return new AccountPage(driver);
	}
   
   public boolean isSearchExists() {
	   return driver.findElement(searchBox).isDisplayed();
   }
   
//   public List<String> loginPageMenuItems() {
//	   
//	  List<WebElement> menuItemsList = driver.findElements(menuItems);
//	   
//	  List <String> collectionOfMenuItems = menuItemsList
//			  .stream()
//			  .filter(ele -> !ele.getText().isEmpty())
//			  .map(ele->ele.getText().trim())
//			  .collect(Collectors.toList());  
//	  return collectionOfMenuItems;
//	 
//   }
   
   public RegistrationPage nagivateToRegistrationPage() {
	   elementUtil.doClick(registartionLink);
	   return new RegistrationPage(driver);
   }
   
}



















