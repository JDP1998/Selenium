package Selenium;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Google extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Wort;
	private JTextArea txt_Ergebnis;
	private JScrollPane scroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Google frame = new Google();
					frame.setVisible(true);
					frame.setTitle("Suchmaschine");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Google() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scroll = new JScrollPane();
		contentPane.add(new JScrollPane(txt_Ergebnis));
		
		txt_Wort = new JTextField();
		txt_Wort.setBounds(43, 56, 262, 26);
		contentPane.add(txt_Wort);
		txt_Wort.setColumns(10);
		
	    txt_Ergebnis = new JTextArea();
		txt_Ergebnis.setBounds(43, 134, 351, 244);
		txt_Ergebnis.setLineWrap(true);
		txt_Ergebnis.setWrapStyleWord(true);
		contentPane.add(txt_Ergebnis);
		
		JLabel lblNewLabel = new JLabel("Suchmaschine:");
		lblNewLabel.setBounds(43, 16, 116, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ergebnis:");
		lblNewLabel_1.setBounds(43, 98, 69, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btn_Suche = new JButton("Los!");
		btn_Suche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int anz_zeichen=35;
				String wort;
				String ergebnis="Kein Ergebnis gefunden";
				if(txt_Wort.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Gib ein Wort ein.");	
				}
				else
				{
					wort=txt_Wort.getText();
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\jportzeh\\Downloads\\chromedriver.exe");
					WebDriver driver = new ChromeDriver();
					driver.manage().window().minimize();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.get("https://www.wikipedia.org/");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.findElement(By.xpath("//*[@id=\"js-link-box-de\"]")).click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys(wort);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).submit();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ergebnis=driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]")).getText();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.close();
					txt_Ergebnis.setText(ergebnis);
					File f = new File("C:\\Users\\jportzeh\\Desktop\\Java Dateien\\Lexikon.txt");
					if(f.exists()==false)
					{
						try {
							f.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true)));
						try {
							bwriter.write(wort+":");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							bwriter.write("\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String [] worte = ergebnis.split(" ");
						String druckzeile="";
						int anz=0;
						for (String s : worte)
						{
							anz=anz+s.length();
							if(anz>60)
							{
								druckzeile=druckzeile+" "+s+"\n";
								try {
									bwriter.write(druckzeile);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								druckzeile="";
								anz=0;
							}
							else
							{
								druckzeile=druckzeile+" "+s;
							}
						}
						try {
							bwriter.write(druckzeile+"\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							bwriter.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		btn_Suche.setBounds(313, 55, 81, 29);
		contentPane.add(btn_Suche);
		
	}
}
