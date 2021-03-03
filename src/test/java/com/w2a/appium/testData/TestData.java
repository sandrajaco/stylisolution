package com.w2a.appium.testData;

import org.testng.annotations.DataProvider;

public class TestData {

		@DataProvider(name="InputData")
		public Object[][] getDataforLogin()
		{
			//2 sets of credentials
			Object[][] obj=new Object[][]
					{
				
				{"standard_user","secret_sauce"}
				
				/*other  credentials can be added like this									
				,{"problem_user","secret_sauce"}*/
					};
					
					return obj;
					
		}
		
		@DataProvider(name="TestData")
		public Object[][] getDataforInvalidLogin()
		{
			//2 sets of credentials
			Object[][] obj=new Object[][]
					{
				
				{"ft","ft"}
				
				
					};
					
					return obj;
					
		}

	}


