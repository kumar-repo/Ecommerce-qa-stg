package com.qa.ecommerce.utils;

import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public  OptionManager(Properties prop) {
		this.prop = prop;
	}

	/*
	 * Headless will execute tests without launching browser
	 * incongnito --> browser will launch incongnito  mode
	 */
	public ChromeOptions getChromeOPtion() {
		co =new ChromeOptions();
		if(prop.getProperty("headless").trim().equals("true")) co.addArguments("--headless");
		if(prop.getProperty("incongnito").trim().equals("true")) co.addArguments("--incongnito");
		return co;
	}
	
	/*
	 * Headless will execute tests without launching browser
	 * incongnito --> browser will launch incongnito  mode
	 */
	public FirefoxOptions getFirefoxOPtion() {
		fo =new FirefoxOptions();
		if(prop.getProperty("headless").trim().equals("true")) fo.addArguments("--headless");
		if(prop.getProperty("incongnito").trim().equals("true")) fo.addArguments("--incongnito");	
		return fo;
	}
	
	
}
