Feature:   Ecommerce Products Verfication

@AddProducts @Regression

Scenario Outline: Adding products into Cart for Verification

							Given the user is on Product endpoint with <id>, "<title>", "<description>", "<category>", <price>, <rating>
							When user select "AddProducts" resource with "POST" http method
							Then user should receve valid repsone with 201 status code
							And "id" should be "<id>"
							And "title" should be "BMW Pencil"
							
							
			Examples:
			
                  | id      |         title        | description                          |  category         |price | rating |  
                         
                 |  195  | BMW Pencil  | The BMW Pencil Canister | beauStationary | 14.99 | 5.64 |
                 
@GetProducts
Scenario:  Getting all the Products into cart for verification 

					Given the user is on product endpoint 
					When user select "GetAllProduct" with "GET" http method
					Then user recieve the response with 200 status code
					And the "total" in respose should be 199
					
					
@GetSingleProduct					
Scenario Outline:  Getting Single Product into cart for verification 

					Given the user is on single product endpoint with "<id>"
					When user select "GetSingleProduct" with specific "<id>" by using "GET" http method
					Then user should recieve the response with 200 ok
					And the "title" in respose should be "Watch Gold for Women"
					
Examples:

				 | id      |    
                         
                 |  193  |	

@LimitSkipProduct
Scenario: Verify that limit and skips the product with given values in parameters 

		         Given the user can able to limit and skip product with query parameter
		         When the user select "LimitSkipProduct" with "GET" http method
		         Then the user should recieve response with 200 ok status code


@DeleteProducts

Scenario Outline: Verify that after selecting Delete product api the product should be deleted from server

				Given the user can able to delete from server
				When User select "DeleteProduct" with "DELETE" http request and with given <id>
				Then user data should be deleted from server with 200 ok status code
				And In the response "isDeleted" should be "true"
	
Examples:
					
				| id  |
				
				|194|









             	 					 
