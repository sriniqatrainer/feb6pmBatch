package com.RadioButtonsTesting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EchoEcho_RadioButtonsTesting {
	
	
	WebDriver driver = null;
	String url="http://www.echoecho.com/htmlforms10.htm";
	
	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "./driverFiles/chromedriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		
		driver.get(url);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test
	public void radioButtonTesting()
	{
		
		// Identify the Radio Buttons Block
	// /html/body/div[2]/table[9]/tbody/tr/td[4]/table/tbody/tr/td/div/span/form/table[3]/tbody/tr/td
		
WebElement radioButtonsBlock=driver.findElement
(By.xpath("/html/body/div[2]/table[9]/tbody/tr/td[4]/table/tbody"
													+ "/tr/td/div/span/form/table[3]/tbody/tr/td"));
		
		// <input type="radio" name="group1" value="Milk">
		// <input type="radio" name="group1" value="Butter" checked="">
		// <input type="radio" name="group1" value="Cheese">
		
		// <input type="radio" name="group2" value="Water">

	// automating Group1 radio buttons 		
		List<WebElement>radioButtons=radioButtonsBlock.findElements(By.name("group1"));
		
		System.out.println(radioButtons.size());
		
		for(int i=0;i<radioButtons.size();i++)
		{
			radioButtons.get(i).click();
			
			for(int j=0;j<radioButtons.size();j++)
			{
			
			String radioButtonName=radioButtons.get(j).getAttribute("value");
			//System.out.println(radioButtonName+" "+radioButtons.get(j).getAttribute("checked"));
			
			System.out.println(radioButtonName+" "+radioButtons.get(j).isSelected());
			}
		}
		
		
	}

}
