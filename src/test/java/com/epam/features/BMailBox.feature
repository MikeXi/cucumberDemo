Feature: Gmail's MailBox Management

  Background: : Login Gmail with email address
    Given I open Gmail page in browser
    When I input Email address and Password
    Then I can see Gmail is logged in with my account

  @Smoke
  Scenario Outline: Create Draft Email
    Given I am in mailbox page
    When I click Add Email button
    Then I can see the new Email popup dialog
    When I input email <To> and <Subject> and <Body> in the new Email popup dialog
    And I close the new Eail popup dialog
    Then I can see the Draft email is created
    Examples:
      |To              |Subject                      |Body                      |
      |mike_xi@epam.com|Automation test email Subject|Automation test email Body|

  Scenario Outline: Check the Draft Email information
    Given I open the Draft Email with <Subject>
    Then I can see the Email <To> and <Subject> and <Body> are correct
    Examples:
      |To              |Subject                      |Body                      |
      |mike_xi@epam.com|Automation test email Subject|Automation test email Body|

  @Smoke
  Scenario Outline: Send the created Draft Email
    Given I open the Draft Email with <Subject>
    When I click the Send button
    Then I can see the Email with <Subject> in the Sent tab
    Examples:
      |Subject|
      |Automation test email Subject|

  Scenario Outline: Search email in Sent tab
    Given I am in the Sent tab
    When I input Email <Subject> in Search Text box and Press ENTER
    Then I can see the search result Email with <Subject> in the Sent tab
    Examples:
      |Subject|
      |Automation test email Subject|

  Scenario Outline: Drag Sent Email to Starred tab
    Given I am in the Sent tab
    When I drag the email with <Subject> and drop on Starred tab
    Then I can see the sent Email with <Subject> in Starred tab
    Examples:
      |Subject|
      |Automation test email Subject|

  Scenario Outline: Delete Email in Starred tab
    Given I am in the Starred tab
    When I delete email with <Subject> in Context menu
    Then I can see the Email with <Subject> disappears in Starred tab
    Examples:
      |Subject|
      |Automation test email Subject|