package com.OHRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OHRM_LogInTest {
	
	WebDriver driver = null;
	String url="http://127.0.0.1/orangehrm-4.0/symfony/web/index.php/auth/login";
	//String UserName="admin";
	//String Password="password";
	
	Logger logger = Logger.getLogger(OHRM_LogInTest.class);
	
	@BeforeTest
	public void setUp()
	{
		
		PropertyConfigurator.configure("Log4j.properties");
		
		System.setProperty("webdriver.gecko.driver", "./driverFiles/geckodriver.exe");
		
		driver = new FirefoxDriver();
		//System.out.println(" Firefox borwser Opened Successfully ");
		logger.info("Firefox borwser Opened Successfully");
		
		driver.get(url);
		//System.out.println(" Successfully navigated to OrangeHRM Application ");
		logger.info(" Successfully navigated to OrangeHRM Application ");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String expected_OrangeHRMLogInPage="OrangeHRM";
//System.out.println(" The Expected Title of OrangeHRM LogIN Page is :"+expected_OrangeHRMLogInPage);
		logger.info(" The Expected Title of OrangeHRM LogIN Page is :"+expected_OrangeHRMLogInPage);
		
		String actual_OrangeHRMWebPageTitle=driver.getTitle();
//System.out.println(" The actual title of the OrangeHRM WebPage is :"+actual_OrangeHRMWebPageTitle);
		logger.info(" The actual title of the OrangeHRM WebPage is :"+actual_OrangeHRMWebPageTitle);
		
		if(actual_OrangeHRMWebPageTitle.equals(expected_OrangeHRMLogInPage))
		{
			// System.out.println(" Successfully navigated to OrangeHRM LogIN Page");
			logger.info(" Successfully navigated to OrangeHRM LogIN Page");
		}
		else
		{
			//System.out.println(" Failed to navigate to OrangeHRM LogIn Page");
			logger.info(" Failed to navigate to OrangeHRM LogIn Page");
		}
	}
	
	@Test
	public void logInTest() throws IOException
	{
		
		FileInputStream file = new FileInputStream("./src/main/java/com/ExcelTestDataFiles/OHRM_LogInTestData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		
		Row row=sheet.getRow(1);
		
		// <input name="txtUsername" id="txtUsername" type="text">
				WebElement userName=driver.findElement(By.id("txtUsername"));
				userName.sendKeys(row.getCell(0).getStringCellValue());
				
			//System.out.println(" Entered User Name ");
				logger.info(" Entered User Name ");
				
				// <input name="txtPassword"  type="password">
				WebElement password=driver.findElement(By.name("txtPassword"));
				password.sendKeys(row.getCell(1).getStringCellValue());
				
			//System.out.println(" Entered Password ");
				logger.info(" Entered Password ");
				
				// <input type="submit"  class="button"  value="LOGIN">
				WebElement logInButton=driver.findElement(By.className("button"));
				logInButton.click();
				
				//System.out.println(" Clicked on logIn Button ");
				logger.info(" Clicked on logIn Button" );
				
				String expected_HomePageTitle="OrangeHRM";
				//System.out.println(" The expected title of OrangeHRM HomePage is :"+expected_HomePageTitle);
				logger.info(" The expected title of OrangeHRM HomePage is :"+expected_HomePageTitle);
				
				String actual_WebPageTitle=driver.getTitle();
				// System.out.println(" The actual title of the OrangeHRM WebPage is :"+actual_WebPageTitle);
				logger.info(" The actual title of the OrangeHRM WebPage is :"+actual_WebPageTitle);
				
				if(actual_WebPageTitle.equals(expected_HomePageTitle))
				{
					//System.out.println(" LogIn Successfull - PASS");
					logger.info(" LogIn Successfull - PASS");
					row.createCell(2).setCellValue("LogIn Successfull - PASS");
				}
				else
				{
					//System.out.println(" LogIn Failed - FAIL");
					logger.info(" LogIn Failed - FAIL");
					row.createCell(2).setCellValue("LogIn Failed - FAIL");
				}
				
				FileOutputStream file1 = new FileOutputStream("./src/main/java/com/ApplicationTestResultFiles/OrangeHRMLogInTestResult.xlsx");
				workBook.write(file1);
				
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
		//System.out.println(" OrangeHRM Application closed Successfully ");
		logger.info(" OrangeHRM Application closed Successfully ");
	}

}
