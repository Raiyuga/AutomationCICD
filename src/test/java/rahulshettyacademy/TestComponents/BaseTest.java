package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobject.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	/* In this class we are writing some generic methods, which will make our main Test class look clean */
	
	public WebDriver intializeDriver() throws IOException {
		
		Properties prop = new Properties(); //Creating object of properties class (pre-defined java class) to use pre-defined properties methods
		
//		FileInputStream object helps to convert given argument into input stream type argument
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		
//		.load method takes "InpurStream" type argument, so we are converting our properties file path to "InputStream", via FileInputStream method
		prop.load(fis); //This load method will help us to access all the values written under properties file
		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")) { 
			ChromeOptions options = new ChromeOptions(); //Creating object of "ChromeOptions" class to access its methods
//			Giving option to run our tests in chrome headless mode (meaning execution of tests will happen without opening chrome, happens invisibly)
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			 driver = new ChromeDriver(options); 
			 driver.manage().window().setSize(new Dimension(1440,900)); //Helps to run in fullscreen
		}else {
			//Firefox
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
		driver.manage().window().maximize();
		return driver;
	}
	
	//Below method is taking data from a JSON file and converting them it to string, then sending it via HashMap List
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//reading json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
//		Convert string to HashMap via Jackson Databind dependency (without this dependency it's impossible to convert)
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	//Below method takes screenshot whenever the test fails and adds it to the HTMl report
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;	
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver = intializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
