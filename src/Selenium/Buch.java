package Selenium;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Buch {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner (System.in);
		String buchtitel,verlag,artikelnr;
		System.out.println("Nach welchem Buch möchten sie suchen?");
		buchtitel=s.nextLine();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jportzeh\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.buecher.de/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"klaro\"]/div/div/div/div/p[2]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"qusearchfield\"]")).sendKeys(buchtitel);
		driver.findElement(By.xpath("//*[@id=\"qusearchfield\"]")).submit();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Buch')]//parent::span//parent::div//parent::div//child::a")).click();
		Thread.sleep(2000);
		verlag=driver.findElement(By.xpath("//*[@id=\"content_product\"]/div[3]/div[2]/div[2]/ul/li[3]/a")).getText();
		artikelnr=driver.findElement(By.xpath("//*[@id=\"content_product\"]/div[3]/div[2]/div[2]/ul/li[4]")).getText();
		driver.quit();
		System.out.println("Verlag: "+verlag);
		System.out.println(artikelnr);
	}

}
