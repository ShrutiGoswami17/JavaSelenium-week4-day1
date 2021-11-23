package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/*Assignment: 3	Myntra
	1) Open https://www.myntra.com/
	2) Mouse hover on MeN 
	3) Click Jackets 
	4) Find the total count of item 
	5) Validate the sum of categories count matches
	6) Check jackets
	7) Click + More option under BRAND
	8) Type Duke and click checkbox
	9) Close the pop-up x
	10) Confirm all the Coats are of brand Duke
	    Hint : use List 
	11) Sort by Better Discount
	12) Find the price of first displayed item
	 13) Click on the first product	
	14) Click on WishList Now
	14) Close Browser(driver.close())
*/

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		

		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//a[text()='Men']"))).perform();
		driver.findElement(By.linkText("Jackets")).click();
		List<WebElement> items = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		System.out.println("No. of items:"+items.size());
		
		driver.findElement(By.xpath("//div[@class='common-checkboxIndicator']")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Duke");
		driver.findElement(By.xpath("//label[@class=' common-customCheckbox']//div")).click();
		driver.findElement(By.xpath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close')]")).click();
		List<WebElement> jackets = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		List<String> jacketNames=new ArrayList<String>();
		System.out.println(jackets.size());
		Thread.sleep(2000);
		int count=0;
		for(int i=0;i<jackets.size();i++)
		{
			
				String text=driver.findElement(By.xpath("//h3[@class='product-brand']")).getText();
				jacketNames.add(text);
		}
		System.out.println(jacketNames);
		System.out.println(jacketNames.size());
		
		for(int i=0;i<jacketNames.size();i++)
		{
			
				if(jacketNames.get(i).equals("Duke"))
					count++;
				
		}
		if(count==jacketNames.size())
			System.out.println("All the jackets are of Duke");
		else
			System.out.println("Jackets are not of Duke");
		
		builder.moveToElement(driver.findElement(By.xpath("//div[text()='Sort by : ']"))).perform();
		driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();
		
		Thread.sleep(2000);
		String price = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price of first item:"+price);
		
		driver.findElement(By.xpath("(//img[@class='img-responsive'])[1]")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList=new ArrayList<String>(windowHandles);
		String secondWindow=windowHandlesList.get(1);
		driver.switchTo().window(secondWindow);
		
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();		
		driver.quit();
		
}
}
