package chataQA.SeleniumUIAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Amazon_SearchResultsPage {

	public static boolean isAscendingList(WebDriver driver, List<WebElement> priceList, Integer productSize) {
		boolean result = false;
		try {
			
			for (int i=0;i<productSize-1;i++) 
			{
				if((i+1) == (productSize)) {
					return result;
				}
				double lowPrice = Double.parseDouble(priceList.get(i).getAttribute("innerHTML").replace("$", ""));
				double highPrice =  Double.parseDouble(priceList.get(i+1).getAttribute("innerHTML").replace("$", ""));
				//System.out.println(lowPrice +" & " + highPrice);
				if(lowPrice <= highPrice)
				{
					result = true;
					continue;
				}
				else 
				{
					try{
						//Elements will start from 1 not 0 thus we need to fetch the low price by adding 1 to webelement
						WebElement secondaryPrice = driver.findElement(By.xpath("(//*/div[@data-cy='price-recipe'])["+(i+1)+"]/following-sibling::div[@data-cy='secondary-offer-recipe']//span[@class='a-color-base']"));
						lowPrice = Double.parseDouble(secondaryPrice.getAttribute("innerHTML").replace("$", ""));
						//System.out.println("Second Compare "+ lowPrice +" & " + highPrice);
						if(lowPrice<=highPrice) 
						{
							result = true;
							continue;
						}
						else
						{
							System.out.println("Price is not descending even after second compare");
							result = false;
							return result;
						}
					}
					catch(org.openqa.selenium.NoSuchElementException e) 
					{
						System.out.println("No Price for "+ (i + 1) + ". Skipping this element");
						continue;
					}
				}

			}
		}
		catch(Exception e) {
			System.out.println("Catch Block" + e.getMessage());

		}
		return result;
	}
	
	public static boolean isDescendingList(WebDriver driver, List<WebElement> priceList, Integer productSize) {
		boolean result = false;
		try {
			
			for (int i=0;i<productSize-1;i++) 
			{
				if(i+1 == productSize) {
					return result;
				}
				double highPrice = Double.parseDouble(priceList.get(i).getAttribute("innerHTML").replace("$", "").replaceAll(",", ""));
				double lowPrice =  Double.parseDouble(priceList.get(i+1).getAttribute("innerHTML").replace("$", "").replaceAll(",", ""));
				if(highPrice >= lowPrice)
				{
					result = true;
					continue;
				}
				else 
				{
					try{
						//Elements will start from 1 not 0 thus we need to fetch the low price by adding 1 to webelement
						WebElement secondaryPrice = driver.findElement(By.xpath("(//*/div[@data-cy='price-recipe'])["+(i+1)+"]/following-sibling::div[@data-cy='secondary-offer-recipe']//span[@class='a-color-base']"));
						highPrice = Double.parseDouble(secondaryPrice.getAttribute("innerHTML").replace("$", "").replaceAll(",", ""));
						if(highPrice>=lowPrice) 
						{
							result = true;
							continue;
						}
						else
						{
							System.out.println("Price is not ascending even after second compare");
							result = false;
							return result;
						}
					}
					catch(org.openqa.selenium.NoSuchElementException e) 
					{
						System.out.println("No Price for "+ (i + 1) + ". Skipping this element");
						continue;
					}
				}

			}
		}
		catch(Exception e) {
			System.out.println("Catch Block" + e.getMessage());

		}
		return result;
	}
}
