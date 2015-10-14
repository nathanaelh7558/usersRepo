package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import user.userObject;
public class runner {

	public runner() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<userObject> users = new ArrayList();
	
 public void doDBstuff(){
	 databaseHelper DB = new databaseHelper();
		Statement stmt = DB.stmt;
	  
	    ResultSet rs;
			try {
				String query = "SELECT username, password FROM users ;";
				rs = stmt.executeQuery(query);
				while (rs.next()) {
		            String username = rs.getString("username");
		            String password = rs.getString("password");
		            userObject temp = new userObject(username, password);
		            users.add(temp);
		            logOn();
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 }
 public void checkUser(String x, String y){
	 databaseHelper DB = new databaseHelper();
		Statement stmt = DB.stmt;	  
	    ResultSet rs;
			try {
String query = "SELECT username, password FROM users WHERE username = \""+x+"\" AND password = \""+y+"\";";
			rs = stmt.executeQuery(query);
			if(!rs.next()){
				System.out.println("Unauthorised Access");
			}
		
				while (rs.next()) {
		            String username = rs.getString("username");
		            String password = rs.getString("password");
		            if(username.equals(x) && password.equals(y)){
		            	System.out.println("Welcome, "+username);
		            } else {
		            	System.out.println("Unauthorised Access");
		            }
		            userObject temp = new userObject(username, password);
		            users.add(temp);
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 }
 public void createUser(String x, String y){
	 databaseHelper DB = new databaseHelper();
		Statement stmt = DB.stmt;
	  
	    ResultSet rs;
			try {
				String query = "INSERT INTO users VALUES(\""+x+"\",\""+y+"\");";
				stmt.executeUpdate(query);		
		        System.out.println("Signed up, Welcome, "+x);
			} catch (SQLException e) {
				System.out.println("User already exists");
				
			}
 }
 public void checkAdmin(String x, String y){
	 if (x.equals("Admin") && y.equals("AdminPassword")){
		 System.out.println("Welcome Admin");
		 System.out.println("Would you like to lock or unlock a users account? (L/U)");
		 Scanner answer = new Scanner(System.in);
		 String theAnswer = answer.nextLine();
		 if(theAnswer.equals("U") || theAnswer.equals("u")){
			 
		 } else if (theAnswer.equals("L") || theAnswer.equals("l")){
			 
		 }
		 System.out.println("Please enter the username for the user you want to lock");
	 }
 }
	public void logOn(){
		System.out.println("Sign up or Log in? (S/L)");
		Scanner answer = new Scanner(System.in);
		String theAnswer = answer.nextLine();
		if(theAnswer.equals("S") || theAnswer.equals("s")){
		System.out.println("Welcome to sign up");
		System.out.println("Please enter a Username");
		String theUsername = answer.nextLine();
		System.out.println("Please enter a Password");
		String thePassword = answer.nextLine();
		createUser(theUsername, thePassword);
		} else if(theAnswer.equals("L") || theAnswer.equals("l")) {
			System.out.println("Welcome to log-in");
			System.out.println("Please enter a Username");
			String theUsername = answer.nextLine();
				System.out.println("Please enter a Password");
				String thePassword = answer.nextLine();
				checkUser(theUsername, thePassword);
		}
		else if(theAnswer.equals("X") || theAnswer.equals("x")){
			System.out.println("Hello Admin");
			System.out.println("Please enter your Username");
			String theUsername = answer.nextLine();
				System.out.println("Please enter a Password");
				String thePassword = answer.nextLine();
				checkAdmin(theUsername, thePassword);
			
		}
	}
}