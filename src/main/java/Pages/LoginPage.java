package Pages;

import java.time.Duration;

import Utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	By txtusername = By.name("username");
	By txtpassword = By.name("password");
	By btnlogin = By.cssSelector("button[type='submit']");
	By txtError = By.xpath("(//div[@class='oxd-form-row']//span)[1]");
	By txtInvalidError = By.xpath("(//div[@class='orangehrm-login-form']//p)[1]");

	public void enterUserName(String un) {
		Utilities.enterText(txtusername, un);
	}

	public void enterPassword(String pwd) {
		Utilities.enterText(txtpassword, pwd);
	}

	public void clickOnLoginBtn() {
		Utilities.clickOnElement(btnlogin);
	}

	public String getErrorMsg() {
		WebDriverWait wait = new WebDriverWait(driver,
				Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		return wait.until(ExpectedConditions.presenceOfElementLocated(txtError)).getText();
	}

	public String getErrorMsgForInvalidCredentials() {
		WebDriverWait wait = new WebDriverWait(driver,
				Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		return wait.until(ExpectedConditions.presenceOfElementLocated(txtInvalidError)).getText();
	}

	public LandingPage LoginToAppl(String un, String pwd) {
		enterUserName(un);
		enterPassword(pwd);
		clickOnLoginBtn();
		return new LandingPage();
	}
}
