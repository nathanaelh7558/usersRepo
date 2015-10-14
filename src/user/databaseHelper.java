package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseHelper {
//	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/userProject";
	private Connection connection;
	public Statement stmt = null;

		
	public databaseHelper() {
		try {
			connection = DriverManager.getConnection(DB_URL, "root", "ch@ngeme1");
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

}
