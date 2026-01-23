package stepDefination;

import static org.junit.Assert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestData;
import resources.Utility;

import static io.restassured.RestAssured.*;

import java.io.IOException;


public class stepDefination extends Utility {
	
	
	TestData td= new TestData();
	
	RequestSpecification request;
	
	ResponseSpecification respec;
	
	Response response;
	
	
	
	@Given("the user is having valid Credentials with {string} and {string} and {int}")
	public void the_user_is_having_valid_credentials_with_and_and(String username, String password, int exptime) throws IOException {
	  
		
		//Write code here where given(pre-requisite)condition such as body and URL, header or query parameter
		
	     request =  given().spec(requestSpecification()).body(td.Logindetails(username, password, exptime));  // requestSpecification() method is called from utility files - which contains url, header ,query parameter 
	     																																												// body is from login details class
	

	}

	@When("User {string} and sent request with {string} http call")
	
	public void user_and_sent_request_with_http_call(String resources, String Method) {
	    
	   
	
		APIResource  AR = APIResource.valueOf(resources);  // resources is argument pass in apiRespouces
		
	    System.out.println(AR.getResource());
	
	    respec = new ResponseSpecBuilder().expectStatusCode(200)
			   .expectContentType(ContentType.JSON).build();
	    
	
	           if(Method.equalsIgnoreCase("POST"))
	        	   
	           {
	        	   
	        	   response = request.when().post(AR.getResource());
	        	   
	           }else if(Method.equalsIgnoreCase("GET"))
	           
	           {
	        	   
	        	             response = request.when().get(AR.getResource());
	        	   
	           }
	
		
	}


	@Then("User able to login successfully into account with status code {int}")
	public void user_able_to_login_successfully_into_account_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
				
		assertEquals(response.getStatusCode(), 200);
	   
	    
	}

	
	@Then("get {string} in response get is {int}")
	
	public void get_in_response_get_is(String key, Integer value) {
	    // Write code here that turns the phrase above into concrete actions
		
		 assertEquals(getJsonPath(response , key), value);
		
	  System.out.println(key);
	  System.out.println(value);
		 
	}

	
	

	@Then("get {string} in response get is {string}")
	
	public void get_in_response_get_is(String key1, String value1) {
	    // Write code here that turns the phrase above into concrete actions
	
		assertEquals(getJsonPath(response, key1), value1);
		System.out.println(key1);
		  System.out.println(value1);
		
	}
	
	
		@Then("verify to authenticate current {string} with access_token.")
		
		public void verify_to_authenticate_current_with_access_token(String current_user) throws IOException {
		    // Write code here that turns the phrase above into concrete actions
			
			String access_token= getJsonPath(response, "accessToken");
			
			System.out.println(access_token);
			
			request = given().spec(requestSpecification()).header("Authorization", access_token);
			
			user_and_sent_request_with_http_call(current_user, "GET");
			
			String UserFirstName= getJsonPath(response, "firstName");
			
			System.out.println(UserFirstName);
			
			
		}

		
		@Then("then the response should match {string} schema")
		public void then_the_response_should_match_schema(String file) {
		    // Write code here that turns the phrase above into concrete actions
		  
			response.then().assertThat()
            .body(matchesJsonSchemaInClasspath(file));
			
		}
		
		
		
		
		
		
	    
	}

	
	


