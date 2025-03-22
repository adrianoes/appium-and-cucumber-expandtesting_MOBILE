Feature: Notes Management

  @notes
  Scenario: Note is created
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes @negative
  Scenario: Note creation attempt with invalid category
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note with invalid category
    Then User should see invalid category message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes @negative
  Scenario: Note creation attempt with invalid token
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note with invalid token
    Then User should see invalid token message response
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes
  Scenario: Notes info are retrieved
    Given User opens the app before note creation
#    And User configures the app before note creation
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

  @notes @negative
  Scenario: Notes info are attempted to be retrieved with invalid token
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create notes
    And User sends request to get notes info with invalid token
    Then User should see invalid token message response
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes
  Scenario: Note info is retrieved
    Given User opens the app before note creation
#    And User configures the app before note creation
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

  @notes @negative
  Scenario: Note info attempt to be retrieved with invalid id
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to get note info with invalid note id
    Then User should see invalid note id message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note

  @notes @negative
  Scenario: Note info attempt to be retrieved with invalid token
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to get note info with invalid token
    Then User should see invalid token message response
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes
  Scenario: Note is updated
    Given User opens the app before note creation
#    And User configures the app before note creation
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

  @notes @negative
  Scenario: Note is attempted to be updated with invalid category
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to update note with invalid category
    Then User should see invalid category message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes @negative
  Scenario: Note is attempted to be updated with invalid token
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to update note with invalid token
    Then User should see invalid token message response
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes
  Scenario: Note status is updated
    Given User opens the app before note creation
#    And User configures the app before note creation
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

  @notes @negative
  Scenario: Note status is attempted to be updated with invalid status
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to update note status with invalid status
    Then User should see note invalid status message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes @negative
  Scenario: Note status is attempted to be updated with invalid token
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to update note status with invalid token
    Then User should see invalid token message response
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes
  Scenario: Note is deleted
    Given User opens the app before note creation
#    And User configures the app before note creation
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

  @notes @negative
  Scenario: Note is attempted to be deleted with invalid note id
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to delete note with invalid note id
    Then User should see invalid note id message
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation

  @notes @negative
  Scenario: Note is attempted to be deleted with invalid token
    Given User opens the app before note creation
#    And User configures the app before note creation
    When User sends request to create user before note creation
    Then User should see created user message before note creation
    When User sends request to log in user before note creation
    Then User should see logged in user message before note creation
    When User sends request to create a note
    Then User should see created note message
    When User sends request to delete note with invalid token
    Then User should see invalid token message response
    When User sends request to delete user after note creation
    Then User should see deleted user message after note creation
#    And App is closed after note creation


