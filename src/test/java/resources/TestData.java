package resources;

import POJOClasses.LoginUser;
import POJOClasses.addProducts;

public class TestData {
	
     public LoginUser Logindetails (String username, String password, int expiresInMins) // created method name Logindetails and returns to LoginUser
     																													//instance using credentials
     {
    	 
    	LoginUser lu= new LoginUser();  // creating the object of POJO class 
    	lu.setUsername(username);       // setting username and password
    	lu.setPassword(password);
    	lu.setExpiresInMins(expiresInMins);     // setting 30 min expire date
    	 
    	return lu;              
    	
     }
     
     public addProducts AddProducts (int id , String Title, String Desc, String Category , Double price , Double rating)
     
     {
    	 addProducts ad= new addProducts();       // creating the object of  add products POJO class 
    	 ad.setId(id);
    	 ad.setTitle(Title);
    	 ad.setDescription(Desc);
    	 ad.setCategory(Category);
    	 ad.setPrice(price);
    	 ad.setRating(rating);
    	 
    	 return ad;
    	 
    	 
     }
     
     
     
     
     
	
	
	
	
	
	

}
