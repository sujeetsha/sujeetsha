package assessment_0629;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC02 {
	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get("https://www.salesforce.com/in/");
//		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		
		driver.get("https://login.salesforce.com/?locale=in");
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(250));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name ='username']"))));
		driver.findElement(By.xpath("//input[@name ='username']")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.xpath("//input[@id ='password']")).sendKeys("Password@123");
		driver.findElement(By.xpath("//input[@id ='Login']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"))));
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Service Appointments");
		driver.findElement(By.xpath("//mark[text()='Service Appointments']")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//div[@title='New']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Description']/..//following-sibling::textarea"))));
		driver.findElement(By.xpath("//span[text()='Description']/..//following-sibling::textarea")).sendKeys("Creating Service Appointments");
		driver.findElement(By.xpath("//input[@role='combobox' and @title='Search Accounts']")).click();
		driver.findElement(By.xpath("//span[@title='New Account']")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Account Name']/..//following-sibling::input"))));
		driver.findElement(By.xpath("//span[text()='Account Name']/..//following-sibling::input")).sendKeys("SujeetShanbhag");
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//h2[text()='New Account']/../following-sibling::div//button[@title='Save']"))); 

		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[@class='pillText'])[1]"))));
		String recordName = driver.findElement(By.xpath("(//span[@class='pillText'])[1]")).getText();
		if(recordName.equalsIgnoreCase("SujeetShanbhag")) {
			System.out.println("Name saved successfully");
		}else {
			System.out.println("Name not saved");
		}
		
		driver.findElement(By.xpath("(//span[text()='Earliest Start Permitted']/../following-sibling::div//input)[1]")).sendKeys("6/1/2022");
		driver.findElement(By.xpath("(//span[text()='Earliest Start Permitted']/../following-sibling::div//input)[2]")).sendKeys("12:00PM");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 5);
		String dateString = sdf.format(c.getTime());
		
		driver.findElement(By.xpath("(//span[text()='Due Date']/../following-sibling::div//input)[1]")).sendKeys(dateString);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@title='Save']"))));
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Appointment Number' and @class='test-id__field-label']/../following-sibling::div//span//span"))));
		String appointmentNumber = driver.findElement(By.xpath("//span[text()='Appointment Number' and @class='test-id__field-label']/../following-sibling::div//span//span")).getText();
		
		String serviceAppointmentNumber = driver.findElement(By.xpath("//div[@class='entityNameTitle slds-line-height_reset']/..//span")).getText();
		
		if(appointmentNumber.equals(serviceAppointmentNumber)) {
			System.out.println("Appointment number verified");
		}else
			System.out.println("Appointment number not verified");
		
		
	}
}
