package com.internetapp.pages;

import org.openqa.selenium.By;

import com.maveric.core.common.functions.CommonDef;

public class OpenAirHome extends CommonDef{
	
	public OpenAirHome clickTimesheetsMenu() 
	{     
    	click(findElement(By.xpath("//div[@class='item-inner']//span[contains(text(),'Timesheets')]")));                 
        return this;
    }
	public OpenAirHome clickTimeSheetCategory(String dCategory) 
	{     
    	click(findElement(By.xpath("//ul[@class='nav-menu-list']//span[contains(text(),'Timesheets')]//..//following::span[contains(text(),'"+ dCategory +"')]")));                 
        return this;
    }
	public OpenAirHome clickCreateMenu() 
	{     
    	click(findElement(By.xpath("//span[contains(text(),'Create')]")));                 
        return this;
    }
	public OpenAirHome clickTimesheetNew() 
	{     
    	click(findElement(By.xpath("//span[contains(text(),'Timesheets: Timesheet, New')]")));                 
        return this;
    }
	public OpenAirHome clickTargetedTimesheet(String date) 
	{     
    	click(findElement(By.xpath("//div[text()='"+date+"']/..//following-sibling::td//a")));                 
        return this;
    }
	public OpenAirHome selectTimesheet(String dValueToSelect) 
	{     
		dropdown(findElement(By.xpath("//select[@name='range']")),dValueToSelect);                 
        return this;
    }
	public OpenAirHome clickSaveToCreate() 
	{     
		click(findElement(By.xpath("//input[@name='save']")));                 
        return this;
    }
	public OpenAirHome selectTSGrid(String dElement,String dValueToSelect) 
	{     
		dropdown(findElement(By.id(dElement)),dValueToSelect);
		return this;
    }
	public OpenAirHome inputHours(String dElement,String data) 
	{     
		clearAndType(findElement(By.id(dElement)),data);
		return this;
    }
	public OpenAirHome clickAddtlInfoLink(String dElement) 
	{     
		click(findElement(By.xpath("//input[@id='"+dElement+"']/following-sibling::a/i")));                 
        return this;
    }
	public OpenAirHome selectPremise(String dValueToSelect) 
	{     
		dropdown(findElement(By.id("//div[@class='dialogControls']/select")),dValueToSelect);
		return this;
    }
	public OpenAirHome clickAddtlInfoSubmit() 
	{     
		click(findElement(By.xpath("//button[text()='OK']")));                 
        return this;
    }
	public OpenAirHome clickSubmitTS() 
	{     
		click(findElement(By.xpath("//button[text()='Save & Submit']")));                 
        return this;
    }
}
