@ApiTests
Feature: Open weather website Feature

  Scenario Outline: Validate weather api call to current location by city name or city id or zip code
    When i hit the api end point with parameter type "<type>" and value "<value>"
    Then verify the response is successful "<statuscode>", "<country>" and "<name>"
    
    Examples:
    	|type |value     | statuscode | country | name      		|
    	|q    |London    | 200        | GB      | london    		|
    	|q    |Hyderabad | 200        | IN      | hyderabad 		|    
    	|id   |2172797   | 200        | AU      | cairns    		|
    	|zip  |94040     | 200        | US      | mountain view |
    	
   Scenario Outline: Validate weather api call to current location by geographic coordinates
    When i hit the api end point with parameter latitude "<lat>" and longitude "<lon>"
    Then verify the response is successful "<statuscode>", "<country>" and "<name>"
    
    Examples:
    	|lat 	|lon  | statuscode | country | name      |
    	|35   |139  | 200        | JP      | shuzenji  |