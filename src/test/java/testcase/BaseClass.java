package testcase;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import utilities.ReadConfig;





public class BaseClass {

	
//	public String baseURL="http://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";
//	public String username="admin@yourstore.com ";
//	public String password="admin";
//	public String cpath="C:\\Users\\User\\Desktop\\New Practise\\nocommerce\\Drivers\\chromedriver.exe";
	
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUseremail();
	public String password=readconfig.getPassword();
	public String cpath=readconfig.getChromePath();
	
	public String fpath=readconfig.getChromePath();
	
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br)
	{
		//Log 4j setting
		logger=Logger.getLogger("nocommerce");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", cpath);
		driver=new ChromeDriver();
		}
		
		else if(br.equals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver", fpath);
		driver=new FirefoxDriver();
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	
	//For capturing report
	
//	public void captureScreen(WebDriver driver, String tname) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
//		FileUtils.copyFile(source, target);
//		System.out.println("Screenshot taken");
//	}
//	
	
	@AfterMethod
	public void captureScreen(ITestResult result) throws IOException
	{
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			
			File source = ts.getScreenshotAs(OutputType.FILE); // capture the screenshot file
			File target = new File(System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png");
			FileUtils.copyFile(source, target);
			System.out.println("screenshot catured");
		}
	}
	
	//To generate Random string and numbers
	
	public static String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}
