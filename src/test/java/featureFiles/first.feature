Feature: Validate Stripe Create Customer API
  In this feature files, I want to validate the Stripe's Create CUstomer API
Scenario: Validate Create Customer API with Valid Email and Description
  Given I hit the url of get users of endpoints
  And I set "Anmol" as the name in parameter
  And I set "sdet" as the job in the parameter
  When I send a Post request to "https://reqres.in/api/users"
  Then I should get 201 as the expectedStatusCode

  Scenario Outline: Validate Create Customer API with Valid Email and Description
    Given I hit the url of get users of endpoints
    And I set "<name>" as the name in parameter
    And I set "<job>" as the job in the parameter
    When I send a Post request to "<url>"
    Then I should get <code> as the expectedStatusCode
Examples:
    |name|job|url|code|
    |anmol|sdet|https://reqres.in/api/users|201|

  Scenario: Get single employee details
    Given the endpoint "https://reqres.in/api/users/2"
    When a GET request is made
    Then the status code should be 200
    And the response content type should be "application/json"
    Then I should get "Janet" as the first name


  Scenario: Validate Create Customer API with Valid Email and Description
    Given I hit the url of get users of endpoints through CSV file
    And I set "Anmol" as the name in parameter through CSV file
    And I set "sdet" as the job in the parameter through CSV file
    When I send a Post request to "https://reqres.in/api/users" through CSV file
    Then I should get 201 as the expectedStatusCode


