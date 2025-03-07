package org.big.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class BbsDbConn {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Connection conn = null;

		String url = "jdbc:mysql://localhost:3306/bookmarket";
		String user = "root";
		String password = "1111";

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);

		return conn;
	}

}
