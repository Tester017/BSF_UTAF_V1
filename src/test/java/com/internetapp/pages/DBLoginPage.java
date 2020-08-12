package com.internetapp.pages;

import com.maveric.core.common.functions.CommonDef;
import de.retest.recheck.RecheckImpl;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.maveric.core.utils.reporter.Report.log;


public class DBLoginPage extends CommonDef{

@FindBy(name="username") 
private WebElement txt_username;
// private final By txt_username = By.name("username");
    private final By txt_password = By.name("password");
    private final By txt_pin = By.name("accpin");
//    private final By btn_submit = By.id("submit");
    @FindBy(id="submit") private WebElement btn_submit;

    public RecheckImpl recheckin;
    
    AppiumDriver<?> mobdriver;
    
   
    public DBLoginPage() 
    {    	
    	//driver=Driver.getWebDriver();
    	//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);       
    }

    public DBLoginPage navigate(String url) {
        driver.navigate().to(url);

        logScreenshot("login");        ;
        log("sample log");
        logScreenshot("login successful");
        return this;

    }
    
    public DBLoginPage navigateUpdated(String url,String driverType) 
    {    	
    	startApp(url, driverType);                 
        return this;
    }

    public DBHomePage login(String userName,String password) 
    {    	

    	clearAndType(txt_username, userName);
//    	clearAndType(txt_password, password);
    	click(btn_submit);
        logScreenshot("login successful");
        
        return new DBHomePage(this.driver);

    }
    
        
}
