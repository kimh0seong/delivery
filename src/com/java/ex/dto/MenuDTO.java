package com.java.ex.dto;

import java.sql.Date;

public class MenuDTO {
	private int menu_no;
	private String b_id;
	private String menuname;
	private int menuprice;
	private String menupic;
	
	public MenuDTO() {
		menu_no = 0;
		b_id = "";
		menuname = "";
		menuprice = 0;
		menupic = "";
	}
	
	
	public MenuDTO(int menu_no, String b_id, String menuname, int menuprice, String menupic) {
		this.menu_no = menu_no;
		this.b_id = b_id;
		this.menuname = menuname;
		this.menuprice = menuprice;
		this.menupic = menupic;
	}


	public int getMenu_no() {
		return menu_no;
	}


	public void setMenu_no(int menu_no) {
		this.menu_no = menu_no;
	}


	public String getB_id() {
		return b_id;
	}


	public void setB_id(String b_id) {
		this.b_id = b_id;
	}


	public String getMenuname() {
		return menuname;
	}


	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}


	public int getMenuprice() {
		return menuprice;
	}


	public void setMenuprice(int menuprice) {
		this.menuprice = menuprice;
	}


	public String getMenupic() {
		return menupic;
	}


	public void setMenupic(String menupic) {
		this.menupic = menupic;
	}
	
	
	
}

