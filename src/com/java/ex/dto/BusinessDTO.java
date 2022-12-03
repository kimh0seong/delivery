package com.java.ex.dto;

import java.sql.Date;

public class BusinessDTO {
	private String id;
	private String pw;
	private String name;
	private String tel;
	private String address;
	private Date reg_date;
	
	public BusinessDTO() {
		id = "";
		pw = "";
		name = "";
		tel = "";
		address = "";
		reg_date = null;
	}
	
	
	public BusinessDTO(String id, String pw, String name,  String tel, String address, Date reg_date) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.reg_date = reg_date;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/*
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	*/
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
