Feature: LogIn Test

To test login funcationality
user should enter userName and Password and perfrom the test related to logIn to OrangeHRM Application

Scenario Outline: Validating LogIn functionality of OrangeHRM Application

Given Open Chrome Browser and Navigate to OrangeHRM Application
When User enters "<UserName>" and "<Password>" and Click on logIn button
Then User should be able to successfully logIn to the OrangeHRM Application and close the Application

Examples:
| UserName   |  Password    |
| admin      |  password    |
| hello      |  testing     |  