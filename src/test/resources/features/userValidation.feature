Feature: Validating User Authentication

@UserValidation
Scenario Outline: Verify if User is able to login to Account successfully

				Given the user is having valid Credentials with "<username>" and "<password>" and 30
				When User "LoginUser" and sent request with "POST" http call
				Then User able to login successfully into account with status code 200
				And get "id" in response get is "1"
				And get "firstName" in response get is "Emily"
				And verify to authenticate current "CurrentUser" with access_token. 
				And then the response should match "jsonSchema.json" schema
				

Examples:
 
 				 | username | password | expiresInMins
    
 				  | emilys      | emilyspass  | 30
 	
		  


