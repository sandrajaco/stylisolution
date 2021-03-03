package com.w2a.appium.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.appium.base.TestBase;
import com.w2a.appium.screens.Login;
import com.w2a.appium.screens.ProductScreen;
import com.w2a.appium.testData.TestData;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.DataProvider;

public class Test_InvalidLogin extends TestBase{

	@Test(dataProvider="TestData",dataProviderClass=TestData.class)
	public void testInvalidLogin(String username, String password) throws IOException, InterruptedException {

		service=startServer();
		AndroidDriver<AndroidElement> driver=AndroidCapabilities("app.under.test");
		Login login=new Login(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElementByAccessibilityId("test-Username").sendKeys(username);
		driver.findElementByAccessibilityId("test-Password").sendKeys(password);
		driver.hideKeyboard();
		driver.findElementByAccessibilityId("test-LOGIN").click();
		String expectedError="Username and password do not match any user in this service.";
		String displayedError=driver.findElementByXPath("//android.widget.TextView[@text='Username and password do not match any user in this service.']").getText();
		System.out.println(displayedError);
		Assert.assertEquals(expectedError, displayedError);
		stopServer();

	}
}
