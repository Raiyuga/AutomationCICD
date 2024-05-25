package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

//	This class is specifically made to check, that the failed test cases are actually failing or not, it will check that by retrying the failed test case
	int count = 0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry) {
			
			count++;
			return true;
		}
		return false;
	}
	
}
