package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.DriverManager;
import utilities.ExcelReader;

public class BaseTest {
	
	/*
	 * WebDriver
	 * TestNG
	 * Screenshot
	 * Log4j
	 * Properties
	 * Keywords
	 * Database
	 * JavaMail
	 * Extent
	 * Listeners
	 * Excel
	 * Waits
	 * 
	 */
	
	public WebDriver driver;
	public Logger log = Logger.getLogger(BaseTest.class.getName());
	public Properties Config = new Properties();
	public FileInputStream fis;
	public ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public WebDriverWait wait;
	static WebElement dropdown;
	
	
	

	
	
	
	public void setUp(String browserName) {
		
	
			
		
			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");
			
			
			
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.info("Config Properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(browserName.equals("chrome")) {
				
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				log.info("Chrome Browser Launched !!!");
			}else if(browserName.equals("firefox")) {
				
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox Browser Launched !!!");
			}
			
			//DriverManager.setWebDriver(driver);
			
			driver.get(Config.getProperty("testsiteurl"));
			log.info("Navigating to the URL : "+Config.getProperty("testsiteurl"));
			DbManager.setMysqlDbConnection();
			log.info("Database connection established");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
			wait = new WebDriverWait(driver,Integer.parseInt(Config.getProperty("explicit.wait")));
		
		
		
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		log.info("Test Execution completed !!!");
		
	}
}

