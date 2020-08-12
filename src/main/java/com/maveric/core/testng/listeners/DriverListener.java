package com.maveric.core.testng.listeners;

import static com.maveric.core.utils.GenericUtils.getTimeStamp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import com.maveric.core.config.ConfigProperties;
import com.maveric.core.cucumber.CucumberListener;
import com.maveric.core.cucumber.reporter.CucumberReporter;
import com.maveric.core.driver.BrowserStackClient;
import com.maveric.core.driver.Driver;
import com.maveric.core.driver.DriverFactory;
import com.maveric.core.driver.SauceClient;
import com.maveric.core.testng.reporter.CustomReporter;
import com.maveric.core.utils.reporter.Report;
import com.maveric.core.utils.web.WebActions;

import io.restassured.RestAssured;

public class DriverListener implements ISuiteListener, ITestListener, IInvokedMethodListener, IAnnotationTransformer {
	private static final Logger logger = LogManager.getLogger();
	public static String reportFolder;
	public static String screenshotFolder;
	public String includedGroups;
	public String excludedGroups;
	public static boolean isCucumber = false;
//    private DriverFactory driverFactory;
	public static ThreadLocal<String> tests = new ThreadLocal<>();
	public static ThreadLocal<String> testCase = new ThreadLocal<>();

	@Override
	public void onStart(ISuite suite) {
		reportFolder = "./reports/test-output-" + getTimeStamp();
		File reportsFolder = new File(reportFolder);
		File screenShotsFolder = new File(reportsFolder + "/screenshots");
		reportsFolder.mkdirs();
		logger.info("reports folder created successfully :" + reportFolder);
		screenShotsFolder.mkdirs();
		screenshotFolder = reportsFolder + "/screenshots";
		CustomReporter.createCustomReport(reportFolder + "/CustomReport.html");
		try {
			FileUtils.copyFile(new File("./lib/MavericLogo.png"), new File(screenshotFolder + "/MavericLogo.png"));
			FileUtils.copyFile(new File("./lib/Background.png"), new File(screenshotFolder + "/Background.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		setThreadCount(suite);
		RestAssured.filters(new RestLogFilter());

		includedGroups = StringUtils.join(suite.getXmlSuite().getIncludedGroups(), ",");
		excludedGroups = StringUtils.join(suite.getXmlSuite().getExcludedGroups(), ",");

		// DriverFactory.downloadDriver();
		// driverFactory = new DriverFactory();

		BrowserStackClient.startBrowserStackLocal();
		BrowserStackClient.uploadFile();
		SauceClient.uploadFile();
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("Daniel in beforeInvocation");

		if (method.isTestMethod() && ConfigProperties.BROWSER_INITIATE.getBoolean()) {
			tests.set(method.getTestMethod().getMethodName());
			// driverFactory.driverSetup();
			logger.info("Driver Setup Completed");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		System.out.println("Daniel in afterInvocation");
		if (method.isTestMethod() && ConfigProperties.BROWSER_INITIATE.getBoolean()) {
			if ((!result.isSuccess()) && CucumberListener.getScenario() == null) {
				takeFailureScreenshot();
			}
			
			Driver.quitDriver();
			logger.info("Driver Quit Completed");
		}

		if (method.isTestMethod())
			if (!isCucumber) {
				String testExecTime = convert(result.getEndMillis() - result.getStartMillis());
				if (!result.isSuccess()) {
					StringWriter sw = new StringWriter();
					result.getThrowable().printStackTrace(new PrintWriter(sw));
					String exceptionAsString = sw.toString();
					Report.log(exceptionAsString);
				}
				String logs = String.join("<br>", Reporter.getOutput(result));
//				String testName = result.getMethod().getMethodName();
				String testName = testCase.get();
				if (result.isSuccess()) {
					CustomReporter.appendPass(logs, testName, testExecTime);
				} else {
					int currentReteryCount = RetryAnalyzer.getCount();
					System.out.println("Test status ->> " + result.getStatus());
					if ((result.getStatus() != TestStatus.SKIP.getCode())) {
						testName = testName + "_Retry_" + currentReteryCount;
					}
					CustomReporter.appendFail(logs, testName, testExecTime);
				}
			}

	}

	@Override
	public void onFinish(ISuite suite) {

		BrowserStackClient.stopBrowserStackLocal();
		BrowserStackClient.deleteFiles();
	}

	private void takeFailureScreenshot() {
		if (!Driver.isWebDriverEmpty() || !Driver.isAppiumDriverEmpty()) {
			WebActions actions = new WebActions();
			actions.logScreenshot("Failure Screenshot");
		}
	}

	@Override
	public void onTestStart(ITestResult result) {

		IRetryAnalyzer curRetryAnalyzer = getRetryAnalyzer(result);
		if (curRetryAnalyzer == null) {
			result.getMethod().setRetryAnalyzer(curRetryAnalyzer);
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {

		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext ctx) {

		long start = ctx.getStartDate().getTime();
		long end = ctx.getEndDate().getTime();
		String executionTime = convert(end - start);
		String endDate = ctx.getEndDate().toString();
		if (!isCucumber) {
			int passedCount = ctx.getPassedTests().size();
			int failedCount = ctx.getFailedTests().size();
			CustomReporter.updateValues(passedCount, failedCount, endDate, executionTime,
					includedGroups + "\n" + excludedGroups);
		} else {

			CucumberReporter.generateCucumberReport();
			CustomReporter.updateValues(CucumberReporter.passedFeatures,
					CucumberReporter.totalFeatures - CucumberReporter.passedFeatures, endDate, executionTime,
					CucumberReporter.featureTags);

		}

	}

	public String convert(long miliSeconds) {
		int hrs = (int) TimeUnit.MILLISECONDS.toHours(miliSeconds) % 24;
		int min = (int) TimeUnit.MILLISECONDS.toMinutes(miliSeconds) % 60;
		int sec = (int) TimeUnit.MILLISECONDS.toSeconds(miliSeconds) % 60;
		return String.format("%02d:%02d:%02d", hrs, min, sec);
	}

	public void setThreadCount(ISuite suite) {
		if (ConfigProperties.THREAD_COUNT.getInt() >= 1) {
			int count = ConfigProperties.THREAD_COUNT.getInt();
			suite.getXmlSuite().setThreadCount(count);
			logger.info("Thread Count : " + count);
		}

		if (ConfigProperties.DATAPROVIDER_COUNT.getInt() >= 1) {
			int count = ConfigProperties.DATAPROVIDER_COUNT.getInt();
			suite.getXmlSuite().setThreadCount(ConfigProperties.DATAPROVIDER_COUNT.getInt());
			logger.info("Data Provider Count : " + count);
		}

		if (suite.getXmlSuite().getParallel() == XmlSuite.ParallelMode.NONE) {
			suite.getXmlSuite().setParallel(XmlSuite.ParallelMode.METHODS);
			logger.info("Parallel Type : Methods");
		}

	}

	private RetryAnalyzer getRetryAnalyzer(ITestResult result) {
		RetryAnalyzer retryAnalyzer = null;
		IRetryAnalyzer iRetry = result.getMethod().getRetryAnalyzer();
		if (iRetry instanceof RetryAnalyzer) {
			retryAnalyzer = (RetryAnalyzer) iRetry;
		}
		return retryAnalyzer;
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer analyzer = annotation.getRetryAnalyzer();
		if (analyzer == null) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
	}

	enum TestStatus {
		PASS(1), FAIL(2), SKIP(3);
		private int statusCode;

		private TestStatus(int code) {
			this.statusCode = code;
		}

		public int getCode() {
			return this.statusCode;
		}
	}

}
