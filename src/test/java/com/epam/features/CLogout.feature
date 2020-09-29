Feature: Logout Gmail

  @Smoke
  Scenario: Logout Gmail
    Given I am logged in Gmail
    When I click Logout button
    Then I am logged out