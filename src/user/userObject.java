package user;

public class userObject {
private String username;
private String password;
	
	public userObject(String x, String y) {
	setUsername(x);
	setPassword(y);
		}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public boolean setUsername(String x){
		if(x != ""){
			username = x;
			return true;
		} else {
			return false;
		}
	}
	public boolean setPassword(String x){
		if(x != ""){
			password = x;
			return true;
		} else {
			return false;
		}
	}

}
