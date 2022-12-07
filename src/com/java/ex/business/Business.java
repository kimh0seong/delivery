package com.java.ex.business;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.dao.OrderDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.OrderDTO;
import com.java.ex.dto.Session;
//import com.java.management.test.TestEditor;
import com.java.ex.login.Login;
import com.java.ex.order.CustomerOrder;

public class Business extends JFrame {
	
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	Font font3 = new Font("돋움", Font.BOLD, 10);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	
	
	public Business() {
		BusinessDTO business = (BusinessDTO)Session.getSession("business");
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,400);
		
		setTitle("업체용");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(710, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		JButton btnMenu = new JButton("매출");
		btnMenu.setBounds(590, 10, 100, 50);
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SaleList();
			}
		});
		
		JButton btnSales = new JButton("메뉴");
		btnSales.setBounds(470, 10, 100, 50);
		btnSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BusinessMenu(business.getId());
			}
		});
		
		JButton btnUserInfoModify = new JButton("회원정보");
		btnUserInfoModify.setBounds(830, 10, 100, 50);
		btnUserInfoModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BusinessModify();
			}
		});
		
		JLabel lbl = new JLabel("주문 목록");
		lbl.setBounds(posX + 60, posY, 300, 50);
		lbl.setFont(font);
		
		OrderDAO dao = new OrderDAO();
		ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
		
		//주문 리스트 불러오기
		dtos = dao.selectAllOrder(business.getId());
		
		for(int i = 0; i < dtos.size(); i++) {
			OrderDTO dtos2 = dtos.get(i); 
			
			JLabel lbl2 = new JLabel(dtos.get(i).getM_id());
			
			lbl2.setBounds(posX, posY + (i * 50), 300, 50);
			lbl2.setFont(font);
			JButton btnBusiness = new JButton("주문 상세 보기");
			btnBusiness.setBounds(posX+600, posY + (i*50), 200, 45);
			btnBusiness.setFont(font2);
			btnBusiness.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new CustomerOrder(dtos2.getM_id());
				}
			});
					
			pane.add(lbl2);
			pane.add(btnBusiness);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		
		add(btnMenu);
		add(btnSales);
		add(btnUserInfoModify);
		add(btnBack);
		add(lbl);
		add(scroll);
		setVisible(true);
	}
}
