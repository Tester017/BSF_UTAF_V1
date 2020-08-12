package com.api.stepDefs;

import java.util.Map;

import com.internetapp.pages.DBHomePage;
import com.internetapp.pages.DBLoginPage;
import com.maveric.core.utils.data.ExcelDataReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java8.En;


public class DemoBankStepDefs implements En {

    
	DBLoginPage login = new DBLoginPage();
	DBHomePage hPage;
	String scn_name;
	
	@io.cucumber.java.Before
	public void before(Scenario scenario) {
	     //this.sce = scenario
		 this.scn_name=scenario.getName().split("_")[0];
	}

    public DemoBankStepDefs() {
    	
    	

        Given("Login to {string}", (String url) -> {
            login.navigate(url);

        });
		
		  Then("Enter the username {string} and password {string}", (String username,
		  String password) -> { 
			  
			// hPage=login.login(username, password);
		  
		  
		  });
		  
		  Then("Enter the User Credentials", (DataTable userCred) -> { 
				
			  Map<String,String> test=userCred.asMap(String.class, String.class);
			  //ExcelDataReader.readExcelData("TC_ID","TC001","UserName","jayaramanbala86@gmail.com");
			  //hPage=login.login(ExcelDataReader.getDatafromExcel(test, "UserName"),ExcelDataReader.getDatafromExcel(test, "Password"));
				  
				  
				  });
		  
		  
		  
		  
		  
		  Then("Check the balance of {string} Savings Account", (String accHolderName) -> { 
					  
					 //hPage.checkSavingsBalance(accHolderName);
				  
				  
				  });
		      
      	
		  When("Transfer {string} from {string} Acc to {string} Acc", (String amount,String fromAcc,String toAcc) -> { 
			  
				 hPage.transferAmount(amount,fromAcc,toAcc);
			  
			  
			  });
		  
		  Then("Verify the amount {string} got credited", (String amount) -> { 
			  
				 hPage.verifyCreditTransactionSuccess(amount);
			  
			  
			  });
		  
		 Then("Verify the amount {string} got {string} in {string} Account", (String amount,String transType,String accHolder) -> { 
			  
				 //hPage.verifyTransactionSuccess(amount,accHolder,transType);
			  
			  
			  });

    }

}
