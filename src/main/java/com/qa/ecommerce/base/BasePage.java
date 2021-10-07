package com.qa.ecommerce.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.ecommerce.utils.OptionManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * @author kumar
 */

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public OptionManager optionManager;
	public static String highlight;
	
	/*
	 * Initializing Thread Local to create multiple driver
	 * Also fix ERROR "Cannot invoke "org.openqa.selenium.TakesScreenshot.getScreenshotAs
	 * (org.openqa.selenium.OutputType)" because "this.driver" is null"
	 * JRE 1.8 and above will support this feature 
	 */
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver> ();
	
	
	/*
	 * This method will initialize the browser based on browser variable 
	 * Also perform clearing the cookies and maximize the browser window
	 * @param browser
	 * @return web driver driver
	 */
	
	public WebDriver init_driver(String browser) {
		
		System.out.println("browser value is :" +browser);
		
		// highliting the web element by flashing using java script util
		highlight =prop.getProperty("highilight");
		optionManager = new OptionManager(prop);
		
		if (browser.equalsIgnoreCase("chrome")) {	
			WebDriverManager.chromedriver().setup();
			
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			}
			//driver= new ChromeDriver();
			// initializing the browser using  thread local
			else {
				tlDriver.set(new ChromeDriver(optionManager.getChromeOPtion()));
			}
		}
		else if (browser.equalsIgnoreCase("firefox")) {	
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			}
			//driver= new FirefoxDriver();
			// initializing the browser using  thread local
			else {
						tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOPtion()));
			}
		}
		else if (browser.equalsIgnoreCase("safari")) {	
			//driver= new SafariDriver();
			// initializing the browser using  thread local
			tlDriver.set(new SafariDriver());
		}
		else{
			System.out.println("Please enter valid browser " +browser+ "is not valid");
		}
		
		//driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
		//driver.manage().window().maximize();
		getDriver().manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	/*
	 * getDriver using ThreadLocal
	 * returns another copy of driver ti getScreenShot method parallelly  while driver is executing the Test
	 */
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/*
	 * To load properties from property file
	 * @return Properties pop
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		try {
		FileInputStream ip = new FileInputStream("./src/main/java/com/qa/ecommerce/config/config.properties");
		try {
			prop.load(ip);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreeShot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination =new File(path);
		try {
			FileUtils.copyFile(src, destination);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	private void init_remoteDriver(String browser) {
		
		System.out.println("Runing test on grid: " +browser);
		
		if (browser.equals("chrome")) {		
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionManager.getChromeOPtion());
			try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			}
			catch (MalformedURLException e){
				e.printStackTrace();			
			}
		}
		if (browser.equals("firefox")) {		
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionManager.getFirefoxOPtion());
			try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			}
			catch (MalformedURLException e){
				e.printStackTrace();			
			}
		}
	}
}
