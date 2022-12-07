package com.java.ex.management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public UserRecord(MemberDTO memberDTO, String m_id) {
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
				if(memberDTO.getAuthority()==2)
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
		
		JLabel _lblPay = new JLabel("최근 결제 가격");
		_lblPay.setBounds(posX+800, posY, 300, 50);
		_lblPay.setFont(font20_bold);
		
		JLabel _lblDate = new JLabel("최근 주문 날짜");
		_lblDate.setBounds(posX+1050, posY, 300, 50);
		_lblDate.setFont(font20_bold);
		
		
		OrderDAO OrderDao = new OrderDAO();
		ArrayList<Map<String, Object>> recentOrderList = new ArrayList<Map<String, Object>>();
		recentOrderList = OrderDao.recentOrder(m_id);
		
		for(int i = 0; i < recentOrderList.size(); i++) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) recentOrderList.get(i);
			String id = (String) hashmap.get("m_id");
			JLabel lblrecentOrder = new JLabel(id);
			//lblrecentOrder.setText(memberDTO.getId());
			lblrecentOrder.setBounds(posX+70, posY + 200 + (i * 60), 300, 50);
			lblrecentOrder.setFont(font20);
			
			System.out.println(id);
			
			String bid = (String) hashmap.get("b_id");
			JLabel lblBid = new JLabel(bid);
			
			lblBid.setBounds(posX+220, posY + (i * 60), 300, 50);
			lblBid.setFont(font20);
			
			System.out.println(bid);
			
			String menuName = (String) hashmap.get("menuname");
			JLabel lblMenuname = new JLabel(menuName);
			lblMenuname.setBounds(posX+470, posY + (i * 60), 300, 50);
			lblMenuname.setFont(font20);
			
			System.out.println(menuName);
			
			int price = (int) hashmap.get("menuprice");
			JLabel lblPrice = new JLabel((String) hashmap.get("menuprice"));
			lblPrice.setBounds(posX+470, posY + (i * 60), 300, 50);
			lblPrice.setFont(font20);
			
			System.out.println(price);
			
			JButton btnTestRecordOpen = new JButton("시험지 보기");
			btnTestRecordOpen.setBounds(posX + 650, posY + (i * 60), 150, 50);
			btnTestRecordOpen.setFont(font20);
			btnTestRecordOpen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					//new UserTestReview(memberDTO, member_id, dtos.get(_i).getTest_id(), dtos.get(_i).getTest_num());
				}
			});
			
			pane.add(lblrecentOrder);
			pane.add(lblBid);
			pane.add(lblMenuname);
			pane.add(lblPrice);
			pane.add(btnTestRecordOpen);
			setVisible(true);
			
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
		add(scroll);
		setVisible(true);
	}
}

