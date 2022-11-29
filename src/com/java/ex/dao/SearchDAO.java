package com.java.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MenuDTO;

public class SearchDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/delivery";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public SearchDAO() {
		try {
			Class.forName(driver);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Map<String,Object>> SearchName(String name) {
		query = "select b_name, b_id from business where b_name like '%" + name + "%'";
		//업체를 클릭했을때 그 업체에 메뉴를 어떻게 불러 올 것인가?
		//query = "select b_name from business";
		ArrayList<Map<String,Object>> BusinessName = new ArrayList<Map<String,Object>>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				String b_name = rs.getString("b_name");
				String b_id = rs.getString("b_id");
		
				Map map = new HashMap<String, Object>();
				map.put("bname", b_name);
				map.put("b_id", b_id);
				BusinessName.add(map);			
			}
		}catch(SQLException ex) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return BusinessName;
	}
}
	