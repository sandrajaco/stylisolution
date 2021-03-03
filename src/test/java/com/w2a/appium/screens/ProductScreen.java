package com.w2a.appium.screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductScreen {
	
	public ProductScreen(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.view.ViewGroup")
	public List<WebElement> productGroup;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[@content-desc='test-Item title']")
	public List<WebElement> productText;
	
	public List<WebElement> getProductGroup() {
		return productGroup;
	}
	
	public List<WebElement> getProductText() {
		return productText;
	}
	
	

}
