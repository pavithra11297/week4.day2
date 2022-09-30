package week4.day2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CountRowsColumns {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		//on the screen first table rows and column count
		String firstTable = driver.findElement(By.xpath("//div[@class='render']//caption")).getText();
		List<WebElement> firstTableColumn = driver.findElements(By.xpath("//div[@class='render']//table//th"));
		List<WebElement> firstTableRow = driver.findElements(By.xpath("//div[@class='render']//table//tbody//tr"));
		
		System.out.println(firstTable);
		System.out.println("  first table Column count is: " +firstTableColumn.size());
		System.out.println("  first table Row count is " +firstTableRow.size());

		//on the screen Second table rows and column count
		String secondTable = driver.findElement(By.xpath("//div[@class='related-pages']//h2")).getText();
		List<WebElement> secondTableColumn = driver.findElements(By.xpath("//table[@class='attributes-list']//th"));
		List<WebElement> secondTableRow = driver.findElements(By.xpath("//table[@class='attributes-list']//tbody//tr//td[1]"));
		//PRINT THE VALUES
		System.out.println(secondTable);
		System.out.println("  second table Column count is: " +secondTableColumn.size());
		System.out.println("  second table Row count is " +secondTableRow.size());

	}

}
