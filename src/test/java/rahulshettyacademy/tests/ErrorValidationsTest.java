package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"Error Handling"},retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		// Logging in E-commerce website with wrong credentials
		landingPage.loginApplication("Wrongemail@gmail.com", "wrongPassword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		// Logging in E-commerce website
		ProductCatalogue productcatalogue = landingPage.loginApplication("Rishabh@gmail.com", "Test@123");
		// Adding product to the cart
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		// Proceeding to checkout
		CartPage cartPage = productcatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
