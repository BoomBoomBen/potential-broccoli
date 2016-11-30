package com.my.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

public class Tool {
	public static AppiumDriver getDriver(){
		AppiumDriver dr = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
        	
        	Properties pp = new Properties();
        	//new FileInputStream("setting.properties");
            InputStream is = 
        		Tool.class.getClassLoader().getResourceAsStream("setting.properties");
			pp.load(is);
			System.out.println(pp.getProperty("appActivity"));
			capabilities.setCapability("platformName",pp.getProperty("platformName"));
		    capabilities.setCapability("deviceName",pp.getProperty("deviceName"));
		    capabilities.setCapability("platformVersion", pp.getProperty("platformVersion"));		
		    capabilities.setCapability("appPackage", pp.getProperty("appPackage"));
		    capabilities.setCapability("appActivity", pp.getProperty("appActivity"));
		    capabilities.setCapability("unicodeKeyboard", "True");
		    capabilities.setCapability("resetKeyboard", "True");
		    dr = new AndroidDriver(new URL(pp.getProperty("URL")), capabilities);
		    dr.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return dr;
	}
	public static void takeScreenShot(WebDriver driver){
		 File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  
		   try {   
		      FileUtils.copyFile(screenShotFile, new File("D:\\AutoScreenCapture\\" + getCurrentDateTime()+ ".jpg"));  
		      } 
		   catch (IOException e) {e.printStackTrace();}  
		
	}
	private static String getCurrentDateTime(){
		   SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		   return df.format(new Date());
		}

}
