package com.mcda5510.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSingleton {
	
	private static Connection connection;
	
	private ConnectionSingleton() {}
	
	public static Connection getConnection() throws Exception
	{
		connection = null;
		if(connection == null) {
			try {
				// This will load the MySQL driver, each DB has its own driver
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Setup the connection with the DB

				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/z_yan?"
						+ "user=z_yan&password=A00429842" // Creds
						+ "&useSSL=false" // b/c localhost
						+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"); // timezone

			} catch (Exception e) {
				throw e;
			} finally {

			}
		}
		return connection;
	}
	
	

}
