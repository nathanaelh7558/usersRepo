package user;
import user.userObject;
public class methodsYo {

	public methodsYo() {

	}
public boolean checkValidUser(String x, String y){
	userObject newUser = new userObject(x, y, false);
	if(newUser.getUsername() != null && newUser.getPassword() != null){
		return true;
	}else{
		return false;
	}
}

}
