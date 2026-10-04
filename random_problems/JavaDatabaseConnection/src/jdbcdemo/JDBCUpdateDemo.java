package jdbcdemo;

import java.sql.*;

public class JDBCUpdateDemo {

	public static void main(String[] args) throws SQLException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String password = "student";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, user, password);
			stmt = conn.createStatement();
			
			String firstName = "Marc", lastName = "Gamil";
			displayEmployee(conn, firstName, lastName);
			int rowsAffected = stmt.executeUpdate(
					"UPDATE employees " +
					"SET email='marcavenzaid@gmail.com' " +
					"WHERE last_name='Gamil' AND first_name='Marc'");
			System.out.println("Rows affected: " + rowsAffected);
			displayEmployee(conn, firstName, lastName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	private static void displayEmployee(Connection conn, String first_name, String last_name) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT email FROM employees " +
				"WHERE last_name='gamil' AND first_name='marc'");
		while (rs.next()) {
			System.out.println(rs.getString("email"));
		}		
	}
	
}
