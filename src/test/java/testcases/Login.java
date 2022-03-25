package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utilities.DataUtil;

public class Login extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	
	public void login(String username , String password) {
		setUp("chrome");
		LoginPage lp=new LoginPage(driver);
		lp.doLogin(username, password);
		
	}
	 
	
}
