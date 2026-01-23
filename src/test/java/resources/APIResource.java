package resources;

//Why it is needed - Keeps endpoints consistent and easy to update.
//Prevents typos and makes refactoring safer.
//Improves readability (logical name → path).

public enum APIResource {
	
	
	LoginUser("auth/login"),    // created a method with name and passed string in it 
	CurrentUser("auth/me"),
	RefreshToken("auth/refresh"),
	GetAllProduct("/products"),
	AddProducts("/products/add"),
	GetSingleProduct("products/"),
	LimitSkipProduct("/products"),
	SearchProduct("products/search"),
	UpdateProduct("products"),
	DeleteProduct("products/");
	
	
	private String resource;          // creating a private variable name resource and declaring globally
	
	APIResource(String resource)    // creating constructor and creating local variable name "resource"
	
	{
		this.resource=resource;          // using this keyword assigning local variable to global variable
		
		
	}
	
	public String getResource()        // creating a method call getResource which is returning the string
	
	{
		return resource;  		// Returning global resource
		
	}
	
	

}
