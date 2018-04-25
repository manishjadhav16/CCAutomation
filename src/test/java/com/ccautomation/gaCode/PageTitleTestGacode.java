package com.ccautomation.gaCode;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.testng.Assert;
//import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ccautomation.utils.ExcelManager;
import com.ccautomation.utils.WebUtils;

import static org.testng.Assert.assertTrue;


public class PageTitleTestGacode extends WebUtils {
	
	@BeforeClass
	public void beforeClass(){
		setUp();
	}
	
	
	@Test(dataProvider="pageTitleDataProvider")
	public void verifygacode(String Sitename, String gacode, String url){
		
				WebUtils.navigateToUrl(url);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String page_source = WebUtils.getpage_source();
				//System.out.println("page source"+page_source);
				//Assert.assertEquals(page_source, gacode);
				assertTrue(page_source.contains("page_source, gacode"));
				//assertTrue(page_source.contains("UA-19458284-1"));
				Reporter.log(page_source+": Success !!");

				
	}
	
	
	@DataProvider(name="pageTitleDataProvider")
	 public Object [][] pageTitleDataProvider(){
	  
	  InputStream is = getClass().getResourceAsStream("/Data/Site-GA-Code.xls");
	  List<HashMap<String, String>> list = ExcelManager.getTestDataFromExcel(is, "Sheet1");

	  Object [][] input =  new Object[list.size()][3];
	  
	  int i=0;
	  for(Map<String, String> map: list){
	    String Sitename = (String) map.get("Site Name");
	    String gacode = (String) map.get("Ga Code");
	    String url = (String) map.get("Site URL");

	    input[i][0] = Sitename;
	    input[i][1] = gacode;
	    input[i][2] = url;
	    i++;
	  }
	  
	  return input;
	 }
	
	
	@AfterClass
	public void afterClass(){
		WebUtils.tearDown();
	}

}
