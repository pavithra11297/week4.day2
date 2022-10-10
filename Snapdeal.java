package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		Actions builder=new Actions(driver);
		WebElement menFashion = driver.findElement(By.xpath("(//ul[@class='nav smallNav']//li)[1]"));
		builder.moveToElement(menFashion).perform();
		menFashion.click();
		//Go to Sports Shoes
		WebElement sportShoeLink = driver.findElement(By.xpath("(//div[@class='colDataInnerBlk']//p)[2]//a//span"));
		builder.moveToElement(sportShoeLink).perform();
		sportShoeLink.click();

		//Get the count of the sports shoes
		String sportsShoeCount = driver.findElement(By.xpath("//div[@class='child-cat-name selected']/following-sibling::div")).getText();
		System.out.println("Sport Shoe count is:"+sportsShoeCount);
		//Click Training shoes
		driver.findElement(By.xpath("(//a[@class='child-cat-node dp-widget-link hashAdded'])[3]")).click();

		//Sort by Low to High
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		Thread.sleep(2000);
		WebElement mouseOverPrice = driver.findElement(By.xpath("//ul[@class='sort-value']//li[@class='search-li sort-active']"));
		builder.moveToElement(mouseOverPrice).perform();
		List<WebElement> options = driver.findElements(By.tagName("li"));
		for(WebElement option : options){
			if(option.getText().contains("Price Low To High")) {
				option.click();
				break;
			}
		}

		//Check if the items displayed are sorted correctly
		List<WebElement> sortedItems = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<String> sortedItemsList=new ArrayList<String>();
		for(int i=1;i<sortedItems.size();i++)
		{
			Thread.sleep(1000);
			String items1=sortedItems.get(i).getText();
			Thread.sleep(2000);
			System.out.println("Values brofore sorted" +items1);
			sortedItemsList.add(items1);
			String replaceall=items1.replaceAll("\\D", "");
			int parseInt=Integer.parseInt(replaceall);
			System.out.println(parseInt);

		}

		//Select the price range (900-1200)
		String fromRange="900";
		String toRange="1200";
		WebElement rangeFrom = driver.findElement(By.xpath("//input[@class='input-filter']"));
		rangeFrom.clear();
		rangeFrom.sendKeys(fromRange);
		WebElement rangeTo=driver.findElement(By.xpath("(//input[@class='input-filter'])[2]"));
		rangeTo.clear();
		rangeTo.sendKeys(toRange);
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

		//Filter with color Navy   --Navy color shoe not available, so i filtered yellow
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='Color_s-Yellow']")).click();

		//verify the all applied filters 
		Thread.sleep(2000);
		String priceFilter = driver.findElement(By.xpath("//div[@class='filters']//div/a")).getText();
		System.out.println("Filtered Price is: "+priceFilter);
		String colorFilter = driver.findElement(By.xpath("(//div[@class='filters']//div/a)[2]")).getText();
		System.out.println("Filtered color is: " +colorFilter);

		if(priceFilter.contains(fromRange))
		{
			System.out.println("Applied Price is Filtered correctly");	
		}
		else
		{
			System.out.println("Applied Price is not Filtered correctly");	
		}
		//shoe color verified
		if(colorFilter.contains("Yellow"))
		{
			System.out.println("Applied color is Filtered correctly");	
		}
		else
		{
			System.out.println("Applied color is not Filtered correctly");	
		}

		//Mouse Hover on first resulting Training shoes
		Thread.sleep(1000);
		WebElement firstResulting = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Thread.sleep(1000);
		builder.moveToElement(firstResulting).perform();
		//		
		//click QuickView button
		WebElement firstResultingShoe = driver.findElement(By.xpath("//a[@class='dp-widget-link hashAdded']/following::div[@class='clearfix row-disc']//div"));
		Thread.sleep(1000);
		firstResultingShoe.click();
		Thread.sleep(2000);

		//Print the cost and the discount percentage
		String cost = driver.findElement(By.xpath("//DIV[@class='product-price pdp-e-i-PAY-l clearfix']//span")).getText();
		String discountPercentage = driver.findElement(By.xpath("(//DIV[@class='product-price pdp-e-i-PAY-l clearfix']//span)[2]")).getText();
		System.out.println("The Cost is "+cost +" and the Discount percentage is " +discountPercentage);

		// Take the snapshot of the shoes
		File Source = driver.getScreenshotAs(OutputType.FILE);
		File designation=new File("./snaps/shoe.png");
		FileUtils.copyFile(Source, designation);

		//Close the current window
		driver.close();
		// Close the main window
		driver.quit();
	}
}
