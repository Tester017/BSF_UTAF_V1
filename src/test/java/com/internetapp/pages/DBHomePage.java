package com.internetapp.pages;

import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.maveric.core.utils.web.WebActions;

public class DBHomePage extends WebActions {

    private final By txt_pagetitle = By.xpath("//div[@class='page-title']//li");
    private final By txt_password = By.name("password");
    private final By txt_pin = By.name("accpin");
   
    private final By link_savings = By.id("savings-menu");
    private final By link_savingsacc_view = By.xpath("//a[contains(@href,'savings-view')]");
    private final By link_checking = By.xpath("//a[text()='Checking']//i");
    private final By link_checkingacc_view = By.xpath("//a[contains(@href,'checking-view')]");
    private final By acc1_balance=By.xpath("//form[contains(@action,'savings-view')]//div[text()='Daisy']//following-sibling::div[last()]");
    
    private final By link_Transfer = By.xpath("//a[text()='Transfer']");
    private final By link_btw_accounts = By.xpath("//a[contains(@href,'xfer-between')]");
    private final By txt_trxferamount = By.name("amount");
    private final By select_fromAcc = By.name("fromAccount");
    private final By select_toAcc = By.name("toAccount");
    
    private final By button_Submit =  By.xpath("//button[@type='submit']");
    
    WebDriverWait wait;
    float balance;

    public DBHomePage(WebDriver driver) {
     this.driver=driver;  
     wait=new WebDriverWait(driver,20);
    }

    public DBHomePage checkSavingsBalance(String accHolderName,String salary) {
    	
    	System.out.println("$$$$$ Entered CheckSavingsBalance $$$$$");
        
    	driver.findElement(link_checking).click();
    	wait.until(ExpectedConditions.elementToBeClickable(link_checkingacc_view)).click();
    	System.out.println("-----------"+wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[contains(@action,'checking-view')]//div[text()='"+accHolderName+"']//following-sibling::div[last()]"))).getText());
    	System.out.println("Salary:"+salary);
    	balance=Float.valueOf(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[contains(@action,'checking-view')]//div[text()='"+accHolderName+"']//following-sibling::div[last()]"))).getText().split("\\$")[1]);
    	System.out.println("Balance:>>>"+balance+"Salary:>>>"+salary);
    	if(balance>Float.parseFloat(salary))
    	{
    		logScreenshot("\n Enough Balance to proceed with the transaction"+"\n<<<<<<Balance:"+balance+"\t<<<<<<Amt to transfer:"+salary);
    	}else {
    		logScreenshot("\nNot Enough Balance to proceed with the transaction"+"\n<<<<<<Balance:"+balance+"\t<<<<<<Amt to transfer:"+salary);
    	}
    	
    	return this;
    }

    public void transferAmount(String fromAcc,String toAcc,String amount) {
        
    	driver.findElement(link_Transfer).click();
    	wait.until(ExpectedConditions.elementToBeClickable(link_btw_accounts)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(txt_trxferamount));
    	
      	Select option=new Select(driver.findElement(select_fromAcc));
    	option.selectByValue(option.getOptions().stream().filter(x->x.getText().contains(fromAcc)).findAny().get().getAttribute("value"));
    	
    	
    	Select option2=new Select(driver.findElement(select_toAcc));
    	option2.selectByValue(option.getOptions().stream().filter(x->x.getText().contains(toAcc)).findAny().get().getAttribute("value"));
    	
    	
    	driver.findElement(txt_trxferamount).sendKeys(amount);
    	driver.findElement(txt_trxferamount).sendKeys(Keys.ENTER);
    	
    	//driver.findElement(button_Submit).click();
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("transactionTable")));
    	logScreenshot("Transaction completed");
    	driver.quit();
    }
    
    public void verifyCreditTransactionSuccess(String amount) {
        
    	if(driver.findElement(By.xpath("//tr[1]//td[text()='Income']//following-sibling::td[text()='$"+amount+"']")).isDisplayed()) {
    		logScreenshot("Transaction Success");
    	}else {
    		logScreenshot("Transaction Failed");
    	}
    }
  
    public void verifyTransactionSuccess(String amount,String accHolderName,String desAccNo,String transType) {
    	
    	DecimalFormat df = new DecimalFormat("0.00");
    	df.setMaximumFractionDigits(2);
    	float updamount=Float.parseFloat(amount);
 	   	amount=df.format(updamount);
 	   	if(!driver.findElement(link_savings).isDisplayed()) {
 	   		System.out.println("Execution in Mobile Mode");
 	   		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggler')]")).sendKeys("");
 	   	driver.findElement(By.xpath("//button[contains(@class,'navbar-toggler')]")).click();
 	   	}
 	   	//if(!driver.findElement(By.xpath("//div[text()='"+accHolderName+"']/preceding-sibling::label//span[@class='switch-label']")).isDisplayed()) {
 	    driver.findElement(link_checking).click();
   		wait.until(ExpectedConditions.elementToBeClickable(link_checkingacc_view)).click();
 	   	//}
    	driver.findElement(By.xpath("//div[text()='"+accHolderName+"']/preceding-sibling::label//span[@class='switch-label']")).click();
    	
    	if(transType.equalsIgnoreCase("debited")) {
    		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(desAccNo);
		    	if(driver.findElement(By.xpath("//tr[1]//td[text()='Misc']//following-sibling::td[text()='$-"+amount+"']")).isDisplayed()) {
		    		logScreenshot("Debit Transaction Success");
		    	}else {
		    		logScreenshot("Debit Transaction Failed");
		    	}
    	}
    	
    	if(transType.equalsIgnoreCase("credited")) {
    		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(desAccNo);
	        	if(driver.findElement(By.xpath("//tr[1]//td[text()='Income']//following-sibling::td[text()='$"+amount+"']")).isDisplayed()) {
	        		logScreenshot("Credit Transaction Success");
	        	}else {
	        		logScreenshot("Credit Transaction Failed");
	        	}
	        	driver.close();    	
    	}
    	
    	
    }
    
        
}
