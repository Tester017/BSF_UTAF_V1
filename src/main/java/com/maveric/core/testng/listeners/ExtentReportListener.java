package com.maveric.core.testng.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportListener extends TestListenerAdapter implements ISuiteListener,ITestListener, IInvokedMethodListener{
	
	static ExtentTest test;
	static ExtentReports report;
	static ExtentHtmlReporter bddreport;
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		if (method.isTestMethod()) {
			if(result.getStatus()==ITestResult.SUCCESS) {
				test.log(Status.PASS, "Test Case Passed");
			}
			if(result.getStatus()==ITestResult.FAILURE) {
				test.log(Status.FAIL, "Test Case Failed");
				
			}
		}	
				
	}
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		if (method.isTestMethod()) {
			test=report.createTest(method.getTestMethod().getMethodName());
			System.out.println("Added the Test");
			test.createNode(result.getTestName());
		}
		
		
		
		
	}
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ISuite arg0) {
		report.flush();
		
	}
	@Override
	public void onStart(ISuite arg0) {
		bddreport=new ExtentHtmlReporter("D:\\Test\\STMExtentReport.html");
		report = new ExtentReports();
		report.attachReporter(bddreport);
		//bddreport.loadXMLConfig("D:\\Test\\extent-config.xml"); 
		logger.info("reports folder created successfully :" + bddreport.getFilePath());
		
	}


}
