package com.ccautomation.testPackage;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ccautomation.utils.WebUtils;

public class GAtestcode extends WebUtils {
	
	@BeforeClass
	public void beforeClass()
	{
		setUp();
	}
	
	@Test
	public void testgacode() {

		String page_source ;
		
		driver.get("http://www.india.com");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		page_source = driver.getPageSource();
		
		System.out.println("page source"+page_source);
		
		assertTrue(page_source.contains("UA-19458284-1"));
		
		
	}
	
	

	

	@AfterClass
	public void afterClass(){
		WebUtils.tearDown();
	}
	
	
	

}
