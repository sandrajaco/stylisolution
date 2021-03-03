package com.w2a.appium.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class TestBase {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement>  driver;

	public AppiumDriverLocalService startServer()
	{
		//
		boolean flag=	checkIfServerIsRunnning(4723);
		if(!flag)
		{

			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static  AppiumDriverLocalService stopServer() {

		boolean flag=checkIfServerIsRunnning(4723);
		if(flag) {
			AppiumDriverLocalService service=AppiumDriverLocalService.buildDefaultService();
			service.stop();
		}
		return service;
	} 

	public static  AndroidDriver<AndroidElement> AndroidCapabilities(String appName) throws IOException
	{


		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\testproperties.properties");
		Properties prop= new Properties();
		prop.load(fis);

		// TODO Auto-generated method stub

		File appDir=new File("src//test//resources//app");
		File app = new File(appDir, (String)prop.get(appName));
		String deviceName=(String)prop.get("device.name");
		String platformName=(String)prop.get("platform.name");
		String platformVersion=(String)prop.get("platform.version");
		String appPackage=(String)prop.get("package.name");
		String appActivity=(String)prop.get("activity.name");


		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability("platformName",platformName);
		capabilities.setCapability("platformVersion",platformVersion);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,10);



		driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		return driver;

	}
	
	public static void getScreenshot(String s) throws IOException
	{
	File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\test-output\\screenshots\\"+s+".png"));
	
	}



}
