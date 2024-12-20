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

import chataQA.SeleniumUIAutomation.Amazon_SelectionFeatures;

public class Amazon_ProductDetailsPage {

	WebDriver driver;
    WebDriverWait wait;
	
	public Amazon_ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="quantity")
	WebElement quantity;
	
	@FindBy(id="add-to-cart-button")
	WebElement addToCartButton;
	
	@FindBy(xpath="//*[@id='sw-atc-confirmation']//h1")
	WebElement addToCartMsg;
	
	@FindBy(xpath="//a[text()='See options']")
	public List<WebElement> productsLink;
	
	@FindBy(xpath="//span[@id='productTitle']")
	WebElement productName;
	
	@FindBy(xpath="	(//div[@id='corePrice_feature_div']//span[@class='a-offscreen'])[1]")
	WebElement productPrice;

	public static HashMap<String,List<String>> expectedProducts=  new HashMap<String,List<String>>();
	
	public boolean addToCartByIndex(WebDriver driver, List<WebElement> options, int index , String count) {
		boolean result;
		try {
			options.get(index).click();
			wait.until(ExpectedConditions.elementToBeClickable(quantity));
			String pName = productName.getText().trim();
			String pPrice = productPrice.getAttribute("innerHTML").trim();
			System.out.println(pName);
			System.out.println(pPrice);
			expectedProducts.put(pName,Arrays.asList(count,pPrice.replaceAll("\\$","").replaceAll(",", "")));
			Amazon_SelectionFeatures.selectQuantityByValue(driver, quantity, count);
			addToCartButton.click();	
			result = true;
		}
		catch(Exception e) {
			System.out.println("Catched "+e.getMessage()+e.getStackTrace());
			result = false;
		}
		
		return result;
	}
	
	public boolean verfiyAddToCart(WebDriver driver) {
		boolean result = false;
		try {
			wait.until(ExpectedConditions.visibilityOf(addToCartMsg));
			String addMsg = addToCartMsg.getText().trim();
			boolean addToCart = addMsg.equalsIgnoreCase("Added to cart");
			if(addToCart) 
			{
				System.out.println("ITEM IS ADDED TO CART");
				result = true;
			}
			else {
				System.out.println("ADD TO CART FAILED");
				result = false;
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return result;
		}
		return result;
	}
}

