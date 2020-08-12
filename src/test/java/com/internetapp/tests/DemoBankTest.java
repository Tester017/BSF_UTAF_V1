package com.internetapp.tests;

import com.api.service.Reqres;
import com.codoid.products.exception.FilloException;
import com.internetapp.pages.DBLoginPage;
import com.maveric.core.testng.BaseTest;
import com.maveric.core.testng.listeners.DriverListener;
import com.maveric.core.utils.data.ExcelDataReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoBankTest extends BaseTest {
	
	Reqres restapi=new Reqres();
	
	@Test(groups = {"web"},dataProvider="ExcelInputs")
    public void verifyLoginWithInvalidCredentials(String tcId) throws FilloException, InterruptedException {
		
		DriverListener.testCase.set(tcId);
		new DBLoginPage()
       .navigateUpdated("http://dbankdemo.com/login", "desktop-web")
       .login(ExcelDataReader.getData("Sheet1", tcId,"SrcUserName"),
    		   ExcelDataReader.getData("Sheet1", tcId,"SrcPassword"))
       .checkSavingsBalance(ExcelDataReader.getData("Sheet1", tcId,"SourceAcc"),
    		   restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", tcId,"EmployeeId")))
       .transferAmount(ExcelDataReader.getData("Sheet1", tcId,"SourceAcc"),
				 ExcelDataReader.getData("Sheet1", tcId,"DestinationAcc"),
				 restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", tcId,"EmployeeId")));
    }


}

