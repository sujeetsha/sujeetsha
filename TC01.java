package assessment_0629;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC01 {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.salesforce.com/in/");
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		
//		driver.get("https://login.salesforce.com/?locale=in");
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(250));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name ='username']"))));
		driver.findElement(By.xpath("//input[@name ='username']")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.xpath("//input[@id ='password']")).sendKeys("Password@123");
		driver.findElement(By.xpath("//input[@id ='Login']")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"))));
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("User Provisioning Request");
		driver.findElement(By.xpath("//mark[text()='User Provisioning Request']")).click();
		Thread.sleep(5000);
		String parent=driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Open in Salesforce Classic.']")).click();
		Set<String>s=driver.getWindowHandles();
		Iterator<String> i1= s.iterator();
		while(i1.hasNext()) {
			String child = i1.next();
			if(!parent.equals(child)) {
				driver.switchTo().window(child);
			}
		}
		driver.findElement(By.xpath("//a[text()='Create New View']")).click();
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("SujeetShanbhag");
		driver.findElement(By.xpath("//input[@id='devname']")).clear();
		driver.findElement(By.xpath("//input[@id='devname']")).sendKeys("sujeet_30");
		
		driver.findElement(By.xpath("//input[@id='fscope1']")).click();
		
		WebElement dropdown1 = driver.findElement(By.xpath("//select[@id='fcol1']"));
		Select select = new Select(dropdown1);
		select.selectByVisibleText("Name");
		
		WebElement operator = driver.findElement(By.xpath("//select[@id='fop1']"));
		Select select1 = new Select(operator);
		List<WebElement> operatorList = select1.getOptions();
		System.out.println("Size of operators dropdown: "+operatorList.size());
		
		Select select2 = new Select(driver.findElement(By.xpath("//select[@id='fcol2']")));
		select2.selectByVisibleText("Created Date");
		Select select3 = new Select(driver.findElement(By.xpath("//select[@id='colselector_select_0']")));
		System.out.println("Available Fields: ");
		List<WebElement> availableList = select3.getOptions();
		for(int i =1; i<=availableList.size();i++) {
			System.out.println(driver.findElement(By.xpath("(//select[@id='colselector_select_0']/option)["+i+"]")).getText());
		}
		
		
		Select select4 = new Select(driver.findElement(By.xpath("//select[@id='colselector_select_1']")));
		System.out.println("Selected Fields: ");
		List<WebElement> selectedList = select3.getOptions();
		for(int i =1; i<=availableList.size();i++) {
			System.out.println(driver.findElement(By.xpath("(//select[@id='colselector_select_1']/option)["+i+"]")).getText());
		}
		
		
		select3.deselectByVisibleText("Created Date");
		driver.findElement(By.xpath("(//img[@title='Add'])[1]")).click();
		
		
		driver.findElement(By.xpath("//input[@id='fsharefshareall']")).click();
		driver.findElement(By.xpath("(//input[@value=' Save '])[2]")).click();
		
		
		String userName = driver.findElement(By.xpath("//img[@title='User Provisioning Request']/following-sibling::h1")).getText();
		if(userName.equalsIgnoreCase("SujeetShanbhag")) {
			System.out.println("User name created");
		}else {
			System.out.println("User name not created");
		}
		
		System.out.println("Page Title: "+driver.getTitle());
		driver.close();
		driver.switchTo().window(parent);
		System.out.println("Parent page title: "+driver.getTitle());
		driver.close();
		driver.quit();
		
		
		
		
	
	}

}
