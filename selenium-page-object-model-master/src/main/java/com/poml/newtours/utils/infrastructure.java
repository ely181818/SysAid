


package com.poml.newtours.utils;

import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.poml.newtours.base.BaseTest;


public  class infrastructure extends BaseTest {
	public infrastructure(String browserProperties) {
		super(browserProperties);
		// TODO Auto-generated constructor stub
	}
	public static long PAGE_LOAD_TIMEOUT = 10;
	public static long IMPLICIT_WAIT = 10;


	
	
	   public static void SelectElement(String SelectBy, By elemnt,String  type) {

         if (type== "SelectByText") {
        	   List<Integer> l1 = new ArrayList<Integer>();
               List<WebElement> textfields = new ArrayList<WebElement>();
               Select  oSelect = new Select (Driver.findElement(elemnt));
               oSelect.selectByVisibleText(SelectBy);;
           }
           if (type == "SelectByValue")
           {
               List<WebElement> textfields = new ArrayList<WebElement>();
               Select  oSelect = new Select (Driver.findElement(elemnt));
               oSelect.selectByValue(SelectBy);
           }


       }
	   
		public void DropDounList(String MysearchText,String ClassName ) {
			
			String searchText = MysearchText;
			WebElement dropdown = Driver.findElement(By.cssSelector("div[class='  "+ ClassName +  "']"));
			dropdown.click(); // assuming you have to click the "dropdown" to open it
			List<WebElement> options = dropdown.findElements(By.tagName("li"));
			for (WebElement option : options)
			{
			    if (option.getText().equals(searchText))
			    {
			        option.click(); // click the desired option
			        break;
			    }
			}
		}

}
