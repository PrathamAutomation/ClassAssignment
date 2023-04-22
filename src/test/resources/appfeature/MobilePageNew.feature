Feature: Mobile page feature

Scenario: SortBy functionality
Given user is on Home Page
When user clicks on "Mobile"
And user selects SortBy dropdown as "Name"
Then validate if products are sorted by Name

Scenario: Price check
Given user is on Home Page
When user clicks on "Mobile"
When user notes the value of sony xperia mobile
And user clicks on Xperia mobile 
And user note the value of mobile on details page 
Then validate if both prices are same


Scenario: Cart functionality
Given user is on Home Page
When user clicks on "Mobile"
When user adds sony xperia mobile to cart
And user chnages the qty to "1000"
Then validate the error message as error msg click Empty cart
|* The maximum quantity allowed for purchase is 500.|
And Validate "SHOPPING CART IS EMPTY" msg



Scenario: Compare functionality check
Given user is on Home Page
When user clicks on "Mobile"
And user clicks on add to compare option for Sony xperia and IPhone
And user clicks on compare button
Then Validate products are reflects in pop-up close the pop-up