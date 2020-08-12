package com.internetapp.tests;

import com.codoid.products.exception.FilloException;
import com.internetapp.pages.LoginPage;
import com.maveric.core.testng.BaseTest;
import com.maveric.core.utils.data.ExcelDataReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginWithExcel extends BaseTest {

    @Test(groups = {"web"})
    public void verifyLoginWithInvalidCredentials() throws FilloException {
    	
    	  new LoginPage()
                .navigate(ExcelDataReader.getData("DanielSheet", "DanielTC001", "url"))
                .login(ExcelDataReader.getData("DanielSheet", "DanielTC001", "userName"), 
                		ExcelDataReader.getData("DanielSheet", "DanielTC001", "password"))
                .assertMessage(ExcelDataReader.getData("DanielSheet", "DanielTC001", "expectedMessage"));
        //Assert.fail();
    }


    @Test(groups = {"web"})
    public void verifyLoginWithValidCredentials() throws FilloException {
        new LoginPage()
                .navigate(ExcelDataReader.getData("DanielSheet", "DanielTC002", "url"))
                .login(ExcelDataReader.getData("DanielSheet", "DanielTC002", "userName"), 
                		ExcelDataReader.getData("DanielSheet", "DanielTC002", "password"))
                .assertMessage(ExcelDataReader.getData("DanielSheet", "DanielTC002", "expectedMessage"))
                .logout();

    }
}

