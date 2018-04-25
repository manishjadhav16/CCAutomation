package com.ccautomation.pagetitle;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fest.swing.annotation.GUITest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ccautomation.utils.ExcelManager;
import com.ccautomation.utils.WebUtils;



@GUITest
public class PageTitleTest extends WebUtils {
	
	@BeforeClass
	public void beforeClass(){
		setUp();
	}
	
	
	@Test(dataProvider="pageTitleDataProvider")
	public void verifyTitle(String page, String title,String description,String Keywords, String url){
		//Reading data from excel
//		InputStream is = getClass().getResourceAsStream("/Data/testdata.xls");
//		List<HashMap<String, String>> list = ExcelManager.getTestDataFromExcel(is, "Sheet1");

		//for(HashMap map: list){
//			Iterator it = map.entrySet().iterator();
//			while (it.hasNext()) {
//				Map.Entry pair = (Map.Entry)it.next();
//				String description = (String) map.get("Page Description");
//				String keywords = (String) map.get("Page Keywords");

				WebUtils.navigateToUrl(url);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String actualTitle = WebUtils.getPageTitle();
				Assert.assertEquals(actualTitle, title);
				Reporter.log("ActualPage Title : "+ actualTitle);
				Reporter.log(page+": Success !!");

				/*try {
					Thread.sleep(400000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			//}
		//}
	}
	
	
	@DataProvider(name="pageTitleDataProvider")
	 public Object [][] pageTitleDataProvider(){
	  
	  InputStream is = getClass().getResourceAsStream("/Data/Page-Meta-tags.xls");
	  List<HashMap<String, String>> list = ExcelManager.getTestDataFromExcel(is, "Sheet1");

	  Object [][] input =  new Object[list.size()][5];
	  
	  int i=0;
	  for(Map<String, String> map: list){
	    String page = (String) map.get("Page name");
	    String title = (String) map.get("Page Title");
	    String description = (String) map.get("Page Description");
	    String keywords = (String) map.get("Page Keywords");
	    String url = (String) map.get("Page URL");

	    input[i][0] = page;
	    input[i][1] = title;
	    input[i][2] = description;
	    input[i][3] = keywords;
	    input[i][4] = url;
	    i++;
	  }
	  
	  return input;
	 }
	
	
	@AfterClass
	public void afterClass(){
		WebUtils.tearDown();
	}

}
