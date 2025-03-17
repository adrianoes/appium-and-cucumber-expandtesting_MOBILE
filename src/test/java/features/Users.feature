Feature: User Account Management

  Scenario: User creates an account successfully
    Given User opens the app
    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to login user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
