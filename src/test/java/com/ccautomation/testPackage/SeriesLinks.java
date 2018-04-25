package com.ccautomation.testPackage;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import com.ccautomation.utils.WebUtils;

import static org.testng.Assert.assertTrue;

public class SeriesLinks extends WebUtils {
	
	
	@BeforeClass
	public void beforeClass()
	{
		setUp();
	}
	
	@Test
	public void Addserieslinks() {
		
		driver.get("http://beta1aws.cricketcountry.com/wp-admin");
		driver.findElement(By.id("user_login")).sendKeys("plaiw");
		driver.findElement(By.id("user_pass")).sendKeys("qwerasdf1234");
		driver.findElement(By.id("wp-submit")).click();
		driver.findElement(By.xpath(".//*[@id='menu-posts-series_links']/a/div[3]")).click();
		driver.findElement(By.xpath(".//*[@id='menu-posts-series_links']/ul/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='title-prompt-text']")).sendKeys("Srilanka vs India");
		driver.findElement(By.xpath(".//*[@id='simple_fields_connector_8']/div[2]/div/div[3]/a")).click();
		
		
		
			
		
	}
	
	

	

	@AfterClass
	public void afterClass(){
		WebUtils.tearDown();
	}
	

}
