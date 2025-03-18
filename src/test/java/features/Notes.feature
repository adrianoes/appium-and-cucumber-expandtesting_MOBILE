Feature: Notes Management

  Scenario: Note is created
    Given User opens the app before note creation
    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  Scenario: Notes info are retrieved
    Given User opens the app before note creation
    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create notes
    And User sends request to get notes info
    Then User should see notes info message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  Scenario: Note info is retrieved
    Given User opens the app before note creation
    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to get note info
    Then User should see note info message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  Scenario: Note is updated
    Given User opens the app before note creation
    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to update note
    Then User should see note updated message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  Scenario: Note status is updated
    Given User opens the app before note creation
    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to update note status
    Then User should see note status updated message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  Scenario: Note is deleted
    Given User opens the app before note creation
    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to delete note
    Then User should see note deleted message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

