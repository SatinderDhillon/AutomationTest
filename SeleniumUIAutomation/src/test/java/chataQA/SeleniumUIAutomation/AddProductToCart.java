package chataQA.SeleniumUIAutomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import chataQA.Amazon.Pages.Amazon_LandingPage;
import chataQA.Amazon.Pages.Amazon_ProductDetailsPage;
import chataQA.Amazon.Pages.Amazon_ShoppingCartPage;
import common.Methods.BaseClass;

public class AddProductToCart extends BaseClass{

	@Test
	public void Amazon_AddProducToCart() throws InterruptedException
	{
		WebDriver driver = lanunchDriver();
		launchApplication("https://www.amazon.ca/");
	    Amazon_LandingPage landPage = new Amazon_LandingPage(driver);
	    Amazon_ShoppingCartPage cartPage = new Amazon_ShoppingCartPage(driver);
	    Amazon_ProductDetailsPage productPage = new Amazon_ProductDetailsPage(driver);
		landPage.hitSearchWithInputString("Apple Iphone");
		landPage.sortFilterWithValue(Amazon_LandingPage.ascOrder);
		if(landPage.filterBy("Ascending")) 
		{
			System.out.println("Ascending order is followed for Low to High Sort");
		}
		else 
		{
			System.out.println("Ascending order is not followed for Low to High Sort");
		}
		refreshBrowser();		
		landPage.sortFilterWithValue(Amazon_LandingPage.descOrder);
    	if(landPage.filterBy("Descending")) 
		{
			System.out.println("Descending order is followed for High to Low Sort");
		}
		else 
		{
			System.out.println("Descending order is not followed for High to Low Sort");
		}
    	refreshBrowser();
    	landPage.hitSearchWithInputString("Apple Ipone");
		String urlForBackNavigation = driver.getCurrentUrl();
        productPage.addToCartByIndex(driver, productPage.productsLink, 0, "2");
		driver.navigate().to(urlForBackNavigation);
		productPage.addToCartByIndex(driver, productPage.productsLink, 1, "3");
	    cartPage.clickGoToCartButton();
	    takeScreenshot("Shopping Cart");
	    scrollToElement(cartPage.savedCartSection);
	    takeScreenshot("Shopping Cart Saved");
	    cartPage.printCartDetails();
	    boolean pass = Amazon_ProductDetailsPage.expectedProducts.equals(Amazon_ShoppingCartPage.actualProducts);
	  	    if(pass) 
	  	    {
	  	    	System.out.println("Shopping cart is as expected");
	  	    }
	  	    else 
	  	    {
	  	    	System.out.println("Shopping cart is not as expected as per product details added as expted were"
	  	                            +Amazon_ProductDetailsPage.expectedProducts+" but got "+Amazon_ShoppingCartPage.actualProducts);
	  	    }	  
	    driver.close();
		
	}		
}


