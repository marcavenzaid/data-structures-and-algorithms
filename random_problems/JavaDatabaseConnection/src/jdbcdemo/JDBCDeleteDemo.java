package jdbcdemo;

import java.sql.*;

public class JDBCDeleteDemo {

	public static void main(String[] args) throws SQLException {
				
		String dbURL = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String password = "student";
		
		Connection conn = null;
		Statement stmt = null;			
				
		try {
			conn = DriverManager.getConnection(dbURL, user, password);
			stmt = conn.createStatement();
			int rowsAffected = stmt.executeUpdate(
					"DELETE FROM employees " +
					"WHERE first_name='Marc' AND last_name='Gamil'");
			System.out.println("Rows affected: " + rowsAffected);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
}
