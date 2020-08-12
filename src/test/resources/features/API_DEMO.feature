@api
Feature: API demo Feature

  @positive
  Scenario: Get and Update User Details
    Given I get User Details
    Then I create user

  @negative
  Scenario: Create and Delete User
    Then I update User Details
    And I Delete User
