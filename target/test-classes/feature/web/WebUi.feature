@WebUiTests
Feature: Takealot website Feature

  Background: 
    Given when user launches the browser
    And user loads the website

  Scenario Outline: Verify user registration to Takealot website
    When user clicks on the Register link
    And user fills in the register form "<fname>", "<lname>", "<email>", "<password>" and "<teleNo>"
    Then verify user is registered to the site "<message>"
    Then close the browser

    Examples: 
      | fname | lname | email      | password | teleNo      | message                             |
      | john  | grant | john.grant | test123  | 07000012345 | Welcome to the TAKEALOT.com family! |
      
  Scenario Outline: Filter search and add specific watch to the basket
    When user searches for the product "<product>"
    And filter by specific brand "<brand>" and colour
    And add specific watch "<model>" to the basket
    Then verify whether the product added correctly to the basket "<findModel>"
    Then close the browser

    Examples: 
      | product | brand   | model                                 | findModel                               | 
      | watches | Garmin  | garmin-forerunner-35-smartwatch-black | Garmin Forerunner 35 Smartwatch - Black |
      
   Scenario Outline: Global search and add specific watch to the basket
    When user searches for the product "<findModel>"
    And add specific watch "<addModel>" to the basket
    Then verify whether the product added correctly to the basket "<findModel>"
    Then close the browser

    Examples: 
      | findModel                                             | addModel                                           |
      | Garmin QuickFit 22mm Silicone Watch Band - Flame Red  | garmin-quickfit-22mm-silicone-watch-band-flame-red |