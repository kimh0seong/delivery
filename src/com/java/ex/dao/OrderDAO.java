package com.java.ex.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.db.DBConnection;
import com.java.ex.dto.BaguniDTO;
import com.java.ex.dto.MenuDTO;
import com.java.ex.dto.OrderDTO;

public class OrderDAO extends DBConnection {

	public  ArrayList<Map<String,Object>> selectOrder(String id) {
		query = "select b_name, menuname, menuprice, o_datetime, o_state from business b, menu m, `order` o where b.b_id = m.b_id and m.b_id = o.b_id and o.m_id='" +id + "'";
		ArrayList<Map<String,Object>> OrderList = new ArrayList<Map<String,Object>>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
	
				String b_name = rs.getString("b_name");
				String menuname = rs.getString("menuname");
				int menuprice = rs.getInt("menuprice");
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
			System.out.println("접속 패");
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
	
	
	
	public void insertOrder(OrderDTO dto) {
		query = "insert into `order`(b_id, m_id, menu_no, o_datetime, o_state , menu_count) values(?,?,?,?,?,?)";
		
		try {
			System.out.println("sss");
			pstmt = con.prepareStatement(query);
			//pstmt.setInt(1, dto.getO_no());
			pstmt.setString(1, dto.getB_id());
			pstmt.setString(2, dto.getM_id());
			pstmt.setInt(3, dto.getMenu_no());
			pstmt.setDate(4, dto.getO_datetime());
			pstmt.setString(5, dto.getO_state());
			pstmt.setInt(6, dto.getMenu_count());
			
			pstmt.executeUpdate();
			System.out.println("오더테이블 담기 성공");
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("접속 dd실패");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<OrderDTO> selectAllOrder(String mid) {
		query = "select * from `order` where m_id = " + "'" + mid + "'";
		ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {				
				int o_no = rs.getInt("o_no");
				String b_id = rs.getString("b_id");
				String m_id = rs.getString("m_id");
				int menu_no = rs.getInt("menu_no");
				Date o_datetime = rs.getDate("o_datetime");
				String o_state = rs.getString("o_state");
				int menu_count = rs.getInt("menu_count");
				
				OrderDTO dto = new OrderDTO(o_no, b_id, m_id, menu_no, o_datetime, o_state, menu_count);
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
