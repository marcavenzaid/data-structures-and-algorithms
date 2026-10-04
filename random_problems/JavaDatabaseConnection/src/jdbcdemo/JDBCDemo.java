package jdbcdemo;

import java.sql.*;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, user, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM employees");
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}
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
	
}
