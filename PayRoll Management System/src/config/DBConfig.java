package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	private final String URL = "jdbc:mysql://localhost:3306/payrolldb?";
	private final String PASSWORD = "root";
	private static Connection con = null;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public Connection getConnection() throws SQLException {
		
		if (con == null) {
			con = (Connection) DriverManager.getConnection(this.URL, "root", this.PASSWORD);
		}
		return con;
	}

}
