package com.qa.ecommerce.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.ecommerce.base.BaseTest;
import com.qa.ecommerce.utils.ExcelUtil;


public class RegistrationPageTest extends BaseTest {
	
	public String sheetName="Registration_form";
	
@BeforeClass
public void registrationPageSetUp() {
	registrationPage=loginPage.nagivateToRegistrationPage();
}
	
	@DataProvider
	public Object[][] getRegistrationData(){
		Object [][] data = ExcelUtil.getTestData(sheetName);
		return data;
	}
	
@Test(dataProvider="getRegistrationData")
public void userRegistrationPageTest(String firstname, String lastname, String email, String telephone, String password,String subscribe) {
	registrationPage.accountRegistration( firstname,  lastname, email, telephone, password, subscribe);
}
	
}
