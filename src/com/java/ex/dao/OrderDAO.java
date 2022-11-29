package com.java.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.java.ex.dto.MenuDTO;
import com.java.ex.dto.OrderDTO;

public class OrderDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/delivery";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public OrderDAO() {
		try {
			Class.forName(driver);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public  ArrayList<Map<String,Object>> selectOrder(String id) {
		query = "select  b_name, menuname, menuprice, o_datetime, o_state from business b, menu m, `order` o where b.b_id = m.b_id and m.b_id = o.b_id and o.m_id='" +id + "'";
		ArrayList<Map<String,Object>> OrderList = new ArrayList<Map<String,Object>>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				
				//int o_no = rs.getInt("o_no");
				String b_name = rs.getString("b_name");
				String menuname = rs.getString("menuname");
				int menuprice = rs.getInt("menuprice");
				//String m_id = rs.getString("m_id");
				//int menu_no = rs.getInt("menu_no");
				Date o_datetime = rs.getDate("o_datetime");
				String o_state = rs.getString("o_state");
								
				Map map = new HashMap<String, Object>();
				map.put("businessname",b_name);
				map.put("menuname",menuname);
				map.put("menuprice",menuprice);
				map.put("datetime",o_datetime);
				map.put("state",o_state);
				OrderList.add(map);
			}
		} catch(SQLException ex) {
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
		return OrderList; 
	}
		
	
}
