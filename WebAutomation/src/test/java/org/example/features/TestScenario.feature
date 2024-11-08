Feature:

  Scenario: open the Application
    Given I open the browser
    Then I verify the menu Section
      | Home            |
      | Products        |
      | Cart            |
      | Signup / Login  |
      | Test Cases      |
      | API Testing     |
      | Video Tutorials |
      | Contact us      |
    Then I verify there are exactly 3 slides in the carousel
    Then I verify there are exactly 3 category sections displayed
    Then I verify there are exactly 8 brand logos displayed in the Brands section
    When I scroll down to the footer
    Then I verify the footer contains a subscription email textbox
    And I verify the footer contains a submit button for subscribing
