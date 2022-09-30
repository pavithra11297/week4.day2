package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.chittorgarh.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@id='navbtn_stockmarket']")).click();
		Actions builder=new Actions(driver);
		WebElement clickBulk = driver.findElement(By.xpath("(//a[text()='NSE Bulk Deals'])[1]"));
		builder.moveToElement(clickBulk).perform();
		clickBulk.click();
		List<WebElement> findElements = driver.findElements(By.xpath("//table[@class='table table-bordered table-condensed table-striped']//tr//td[3]"));
		List<String> listText=new ArrayList<String>();
		for (WebElement string : findElements) {
			String text=string.getText();
			listText.add(text);
			System.out.println(text);
		}
		Set<String> setValue=new HashSet<String>(listText);
		if(listText.size()==setValue.size())
		{
			System.out.println("!....Security Name not have duplicate...!");

		}
		else
		{
			System.out.println("!....Security Name have duplicate....!");

		}
		driver.close();
	}


}


