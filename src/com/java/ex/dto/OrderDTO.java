package com.java.ex.dto;

import java.sql.Date;

public class OrderDTO {
	private int o_no;
	private String b_id;
	private String m_id;
	private int menu_no;
	private Date o_datetime;
	private String o_state;
	private int menu_count;
	
	public OrderDTO() {
		o_no = 0;
		b_id="";
		m_id="";
		menu_no=0;
		o_datetime=null;
		o_state="";
		menu_count=0;
	}
	
	public OrderDTO(int o_no, String b_id, String m_id, int menu_no, Date o_datetime, String o_state,int menu_count) {
		this.o_no=o_no;
		this.b_id=b_id;
		this.m_id=m_id;
		this.menu_no=menu_no;
		this.o_datetime=o_datetime;
		this.o_state=o_state;
		this.menu_count=menu_count;
	}

	public int getO_no() {
		return o_no;
	}

	public void setO_no(int o_no) {
		this.o_no = o_no;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public int getMenu_no() {
		return menu_no;
	}

	public void setMenu_no(int menu_no) {
		this.menu_no = menu_no;
	}

	public Date getO_datetime() {
		return o_datetime;
	}

	public void setO_datetime(Date o_datetime) {
		this.o_datetime = o_datetime;
	}

	public String getO_state() {
		return o_state;
	}

	public void setO_state(String o_state) {
		this.o_state = o_state;
	}

	public int getMenu_count() {
		return menu_count;
	}

	public void setMenu_count(int menu_count) {
		this.menu_count = menu_count;
	}

	
	
}
