Feature: User Account Management

  Scenario: User creates an account successfully
    Given User opens the app
    And User configures the app
    When User creates a new account with name "John Doe", email "john.doe@example.com" and password "securePass123"
    Then User should see a success message "User account created successfully"
