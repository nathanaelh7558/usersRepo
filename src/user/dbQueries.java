package user;

public class dbQueries {

	public dbQueries() {

	}
	public String checkLocked(String x){
		String query = "SELECT username,password FROM users WHERE username = \""+x+"\" AND locked = 0;";
		return query;
	}
	public String createUser(String x, String y){
		String query = "INSERT INTO users VALUES(\""+x+"\",\""+y+"\",DEFAULT,DEFAULT);";
		return query;
	}
	public String checkUser(String x, String y){
		String query = "SELECT username, password FROM users WHERE username = \""+x+"\" AND password = \""+y+"\";";
		return query;
	}
	public String lockUser(String x){
		String query = "UPDATE users SET locked = 1 WHERE username = \""+x+"\";";
		return query;
	}
}
