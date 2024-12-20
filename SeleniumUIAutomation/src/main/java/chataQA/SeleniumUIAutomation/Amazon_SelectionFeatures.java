package chataQA.SeleniumUIAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon_SelectionFeatures {

	static WebDriverWait wait;
	public static boolean selectItemByValue(WebDriver driver, WebElement selectItem, String Value) {
		try {
			Select sortDropDown = new Select(selectItem);
			sortDropDown.selectByValue(Value);
			String value = driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getAttribute("innerHTML");
			System.out.println(value);
			return true;
		}
		catch(Exception e) {
			
			System.out.println("selectItemByValue "+e.getMessage());
			return false;
		}
	}
	
	public static boolean selectQuantityByValue(WebDriver driver, WebElement selectItem, String Value) {
		try {
			Select sortDropDown = new Select(selectItem);
			sortDropDown.selectByValue(Value);
			return true;
		}
		catch(Exception e) {
			
			System.out.println("selectItemByValue "+e.getMessage());
			return false;
		}
		
	}
}
