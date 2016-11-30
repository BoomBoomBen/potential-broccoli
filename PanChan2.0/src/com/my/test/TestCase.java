package com.my.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.my.business.SimpleBusiness;
import com.my.util.TestBase;
import com.my.util.Tool;

import io.appium.java_client.AppiumDriver;

@Listeners({ Listener.class })
public class TestCase {
//	AppiumDriver ad = TestBase.getDriver();
	SimpleBusiness sb = new SimpleBusiness();

	public void Login(){
		sb.Login("13613600001", "suyi123456");
	}

//	@Test
	public void FindPass(){
		System.out.println("我是找回密码");
		sb.FindPassward("210101198001090368","690755000011112222","13613600001", "suyi123456");
	}
	@Test
	public void Regist(){
		sb.Register("13013000008", "suyi123456");	
	}
	@Test
    public void Bank(){
		sb.BindBank("13013000008", "suyi123456", "123321", "苏逸", "210101198001090421", "招商银行", "690755000011112222", "15800604326");
	}	
	@Test
	public void Recharge(){

		sb.Recharge("1", "13013000005", "suyi123456");
	}
	@Test
    public void Withdrawals(){
    	sb.Withdrawals("5", "13013000005", "suyi123456");
    }

	@Test
	public void Ticket(){
		sb.BuyTicket("13013000005", "suyi123456");
	}
}
