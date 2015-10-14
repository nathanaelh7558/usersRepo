package user;

public class userObject {
	private String username;
	private String password;
	private Boolean locked;

	public userObject(String x, String y, Boolean z) {
		if(setUsername(x) && setPassword(y) && setLocked(z)){	
		}else {
			username = null;
			password= null;
			locked = false;
		}
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public Boolean getLocked() {
		return locked;
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
	public boolean setLocked(Boolean x){
		if(x == true || x == false){
			locked  = x;
			return true;
		} else {
			return false;
		}
	}

}
