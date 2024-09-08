package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	
	private static Connection connection;
	
	private DB() {
		
	}
	
	public static Connection getConnection() throws Exception {
		
		if(connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(
	                "jdbc:mysql://insurancedb.cjyslqasspqj.ca-central-1.rds.amazonaws.com:3306/j2ee_project", "admin", "jaykumar");
		}
		return connection;
	}

}
