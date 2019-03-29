package testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.*;

import pageobjects.LoginPage;

public class TC_LoginTest_002 extends BaseClass {
	
	
	@Test(dataProvider="Data")
	public void Login(String uname,String pwd) throws InterruptedException, IOException
	{
		driver.get(baseURL);
		logger.info("URL opened");
		
		LoginPage lp =new LoginPage(driver);
		
		
		lp.setUseName(uname);	
		lp.setPassword(pwd);
		lp.clickLogin();
		logger.info("Login button clicked");
		
		
		
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
	
	
	@DataProvider(name="Data")
	public static String[][] testData1() throws IOException
	{
		String path="C:\\Users\\User\\Desktop\\New Practise\\nocommerce\\src\\test\\java\\testData\\LoginData.xlsx";
		
		
		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][]=new String [rownum][colcount];
		
		for(int r=1;r<=rownum;r++)
		{
			for(int c=0;c<colcount;c++)
			{
				loginData[r-1][c]=XLUtils.getCellData(path,"Sheet1", r, c);	
				
			}
		}
		
		return loginData;
	}
	

}
