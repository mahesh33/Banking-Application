package com.ooppractice.bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDatabase {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con;

		// Load the driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// Connect to the database
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "system", "Sagarmatha3");

		return con;

	}
}
