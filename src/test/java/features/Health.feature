Feature: API Client - Health Check

  Scenario: Validate API health check functionality
    Given User opens the app
    And User configures the app
    When User sends request to check API health
    Then User should see API health message
    And App is closed