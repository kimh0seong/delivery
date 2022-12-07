package com.java.ex.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.customer.Customer;
import com.java.ex.dao.OrderDAO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.OrderDTO;
import com.java.ex.dto.Session;

public class OrderList extends JFrame{
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	Font font3 = new Font("돋움", Font.BOLD, 15);
	int posX = 20, posY = 20;
	MemberDTO member = (MemberDTO)Session.getSession("member");
	
	JPanel pane;
	JScrollPane scroll;
	
	public OrderList() {
		
		setTitle("주문 내역");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,300);
		
		JLabel titleOrder = new JLabel(member.getName()+ "님의 주문 내역");
		titleOrder.setBounds(80, 10, 500, 50);
		titleOrder.setFont(font2);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(830, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Customer();
			}
		});
		
		OrderDAO OrderDao = new OrderDAO();
		ArrayList<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
		Map map = new HashMap<String, Object>();
		OrderList.add(map);
		OrderList = OrderDao.selectOrder(member.getId());
	     
	    for(int i=0; i<OrderList.size(); i++) {
	    	HashMap<String, Object> hashmap = (HashMap<String, Object>) OrderList.get(i);
	    	JLabel lblOrder = new JLabel("가게명 : " + hashmap.get("businessname") + "  " + "메뉴이름 : " + hashmap.get("menuname") + "  " + "가격 : " + hashmap.get("menuprice") + "  " + "주문 날짜 : " + hashmap.get("datetime") + "  " + "배달 상태 : " + hashmap.get("state"));
	    	lblOrder.setBounds(posX, posY + (i * 50), 800, 50);
			lblOrder.setFont(font3);
	    	pane.add(lblOrder);
	    	Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
	    }
	    setVisible(true);
	    add(titleOrder);
		add(scroll);
	    add(btnBack);
		
	    /*
		for(Entry<String, Object> elem : OrderList.entrySet()) {
			JLabel lblOrder = new JLabel("키 : " + elem.getKey() + "값 : " + elem.getValue());
			add(lblOrder);
			setVisible(true);
		}	
		*/
		
	    
	    
	}
}
