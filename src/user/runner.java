package user;

import java.sql.ResultSet;

import user.dbQueries;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import user.userObject;
import user.methodsYo;

public class runner {
	public runner() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<userObject> users = new ArrayList<userObject>();

	public boolean getAdmin(String x, String y){
		dbQueries yup = new dbQueries();
		databaseHelper DB = new databaseHelper(yup.checkAdmin());
		PreparedStatement pstmt = DB.pstmt;
		ResultSet rs;
		try {
			pstmt.setString(1, x);
			pstmt.setString(2, y);
			rs = pstmt.executeQuery();
			if(!rs.isBeforeFirst()){
				System.out.println("Unauthorised Access");
				return false;
			}

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				if(username.equals(x) && password.equals(y)){
					return true;
				} else {
					System.out.println("Unauthorised Access");
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		return false;
	}
	public boolean checkUser(String x, String y){
		dbQueries yup = new dbQueries();
		databaseHelper DB = new databaseHelper(yup.checkUser());
		PreparedStatement pstmt = DB.pstmt;
		ResultSet rs;
		try {
			pstmt.setString(1,x);
			pstmt.setString(2,y);
			rs =  pstmt.executeQuery();
			if(!rs.isBeforeFirst()){
				System.out.println("Unauthorised Access");
				return false;
			}

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				Boolean locked = rs.getBoolean("locked");
				if(username.equals(x) && password.equals(y)){
					if(locked){
						System.out.println("User Account Locked, please contact administrator");
						return false;
					} else{
						userObject newUser = new userObject(username, password, locked);
						updateTime(newUser);
						
						return true;
					}
				} else {
					System.out.println("Unauthorised Access");
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			return false;
		}
		return false;
	}
	public boolean createUser(String x, String y){
		try {
			dbQueries yup = new dbQueries();
			databaseHelper DB = new databaseHelper(yup.createUser());
			PreparedStatement pstmt = DB.pstmt;
			pstmt.setString(1, x);
			pstmt.setString(2, y);
			pstmt.executeUpdate();
			ResultSet rs;			       
			return true;
		} catch (SQLException e) {
			System.out.println("User already exists");
			return false;

		}
	}
	public void checkAdmin(String x, String y){
		if (getAdmin(x,y)){
			System.out.println("Welcome Admin");
			System.out.println("Would you like to lock or unlock a users account? (L/U)");
			Scanner answer = new Scanner(System.in);
			String theAnswer = answer.nextLine();
			if(theAnswer.equals("U") || theAnswer.equals("u")){
				System.out.println("Please enter the username for the user you want to unlock");
				String unlockUser = answer.nextLine();
				dbQueries yup = new dbQueries();
				databaseHelper DB = new databaseHelper(yup.checkUnlocked());
				PreparedStatement pstmt = DB.pstmt;
				ResultSet rs;
				try {
					pstmt.setString(1,unlockUser);
					rs = pstmt.executeQuery();
					if(!rs.isBeforeFirst()){
						System.out.println("Unable to unlock user");
					}
					while (rs.next()) {
						String username = rs.getString("username");
						if(username != ""){	
							System.out.println("Are you sure you want to unlock this user, "+username+"? (Y/N)" );
							String adminLock = answer.nextLine();
							if(adminLock.equals("Y")||adminLock.equals("y")){
								System.out.println(username);
								DB = new databaseHelper(yup.unlockUser());
								pstmt = DB.pstmt;
								pstmt.setString(1, username);
								pstmt.executeUpdate();
								System.out.println("Unlocked");
							}
						}
					}
				} catch (SQLException e) {
					System.out.println(e);

				}
			} else if (theAnswer.equals("L") || theAnswer.equals("l")){
				System.out.println("Please enter the username for the user you want to lock");
				String lockUser = answer.nextLine();
				dbQueries yup = new dbQueries();
				databaseHelper DB = new databaseHelper(yup.checkLocked());
				PreparedStatement pstmt = DB.pstmt;
				ResultSet rs;
				try {
					pstmt.setString(1,lockUser);
					rs = pstmt.executeQuery();
					if(!rs.isBeforeFirst()){
						System.out.println("Unable to lock user");
					}
					while (rs.next()) {
						String username = rs.getString("username");
						if(username != ""){	
							System.out.println("Are you sure you want to lock this user, "+username+"? (Y/N)" );
							String adminLock = answer.nextLine();
							if(adminLock.equals("Y")||adminLock.equals("y")){
								System.out.println(username);
								DB = new databaseHelper(yup.lockUser());
								pstmt = DB.pstmt;
								pstmt.setString(1, username);
								pstmt.executeUpdate();
								System.out.println("Locked");
							}
						}
					}
				} catch (SQLException e) {
					System.out.println(e);

				}

			}
		}
	}
	public void updateTime(userObject x){
		dbQueries yup = new dbQueries();
		databaseHelper DB = new databaseHelper(yup.checkLocked());
		PreparedStatement pstmt = DB.pstmt;
		DB = new databaseHelper(yup.updateTime());
		pstmt = DB.pstmt;
		try {
			pstmt.setString(1, x.getUsername());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logOn(){
		methodsYo methods = new methodsYo();
		System.out.println("Sign up or Log in? (S/L)");
		Scanner answer = new Scanner(System.in);
		String theAnswer = answer.nextLine();
		if(theAnswer.equals("S") || theAnswer.equals("s")){
			System.out.println("Welcome to sign up");
			System.out.println("Please enter a Username");
			String theUsername = answer.nextLine();
			System.out.println("Please enter a Password");
			String thePassword = answer.nextLine();
			if(methods.checkValidUser(theUsername, thePassword)){
				if(createUser(theUsername, thePassword)){
					System.out.println("User succesfully created!");
					if(checkUser(theUsername, thePassword)){
						System.out.println("Welcome, "+theUsername);
					}
				} else {
					System.out.println("Error, user not created");
				}
			} else {
				System.out.println("Invalid credentials, please try again");
			}
		} else if(theAnswer.equals("L") || theAnswer.equals("l")) {
			System.out.println("Welcome to log-in");
			System.out.println("Please enter a Username");
			String theUsername = answer.nextLine();
			System.out.println("Please enter a Password");
			String thePassword = answer.nextLine();
			if(checkUser(theUsername, thePassword)){
				System.out.println("Welcome, "+theUsername);
			}

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
