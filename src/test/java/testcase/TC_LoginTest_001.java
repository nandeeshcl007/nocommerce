package testcase;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.LoginPage;



public class TC_LoginTest_001 extends BaseClass {
	
	
	
	@Test
	public void Login() throws InterruptedException, IOException
	{
		driver.get(baseURL);
		logger.info("URL opened");
		
		LoginPage lp =new LoginPage(driver);
		
		lp.setUseName(username);
		//JavaScriptUtil.drawBorder(txtUsername, driver);
		lp.setPassword(password);
		lp.clickLogin();
		logger.info("Login button clicked");
		
		Thread.sleep(5000);
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
		{
			Thread.sleep(5000);
			lp.clickLogout();
			logger.info("Logout button clicked");
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.info("Login Failed");
			//captureScreen(driver, "Login");
			Assert.assertTrue(false);
	
		}
	}
	
	

}
