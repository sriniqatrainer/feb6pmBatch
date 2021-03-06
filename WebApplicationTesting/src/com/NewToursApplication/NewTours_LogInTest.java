package com.NewToursApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class NewTours_LogInTest extends BaseTest {
	
	@Test
	public void logInTest() throws IOException
	{
		// identifying the Excel file - Test Data file
FileInputStream file = new FileInputStream
						("./src/com/ApplicationTestDataFiles/NewTours_LogIn_TestData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		

		// Identifying the properties file - Properties of the Elements are saved
		FileInputStream file2 = new FileInputStream("./newTours.properties");
		
		Properties prop = new Properties(); // creating an object for the Properties class
		prop.load(file2); // loading the properties file
				
		int rowCount=sheet.getLastRowNum();
		
		for(int k=1;k<=rowCount;k++)
		{
			Row row=sheet.getRow(k);
						
			WebElement userName=driver.findElement(By.name(prop.getProperty("UserName")));
			userName.clear();
			userName.sendKeys(row.getCell(0).getStringCellValue());
			
			WebElement password=driver.findElement(By.name(prop.getProperty("Password")));
			password.clear();
			password.sendKeys(row.getCell(1).getStringCellValue());
			
			WebElement signIn=driver.findElement(By.name(prop.getProperty("signIn")));
			signIn.click();
						
			String expected_HomePageTitle="Find";
	System.out.println(" The expected title of NewTours Home Page is : "+expected_HomePageTitle);
			
			String actual_WebPageTitle=driver.getTitle();
			System.out.println(" The actual WebPage title is : "+actual_WebPageTitle);
			
			
			if(actual_WebPageTitle.contains(expected_HomePageTitle))
			{
				System.out.println(" LogIn Successfull - PASS");
				row.createCell(2).setCellValue("LogIn Successfull - PASS");
			}
			else
			{
				System.out.println(" LogIn failed - FAIL");
				row.createCell(2).setCellValue("LogIn Failed - FAIL");
			}
			
			
FileOutputStream file1 = new FileOutputStream
					(".//src//com//ApplicationTestResultFiles//NewTours_LogIn_TestResults.xlsx");
			workBook.write(file1);
			
			driver.navigate().back();
						
		}
			
	}

}
