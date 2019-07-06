package com.ethans.automation.poianddb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Organisation";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root@123";
	static Connection conn = null;

	public static void createDataBase() {
		String dbname;
		boolean flag = false;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet resultSet = conn.getMetaData().getCatalogs();
			while (resultSet.next()) {
				dbname = resultSet.getString(1);
				if (dbname.equalsIgnoreCase("Organisation")) {
					flag = true;
				}
			}
			resultSet.close();
			if (!flag) {
				System.out.println("Creating database...");
				stmt = conn.createStatement();

				String sql = "CREATE DATABASE Organisation";
				stmt.executeUpdate(sql);
				System.out.println("Database created successfully...");
			} else {
				System.out.println("Using DataBase Organisation");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

		}
	}

	public static void createTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Creating Employee Table....");

		String sql = "CREATE TABLE Employee " + "(id VARCHAR(255) not NULL, " + " name VARCHAR(255), "
				+ " age VARCHAR(255), " + " gender VARCHAR(255), " + "role VARCHAR(255), " + " PRIMARY KEY ( id ))";
		try {
			stmt.executeUpdate(sql);
			System.out.println("Created Table Employee Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertRecord(String id, String name, String age, String gender, String role) {
		PreparedStatement stmt = null;
		System.out.println("Inserting records into the table...");
		String sql = "INSERT INTO Employee(id,name,age,gender,role) VALUES (?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, name);
			stmt.setString(3, age);
			stmt.setString(4, gender);
			stmt.setString(5, role);
			int record = stmt.executeUpdate();
			System.out.println("Inserted record into the table..." + record);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void clean() {

		try {
			System.out.println("Closing connection to database...");
			if (conn != null)
				conn.close();
			System.out.println("Connection to database closed");
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
}
