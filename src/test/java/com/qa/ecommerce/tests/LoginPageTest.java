package com.qa.ecommerce.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.ecommerce.base.BaseTest;
import com.qa.ecommerce.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
// for Allure report
@Epic("EPIC-100: define login pafe feature....")
@Story("US-101: define the login page class feature with title , forgot pwd link abd login functinal")
public class LoginPageTest extends BaseTest{
	
	@Description("Verify loginpage title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verfiyLoginPageTitleTest() {
		String title= loginPage.getLoginPageTile();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	//will displayed in allure report
	@Description("Verify forgotpassword link")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void verifyForgotpasswordTextTest() {
		Assert.assertTrue(loginPage.isForgotPassTextLinkExists());
	}
	@Description("Verify login with us and pwd")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Verify search box is displayed")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=4)
	public void SearchBoxTest() {
		Assert.assertTrue(loginPage.isSearchExists());
		   System.out.println("Search is Displayed on Login page........");
	}
//	@Test (priority=5)
//	public void loginPageMenuItemTest() {
//		List<String> menuListItems= loginPage.loginPageMenuItems();
//		for (int items=0; items<=menuListItems.size()-1;items++) {
//		       String itemName= menuListItems.get(items);
//		       if (itemName.equalsIgnoreCase("Wish List")) {
//		    	   Assert.assertEquals(itemName, "Wish List");
//		    	   System.out.println("Menu item found :" +itemName);
//		    	   break;
//		       }
//		}	
//        System.out.println("Menu Item not found");
//		}
}
 	 	