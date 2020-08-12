package com.maveric.core.testng;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.LinkedHashSet;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.codoid.products.exception.FilloException;
import com.maveric.core.driver.Driver;
import com.maveric.core.driver.DriverFactory;
import com.maveric.core.testng.listeners.DriverListener;
import com.maveric.core.utils.data.ExcelDataReader;
import com.maveric.core.utils.web.WebActions;

//@Listeners({DriverListener.class/*,ExtentITestListenerClassAdapter.class*/})
public class BaseTest extends WebActions{
	
    DriverFactory driverFactory=new DriverFactory();

	
	@DataProvider(name="inputs",parallel=true)
	public Object[][] getData() {
		return new Object[][] {
			{"TC001"},
			{"TC002"},/*,
			{"TC003"},
			{"TC004"},
			{"TC005"},
			{"TC006"},*/
		};
	}
	
	@DataProvider(name="ExcelInputs",parallel=true)
	public Object[] getExcelData() throws FilloException {
		return ExcelDataReader.getControlData("ControlSheet", "TC_ID");
	}
	
    /**	
	* Method Name			:	beforeSuite 
	* Use					:	Prints Suite name
	* Return Type			:	Has no Return Type
	* Designed By			:	Daniel
	* Date Last Modified	:	05-Aug-2020
	*/
	/*@BeforeSuite(alwaysRun = true)
	public static void beforeSuite(ITestContext ctx) {
		System.out.println("*******Start of BeforeSuite****suite**************");
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		System.out.println(suiteName);
		System.out.println("*******End of BeforeSuite****suite**************");

	}
    
	@AfterSuite(alwaysRun = true)
	public static void afterSuite(ITestContext ctx) {
		System.out.println("*******Start of AfterSuite****EndOfsuite**************");
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		System.out.println(suiteName);
		System.out.println("*******End of AfterSuite****EndOfsuite**************");

	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext ctx) {
		System.out.println("*******Start of @BeforeClass******************");
		String className = this.getClass().getSimpleName();
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		String testName = ctx.getCurrentXmlTest().getName();		
		System.out.println(suiteName+" - "+className+" - "+testName);
		System.out.println("*******End of @BeforeClass******************");
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass(ITestContext ctx) {
		System.out.println("*******Start of @AfterClass******************");
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		String className = this.getClass().getSimpleName();
		System.out.println(suiteName+" - "+className);
		System.out.println("*******End of @AfterClass******************");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Object[] testArgs, ITestContext ctx, Method method) {
		System.out.println("*******Start of @BeforeMethod******************");
		
		System.out.println("*******End of @BeforeMethod******************");
		
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context, Method method, ITestResult result) 
	{
		System.out.println("*******Start of @AfterMethod******************");
//		driver.close();
		System.out.println("*******End of @AfterMethod******************");		
	}*/

}
