package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.http.entity.ContentType;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Utility {
	
	// Here we are setting up when reqbody is null or empty then in logging.txt file log with PrintStream method
   //  With RequestSpecBuilder we are setting up baseURI and adding filter when logging response  and setting content type.
  //    This all are common things (blue print -URL, Header, Logging) which is setting every time when new test is needed.
	// The below code is call Singleton Pattern implementation.
	
	public static RequestSpecification reqbody;  // why static- because of class variable , stays in memory for entire duration of test execution
																				 // RequestSpecification is interface that store all the details lie BaseURL , Query Parameter
	public RequestSpecification requestSpecification() throws IOException
	{
		if(reqbody == null) // First time the reqbody will be null, then afterward it returns that already exists.
											// this we are preventing everytime creating new logging1.txt file
		{
			PrintStream log = new PrintStream(new FileOutputStream("loggingAPI.txt"));
			
		  
			reqbody = new RequestSpecBuilder()
			.setBaseUri(getGlobalValue("baseUrI"))
			.addFilter(RequestLoggingFilter.logRequestTo(log))  // write or log what is send to server to logging1.txt
			.addFilter(ResponseLoggingFilter.logResponseTo(log)) // write or log what is received from server into logging1.txt
			.setContentType(io.restassured.http.ContentType.JSON).build();// build - means convert all that setting into Single RequestSpecification object.
				
			return reqbody;
			
		}
		return reqbody;
		
	
}
	public String getGlobalValue(String Keyi) throws IOException
	
	{
		Properties pros = new Properties();   // Created the object of properties class to access the load and getProperties method
																		// as it is sub class of HashTable to store key value pair , we are taking here is key is baseURI and value is https://dummyjson.com
		
		FileInputStream fis = new FileInputStream("/Users/saurabhbansod/Desktop/Asus Computer/Eclipse Workspace 2020-09/ProductsClass/ProductUserAPI/src/test/java/resources/global.properties");
		
		pros.load(fis);
		
		return pros.getProperty(Keyi);
		
		
	}
	// This below code is for capturing the response and parse that json with JsonPath 
	// capturing Response and Key 
	//The Response object is the container that holds everything the server sends back after you make an API call.
	
	public String getJsonPath(Response response, String key)
	
	{
		String resp = response.asString();  //What it does: The raw Response object is complex. This line extracts just the body of the message and converts it into a plain text String.// Here we are changing response Object(Envelope) to response String.

																	//Why? You can't directly "search" a Response object for a specific field; you first need to turn it into a format that a parser can read.
		
		
		JsonPath js = new JsonPath(resp);  // Parsing or extracting the JSON response in Rest Assured
	  
		
		
		
		return	 js.getString(key);  //  What it does: It looks for the specific key (path) you provided and returns the value found there as a String.

									//Why? This is the final result. If your JSON had { "status": "Success" } and your key was "status", this line would return "Success".
	    
		
		
	}
	
	
	
	
   
	
	
}
