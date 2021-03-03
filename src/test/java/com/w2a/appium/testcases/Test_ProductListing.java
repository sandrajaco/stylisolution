package com.w2a.appium.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.appium.base.TestBase;
import com.w2a.appium.screens.ProductScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class Test_ProductListing extends TestBase{
	
	@Test
	public void productValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		service=startServer();
		AndroidDriver<AndroidElement> driver=AndroidCapabilities("app.under.test");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ProductScreen productScreen=new ProductScreen(driver);
		
		driver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
		driver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
		driver.hideKeyboard();
		driver.findElementByAccessibilityId("test-LOGIN").click();

		//product listing

		//int count=driver.findElementsByXPath("//android.widget.ScrollView//android.view.ViewGroup").size();
		int count=productScreen.getProductGroup().size();
		System.out.println(count);

		String expectedProduct="Sauce Labs Backpack";
		String displayedProduct="";

		for(int i=0;i<count;i++)

		{
			
			//String text=driver.findElementsByXPath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView").get(i).getText();
			String text=productScreen.getProductText().get(i).getText();
			System.out.println(text);
			if(text.equalsIgnoreCase(expectedProduct))

			{

				productScreen.getProductText().get(i).click();
				
				displayedProduct=productScreen.getProductText().get(i).getText();
				System.out.println(displayedProduct);
				break;

			}

		}
		
		Assert.assertEquals(expectedProduct, displayedProduct);
		stopServer();

}

}

