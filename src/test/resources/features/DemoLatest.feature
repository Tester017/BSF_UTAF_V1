@web
Feature: Demo Bank Feature using UTAF Report

  Background: login
    Given Access "http://dbankdemo.com/home"

  Scenario Outline: <TC_ID>_<TC_Description>
    And Enter the User Credentials and Signin
    Then Check the balance of Source Savings Account
	When Perform Transfer between two Savings Account
	
	Examples:
	|TC_ID|TC_Description|
	|TC001|Transfer the Salary amount to the Employee Account|
	#|TC002|Transfer the Salary amount to the Employee Account|
	#|TC003|Transfer the Salary amount to the Employee Account|
	
	
  #Scenario Outline: <TC_ID>_<TC_Description>
    #Given Enter the User Credentials and Signin
	#Then Verify the transaction status in Source Account
	#And Verify the transaction status in Destination Account
	
	#Examples:
	#|TC_ID|TC_Description|
	#|TC001|Transfer the amount between the Savings Accounts|
	#|TC002|Transfer the Salary amount to the Employee Account|
	#|TC003|Transfer the Salary amount to the Employee Account|