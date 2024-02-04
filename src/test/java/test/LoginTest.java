package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;
import util.ExcelReader;

public class LoginTest {

	WebDriver driver;

	ExcelReader exlRead = new ExcelReader("src\\main\\java\\testData\\TF_TestData.xlsx");

	String userName = exlRead.getCellData("LoginInfo", "UserName", 2);
	String password = exlRead.getCellData("LoginInfo", "Password", 2);
	String dashBoardvalText = exlRead.getCellData("DashboardInfo", "DashboardHeader", 2);
	String userNameErrorMsg = exlRead.getCellData("LoginInfo", "alertUserValidationText", 2);
	String passwordErrorMsg = exlRead.getCellData("LoginInfo", "alertPasswordValidationText", 2);

	@Test(priority = 1)
	public void validUserShouldBeAbleToLogin() {

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickSigninButton();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(dashBoardvalText);
		BrowserFactory.tearDown();
	}
	
	@Test(priority = 2)
	public void validateLoginPageErrorMsgs() {
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.validateUserNameErrorMsg(userNameErrorMsg);
		loginPage.validatePasswordErrorMsg(userName, passwordErrorMsg);
		BrowserFactory.tearDown();
	}

}
