package com.internetapp.stepDefs;

import java.util.Map;

import com.api.service.Reqres;
import com.internetapp.pages.DBHomePage;
import com.internetapp.pages.DBLoginPage;
import com.maveric.core.utils.data.ExcelDataReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java8.En;


public class DemoLatestStepDefs implements En {

    
	DBLoginPage login = new DBLoginPage();
	Reqres restapi=new Reqres();
	DBHomePage hPage;
	String scn_name;
	
	@io.cucumber.java.Before
	public void before(Scenario scenario) {
	     //this.sce = scenario
		 this.scn_name=scenario.getName().split("_")[0];
	     System.out.println(">>>>>>-------"+scenario.getName());
	     System.out.println(">>>>>>-------"+scenario.getId());
	}

    public DemoLatestStepDefs() {
    	
    	  Given("Access {string}", (String url) -> {
    			login.navigate(url);

    	  });
    	  
    	  Given("Access the URL in the {string}", (String driverType) -> {
  			login.navigateUpdated(ExcelDataReader.getData("Sheet1", scn_name,"AUTURL"),driverType);

    	  });
		  
		  Then("Enter the User Credentials and Signin as Maveric Systems", () -> { 
				
		     hPage=login.login(ExcelDataReader.getData("Sheet1", scn_name,"SrcUserName"),ExcelDataReader.getData("Sheet1", scn_name,"SrcPassword"));
		  });
		  
		  Then("Enter the User Credentials and Signin as JohnSmith", () -> { 
				
			     hPage=login.login(ExcelDataReader.getData("Sheet1", scn_name,"DstUserName"),ExcelDataReader.getData("Sheet1", scn_name,"DstPassword"));
			  });
		  
		  
		 Then("Check the balance of Source Checking Account", () -> { 
			  
				 hPage.checkSavingsBalance(ExcelDataReader.getData("Sheet1", scn_name,"SourceAcc"),restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", scn_name,"EmployeeId")));
			  
			  
		  });
		  
		  When("Perform Transfer between two Savings Account", () -> { 
			  
				 hPage.transferAmount(ExcelDataReader.getData("Sheet1", scn_name,"SourceAcc"),
						 ExcelDataReader.getData("Sheet1", scn_name,"DestinationAcc"),
						 restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", scn_name,"EmployeeId")));
			  
			  
	      });
		  
		   
		 Then("Verify the transaction status in Source Account", () -> { 
			  
			 //restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", scn_name,"EmployeeId"))
				 hPage.verifyTransactionSuccess(restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", scn_name,"EmployeeId")),
						 ExcelDataReader.getData("Sheet1", scn_name,"SourceAcc"),ExcelDataReader.getData("Sheet1", scn_name,"destAccNo"),
						 "Debited");
			  
			  
			  });
		 
		 Then("Verify the transaction status in JohnSmith Account", () -> { 
			 //restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", scn_name,"EmployeeId")) 
			 hPage.verifyTransactionSuccess(restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", scn_name,"EmployeeId")),
					 ExcelDataReader.getData("Sheet1", scn_name,"DestinationAcc"),ExcelDataReader.getData("Sheet1", scn_name,"SourceAccNo"),
					 "Credited");
		  
		  
		  });

    }

}
