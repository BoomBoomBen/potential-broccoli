package com.my.business;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.my.util.TestBase;
import com.my.util.Tool;

import io.appium.java_client.AppiumDriver;

public class SimpleBusiness {
	 AppiumDriver ad = TestBase.getDriver();
	 
     public void Register(String UserName,String Passward){	
    	 try{
    	     ad.findElement(By.name("我的")).click();
    	     //用户名输入
	         ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_mobile")).sendKeys(UserName);
	         ad.findElement(By.name("下一步")).click();
	         //验证码、注册密码输入
	         String verification = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/dialog_message")).getText();
	         ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_positive")).click();
	         ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_sms")).sendKeys(verification);
	         ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_password")).sendKeys(Passward);
	         ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();
	         //注册成功弹出框
	    	 String success = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/dialog_message")).getText();
	         Assert.assertEquals(success, "恭喜您，注册成功");
	     }

	     finally{
	    	 ad.resetApp();	
	     }
	     
	     //ad.findElement(By.id("cn.com.panchan.wallet.debug:id/btn_positive")).click();    	 
     }
     
	 public void Login(String UserName,String Passward){
			ad.findElement(By.name("我的")).click();
			ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_mobile")).sendKeys(UserName);
			ad.findElement(By.name("下一步")).click();
			ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_password")).sendKeys(Passward);
			ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();
	 }     
     public void BindBank(String UserName,String Passward,String Trade,String Name,String ID,String BankName,String Card,String Phone){
    	 try{ 
    	     Login(UserName,Passward);
    	     //进入绑定银行卡页面
    	     ad.findElement(By.name("我的")).click();
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/layout_card")).click();
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_negative")).click();
    	     //设置交易密码
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_set_tradePassword")).sendKeys(Trade);
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();
    	     //银行卡信息填写
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_bankCard_number_owner_name")).sendKeys(Name);
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_id_number")).sendKeys(ID);
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_selected_bank")).click();
    	     ad.scrollTo(BankName);  //滚动到银行名称位置
    	     ad.findElement(By.name(BankName)).click();
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_bankCard_number")).sendKeys(Card);
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_mobileNumber")).sendKeys(Phone);
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_negative")).click();
    	     String text = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/dialog_message")).getText();
    	     String conf = text.replaceAll("\\D+", "");
    	     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_positive")).click();
             ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_sms")).sendKeys(conf);
             ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();
             String banknum = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_bankInfo")).getText();
             String Bank = banknum.replaceAll("\\D+", "");
             System.out.println(Card.substring(Card.length()-4));
             Assert.assertEquals(Bank, Card.substring(Card.length()-4));
    	 }
    	 finally{
    		  ad.resetApp();	
    	 }

    	
    	 
     }
     	 
	 public void Recharge(String Money,String UserName,String Passward){
		 try{			 
			 Login(UserName,Passward);
			 ad.findElement(By.name("充值")).click();
		     ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_amount")).sendKeys(Money);
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_yes")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t1")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t2")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t3")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t3")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t2")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t1")).click();
			 List<WebElement> lis = ad.findElementsById("cn.com.panchan.wallet.preview:id/tv_amount");
			 WebElement money = lis.get(0);   
			 System.out.println("充值金额："+money);    //测试代码
			 String act = money.getText();
			 Assert.assertEquals(act, TestString("+",Money,".00"));
		 }
		 finally{
			  ad.resetApp();		 
		 }
		
	 }
	 
	 public void Withdrawals(String Money,String UserName,String Passward){
		 try{
		     Login(UserName,Passward);
			 ad.findElement(By.name("理财")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/menu_withdrawing")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_amount")).sendKeys(Money);
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_yes")).click();
		 }
		 finally{
			  ad.resetApp();	
		 }
		
		
	 }
	 
	 public void FindPassward(String id,String Bank,String UserName,String Passward){
		 try{	 
		     Login(UserName,Passward);
			 ad.findElement(By.name("我的")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/layout_setting")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/frameLayout_back_tradePassword")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/relativeLayout_check_sms_id_card")).click();
			 String Det = 
						ad.findElement(By.id("cn.com.panchan.wallet.preview:id/dialog_message")).getText();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_positive")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_sms")).sendKeys(Det);
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_bankCard_number")).sendKeys(id);
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/et_id_number")).sendKeys(Bank);
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();			 
		 }
		 finally{
			 ad.resetApp();
		 }
	 }
	 
	 public void BuyTicket(String UserName,String Passward){
		 try{
			 Login(UserName,Passward);
			 ad.findElement(By.name("地铁购票")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_city")).click();
			 ad.findElement(By.name("广州")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_start_station")).click();
			 ad.findElement(By.name("广州东站")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_end_station")).click();
			 ad.findElement(By.name("公园前")).click();
			 ad.findElement(By.name("2张")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/btn_next")).click();
			 ad.findElement(By.name("盘缠支付")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t1")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t2")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t3")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t3")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t2")).click();
			 ad.findElement(By.id("cn.com.panchan.wallet.preview:id/t1")).click();
             String confirm = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_successful")).getText();
             Assert.assertEquals(confirm, "恭喜您,支付成功");
             ad.findElement(By.id("cn.com.panchan.wallet.preview:id/action_pay_successful")).click();
             //检查购买的单程票是否存在
             ad.findElement(By.name("我的")).click();
             ad.findElement(By.id("cn.com.panchan.wallet.preview:id/layout_oneway_ticket")).click();
             String start = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_start_station")).getText();
             Assert.assertEquals(start, "广州东站");
             String end = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_end_station")).getText();
             Assert.assertEquals(end, "公园前");
             ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_amount")).click();
             String ticket = ad.findElement(By.id("cn.com.panchan.wallet.preview:id/tv_orderStatus")).getText();
             Assert.assertEquals(ticket, "待取票");
		 }
		 finally{
			 ad.resetApp();
		 }		 
	 }
	 private  String TestString(String s1,String s2,String s3){
		 StringBuilder sb = new StringBuilder();
			sb.append(s1);
			sb.append(s2);
			sb.append(s3);
			return sb.toString();
		 
	 }

}
