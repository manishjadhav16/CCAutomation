package com.ccautomation.testPackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ccautomation.utils.WebUtils;
import com.thoughtworks.selenium.DefaultSelenium;

public class Check404 extends WebUtils {

	public int invalidLink;
	String currentLink;
	String temp;
	public DefaultSelenium selenium;

	@BeforeClass
	public void beforeClass()
	{
		setUp();
	}


	@Test
	public void testUntitled() throws Exception {

		FileOutputStream fout = new FileOutputStream ("broken_links.txt", true);
		invalidLink=0;
		//selenium.open("http://www.india.com");
		driver.navigate().to("http://www.india.com");
		int linkCount = selenium.getXpathCount("//a").intValue();
		//int linkCount   = driver.findElement(By.xpath(".//*[@id='primary']/section/div[1]/figure/figcaption/h3/a")).click();
		new PrintStream(fout).println("URL : " + selenium.getLocation());
		new PrintStream(fout).println("--------------------------------------------");
		for (int i = 0; i < linkCount; i++) 
		{
			int statusCode=0;

			currentLink = "this.browserbot.getUserWindow().document.links[" + i + "]";
			temp = selenium.getEval(currentLink + ".href");
			statusCode=getResponseCode(temp);
			if (statusCode==404)
			{
				new PrintStream(fout).println(selenium.getEval(currentLink + ".href") + " "+ statusCode);
				invalidLink++; 
			}
		}
		new PrintStream(fout).println("Total broken Links = " + invalidLink);
		new PrintStream(fout).println(" ");
		fout.close();
		System.out.println(currentLink);
		System.out.println(temp);
	}

	public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
		URL u = new URL(urlString); 
		HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
		huc.setRequestMethod("GET"); 
		huc.connect(); 
		return huc.getResponseCode();
	}

	@AfterClass
	public void afterClass(){
		WebUtils.tearDown();
	}

}
