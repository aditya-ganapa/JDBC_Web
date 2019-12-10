package org.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	static Connection con;
	static {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getCon() throws SQLException {
		if (con == null) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db10", "root", "password-1");
		}
		return con;
	}
}