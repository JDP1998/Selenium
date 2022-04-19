package Selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wikipedia {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		Scanner s2= new Scanner(System.in);
		String wort,ergebnis;
		boolean is_beendet=false;
		int i=0,clicks,random;
		System.out.println("Wähle dein Anfangswort!");
		wort=s.nextLine();
		System.out.println("Wähle die Anzahl an Klicks");
		clicks=s2.nextInt();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jportzeh\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.get("https://www.wikipedia.org/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-link-box-de\"]")).click();
		Thread.sleep(1000);
	//	driver.findElement(By.xpath("//*[@id=\"WMDE-Banner-Container\"]/div/div/div/div[1]/div[2]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys(wort);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).submit();
		Thread.sleep(1000);
		for(i=0;i<=clicks;i++)
		{
			List <WebElement> lst_Elements=driver.findElements(By.xpath("//p//child::a[contains(@href,'wiki')]"));
			if(lst_Elements.size()>0)
			{
			random=(int)(Math.random()*lst_Elements.size());
			try {
			lst_Elements.get(random).click();
			
			lst_Elements.clear();
			}
			catch(ElementNotInteractableException e)
			{
				is_beendet=true;
			}
			Thread.sleep(5000);
			}
			else
			{
				driver.navigate().back();
				if(driver.getCurrentUrl().equals("https://de.wikipedia.org/wiki/Wikipedia:Hauptseite"))
				{
					is_beendet=true;
				}
			}
			if(is_beendet==true)
			{
				break;
			}
		}
		ergebnis=driver.findElement(By.xpath("//h1[@id=\"firstHeading\"]")).getText();
		driver.quit();
		System.out.println("Das Wort ist: "+ergebnis);
		File f = new File("C:\\Users\\jportzeh\\Desktop\\Java Dateien\\Wikipedia.txt");
		if(f.exists()==false)
		{
			f.createNewFile();
		}
		BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true)));
		bwriter.write(wort);
		bwriter.write(" : ");
		bwriter.write(ergebnis);
		bwriter.write("\n");
		bwriter.close();

	}

}
