package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DuplicateTrainNames {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.erail.in/");
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("txtStationFrom")).clear();
		driver.findElement(By.id("txtStationFrom")).sendKeys("MAS");
		Thread.sleep(1000);
		driver.findElement(By.id("txtStationFrom")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("txtStationTo")).clear();
		driver.findElement(By.id("txtStationTo")).sendKeys("SBC");
		Thread.sleep(1000);
		driver.findElement(By.id("txtStationTo")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr"));
		
		int rowSize = rows.size();
		System.out.println("Row size:"+rowSize);
		
		Set<String> trainSet=new HashSet<String>();
		for(int i=1;i<=rowSize;i++)
		{
			String text=driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+i+"]/td[2]")).getText();
			
			boolean added=trainSet.add(text);
			if(!added)
				System.out.println(text);
		
		}
		
		
		/*Set<String> trainSet=new HashSet<String>(trainNames);
		
		int setSize=trainSet.size();
		System.out.println("Train set size:"+setSize);
		
		if(rowSize==setSize)
			System.out.println("No duplicates");
		else
			System.out.println("There are duplicate values");*/
		
		
		
		
		
		
		
		
		driver.close();
	}

}
