package com.qa.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.ecommerce.base.BaseTest;
import com.qa.ecommerce.utils.Constants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC-200: define account page feature....")
@Story("US-102: define the account page class feature with title , faccount sessions")
public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void homePageSetup() {	
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
     @Test(priority=1)
     public void getHomePageTitleTest() {
    	 String title = accountPage.getHomePageTitle();
    	 System.out.println("Home page title is :" +title);
    	 Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
     }
     
     @Test (priority=2)
     public void homePaheAccountLabelTest() {
    	 String label=accountPage.getHomepageAccountLabel();
    	 Assert.assertEquals(label,Constants.HOME_LABEL);
     }
     @Test (priority=3)
     public void homePageIconTest() {
    	 Assert.assertTrue(accountPage.checkHomepageIcon());
    	 accountPage.getScreeShot();
     }
     
}
