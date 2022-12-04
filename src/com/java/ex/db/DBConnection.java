package com.java.ex.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	public static String driver = "org.mariadb.jdbc.Driver";
	public static String url = "jdbc:mariadb://localhost:3306/delivery";
	public static String uid = "root";
	public static String pwd = "1234";
	
	public static Connection con = null;
	
	public Statement stmt = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	public String query = null;
	
	public DBConnection() {
		try {
			if(con == null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url, uid, pwd);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Exception[DBConnection] : " + e.getMessage());
		}
	}
}
