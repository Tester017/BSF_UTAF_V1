package com.maveric.core.utils.web;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.maveric.core.driver.Driver;
import com.maveric.core.testng.listeners.DriverListener;
import com.maveric.core.utils.reporter.Report;



public class WebActions {
    private final static AtomicInteger counter = new AtomicInteger();
    private final static Logger logger = LogManager.getLogger();
    public WebDriver driver;
    protected WebDriverWait wait;
    public String test= "Daniel from WebActions";

    public void logScreenshot(String name) {
        String path = captureScreenshot();
        String html = "<a target=_blank href=" + "screenshots/"+DriverListener.testCase.get() + path.replaceAll(" ", "%20") + ">" + name + "  </a>";
        Report.log(html);
    }

	
	 public WebActions() { if (!Driver.isWebDriverEmpty()) { driver =
	 Driver.getWebDriver(); } else if (!Driver.isAppiumDriverEmpty()) { driver =
	 Driver.getAppiumDriver(); } }
	 

    private String captureScreenshot() {
        String screenshotName = null;
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            screenshotName = "/screenshot" + "_" + getId() + ".png";
            File tcFolder = new File(DriverListener.screenshotFolder+"/"+DriverListener.testCase.get());
            String screenshotPath = tcFolder + screenshotName;
            System.out.println(screenshotPath);
            FileUtils.copyFile(file, new File(screenshotPath));

        } catch (Exception e) {
            logger.error("unable to capture screenshot" + e);
        }
        return screenshotName;
    }

    private int getId() {
        return counter.incrementAndGet();
    }

}
