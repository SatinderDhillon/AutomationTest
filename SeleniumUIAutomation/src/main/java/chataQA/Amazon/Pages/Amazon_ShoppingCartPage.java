package chataQA.Amazon.Pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon_ShoppingCartPage {
	WebDriver driver;
    WebDriverWait wait;
	
	public Amazon_ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@id='sw-gtc']//a")
	WebElement goToCartButton;
	
	@FindBy(id="sc-saved-cart")
	public WebElement savedCartSection;
	
	@FindBy(xpath="(//div[@data-name='Active Items']//div)/following-sibling::div[@class='sc-list-overwrap']/following-sibling::div")
	List<WebElement> cartItems;
	
	@FindBy(xpath="//h4")
	List<WebElement> productNames;
	
	public static HashMap<String,List<String>> actualProducts = new HashMap<String,List<String>>();
	
	static String quant = "data-quantity";
	static String price = "data-price";
	
	public void clickGoToCartButton() 
	{
		wait.until(ExpectedConditions.visibilityOf(goToCartButton));
		goToCartButton.click();
	}
	
	public void printCartDetails() {
		  for(int i=0;i<productNames.size();i++) 
		     {
			  actualProducts.put(productNames.get(i).getText(), Arrays.asList((cartItems.get(i).getAttribute(quant)),(cartItems.get(i).getAttribute(price))));
			  System.out.println(productNames.get(i).getText()+ " is added "+cartItems.get(i).getAttribute(quant)+" times with price per unit as "+cartItems.get(i).getAttribute(price));
		     }
	}

}
