package com.java.ex.usermanagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.java.ex.customer.Customer;
import com.java.ex.dao.OrderDAO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;


public class UserRecord extends JFrame{
	
	int posX = 20, posY = 20;
	int radioPosX = 0, radioPosY = 170;
	Font font25 = new Font("돋움", Font.PLAIN, 25);
	Font font20 = new Font("돋움", Font.PLAIN, 20);
	Font font15 = new Font("돋움", Font.PLAIN, 15);
	Font font20_bold = new Font("돋움", Font.BOLD, 20);
	
	MemberDTO memberDTO;
	
	JPanel pane;
	JScrollPane scroll;
	
	public UserRecord(String m_id) {
		MemberDTO manager = (MemberDTO)Session.getSession("manager");
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,1500,600);
		
		this.memberDTO = memberDTO;
		
		setTitle("시험 기록");
		setSize(1700, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if(manager.getAuthority()==2)
					new UserManager();
				else
					new Customer();
			}
		});
		
		JLabel _lblID = new JLabel("ID");
		_lblID.setBounds(posX+150, posY, 300, 50);
		_lblID.setFont(font20_bold);
		
		JLabel _lblOrderBusiness = new JLabel("최근 주문한 가게");
		_lblOrderBusiness.setBounds(posX+300, posY, 300, 50);
		_lblOrderBusiness.setFont(font20_bold);
		
		JLabel _lblMenu = new JLabel("최근 주문한 메뉴");
		_lblMenu.setBounds(posX+550, posY, 300, 50);
		_lblMenu.setFont(font20_bold);
		
		JLabel _lblCount = new JLabel("수량");
		_lblCount.setBounds(posX+780, posY, 300, 50);
		_lblCount.setFont(font20_bold);
		
		JLabel _lblPay = new JLabel("최근 결제 가격");
		_lblPay.setBounds(posX+950, posY, 300, 50);
		_lblPay.setFont(font20_bold);
		
		JLabel _lblDate = new JLabel("최근 주문 날짜");
		_lblDate.setBounds(posX+1190, posY, 300, 50);
		_lblDate.setFont(font20_bold);
		
		
		OrderDAO OrderDao = new OrderDAO();
		ArrayList<Map<String, Object>> recentOrderList = new ArrayList<Map<String, Object>>();
		recentOrderList = OrderDao.recentOrder(m_id);
		
		for(int i = 0; i < recentOrderList.size(); i++) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) recentOrderList.get(i);
			String id = (String) hashmap.get("m_id");
			JLabel lblrecentOrder = new JLabel(id);
			//lblrecentOrder.setText(memberDTO.getId());
			lblrecentOrder.setBounds(posX+70, posY + (i * 60), 300, 50);
			lblrecentOrder.setFont(font20);
			
			String bname = (String) hashmap.get("b_name");
			
			JLabel lblBname = new JLabel(bname);			
			lblBname.setBounds(posX+220, posY + (i * 60), 300, 50);
			lblBname.setFont(font20);
			
			String menuName = (String) hashmap.get("menuname");
			JLabel lblMenuname = new JLabel(menuName);
			lblMenuname.setBounds(posX+470, posY + (i * 60), 300, 50);
			lblMenuname.setFont(font20);
			
			String menucount = Integer.toString((int) hashmap.get("menu_count"));
			JLabel lblCount = new JLabel(menucount);
			lblCount.setBounds(posX+720, posY + (i * 60), 300, 50);
			lblCount.setFont(font20);
			
			
			JLabel lblPrice = new JLabel(Integer.toString((int)hashmap.get("menutotalprice")) + "원");
			lblPrice.setBounds(posX+890, posY + (i * 60), 300, 50);
			lblPrice.setFont(font20);
			
			Date odatetime = (Date) hashmap.get("o_datetime");
			JLabel lblOdatetime = new JLabel(odatetime.toString());
			lblOdatetime.setBounds(posX+1120, posY + (i * 60), 300, 50);
			lblOdatetime.setFont(font20);
			
			pane.add(lblCount);
			pane.add(lblrecentOrder);
			pane.add(lblBname);
			pane.add(lblMenuname);
			pane.add(lblPrice);
			pane.add(lblOdatetime);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		add(btnBack);
		add(_lblID);
		add(_lblOrderBusiness);
		add(_lblMenu);
		add(_lblPay);
		add(_lblDate);
		add(_lblCount);
		
		add(scroll);
		setVisible(true);
	}
}

