package stepDefination;
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
import static org.junit.Assert.*;  // with this import package you can use assert method 

import static io.restassured.RestAssured.*;    // this package is useful to import the .given() .when() .then() methods

import java.io.IOException;

public class stepDefination1 extends Utility { // Inheriting the utility class to access it's method examp 
																			// in given requestSpecification(), in when 
	
	RequestSpecification request1; 
	ResponseSpecification resp1;
	TestData td = new TestData();
	Response response;
	Response response1;
	Response response2;
	Response response3;
	Response response4;
	
	//Add Product
	
	@Given("the user is on Product endpoint with {int}, {string}, {string}, {string}, {double}, {double}")
	public void the_user_is_on_product_endpoint_with(Integer id, String title, String description, String category, Double price, Double rating) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	 
			request1 = given().spec(requestSpecification()).body(td.AddProducts(id, title, description, category, price, rating));	
		
	}

	

	@When("user select {string} resource with {string} http method")
	public void user_select_resource_with_http_method(String Resource1, String Method1)  // here Resources1- addProduct from feature file. and Method - POST
	 
	{
	    // Write code here that turns the phrase above into concrete actions
		
		
	        APIResource resources1 = APIResource.valueOf(Resource1);    // 
		  
	    	System.out.println(resources1.getResource());    // AddProducts resources will get here from feature file
	    	
	    	resp1  = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(201).build();
	       
	        
	        	 response = request1.when().post(resources1.getResource());
	  
	}	
	


@Then("user should receve valid repsone with {int} status code")
public void user_should_receve_valid_repsone_with_status_code(Integer statuscode) {
    // Write code here that turns the phrase above into concrete actions
    
         assertEquals(response.getStatusCode(), 201);
         System.out.println(response.getStatusCode());
    
    
}
	
	@Then("{string} should be {int}")
	public void should_be(String key, int value) {
	    // Write code here that turns the phrase above into concrete actions
	  
			assertEquals(getJsonPath(response, key), value);   // Calling getJsonPath method and adding key and value 
		
			System.out.println(response);
			System.out.println(key);
			System.out.println(value);
	}




	@Then("{string} should be {string}")
	public void should_be(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	
		assertEquals(getJsonPath(response, key),value);
		
		System.out.println(key);
		System.out.println(value);
	
	}
	
	
	// GetProducts 
	
	@Given("the user is on product endpoint")
	public void the_user_is_on_product_endpoint() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		request1 = given().spec(requestSpecification());
		
	   
	}
	
	@When("user select {string} with {string} http method")
	public void user_select_with_http_method(String resource2, String method2) {
	    // Write code here that turns the phrase above into concrete actions

		APIResource apro = APIResource.valueOf(resource2);
		
		System.out.println(apro.getResource());
		
		 response2 = request1.when().get(apro.getResource());
			
	}
	
	
	
	@Then("user recieve the response with {int} status code")
	public void user_recieve_the_response_with_status_code(Integer statuscod) {
	    // Write code here that turns the phrase above into concrete actions
	   
		assertEquals((response2.getStatusCode()), 200);
		
	}
	
	
	@Then("the {string} in respose should be {int}")
	public void the_in_respose_should_be(String key, int value) {
	    // Write code here that turns the phrase above into concrete actions
       
		
		String totalproduct = getJsonPath(response2, "total");    // here in the response it is returning integer which is 194
		
		System.out.println(key);   
		System.out.println(totalproduct);      // So by using json path retrieve 194 and save it in total product
		System.out.println(value); 
		assertEquals(getJsonPath(response2, key), value);   // then I compare it with JsonValue total = 194 ?
		
	}


	// GetSingleProducts
	
	@Given("the user is on single product endpoint with {string}")
	public void the_user_is_on_single_product_endpoint_with(String id) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		request1 = given().spec(requestSpecification());
		
	}

	
	@When("user select {string} with specific {string} by using {string} http method")
	public void user_select_with_specific_by_using_http_method(String resource, String id, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
        APIResource apires = APIResource.valueOf(resource);
		
		System.out.println(apires.getResource()+id);  
		
		response1 = request1.when().get(apires.getResource()+id);

	}
	

	@Then("user should recieve the response with {int} ok")
	public void user_should_recieve_the_response_with_ok(Integer statuscode) {
	    // Write code here that turns the phrase above into concrete actions
		
		   assertEquals(response1.getStatusCode(), 200);
	 
	}

	
	@Then("the {string} in respose should be {string}")
	public void the_in_respose_should_be(String key1, String value1) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(response1, key1), value1);  
		  
	}
	
	// Limit and Skip Product 
	
	
	@Given("the user can able to limit and skip product with query parameter")
	public void the_user_can_able_to_limit_and_skip_product_with_query_parameter() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		request1 = given().spec(requestSpecification());
		
	}

	@When("the user select {string} with {string} http method")
	public void the_user_select_with_http_method(String resource3, String method3) {
	    // Write code here that turns the phrase above into concrete actions
	    
		APIResource resourceapi = APIResource.valueOf(resource3);
		
		System.out.println(resourceapi.getResource());
		
		response3= request1.when().queryParam("limit", 5).queryParam("skip", 1).queryParam("select", "title","price","rating").get(resourceapi.getResource());
	
		
	}

	@Then("the user should recieve response with {int} ok status code")
	public void the_user_should_recieve_response_with_ok_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   
		   assertEquals(response3.getStatusCode(), 200);
		
		
	}
	
	
	// Delete Products 
	
@Given("the user can able to delete from server")
public void the_user_can_able_to_delete_from_server() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	request1  = given().spec(requestSpecification());
	
	
}

@When("User select {string} with {string} http request and with given {int}")
public void user_select_with_http_request_and_with_given(String deletePlace, String Delete, Integer userId) {
    // Write code here that turns the phrase above into concrete actions
   

	APIResource resource = APIResource.valueOf(deletePlace);
	
	System.out.println(resource.getResource());
	
	response4= request1.when().delete(resource.getResource()+userId);
	
	
}


	@Then("user data should be deleted from server with {int} ok status code")
	public void user_data_should_be_deleted_from_server_with_ok_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    
		 assertEquals(response4.getStatusCode(), 200);
		
	}

	@Then("In the response {string} should be {string}")
	public void in_the_response_should_be(String key4, String value4) {
	    // Write code here that turns the phrase above into concrete actions
	   
		assertEquals(getJsonPath(response4 , key4) , value4);
		
	}


	

}
