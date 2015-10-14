package user;

public class dbQueries {

	public dbQueries() {

	}
	public String checkLocked(){
		String query = "SELECT username,password FROM users WHERE username = ? AND locked = 0;";
		return query;
	}
	public String checkUnlocked(){
		String query = "SELECT username,password FROM users WHERE username = ? AND locked = 1;";
		return query;
	}
	public String createUser(){
		String query = "INSERT INTO users VALUES (?,?,DEFAULT,DEFAULT);";
		return query;
	}
	public String checkUser(){
		String query = "SELECT username, password, locked FROM users WHERE username = ? AND password = ?;";
		return query;
	}
	public String checkAdmin(){
		String query = "SELECT username, password FROM admins WHERE username = ? AND password = ?;";
		return query;
	}
	public String lockUser(){
		String query = "UPDATE users SET locked = 1 WHERE username = ?;";
		return query;
	}
	public String unlockUser(){
		String query = "UPDATE users SET locked = 0 WHERE username = ?;";
		return query;
	}
	public String updateTime(){
		String query = "UPDATE users SET log_in_time = CURRENT_TIMESTAMP WHERE username = ?;";
		return query;
	}
}
