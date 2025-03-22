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

  Scenario: User creates account with invalid email
    Given User opens the app
#    And User configures the app
    When User sends request to create user with invalid email
    Then User should see invalid email message
#    And App is closed

  Scenario: User creates, logs with invalid email, logs right and delete account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request with invalid email to log in user
    Then User should see invalid email message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User creates, logs with invalid password, logs right and delete account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request with invalid password to log in user
    Then User should see login with invalid password message
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

  Scenario: User try to get user account info with invalid token
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to get user info with invalid token
    Then User should see invalid token message
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

  Scenario: User updates account info with invalid username
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates user info with invalid username
    Then User should see invalid username message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed

  Scenario: User updates account info with invalid token
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to updates user info with invalid token
    Then User should see invalid token message
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

  Scenario: User try to update password with invalid token
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to update password with invalid token
    Then User should see invalid token message
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

  Scenario: User creates, logs in, logs out with invalid token and deletes an account
    Given User opens the app
#    And User configures the app
    When User sends request to create user
    Then User should see created user message
    When User sends request to log in user
    Then User should see logged in user message
    When User sends request to log out user with invalid token
    Then User should see invalid token message
    When User sends request to delete user
    Then User should see deleted user message
#    And App is closed