

package com.maveric.core.cucumber;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.maveric.core.testng.BaseTest;
import com.maveric.core.testng.listeners.DriverListener;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.Pickle;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(plugin = {
        "pretty",
        "json:target/cucumber.json"
}, extraGlue = "com.maveric.core.cucumber"
)
public class CucumberBaseTest extends BaseTest implements ITest {

    private static final Logger logger = LogManager.getLogger();

    private TestNGCucumberRunner testNGCucumberRunner;
    private final ThreadLocal<String> scenarioName = new ThreadLocal<>();
	

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        DriverListener.isCucumber = true;		
    }

    @BeforeMethod(alwaysRun = true)
    public void setTestName(Method method, Object[] testData) {
        Pickle pickle = ((PickleWrapper) testData[0]).getPickle();
        String scenarioName = pickle.getName();
        this.scenarioName.set(scenarioName);
        logger.info("START {}", scenarioName);

    }

    @Test(dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper fw) throws Throwable {

        testNGCucumberRunner.runScenario(pickle.getPickle());


    }

    @DataProvider()
    public Object[][] scenarios() {

        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        logResult(result);
    }

    private void logResult(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                logger.error("FAIL  {}", scenarioName.get());
                break;
            case ITestResult.SKIP:
                logger.warn("SKIP  {}", scenarioName.get());
                // scenarioName.set(scenarioName+"_Retry_"+RetryAnalyzer.getRetryCount());
                break;
            case ITestResult.SUCCESS:
                logger.info("PASS  {}", scenarioName.get());
                break;
            default:
                logger.warn("Unexpected result status: {}", result.getStatus());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
        
        
    }


    @Override
    public String getTestName() {
        return scenarioName.get();
    }
}



