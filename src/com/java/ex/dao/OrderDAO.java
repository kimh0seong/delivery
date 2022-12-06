package com.java.ex.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.db.DBConnection;
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
	
	
	public ArrayList<OrderDTO> selectAllOrder(String bid) {
		query = "select * from `order` where b_id = '" + bid + "' group by m_id";
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
			System.out.println("접속 ddd실패");
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
	
	
	public  ArrayList<Map<String,Object>> CustomerOrder(String b_id) {
		//query = "select b_id, menuname, (menuprice*menu_count) as menutotalprice, menu_count from baguni b, menu m where b.menu_no = m.menu_no AND b.m_id = '" + m_id + "'";
		query = "select o.m_id, o_datetime, o_state, menuname, (menuprice*b.menu_count) as menutotalprice, b.menu_count from baguni b, menu m, `order` o  where b.menu_no = m.menu_no AND o.menu_no = m.menu_no and b.m_id = '" + b_id + "'";
		ArrayList<Map<String,Object>> payBaguniList = new ArrayList<Map<String,Object>>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				String m_id = rs.getString("m_id");	
				Date o_datetime = rs.getDate("o_datetime");
				String o_state = rs.getString("o_state");
				String menuname = rs.getString("menuname");
				int menuprice = rs.getInt("menutotalprice");
				int menu_count = rs.getInt("menu_count");
								
				Map map = new HashMap<String, Object>();
				map.put("b_id",b_id);
				map.put("o_datetime", o_datetime);
				map.put("o_state", o_state);
				map.put("menuname", menuname);
				map.put("menutotalprice", menuprice);
				map.put("menu_count", menu_count);
				payBaguniList.add(map);
				System.out.println("성공");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return payBaguniList; 
	}
	
	public  ArrayList<Map<String,Object>> OrderList(String m_id, String b_id) {
		query = "select o_no, menuname, m_address, m_tel, o_datetime, o_state, (menuprice*menu_count) as menutotalprice, menu_count "
			  + "from member m, menu me, `order` o  "
			  + "WHERE m.m_id = o.m_id AND o.menu_no = me.menu_no and m.m_id = '" + m_id + "' AND me.b_id = '"+ b_id +"'";
		ArrayList<Map<String,Object>> CustomerOrderList = new ArrayList<Map<String,Object>>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int o_no = rs.getInt("o_no");
				String menuname = rs.getString("menuname");	
				String m_address = rs.getString("m_address");
				String m_tel = rs.getString("m_tel");
				Date o_datetime = rs.getDate("o_datetime");
				String o_state = rs.getString("o_state");
				int menuprice = rs.getInt("menutotalprice");
				int menu_count = rs.getInt("menu_count");
				
				Map map = new HashMap<String, Object>();
				map.put("o_no", o_no);
				map.put("menuname", menuname);
				map.put("m_address", m_address);
				map.put("m_tel", m_tel);
				map.put("o_datetime", o_datetime);
				map.put("o_state", o_state);
				map.put("menutotalprice", menuprice);
				map.put("menu_count", menu_count);
				CustomerOrderList.add(map);
				System.out.println("성공");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("실패");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return CustomerOrderList; 
	}
	
	public void OrderSelectDelete(String m_id, int o_no) {
		query = "delete from `order` where m_id =? and o_no =?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, o_no);
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
	
	public void StateUpdate(String m_id, String o_state, int o_no) {
		//query = "update `Order` set o_state = '완료'  where m_id = '" + m_id + "' and o_no = '" + o_no + "'";
		query = "update `Order` set o_state = '완료'  where m_id = ? and o_no = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m_id);
			//pstmt.setString(1, o_state);
			System.out.println("성");
			pstmt.setInt(2, o_no);
			System.out.println("실");
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
