package com.java.ex.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.db.DBConnection;
import com.java.ex.dto.BaguniDTO;
import com.java.ex.dto.MenuDTO;

public class MenuDAO extends DBConnection {
	
	public MenuDTO selectMenu(String id) {
		query = "select * from menu where b_id='"+id+"'";
		MenuDTO dto = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()==true) {
				int menu_no = rs.getInt("menu_no");
				String b_id = rs.getString("b_id");
				String menuname = rs.getString("menuname");
				int menupirce = rs.getInt("menupirce");
				String menupic = rs.getString("menupic");
			
				dto = new MenuDTO(0, id, menuname, 0, menupic);
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
		return dto;
	}
	
	public ArrayList<MenuDTO> selectAllMenu(String bid) {
		query = "select * from menu where b_id = " + "'" + bid + "'";
		ArrayList<MenuDTO> dtos = new ArrayList<MenuDTO>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				
				int menu_no = rs.getInt("menu_no");
				String b_id = rs.getString("b_id");
				String menuname = rs.getString("menuname");
				int menuprice = rs.getInt("menuprice");
				String menupic = rs.getString("menupic");
				
				MenuDTO dto = new MenuDTO(menu_no, b_id, menuname, menuprice, menupic);
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
	
	public void insertBaguni(BaguniDTO dto) {
		query = "insert into baguni(menu_no, m_id, menu_count) value(?,?,1)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dto.getMenu_no());
			pstmt.setString(2, dto.getM_id());
			
			pstmt.executeUpdate();
			System.out.println("담기 성공");
			
		} catch(SQLException ex) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public  ArrayList<Map<String,Object>> selectBaguni(String id) {
		query = "select sb_no, b_name, menuname, menuprice, menu_count from baguni b, business bu, menu m where b.menu_no = m.menu_no AND bu.b_id = m.b_id AND  b.m_id = '" +id + "'";
		ArrayList<Map<String,Object>> BaguniList = new ArrayList<Map<String,Object>>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				
				String b_name = rs.getString("b_name");
				String menuname = rs.getString("menuname");
				int menuprice = rs.getInt("menuprice");
				int sb_no = rs.getInt("sb_no");
				int menu_count = rs.getInt("menu_count");
								
				Map map = new HashMap<String, Object>();
				map.put("businessname",b_name);
				map.put("menuname",menuname);
				map.put("menuprice",menuprice);
				map.put("sb_no", sb_no);
				map.put("menu_count", menu_count);
				BaguniList.add(map);
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
		return BaguniList; 
	}
	
	public void BaguniDelete(String m_id) {
		query = "delete from baguni where m_id =?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m_id);
			pstmt.executeUpdate();
			System.out.println("삭제 성공");
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void BaguniSelectDelete(String m_id, int sb_no) {
		query = "delete from baguni where m_id =? and sb_no =?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, sb_no);
			pstmt.executeUpdate();
			System.out.println("삭제 성공");
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void BaguniUpdate(String m_id, int menu_count ,int sb_no) {
		query = "update baguni set menu_count = ? where m_id =? and sb_no =?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, menu_count);
			pstmt.setString(2, m_id);
			pstmt.setInt(3, sb_no);
			pstmt.executeUpdate();
			System.out.println("수정 성공");
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
	