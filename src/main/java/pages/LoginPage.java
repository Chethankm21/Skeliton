package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(name="userName")
	WebElement usernameTextfield;
	
	@FindBy(name="passWord")
	WebElement passwordTextfield;
	
	@FindBy(xpath="//button[text()='Sign In']")
	WebElement signinButton;
	
	public void doLogin(String username , String password) {
		usernameTextfield.sendKeys(username);
		passwordTextfield.sendKeys(password);
		signinButton.click();
	}
}
