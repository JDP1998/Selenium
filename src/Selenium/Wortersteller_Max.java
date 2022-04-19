package Selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Wortersteller_Max {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String [] teile;
		String anfang,wort="",maxbuchstabe = null,ergebnis;
		int fallnr=0,anz=0,anz_akt=0,i=97;
		long anz_ergebnisse,max=0;
		System.out.println("Mit welchem Buchstaben soll das Wort beginnen?");
		anfang=s.next();
		wort=wort+anfang;
		System.out.println("Wie viele Buchstaben soll das Wort haben?");
		anz=s.nextInt();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jportzeh\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.get("https://www.google.de/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys(anfang);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).submit();
		Thread.sleep(1000);
		while(anz_akt<anz)
		{
			max=0;
			i=48;
			while(i<123)
			{
				if(i<58)
				{
				driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).clear();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).sendKeys(wort+String.valueOf((char)i));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).submit();
				Thread.sleep(1000);
				ergebnis=driver.findElement(By.xpath("//div[@id=\"result-stats\"]")).getText();
				teile=ergebnis.split(" ");
				Thread.sleep(1000);
				ergebnis=teile[1];
				anz_ergebnisse=Long.valueOf(ergebnis.replace(".", ""));
			//	System.out.println(anz_ergebnisse);
				if(anz_ergebnisse>max)
				{
					max=anz_ergebnisse;
					maxbuchstabe=String.valueOf((char)i);
				}
				}
				if(i>96)
				{
					driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).clear();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).sendKeys(wort+String.valueOf((char)i));
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).submit();
					Thread.sleep(1000);
					ergebnis=driver.findElement(By.xpath("//div[@id=\"result-stats\"]")).getText();
					teile=ergebnis.split(" ");
					Thread.sleep(1000);
					ergebnis=teile[1];
					anz_ergebnisse=Long.valueOf(ergebnis.replace(".", ""));
				//	System.out.println(anz_ergebnisse);
					if(anz_ergebnisse>max)
					{
						max=anz_ergebnisse;
						maxbuchstabe=String.valueOf((char)i);
					}
				}
				wort=anfang;
				i++;
			}
			anfang=anfang+maxbuchstabe;
			anz_akt++;
		}
		driver.quit();
		System.out.println("Das Wort ist: "+anfang+" mit "+max+" Ergebnissen");
		File file = new File("C:\\Users\\jportzeh\\Desktop\\Java Dateien\\Wörter.txt");
		if(file.exists()==false)
		{
			file.createNewFile();
		}
		BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
		bwriter.write(anfang+" : "+max);
		bwriter.write("\n");
		bwriter.close();

	}

}
