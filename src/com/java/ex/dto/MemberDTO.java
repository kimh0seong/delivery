package com.java.ex.dto;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String address;
	private String tel;
	private String nickn;
	private Date reg_date;
	private int authority;
	
	public MemberDTO() {
		id = "";
		pw = "";
		name = "";
		address = "";
		tel = "";
		nickn = "";
		reg_date = null;
		authority = 0;
	}
	
	
	public MemberDTO(String id, String pw, String name, String address, String tel,String nickn, Date reg_date, int authority) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.nickn = nickn;
		this.reg_date = reg_date;
		this.authority = authority;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNickn() {
		return nickn;
	}
	public void setNickn(String nickn) {
		this.nickn = nickn;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
}
