package com.NewToursApplication_TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.BaseTest.BaseTest;
import com.NewToursApplication_WebPages.NewTours_LogInPage;

public class NewTours_LogInPageTest extends BaseTest{

	NewTours_LogInPage newToursLogInPage = null;
	
	@Test(priority=1)
	public void signOn()
	{
		newToursLogInPage = PageFactory.initElements(driver,NewTours_LogInPage.class );
		
		newToursLogInPage.SignOn();
		
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		driver.navigate().back();
	}
	
	
	@Test(priority=2)
	public void Register()
	{
		newToursLogInPage = PageFactory.initElements(driver,NewTours_LogInPage.class );
		
		newToursLogInPage.Register();
		
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
	}
	
	@Test(priority=3)
	public void LogIn() throws IOException
	{
		newToursLogInPage = PageFactory.initElements(driver,NewTours_LogInPage.class );
		
		FileInputStream file = new FileInputStream("./src/com/ApplicationTestData/NewTours_LogInTestData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		
		int rowCount=sheet.getLastRowNum();
		
		for(int i=0;i<=rowCount;i++)
		{
		
			Row row=sheet.getRow(i);
			
		newToursLogInPage.logIn(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
		
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		// validating Login
		
		driver.navigate().back();
		
		}
	}

}
