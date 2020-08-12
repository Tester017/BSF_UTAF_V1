package com.internetapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.maveric.core.common.functions.CommonDef;

public class OutlookHome extends CommonDef{
	
	//@FindBy(id="O365_MainLink_NavMenu") private WebElement eMenu;
	//@FindBy(xpath="//span[text()='OpenAir (PSA) ']") private WebElement eMenuOpenAir;
	
	public OutlookHome clickMenu() 
	{      
    	click(By.id("O365_MainLink_NavMenu"), "Outlook Menu");                 
        return this;
    }
	public OpenAirHome clickMenuOpenAir() 
	{      
    	click(By.xpath("//span[text()='OpenAir (PSA) ']"), "Open Air Menu");                 
        return new OpenAirHome();
    }


}
