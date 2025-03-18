Feature: API Client - Health Check

  Scenario: Validate API health check functionality
    Given User opens the app before health check
    And User configures the app before health check
    When User sends request to check API health
    Then User should see API health message after health check
#    And App is closed after health check