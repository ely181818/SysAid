package com.poml.tests.newtours;


import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.poml.newtours.pages.HelpDesk;
import com.poml.newtours.pages.Home;
import com.poml.newtours.pages.Login;


public class LoginTest extends Login{


	String browserProperties = "chrome.properties";
	Login ln;
	Home ht ;
	HelpDesk he;
	public LoginTest() {		
		super("chrome.properties");
		
	}

	
	@BeforeClass
	public void setUp() {
		initialization();
		ln =new Login(browserProperties);	
		ht =new Home();
		he =new HelpDesk();
		
	}
	
	
	
	@Test(priority=1)
	public void verifyPageTitle() {
		Assert.assertEquals("SysAid Help Desk Software", ln.getHomePageTitle());
	}
	
	
	
	@Test(priority=1)
	public void testLogin_user_name_password() {
		//3.Open the excel file and insert the user name and password to the login page fields.
		ln.validateLogin();
		
	}
	
	@Test(priority=1)
	public void verifyPageTitle_Page_Home_and_got_help_desk_list_page() {
		
		Assert.assertEquals("SysAid Help Desk Software", ht.getHomePageTitle());	
		///step 4 Go to “service desk”
		ht.service_click();
		//step 6 Click on ”new.”
		ht.hrf_click();
		//step 5  Check you got the help desk list page
		Assert.assertEquals("Help Desk", he.getHelpDeskPageTitle());
	}
	
	@Test(priority=2)
	public void verifHelp_desk() {
		//get the number of row from the table before adding new data
		int before_add=he.get_number_of_rowes_befor_add_data();
		//6. Click on ”new.”
		he.new_button_click();
		//7. Enter only the following fields
		he.DropDounListMenue();
		//8. save the Incident
		he.save_record();
		//9. confirm that it save and check if you return to the list page
		Assert.assertEquals("List", he.cheak_if_retrun_to_list_page());
		//10. look fo the new line created by entering the EU to the advance search
		//11. Make sure that the incident found in the search otherwise end the test with failure
		int after_add=he.get_number_of_rowes_after_add_data();
		Assert.assertTrue( before_add < after_add);
		//12. Click on the check box on the left side of the record.
		he.click_on_checkBox();
		//he.testTable();
		//13. make sure that the background color changed to green 
		 if(he.background_color_changed()) { System.out.print( "THE backgroundColor IS GREEN" );}else { System.out.print( "THE backgroundColor IS  NOT GREEN" );};
		
		//14. Check if the font in the line is bold (don’t fail the test if it isn’t, only report if not).

		 if(he.Check_font_bold()){ System.out.print( "THE font is bold" );}else { System.out.print( "THE font is  not bold" );};
			
	}
	
	
	
	
	
	
	@AfterSuite
	public void tearDown() {
	  Driver.close();
	}
}
