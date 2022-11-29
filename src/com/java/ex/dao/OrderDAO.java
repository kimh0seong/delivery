package com.java.ex.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.db.DBConnection;

public class OrderDAO extends DBConnection {

	public  ArrayList<Map<String,Object>> selectOrder(String id) {
		query = "select  b_name, menuname, menuprice, o_datetime, o_state from business b, menu m, `order` o where b.b_id = m.b_id and m.b_id = o.b_id and o.m_id='" +id + "'";
		ArrayList<Map<String,Object>> OrderList = new ArrayList<Map<String,Object>>();
		
		try {
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return OrderList; 
	}
		
	
}
