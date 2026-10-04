package jdbcdemo;

import java.sql.*;

public class JDBCInsertDemo {

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
			System.out.println("Inserting a new employee to database\n");
			int rowsAffected = stmt.executeUpdate(
					"INSERT INTO employees " +
					"(last_name, first_name, email, department, salary) " +
					"VALUES " +
					"('Gamil', 'Marc', 'marc@gmail.com', 'IT', 30000.00)");
			rs = stmt.executeQuery("SELECT * FROM employees ORDER BY last_name");
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}
			System.out.println("Rows affected: " + rowsAffected);
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
