package common.Methods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	static WebDriver driver;
	
	public static WebDriver lanunchDriver()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); 
		options.addArguments("--disable-cache");
		options.addArguments("--remote-allow-origins=*"); 
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--user-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36\r\n");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static boolean launchApplication(String url) 
	{
		try {
			driver.get(url);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	public static void refreshBrowser()
	{
		driver.navigate().refresh();
	}
	
	public static void takeScreenshot(String pageName)
	{
		try {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("./target/screenshots/"+pageName+".jpg");
		FileUtils.copyFile(screenshot, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void scrollToElement(WebElement element){
		{
			try {
				Actions act = new Actions(driver);
				act.scrollToElement(element).build().perform();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
		
	}
}
