package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.leafground.com/drag.xhtml");
		driver.manage().window().maximize();
		// search as oneplus 9 pro 
		Actions builder=new Actions(driver);

		//Draggable
		WebElement drag = driver.findElement(By.id("form:conpnl_header"));
		WebElement drop = driver.findElement(By.id("form:restrictPanel"));
		builder.dragAndDrop(drag, drop).perform();

		//Drappable
		WebElement drag1 = driver.findElement(By.id("form:drag_content"));
		WebElement drop1 = driver.findElement(By.id("form:drop_content"));
		builder.dragAndDrop(drag1, drop1).perform();
		WebElement dropped = driver.findElement(By.xpath("//p[@class='ui-widget-header']"));
		String verificationMsg=dropped.getText();
		System.out.println("Successfully Done for " +verificationMsg);

		//Draggable Columns
		WebElement drag3 = driver.findElement(By.xpath("//div[@class='ui-datatable-tablewrapper']//table//tr//th[3]"));
		WebElement drop3 = driver.findElement(By.xpath("//div[@class='ui-datatable-tablewrapper']//table//tr//th[2]"));
		builder.dragAndDrop(drag3, drop3).perform();
		Thread.sleep(1000);
		//Columns reordered

		//Draggable Rows 
		WebElement drag4 = driver.findElement(By.xpath("(//div[@class='ui-datatable ui-widget ui-datatable-striped ui-datatable-sm ui-datatable-gridlines'])[2]//table//tr[2]"));
		WebElement drop4 = driver.findElement(By.xpath("(//div[@class='ui-datatable ui-widget ui-datatable-striped ui-datatable-sm ui-datatable-gridlines'])[2]//table//tr[4]"));
		builder.clickAndHold(drag4).perform();
		builder.dragAndDrop(drag4, drop4).perform();
		Thread.sleep(1000);

		//Progress Bar
		driver.findElement(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only mr-2']")).click();
		Thread.sleep(10000);
		String progressBar = driver.findElement(By.xpath("//div[@class='ui-progressbar-label']")).getText();
		System.out.println("Progress Bar is: " +progressBar);

		// Slider Range
		String rangeBetween = driver.findElement(By.xpath("(//div[@class='card']//table[@style='margin-bottom: 10px']//td//span)[1]")).getText();
		WebElement rangesliderLeft = driver.findElement(By.xpath("(//table[@style='margin-bottom: 10px']//tr)[3]/td//span"));
		System.out.println("Range Slider Before : " +rangeBetween);
		builder.doubleClick(rangesliderLeft).perform();
		for(int i=0;i<=10;i++)
		{
			//Slide to RIGHT
			rangesliderLeft.sendKeys(Keys.ARROW_LEFT);
		}
		String rangeBetweenNew = driver.findElement(By.xpath("(//div[@class='card']//table[@style='margin-bottom: 10px']//td//span)[1]")).getText();
		System.out.println("Range Slider after : " +rangeBetweenNew);

		//Resize image
		WebElement resize = driver.findElement(By.xpath("//div[@class='ui-wrapper']/img"));
		builder.clickAndHold(resize).moveByOffset(72, 22).release().build().perform();


		driver.close();
	}

}
