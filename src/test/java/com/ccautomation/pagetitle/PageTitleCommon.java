package com.ccautomation.pagetitle;

import org.openqa.selenium.By;

import com.ccautomation.utils.WebUtils;



public class PageTitleCommon extends WebUtils {
	
	public static String getDescription(){
		return getAttribute("content", PageTitleObjects.description);
	}
	
	public static String getKeywords(){
		return getAttribute("content", PageTitleObjects.keywords);
	}

}
