package com.java.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.ex.dto.MemberDTO;

public class MemberDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/delivery";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public MemberDAO() {
		try {
			Class.forName(driver);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO selectMember(String id) {
		query = "select * from member where m_id='"+id+"'";
		MemberDTO dto = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()==true){
			
				String pw = rs.getString("m_pw");
				String address = rs.getString("m_name");
				String name = rs.getString("m_address");
				String tel = rs.getString("m_tel");
				String nickn = rs.getString("m_nickn");
				Date reg_date = rs.getDate("m_reg_date");
				int authority = rs.getInt("m_authority");
			
				dto = new MemberDTO(id, pw, name, address, tel, nickn, reg_date, authority);
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
		return dto;
	}
	
	public MemberDTO selectMemberName(String nickn) {
		query = "select * from member where m_nickn='"+nickn+"'";
		MemberDTO dto = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()==true){
			
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String address = rs.getString("m_name");
				String name = rs.getString("m_address");
				String tel = rs.getString("m_tel");
				//String nickn = rs.getString("m_nickn");
				Date reg_date = rs.getDate("m_reg_date");
				int authority = rs.getInt("m_authority");
			
				dto = new MemberDTO(id, pw, name, address, tel, nickn, reg_date, authority);
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
		return dto;
	}
	
	public ArrayList<MemberDTO> selectAllMember() {
		query = "select * from member";
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String name = rs.getString("m_name");
				String address = rs.getString("m_address");
				String tel = rs.getString("m_tel");
				String nickn = rs.getString("m_nickn");
				Date reg_date = rs.getDate("m_reg_date");
				int authority = rs.getInt("authority");
				
				MemberDTO dto = new MemberDTO(id, pw, name, address, tel, nickn, reg_date, authority);
				dtos.add(dto);
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
		return dtos; 
	}
	
	
	
	public void mDelete(MemberDTO dto) {
		query = "delete from member where id='" + dto.getId() + "'";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			int result = stmt.executeUpdate(query);
			if(result == 1) 
				System.out.println("삭제 성공");
			else System.out.println("삭제 실패");
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
	}
	
	public void signUpMember(MemberDTO dto) {
		query = "insert into member value(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getTel());
			pstmt.setString(6, dto.getNickn());
			pstmt.setDate(7, dto.getReg_date());
			pstmt.setInt(8, dto.getAuthority());
			pstmt.executeUpdate();
			 
			System.out.println("회원가입 성공");
			
		} catch(SQLException ex) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateMember(MemberDTO dto) {
		query = "update member set m_pw = ?, m_name = ?, m_address = ?, m_tel = ?, m_nickn = ? where m_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getNickn());
			pstmt.setString(6, dto.getId());
			
			pstmt.executeUpdate();
			 
			System.out.println("회원 정보 수정 성공");
		} catch(SQLException ex) {
			System.out.println("접속 실패");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}