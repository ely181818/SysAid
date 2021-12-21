package com.poml.newtours.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.poml.newtours.utils.PomlEventListener;

import com.poml.newtours.utils.GenerateScreenshot;

public class BaseTest {
	public static WebDriver Driver;
	public static Properties prp;
	public  static EventFiringWebDriver efw_driver;
	public static PomlEventListener eventListener;
	
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/resources/testData/" + "SisAid.xlsx";

	static Workbook book;
	static Sheet sheet;

	
	public static WebDriver getDriver() {
		return Driver;
	}
	
	static void setWebDriver(WebDriver driver) {
		Driver = driver;
	}
	
	
	
	
	public BaseTest(String browserProperties){
		try {
			prp = new Properties();
			System.out.println("The browser property is: " + browserProperties);
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/config/"
					+ browserProperties);
			prp.load(ip);
			
			Object[][] data=getTestData("user_data");
			// add some properties
			prp.setProperty("username", data[0][0].toString());
			//password1=A12345^y
			prp.setProperty("password", data[0][1].toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BaseTest(){
		
	}
	public  void initialization(){
		String browserName = prp.getProperty("browser");
		String driverName = prp.getProperty("driver");
		System.out.println("The browser is :" + browserName + ", The driver is: " + driverName);
		System.out.println(System.getProperty("user.dir")+"/src/main/resources/drivers/" + driverName);
		if(browserName.equals("googlechrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/" + driverName);
			
			Driver = new ChromeDriver();
			
			
			
			setWebDriver(Driver);
			
			Driver.manage().window().maximize();
		}
		else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/" + driverName);	
			Driver = new FirefoxDriver(); 
			Driver.manage().window().maximize();
		}
		
		
		efw_driver = new EventFiringWebDriver(Driver);
		// Create an object of EventListerHandler to register with EventFiringWebDriver
		eventListener = new PomlEventListener();
		efw_driver.register(eventListener);
		Driver = efw_driver;
		
		Driver.manage().window().maximize();
		Driver.manage().deleteAllCookies();
		Driver.manage().timeouts().pageLoadTimeout(GenerateScreenshot.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		Driver.manage().timeouts().implicitlyWait(GenerateScreenshot.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		Driver.get(prp.getProperty("url"));
		
		 
		
		
	}
	
	public void DropDounList(String MysearchText,String ClassName ) {
		
		String searchText = MysearchText;
		WebElement dropdown = Driver.findElement(By.cssSelector("div[class='"+ClassName+"']"));
		dropdown.click(); // assuming you have to click the "dropdown" to open it
		List<WebElement> options = dropdown.findElements(By.tagName("li"));
		for (WebElement option : options)
		{
		    if (option.getText().trim().equals(searchText))
		    {
		        option.click(); // click the desired option
		        break;
		    }
		}
	}
	
	  public static WebElement WaitUntilElementClickable( By elementLocator, int timeout)
      {
         
		    try {
		    	
		    	WebDriverWait wait = new WebDriverWait(Driver, timeout);
		    	wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		    	wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
				
		    	
         
			} catch (Exception e) {
				// TODO: handle exception
			
              System.out.println("Element with locator: '" + elementLocator + "' was not found in current context page.");
            
			}
			return null;
         
      }
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	//Create an excel file and add two columns userName, Password.

	
	public static Object[][] getTestData(String sheetName) {
		
	
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	

}
