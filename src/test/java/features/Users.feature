Feature: User Account Management

  Scenario: User creates, logs in and deletes an account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User creates account with wrong email
    Given User opens the app
#    And User configures the app
    When User sends request to create user with wrong email
    Then User should see wrong email message
#    And App is closed

  Scenario: User creates, logs with wrong email, logs right and delete account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request with wrong email to log in user
    Then User should see wrong email message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User creates, logs with wrong password, logs right and delete account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request with wrong password to log in user
    Then User should see login with wrong password message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User gets user account info
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to get user info
    Then User should see user info
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User try to get user account info with wrong token
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to get user info with wrong token
    Then User should see wrong token message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates account info
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates user info
    Then User should see updated user info
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates account info with wrong username
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates user info with wrong username
    Then User should see wrong username message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates account info with wrong token
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates user info with wrong token
    Then User should see wrong token message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates password
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates password
    Then User password should be updated
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User try to update a short password
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to update a short password
    Then User should receive short password message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User try to update password with wrong token
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to update password with wrong token
    Then User should see wrong token message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User creates, logs in, logs out, logs in and deletes an account
    Given User opens the app
#    And User configures the app
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

  Scenario: User creates, logs in, logs out with wrong token and deletes an account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to log out user with wrong token
    Then User should see wrong token message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed