package com.poml.newtours.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.poml.newtours.base.BaseTest;


public class Home extends BaseTest{

	public Home(String browserProperties) {
		super(browserProperties);
		PageFactory.initElements(Driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Home() {
		PageFactory.initElements(Driver, this);
	}
	
	
	
	
	
	@FindBy(className ="main-items")
	WebElement service;	
	
	@FindBy( partialLinkText="Incidents")
	WebElement hrf;	
	
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
	
	
	public void hrf_click() {
		
		this.hrf.click();
		
		
		
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
	
	

	
    

}