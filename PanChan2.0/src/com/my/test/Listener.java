package com.my.test;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.my.util.TestBase;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Listener extends TestListenerAdapter{

     public static String locator = null;
     public static String type = null;
     public static HashMap<String,String[]> result = new HashMap<String,String[]>();
     private int number;
     private static Logger logger = Logger.getLogger(TestCase.class);

     @Override
     public void onTestFailure(ITestResult tr){
    	 super.onTestFailure(tr);
    	 setLog(tr,"失败");
    	 SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
//    	 String name = tr.getName()+"_"+df.format(new Date()).toString();
         String name = TestBase.takeScreenShot();
    	 logger.info("============================");
    	 logger.info("测试方法"+tr.getName()+"执行失败，请参考");
    	 logger.error("可以查看D:\\Screen文件夹中的"+name+".jpg截图");
    	 logger.error("失败原因为："+tr.getThrowable().getMessage());
    	 logger.info("============================");

    	/*尚未理解的内容
    	 new ElementBase().lightElement(locator,type);
    	 TestBase.driver.screenShot(name); 
    	 TestBase.driver.screenShot(name);
    	 TestBase.info("================");
    	 TestBase.info("测试方法"+tr.getName()+"执行失败，请参考");
    	 TestBase.info("screenshot文件夹中的"+name+".jpg");
    	 TestBase.info("失败原因为："+tr.getThrowable().getMessage());
    	 TestBase.info("========================");
    	 */
     }
     @Override
     public void onTestSkipped(ITestResult tr){
    	 super.onTestSkipped(tr);
    	 //收集log
    	 setLog(tr,"跳过");
    	 logger.info("========================================");
    	 logger.info("测试方法"+tr.getName()+"跳过");
    	 logger.info("========================================");

    	 
     }
     @Override
     public void onTestSuccess(ITestResult tr){
    	 super.onTestSuccess(tr);
    	 setLog(tr,"通过"); 
    	 logger.info("测试方法"+tr.getName()+"执行成功");

    	/*
    	   TestBase.info("=====================");
    	 
    	 TestBase.info("测试方法"+tr.getName()+"执行成功！");
    	 TestBase.info("========================");
    	 */
     }
     
     //以下包括setLog尚未使用
     public void setLog(ITestResult tr,String state){
    	 String id = String.valueOf(++ number);
    	 String className = tr.getTestClass().getName();
    	 String methodName = tr.getName();
    	 String startTime = getStartTime(tr);
    	 String totalTime = getTotalTime(tr);
    	 String[] array = new String[]{id,className,methodName,state,startTime,totalTime,/*TestBase.logName*/ };
    	 result.put(id, array);
    	 
     }

     private String getTotalTime(ITestResult tr){
    	 //计算执行过程所需要的时间
    	 long start = 0,stop = 0;
    	 long tmp_start = tr.getStartMillis(),tmp_stop = tr.getEndMillis();
    	 if(start == 0){
    		 start = tmp_start;
    	 }
    	 else{
    		 start = Math.min(start, tmp_start);
    	 }
    	 if(stop == 0){
    		 stop = tmp_stop;
    	 }
    	 else{
    		 stop = Math.max(stop, tmp_stop);
    	 }
    	 return String.valueOf(stop - start);
    	 
     }
     private String getStartTime(ITestResult tr){
    	 //按照指定格式获取当前时间
    	 Date date = new Date(tr.getStartMillis());
    	 SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
    	 String startTime = sdformat.format(date);
    	 return startTime;
     }

}

