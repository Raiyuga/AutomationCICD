package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";	

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		// Logging in E-commerce website
		ProductCatalogue productcatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// Adding product to the cart
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		// Proceeding to checkout
		CartPage cartPage = productcatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		// Giving payment details for order confirmation
			
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	//To verify ZARA COAT 3 is displaying in orders page
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		// Logging in E-commerce website
		ProductCatalogue productcatalogue = landingPage.loginApplication("Rishabh123@gmail.com", "Test@123");
		OrderPage orderspage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object [] [] {{data.get(0)}, {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object [] [] {{"Rishabh123@gmail.com","Test@123","ZARA COAT 3"}, {"Rishabh@gmail.com","Test@123", "ADIDAS ORIGINAL"}};
//	}
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email","Rishabh123@gmail.com");
//	map.put("password","Test@123");
//	map.put("product","ZARA COAT 3");
	
//	HashMap<String,String> nap = new HashMap<String,String>();
//	map.put("email","Rishabh@gmail.com");
//	map.put("password","Test@123");
//	map.put("product","ADIDAS ORIGINAL");
}
