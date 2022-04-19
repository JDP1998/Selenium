package Selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Random {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner (System.in);
		int anz_buchstaben,akt_anz=1,random;
		boolean is_ergebnis=false;
		String druckzeile="",ergebnis;
		String [] teile;
		long anz_ergebnisse = 0;
		System.out.println("Wie viele Zeichen soll dein Zufallswort haben");
		anz_buchstaben=s.nextInt();
		random=(int)(Math.random()*26)+65;
		druckzeile=druckzeile+(char)(random);
		while(akt_anz<=anz_buchstaben-1)
		{
			random=(int)(Math.random()*93)+33;
			druckzeile=druckzeile+(char)(random);
			akt_anz++;
		}
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jportzeh\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.get("https://www.google.de/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys(druckzeile);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).submit();
		while(is_ergebnis==false)
		{
			List <WebElement>lst_Elemente=driver.findElements(By.xpath("//div[@id='result-stats']"));
			if(lst_Elemente.size()>0)
			{
				is_ergebnis=true;
				ergebnis=driver.findElement(By.xpath("//div[@id='result-stats']")).getText();
				teile=ergebnis.split(" ");
				if(teile.length==5)
				{
				ergebnis=teile[1];
				}
				else
				{
				ergebnis=teile[0];
				}
				if(ergebnis.contains(".")==true)
				{
					anz_ergebnisse=Long.valueOf(ergebnis.replace(".", ""));
				}
				else
				{
					anz_ergebnisse=Long.valueOf(ergebnis);
				}
			}
			else
			{
			druckzeile=druckzeile.substring(0, druckzeile.length()-1);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).sendKeys(druckzeile);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).submit();
			Thread.sleep(2000);
			}
			lst_Elemente.clear();
		}
		driver.quit();
		System.out.println("Das Wort: "+druckzeile+" hat "+anz_ergebnisse+" Ergebnisse.");
		File file = new File("C:\\Users\\jportzeh\\Desktop\\Java Dateien\\Wenig Ergebnisse.txt");
		if(file.exists()==false)
		{
			file.createNewFile();
		}
		BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
		bwriter.write(druckzeile+" : "+anz_ergebnisse);
		bwriter.write("\n");
		bwriter.close();
	}

}
