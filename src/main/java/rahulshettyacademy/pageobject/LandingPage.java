package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {  //Constructor will always be executed first if any other class is calling it by creating object of this class
		super(driver);
		this.driver = driver; //this.driver refers to the current class instance variable (which is in this case is "WebDriver driver")
		PageFactory.initElements(driver, this); //this line initializes driver method in our pageFactory page objects
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
//	PageFactory - It's an another way of writing page objects, as shown below
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
//	driver.findElement(By.id("userPassword"))
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
		
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		//Instead of creating objects for each class in main class, we are creating the object to the methods, where we are sure that it will be called
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);   
		return productcatalogue;

	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
