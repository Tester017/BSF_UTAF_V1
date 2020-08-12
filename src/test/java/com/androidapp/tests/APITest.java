package com.androidapp.tests;

import com.androidapp.pages.LoginPage;
import com.androidapp.pages.ProductPage;
import com.maveric.core.driver.Driver;
import com.maveric.core.testng.BaseTest;
import com.maveric.core.utils.reporter.Report;

import io.appium.java_client.AppiumDriver;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class APITest{

    private String user_name = "standard_user";
    private String password = "secret_sauce";

    @Test
    public void loginWithValidCredentials() {
    	Report.log("this is a ger user test");
        int statusCode;
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET);
        statusCode = response.getStatusCode();
        //Assert.assertEquals(statusCode, 200);
        System.out.println(response.getBody().jsonPath().get("data.find{it.id=='1'}.employee_salary").toString());
    }

}
