package POJOClasses;

public class LoginUser {
	
	private String username;
	private String password;
	private int expiresInMins;


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getExpiresInMins() {
		return expiresInMins;
	}
	public void setExpiresInMins(int expiresInMins) {
		this.expiresInMins = expiresInMins;
	}
	
}
