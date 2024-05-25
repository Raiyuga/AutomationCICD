
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @tag1
  Scenario: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When Add product <productName> to the cart
    And Checkout <procutName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name                 | password | productName |
      | Rishabh123@gmail.com | Test@123 | ZARA COAT 3 |
  
