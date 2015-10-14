package user;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import user.runner;
public class mainUser {
	
	public mainUser() {

	}
 
	public static void main(String[] args) {
	runner YES = new runner();
	YES.logOn();
	}
	
	
}
