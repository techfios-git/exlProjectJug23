package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import page.NewCustomerPage;
import util.BrowserFactory;
import util.ExcelReader;

public class NewCustomerTest {
	
	WebDriver driver;
	
	ExcelReader exlRead = new ExcelReader("src\\main\\java\\testData\\TF_TestData.xlsx");

	String userName = exlRead.getCellData("LoginInfo", "UserName", 2);
	String password = exlRead.getCellData("LoginInfo", "Password", 2);
	String dashBoardvalText = exlRead.getCellData("DashboardInfo", "DashboardHeader", 2);
	String newCustomerValidationText = exlRead.getCellData("AddContactInfo", "AddContactValidationText", 2);
	String fullName = exlRead.getCellData("AddContactInfo", "FullName", 2);
	String company = exlRead.getCellData("AddContactInfo", "CompanyName", 2);
	String email = exlRead.getCellData("AddContactInfo", "Email", 2);
	String phone = exlRead.getCellData("AddContactInfo", "Phone", 2);
	String address = exlRead.getCellData("AddContactInfo", "Address", 2);
	String city = exlRead.getCellData("AddContactInfo", "City", 2);
	String country = exlRead.getCellData("AddContactInfo", "Country", 2);
	String state = exlRead.getCellData("AddContactInfo", "State", 2);
	String zip = exlRead.getCellData("AddContactInfo", "Zip", 2);
	
	@Test
	public void userShouldBeAbleToAddNewCustomer() {
		
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(dashBoardvalText);
		dashboardPage.clickOnCustomer();
		dashboardPage.clickOnAddCustomer();
		
		NewCustomerPage newCustomerPage = PageFactory.initElements(driver, NewCustomerPage.class);
		newCustomerPage.validatenNewCustomerPage(newCustomerValidationText);
		newCustomerPage.insertFullName(fullName);
		newCustomerPage.selectCompany(company);
		newCustomerPage.insertEmail(email);
		newCustomerPage.insertPhone(phone);
		newCustomerPage.insertAddress(address);
		newCustomerPage.insertCity(city);
		newCustomerPage.insertZip(zip);
		newCustomerPage.selectCountry(country);
		newCustomerPage.clickSaveButton();
		
		newCustomerPage.validateInsertedNameAndDelete();
		
		
	}

}
