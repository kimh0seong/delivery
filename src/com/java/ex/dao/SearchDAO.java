package com.java.ex.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.db.DBConnection;

public class SearchDAO extends DBConnection {
	
	public ArrayList<Map<String,Object>> SearchName(String name) {
		query = "select b_name, b_id from business where b_name like '%" + name + "%'";
		//업체를 클릭했을때 그 업체에 메뉴를 어떻게 불러 올 것인가?
		//query = "select b_name from business";
		ArrayList<Map<String,Object>> BusinessName = new ArrayList<Map<String,Object>>();
		
		try {
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return BusinessName;
	}
}
	