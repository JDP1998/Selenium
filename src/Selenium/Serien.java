package Selenium;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Serien {
	public static void main(String[]args) throws InterruptedException {
		Scanner s = new Scanner (System.in);
		String inhalt,infos,serie,titel,wochentag,datum,sender,episodentitel,episodennummer,staffel,episodestaffel,uhrzeit;
		String teile [];
		System.out.println("Nach welcher Serie willst du gucken?");
		serie=s.nextLine();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jportzeh\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor jscript = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.get("https://www.fernsehserien.de/"+serie+"/episodenguide");
		Thread.sleep(5000);
		WebElement button = driver.findElement(By.xpath("//*[@id=\"fs-frame-2\"]/main/article/div[2]/div/section[1]/div/a[1]"));
		Thread.sleep(2000);
		jscript.executeScript("arguments[0].click();", button);
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveByOffset(100, 100);
		Thread.sleep(2000);
		actions.click().perform();
		Thread.sleep(3000);
		actions.click().perform();;
		Thread.sleep(10000);
		titel=driver.findElement(By.xpath("//*[@id=\"episode-184917\"]/a/span[2]")).getText();
		inhalt=driver.findElement(By.xpath("//*[@id=\"fs-frame-2\"]/main/article/div[2]/div/section[1]/ul[1]/li/section/div[2]/div")).getText();
		infos=driver.findElement(By.xpath("//*[@id=\"fs-frame-2\"]/main/article/div[2]/div/section[1]/ul[1]/li/section/ea-angaben-wrapper/ea-angaben-wrapper-inner/ea-angaben/ea-angabe/ea-angabe-datum")).getText();
		Thread.sleep(2000);
		teile=infos.split(" ");
		wochentag=teile[0];
		datum=teile[1];
		sender=driver.findElement(By.xpath("//*[@id=\"fs-frame-2\"]/main/article/div[2]/div/section[1]/ul[1]/li/section/ea-angaben-wrapper/ea-angaben-wrapper-inner/ea-angaben/ea-angabe/ea-angabe-sender")).getText();
		driver.quit();
		System.out.println("Titel: "+titel);
		System.out.println("Inhalt: "+inhalt);
		System.out.println("Wochentag: "+wochentag);
		System.out.println("Datum: "+datum);
		System.out.println("Sender: "+sender);
		
	}

}
