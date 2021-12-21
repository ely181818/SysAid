package com.poml.newtours.pages;




import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.poml.newtours.base.BaseTest;


public class HelpDesk extends BaseTest{

	
	/*public HelpDesk() {
		
		PageFactory.initElements(MobileDriver_OLD.getDriver(), this);
		
	}*/
	
	/*public HelpDesk(String browserProperties) {
		super(browserProperties);
		PageFactory.initElements(Driver, this);
		// TODO Auto-generated constructor stub
	}*/
	public HelpDesk() {
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(className ="main-items")
	WebElement service;	
	@FindBy(partialLinkText="Incidents")
	WebElement hrf;	
	@FindBy(className="breadcrumbs-wrapper-new-button")
	WebElement NewButton;	
	@FindBy(id="contentFrame")
	WebElement contentFrame;
	@FindBy(css="span[class='breadcrumbs-wrapper-title-1']")
	WebElement title;
	
	public String getHomePageTitle(){
		return Driver.getTitle();
	}
	
	public void service_click(){
		this.service.click();
	}
	public void new_button_click() {
		WaitUntilElementClickable(By.className("breadcrumbs-wrapper-new-button"),10);
		this.NewButton.click();
	}
	
	public String getHelpDeskPageTitle(){
		try {
			WebElement  element =contentFrame; 
			Driver.switchTo().frame(element);
			return title.getText();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return "";
		
	}

	public void DropDounListMenue() {
		
		WaitUntilElementClickable(By.cssSelector("div[class='newListSelected problem_type_CustomSelect']"),10);
		DropDounList("ERP", "newListSelected problem_type_CustomSelect");	
	    sleep(1000);	
		DropDounList("HR","newListSelected subcategory_CustomSelect");
		sleep(1000);
		WaitUntilElementClickable(By.cssSelector("div[class='newListSelected thirdLevelCategory_CustomSelect']"),10);
		DropDounList("Other","newListSelected thirdLevelCategory_CustomSelect");
	    sleep(1000);
		WaitUntilElementClickable(By.id("title"),10);
		Driver.findElement(By.id("title")).sendKeys("Problem with my laptop");
		sleep(1000);
		WaitUntilElementClickable(By.id("desc"),10);
		Driver.findElement(By.id("desc")).sendKeys(" desc Problem with my laptop");
		sleep(1000);
		WaitUntilElementClickable(By.cssSelector("div[class='newListSelected requestUser_CustomSelect']"),10);
		DropDounList("EU","newListSelected requestUser_CustomSelect");
		sleep(1000);
		WaitUntilElementClickable(By.id("sr-save-btn"),10);
		
	}
	
	
	
	public void  save_record(){
		Driver.findElement(By.id("sr-save-btn")).click();
	}
	
	public String cheak_if_retrun_to_list_page(){
		return Driver.findElement(By.className("breadcrumbs-wrapper-title-2")).getText();
	}
	
	
	
	
	public int get_number_of_rowes_befor_add_data() {
	Driver.findElement(By.id("searchField")).clear();
	 int 	number_of_rowes=0;
	 WaitUntilElementClickable(By.id("searchField"),10);
	 
	
	 
	 WebElement element =Driver.findElement(By.id("searchField"));
	
	Driver.findElement(By.id("searchField")).clear();
	
	sleep(800);
	Driver.findElement(By.id("searchField")).clear();
	
	element.sendKeys("EU");
	
	/*Driver.findElement(By.id("advanceDateSearchId")).click();
	
	List<WebElement> rows1111 = Driver.findElements(By.className("FieldBox"));
	sleep(500);
	rows1111.get(0).click();
	
	sleep(500);
	 Driver.findElement(By.cssSelector("button[class='ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all']")).click();

	*/
	 Driver.findElement(By.id("search_button")).click();
	 sleep(5000);
	 WebElement simpleTable1 = Driver.findElement(By.id("t"));
     //Get all rows
	 List<WebElement> rows = simpleTable1.findElements(By.className("GridCheckbox"));
	
	    
	 number_of_rowes=rows.size();
	 return number_of_rowes;
	}
	
	
	public int get_number_of_rowes_after_add_data() {
	
	 WebElement simpleTable1 = Driver.findElement(By.id("t"));
     //Get all rows
	 List<WebElement> rows = simpleTable1.findElements(By.className("GridCheckbox"));
	    
	int  number_of_rowes=rows.size();
	 return number_of_rowes;
	}
	
	/*public void testTable() { 
	    
		 WaitUntilElementClickable(By.id("t"),10);
		
	    WebElement simpleTable = Driver.findElement(By.id("t"));
	    
	    //Get all rows
	    WaitUntilElementClickable(By.tagName("tbody"),10);
	    List<WebElement> rows = simpleTable.findElements(By.tagName("tbody"));
	   
	    
	    WebElement 	ROW_SELECT=rows.get(rows.size()-1);
	    
	    List<WebElement> cols = ROW_SELECT.findElements(By.tagName("td"));
	    cols.get(0).findElement(By.className("GridCheckbox")).click();;
	    
	   
	    String fontWeight=cols.get(0).getCssValue("font-weight");
	    if(Integer.parseInt(fontWeight)>700) {
	    	
	    	 System.out.print(cols.get(0).getCssValue("font-weight"));
	    	 System.out.print("the row is bold");
	    	
	    };
	    
	 
	    
	 
	}*/
	public void  click_on_checkBox() { 
	    
		 WaitUntilElementClickable(By.id("t"),10);
		
	    WebElement simpleTable = Driver.findElement(By.id("t"));
	    
	    //Get all rows
	    WaitUntilElementClickable(By.tagName("tbody"),10);
	    List<WebElement> rows = simpleTable.findElements(By.tagName("tbody"));
	    WebElement 	ROW_SELECT=rows.get(rows.size()-1);
	    List<WebElement> cols = ROW_SELECT.findElements(By.tagName("td"));
	    cols.get(0).findElement(By.className("GridCheckbox")).click();;
	   
	    
	 
	  
	 
	}
	
	public boolean Check_font_bold() { 
	    
		 WaitUntilElementClickable(By.id("t"),10);
		
	    WebElement simpleTable = Driver.findElement(By.id("t"));
	    
	    //Get all rows
	    WaitUntilElementClickable(By.tagName("tbody"),10);
	    List<WebElement> rows = simpleTable.findElements(By.tagName("tbody"));
	    WebElement 	ROW_SELECT=rows.get(rows.size()-1);
	    List<WebElement> cols = ROW_SELECT.findElements(By.tagName("td"));
	    cols.get(0).findElement(By.className("GridCheckbox")).click();;
	    String fontWeight=cols.get(0).getCssValue("font-weight");
	    if(Integer.parseInt(fontWeight)>700) {
	    	
	    	 System.out.print(cols.get(0).getCssValue("font-weight"));
	    	 System.out.print("the row is bold");
	    	 return true;
	    	
	    };
	    
	 
	    return false;
	 
	}
	
	public boolean background_color_changed() {
		
		
		  WaitUntilElementClickable(By.className("RowSelected"),10);
		   String backgroundColor =Driver.findElement(By.className("RowSelected")).getCssValue("background-color").toString();
		  if(backgroundColor=="rgba(230, 248, 231, 1)") {
		    
		    System.out.print( Driver.findElement(By.className("RowSelected")).getCssValue("background-color").toString());
		    
		    System.out.print( "THE backgroundColor IS GREEN" );
		    return true;
	     }
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
    

}
