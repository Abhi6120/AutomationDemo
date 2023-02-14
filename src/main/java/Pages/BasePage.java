package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	public static Properties prop;
	
	public BasePage() {
		
	String fileName=null;

	try {
		// fileName = System.getProperty("user.dir")+"src/test/resources/TestData/config.properties";
		fileName = "C:\\Users\\abhim\\eclipse-workspace\\Alpha\\src\\test\\resources\\TestData\\config.properties";
	
		FileInputStream fis = new FileInputStream (fileName);
		prop = new Properties();
		prop.load(fis);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new RuntimeException ("Config.properties file not found "+ fileName);
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException ("Config.properties could not load "+ fileName);
	}
}
	public void init () {
		
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else 
			throw new RuntimeException("Invalid Browser - "+ browser);
		
		driver.get(prop.getProperty("url"));
		boolean maximize = Boolean.getBoolean(prop.getProperty("maximize"));
		if(maximize)
			driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("timeout"))));
	}
	
	public String getScreenshot(String testCaseName, WebDriver  driver)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"/src/test/resources/reports/" + testCaseName + ".png");
		try {
			FileUtils.copyFile(source, file);
		} catch (IOException e) {
			System.out.println("ERRROR===> Error in copying screenshot into a new file");
			e.printStackTrace();
		}
		return System.getProperty("user.dir")+"/src/test/resources/reports/" + testCaseName + ".png";
	}
}
