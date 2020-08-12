@web
Feature: Demo Bank Feature

  Background: login
    Given Login to "http://dbankdemo.com/home"

 
  Scenario: Transfer the amount between the Savings Accounts
    Given Login using the User credentials
		|TC_ID|TC001|
    Then Check the balance of "Account-1" Savings Account
	When Transfer "2.00" from "Account-1" Acc to "Account-2" Acc


 Scenario: Verify the transaction status in the Savings Accounts
    Given Login using the User credentials
		|TC_ID|TC001|
	#Then Check the balance of "Account-1" Savings Account	
    Then Verify the amount "2.00" got "debited" in "Account-1" Account
	And Verify the amount "2.00" got "credited" in "Account-2" Account