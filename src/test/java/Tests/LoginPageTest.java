package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.LandingPage;
import Pages.LoginPage;

public class LoginPageTest extends BasePage{
	LoginPage  loginPage;
	LandingPage landingPage;
	
	@BeforeMethod
	public void setup() {
		init();
		loginPage = new LoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verifyLogin() {
		landingPage = loginPage.LoginToAppl("Admin", "admin123");
		String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(landingPage.getUrl(), expUrl ,"Landing Page Title is Not Match With" + expUrl);
	}
	
	@Test(dataProvider="Login Credentials")
	public void verifyInvalidCredentials(String un,String pwd) {
		
		loginPage.enterUserName(un);
		loginPage.enterPassword(pwd);
		loginPage.clickOnLoginBtn();
		
		if(un.isBlank() || pwd.isBlank())
			Assert.assertEquals("Required123",loginPage.getErrorMsg(),"Error Msg for Blank Username/Password.....");
		else
			Assert.assertEquals("Invalid credentials",loginPage.getErrorMsgForInvalidCredentials(),"Error Msg for Wrong Username/Password....");
	}
	
	@DataProvider(name = "Login Credentials")
	public Object[][] getData(){
		
		Object[][] data = {
				{" ","admin123"},
				{"Admin"," "},
				{"admin123","Admin"}
		};
		return data;
	}
}

