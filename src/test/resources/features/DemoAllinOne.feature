@web
Feature: Demo Bank Feature using UTAF Report

  Scenario Outline: <TC_ID>_<TC_Description>
   # Given Access the URL in the "desktop-web"
   # And Enter the User Credentials and Signin as Maveric Systems
   # Then Check the balance of Source Checking Account
	 # When Perform Transfer between two Savings Account
	  Given Access the URL in the "mobile-web" 
	  When Enter the User Credentials and Signin as JohnSmith
	  Then Verify the transaction status in JohnSmith Account
		
	Examples:
	|TC_ID|TC_Description|
	|TC001|Transfer the Salary amount to the Employee Account|
	|TC002|Transfer the Salary amount to the Employee Account|

