package chataQA.Amazon.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import chataQA.SeleniumUIAutomation.Amazon_SearchResultsPage;
import chataQA.SeleniumUIAutomation.Amazon_SelectionFeatures;

public class Amazon_LandingPage {
	
	
	WebDriver driver;
    WebDriverWait wait;
	
	public Amazon_LandingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchInput;
	
	@FindBy(id="nav-search-submit-button")
	WebElement searchButton;
	
	@FindBy(xpath="//span[@data-action='a-dropdown-button']")
	WebElement sortFilter;
	
	@FindBy(id="s-result-sort-select")
	WebElement sortSelect;
	
	@FindBy(xpath="//*[@id='search']//span[@data-component-type='s-search-results']//div[@class='a-section a-spacing-base']")
	List<WebElement> productList;
	
	@FindBy(xpath="//*/div[@data-cy='price-recipe']//span[@class='a-price']//span[@class='a-offscreen']")
	List<WebElement> priceList;
	
	@FindBy(xpath="//a[text()='See options']")
	List<WebElement> productsLink;
	
	
	public static String ascOrder = "price-asc-rank";
	public static String descOrder = "price-desc-rank";
	
	public void hitSearchWithInputString(String input) 
	{
		wait.until(ExpectedConditions.visibilityOf(searchInput));
		searchInput.sendKeys(input);
		searchButton.click();
	}
	
	public void sortFilterWithValue(String value) 
	{
		wait.until(ExpectedConditions.visibilityOf(sortFilter));
		sortFilter.click();
		Amazon_SelectionFeatures.selectItemByValue(driver, sortSelect, value);
		wait.until(ExpectedConditions.elementToBeClickable(sortSelect));
	}
	
	public boolean filterBy(String value)
	{
		if(value.equalsIgnoreCase("Ascending")){
			
		try 
		{
			Thread.sleep(1000);
			System.out.println(priceList.size());
			System.out.println(productList.size());
		if(!Amazon_SearchResultsPage.isAscendingList(driver,priceList,productList.size()))
		{
			return false;
		}
		else 
		{
			return true;
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(value.equalsIgnoreCase("Descending")){
			try {
				Thread.sleep(1000);
			
			if(!Amazon_SearchResultsPage.isDescendingList(driver,priceList,productList.size()))
			{
				return false;
			}
			else 
			{
				return true;
			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Match not found");
			return false;
		}
		return false;
	}
}
