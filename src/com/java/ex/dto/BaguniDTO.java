package com.java.ex.dto;

import java.sql.Date;

public class BaguniDTO {
	private int menu_no;
	private int sb_no;
	private String m_id;
	private int menu_count;
		
	public BaguniDTO() {
		menu_no = 0;
		sb_no = 0;
		m_id = "";
		menu_count = 0;
	}
	
	
	public BaguniDTO(int menu_no, int sb_no, String m_id, int menu_count) {
		this.menu_no = menu_no;
		this.sb_no = sb_no;
		this.m_id = m_id;
		this.menu_count = menu_count;
	}


	public int getMenu_no() {
		return menu_no;
	}


	public void setMenu_no(int menu_no) {
		this.menu_no = menu_no;
	}


	public int getMenu_count() {
		return menu_count;
	}


	public void setMenu_count(int menu_count) {
		this.menu_count = menu_count;
	}


	public int getSb_no() {
		return sb_no;
	}


	public void setSb_no(int sb_no) {
		this.sb_no = sb_no;
	}


	public String getM_id() {
		return m_id;
	}


	public void setM_id(String m_id) {
		this.m_id = m_id;
	}


	

			
}

