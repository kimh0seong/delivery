package com.java.ex.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.ex.db.DBConnection;
import com.java.ex.dto.BaguniDTO;

public class BaguniDAO extends DBConnection {
	public ArrayList<BaguniDTO> selectAllBaguni(String mid) {
		query = "select * from baguni where m_id = " + "'" + mid + "'";
		ArrayList<BaguniDTO> dtos = new ArrayList<BaguniDTO>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {				
				int menu_no = rs.getInt("menu_no");
				int sb_no = rs.getInt("sb_no");
				String m_id = rs.getString("m_id");
				int menu_count = rs.getInt("menu_count");
				
				BaguniDTO dto = new BaguniDTO(menu_no, sb_no, m_id, menu_count);
				dtos.add(dto);
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
		return dtos; 
	}
	
	
}
