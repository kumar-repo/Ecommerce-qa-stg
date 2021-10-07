package com.qa.ecommerce.base;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.ecommerce.pages.AccountPage;
import com.qa.ecommerce.pages.LoginPage;
import com.qa.ecommerce.pages.RegistrationPage;

public class BaseTest {

	public BasePage basePage;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public RegistrationPage registrationPage;
	public AccountPage accountPage;
	
@Parameters("browser")	
@BeforeTest
public void setUp(String browserName) {	
	basePage = new BasePage();
	 prop=basePage.init_prop();
	 String browser=prop.getProperty("browser");
	 // getting browser name from testNg xml and passing to browser same value for launching the browser 
	 //other wise browser value will be getting from config.properties files
	 System.out.println("browsername value: "+browserName);
	 if (browserName!=null) {
		 browser=browserName;
	 }
	 driver=basePage.init_driver(browser);
	 loginPage = new LoginPage(driver);
	 driver.get(prop.getProperty("url"));
	
}


@AfterTest
public void tearDown() {
	driver.quit();
}


}
