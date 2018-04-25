package com.ccautomation.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebUtils {
	
	
public static WebDriver driver;
	
	public  void setUp(){
		//driver= new FirefoxDriver();
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING , true);
			            capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS , true);
			            System.setProperty("webdriver.chrome.driver", getClass().getResource("/Data/chromedriver.exe").toString().replace("%20", " ").replace("file:",""));
			            driver = new ChromeDriver(capabilities);
			            //driver.get("http://www.cricketcountry.com/");
			            
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
	}
	
	public static String getPageTitle(){
		return driver.getTitle();
	}
	

	
	public static String getAttribute(String attribute, By locator){
		return driver.findElement(locator)
	    .getAttribute(attribute);
	}
	
	
	public static void navigateToUrl1(String url){
		  driver.navigate().to(url);
		 }
	
	public static void click(By locator){
		driver.findElement(locator).click();
	}
	
	public static void tearDown(){
		driver.quit();
	}
	
	public static void navigateToUrl(String url){
		driver.get(url);
	}
	
	public static String getpage_source(){
		return driver.getPageSource();
	}

}
