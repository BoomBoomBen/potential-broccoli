package com.my.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestBase {
	private static AppiumDriver dr;
	private static TestBase tb = new TestBase();	
	private TestBase(){
		//注册驱动
		DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
        	
        	Properties pp = new Properties();
        	//new FileInputStream("setting.properties");
            InputStream is = 
        		TestBase.class.getClassLoader().getResourceAsStream("setting.properties");
			pp.load(is);
			capabilities.setCapability("platformName",pp.getProperty("platformName"));
		    capabilities.setCapability("deviceName",pp.getProperty("deviceName"));
		    capabilities.setCapability("platformVersion", pp.getProperty("platformVersion"));		
		    capabilities.setCapability("appPackage", pp.getProperty("appPackage"));
		    capabilities.setCapability("appActivity", pp.getProperty("appActivity"));
		    capabilities.setCapability("unicodeKeyboard", "True");
		    capabilities.setCapability("resetKeyboard", "True");
		    dr = new AndroidDriver(new URL(pp.getProperty("URL")), capabilities);
		    dr.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		    System.out.println(dr);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
//	public static TestBase getInstance(){
//		return tb;
//	}
	public static AppiumDriver getDriver(){
		
		return dr;
	}
	public static String takeScreenShot(){
		 String Time = getCurrentDateTime();
		 File screenShotFile = dr.getScreenshotAs(OutputType.FILE);  
		   try {   
		      FileUtils.copyFile(screenShotFile, new File("D:\\Screen\\" + Time+ ".jpg"));  
		      } 
		   catch (IOException e) {e.printStackTrace();}  
		 return Time;
		
	}
	private static String getCurrentDateTime(){
		   SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		   return df.format(new Date());
		}

}
