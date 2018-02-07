package brew_day;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:postgresql://localhost:5432/brewday_db";
	private static final String USER = "postgres";
	private static final String PASSWORD = "";
	
	private static Connection conn = null;
	
	static {
		try {
			// 1. load the driver
			Class.forName("org.postgresql.Driver");

			// 2. create the connection object
			conn = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
