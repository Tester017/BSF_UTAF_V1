package com.internetapp.pages;

import static com.maveric.core.utils.reporter.Report.log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.maveric.core.common.functions.CommonDef;

public class OutlookLogin extends CommonDef{
		
	public OutlookLogin outlookNavigate(String url,String driverType) 
	{      
    	startApp(url, driverType);                 
        return this;
    }
	public OutlookLogin enterUserName(String dUserName) 
	{     
    	clearAndType(findElement(By.name("loginfmt")), dUserName);                 
        return this;
    }
	public OutlookLogin enterPassword(String dPassword) 
	{      
    	clearAndType(findElement(By.name("passwd")), dPassword);                 
        return this;
    }
	public OutlookLogin clickNext() 
	{      
    	click(findElement(By.id("idSIButton9")));                 
        return this;
    }
	public OutlookHome clickStaySignNo() 
	{      
    	click(findElement(By.id("idBtn_Back")));                 
        return new OutlookHome();
    }


}
