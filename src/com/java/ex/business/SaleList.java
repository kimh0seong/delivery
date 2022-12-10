package com.java.ex.business;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.business.Business;
import com.java.ex.dao.OrderDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.Session;

public class SaleList extends JFrame {
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	Font font3 = new Font("돋움", Font.BOLD, 15);
	Font font4 = new Font("돋움", Font.BOLD, 12);
	Font font5 = new Font("돋움", Font.BOLD, 14);
	int posX = 20, posY = 20;

	JPanel pane;
	JScrollPane scroll;

	public SaleList() {
		BusinessDTO business = (BusinessDTO) Session.getSession("business");

		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));

		scroll = new JScrollPane(pane);
		scroll.setBounds(80, 80, 850, 350);

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Business();
			}
		});	

		setTitle("매출 내역");
		setSize(1050, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
	    
		JLabel titleOrder = new JLabel("오늘 매출");
		titleOrder.setBounds(120, 10, 500, 50);
		titleOrder.setFont(font2);
		add(titleOrder);
		
		OrderDAO OrderDao = new OrderDAO();
		ArrayList<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
		OrderList = OrderDao.TodayOrderdateList(business.getId());
		for(int i=0; i<OrderList.size(); i++) {
	    	HashMap<String, Object> hashmap = (HashMap<String, Object>) OrderList.get(i);
	    	JLabel lblOrder = new JLabel("메뉴 : " + hashmap.get("menuname") + "  " + "판매 갯수 : " + hashmap.get("salecnt") + "  " + "판매 가격 : " + hashmap.get("menutotalprice") + "원");
	    	lblOrder.setBounds(posX, posY + (i * 50), 800, 50);
			lblOrder.setFont(font3);
	    	pane.add(lblOrder);
	    	
	    	Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
	    }
		
		JButton btnNameSales = new JButton("메뉴별 매출");
		btnNameSales.setBounds(680, 10, 120, 50);
		btnNameSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				repaint();
				setVisible(true);
				OrderDAO OrderDao = new OrderDAO();
				ArrayList<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
				OrderList = OrderDao.CustomerOrderList(business.getId());
				titleOrder.setText("메뉴별 매출");
				for(int i=0; i<OrderList.size(); i++) {
			    	HashMap<String, Object> hashmap = (HashMap<String, Object>) OrderList.get(i);
			    	JLabel lblOrder = new JLabel("메뉴 : " + hashmap.get("menuname") + "  " + "판매 갯수 : " + hashmap.get("salecnt") + "  " + "판매 가격 : " + hashmap.get("menutotalprice") + "원");
			    	lblOrder.setBounds(posX, posY + (i * 50), 800, 50);
					lblOrder.setFont(font3);
			    	pane.add(lblOrder);
			    
			    }
				Dimension di = pane.getPreferredSize();
				di.height += 60;
				pane.setPreferredSize(di);
			}
		});	
		
		 
		JButton btnDaySales = new JButton("날짜별 매출");
		btnDaySales.setBounds(810, 10, 120, 50);
		btnDaySales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				repaint();
				setVisible(true);
				OrderDAO OrderDao = new OrderDAO();
				ArrayList<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
				OrderList = OrderDao.CustomerOrderdateList(business.getId());
				titleOrder.setText("날짜별 매출");
				for(int i=0; i<OrderList.size(); i++) {
			    	HashMap<String, Object> hashmap = (HashMap<String, Object>) OrderList.get(i);
			    	JLabel lblOrder = new JLabel("날짜 : " + hashmap.get("o_datetime") + " " + "메뉴 : " + hashmap.get("menuname") + "  " + "판매 갯수 : " + hashmap.get("salecnt") + "  " + "판매 가격 : " + hashmap.get("menutotalprice") + "원");
			    	lblOrder.setBounds(posX, posY + (i * 50), 800, 50);
					lblOrder.setFont(font3);
			    	pane.add(lblOrder);
			    	
			    	Dimension di = pane.getPreferredSize();
					di.height += 60;
					pane.setPreferredSize(di);
			    }
			}
			
		});	
		Dimension di = pane.getPreferredSize();
		di.height += 60;
		pane.setPreferredSize(di);
		
		
		
	    add(btnDaySales);
	    add(btnNameSales);
		add(scroll);
	    add(btnBack);
		
		
		add(scroll);
		add(btnBack);
		setVisible(true);
	
	}

}
