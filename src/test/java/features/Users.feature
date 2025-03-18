Feature: User Account Management

  Scenario: User creates, logs in and deletes an account
    Given User opens the app
    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User gets user account info
    Given User opens the app
    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to get user info
    Then User should see user info
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates account info
    Given User opens the app
    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates user info
    Then User should see updated user info
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates password
    Given User opens the app
    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates password
    Then User password should be updated
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User creates, logs in, logs out, logs in and deletes an account
    Given User opens the app
    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to log out user
    Then User should see logged out user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed