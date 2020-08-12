package com;


import org.testng.annotations.AfterClass;
import com.maveric.core.cucumber.CucumberBaseTest;
import com.internetapp.pages.DBLoginPage;

import de.retest.recheck.RecheckImpl;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"./src/test/resources/features/DemoAllinOne.feature"},
        glue ={"com.internetapp.stepDefs"}
        //plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter", "json:target/cucumber-report.json"}
        //monochrome=true
)
public class CucumberRunner extends CucumberBaseTest{

	  
	 @AfterClass
	 public static void writeExtentReport() {
	 //Reporter.loadXMLConfig("D:\\Test\\extent-config.xml");
		 System.out.println("&&&&& complete");
		 DBLoginPage objDBLoginPage = new DBLoginPage();
		 
		 objDBLoginPage.recheckin.cap();
		// objDBLoginPage.cap();
		 
	 }
	
}
