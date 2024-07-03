Feature: Validate Stripe Create Customer API In this feature file, I want to validate the Stripe's Create Customer API
  Scenario: Validate Create Customer API with Valid Email and Description
    Given I have a JSON file "C:\Users\ANMPATEL\eclipse-workspace\CucumberApi\src\test\java\testdata\test.json"
    Given I read data from the JSON file
    And I set "name" as the name parameter from the JSON data
    And I set "job" as the job parameter from the JSON data
    When I send a POST request to "https://reqres.in/api/users"
    Then I should get 201 as the expected status code

  Scenario: Validate Create Customer API
    Given I have a XML file "C:/Users/ANMPATEL/eclipse-workspace/CucumberApi/src/test/java/testdata/test.json"
    When I send a POST request to XML "https://reqres.in/api/users"
