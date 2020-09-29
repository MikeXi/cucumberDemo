Feature: Login Gmail

  @Smoke
  Scenario: Login Gmail with email address
    Given I open Gmail page in browser
    When I input Email address and Password
    Then I can see Gmail is logged in with my account