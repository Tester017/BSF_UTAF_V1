package com.androidapp.tests;

import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.web.selenium.By;

public class RecheckTest{

	private WebDriver driver;
	private Recheck re;
    
    @BeforeTest
    public void setup() {
    	
    	System.setProperty("webdriver.chrome.driver", "D:\\Test\\chromedriver.exe");
    	re = new RecheckImpl();
        //System.setProperty("webdriver.chrome.driver", "C:\\pathto\\chromedriver.exe");
        driver = new ChromeDriver();   	
    }

    @Test
    public void loginWithValidCredentials() throws MalformedURLException {
    	
    	re.startTest();
        driver.get("http://google.com");
        re.check(driver, "open");
        re.capTest();
    	
    	
    	
    }
    
    @AfterTest
    public void tearDown() {
    	
    	driver.quit();
        re.cap();

    	
    }

}
