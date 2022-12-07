package com.java.ex.order;

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

public class CustomerOrder extends JFrame {
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	Font font3 = new Font("돋움", Font.BOLD, 15);
	Font font4 = new Font("돋움", Font.BOLD, 12);
	Font font5 = new Font("돋움", Font.BOLD, 14);
	int posX = 20, posY = 20;

	JPanel pane;
	JScrollPane scroll;

	ArrayList<JLabel> lblList = new ArrayList<JLabel>();
	ArrayList<JButton> btnDeleteList = new ArrayList<JButton>();
	ArrayList<JButton> btnCompleteList = new ArrayList<JButton>();

	public CustomerOrder(String m_id) {
		BusinessDTO bussiness = (BusinessDTO) Session.getSession("business");

		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));

		scroll = new JScrollPane(pane);
		scroll.setBounds(80, 80, 1500, 600);

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Business();
			}
		});
		

		setTitle("고객주문");
		setSize(1700, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);

		OrderDAO dao = new OrderDAO();
		ArrayList<Map<String, Object>> CustomerOrderList = dao.OrderList(m_id, bussiness.getId());
		
		OrderDAO odao = new OrderDAO();
		for (int i = 0; i < CustomerOrderList.size(); i++) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) CustomerOrderList.get(i);
			
			int o_no = (int) hashmap.get("o_no");
			String menuname = (String) hashmap.get("menuname");
			String address = (String) hashmap.get("m_address");
			String tel = (String) hashmap.get("m_tel");
			Date odatetime = (Date) hashmap.get("o_datetime");
			String state = (String) hashmap.get("o_state");
			int count = (int) hashmap.get("menu_count");
			int price = (int) hashmap.get("menutotalprice");
			
			JLabel lbl = new JLabel("");
			lbl.setText("주문한 메뉴 : " + menuname + " " + "주소 : " + address + " " + "전화번호 : " + tel + " " + "주문 날짜 : "
					+ odatetime + " " + "배달 상태 : " + state + " " + "수량 : " + count + " " + "가격 : " + price + "원");
			lbl.setBounds(posX, posY + (i * 50), 1500, 50);
			lbl.setFont(font5);
			JButton btnState = new JButton("배달 완료");
			btnState.setBounds(posX + 1130, posY + (i * 50), 150, 35);
			btnState.setFont(font3);
			
			btnState.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int complete = JOptionPane.showConfirmDialog(null, "배달을 완료하시겠습니까?", "배달 완료",
							JOptionPane.YES_NO_OPTION);
					if (complete == JOptionPane.YES_OPTION) {
						odao.StateUpdate(m_id, state, o_no);
						lbl.setText("주문한 메뉴 : " + menuname + " " + "주소 : " + address + " " + "전화번호 : " + tel + " " + "주문 날짜 : "
								+ odatetime + " " + "배달 상태 : 완료" + " " + "수량 : " + count + " " + "가격 : " + price + "원");
						
					}
				}
			});
			
			JButton btnDelete = new JButton("주문 취소");
			btnDelete.setBounds(posX + 1300, posY + (i * 50), 150, 35);
			btnDelete.setFont(font3);

			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int clear = JOptionPane.showConfirmDialog(null, "주문을 취소하시겠습니까?", "주문 취소",
							JOptionPane.YES_NO_OPTION);
					if (clear == JOptionPane.YES_OPTION) {
						odao.OrderSelectDelete(m_id, o_no);
						lbl.setVisible(false);
						btnDelete.setVisible(false);

						int index = lblList.indexOf(lbl);

						lblList.remove(lbl);
						btnDeleteList.remove(btnDelete);
						btnCompleteList.remove(btnState);
						
						for (int i = index; i < lblList.size(); i++) {
							Point testNamePoint = lblList.get(i).getLocation();
							testNamePoint.y -= 55;
							Point deletePoint = btnDeleteList.get(i).getLocation();
							deletePoint.y -= 60;
							Point CompleteStatePoint = btnCompleteList.get(i).getLocation();
							CompleteStatePoint.y -= 60;

							btnDeleteList.get(i).setLocation(deletePoint);
							lblList.get(i).setLocation(testNamePoint);
							btnCompleteList.get(i).setLocation(CompleteStatePoint);
						}
						Dimension di = pane.getPreferredSize();
						di.height -= 60;
						pane.setPreferredSize(di);
					
					}
				}
			});
			lblList.add(lbl);
			btnDeleteList.add(btnDelete);
			btnCompleteList.add(btnState);

			pane.add(lbl);
			pane.add(btnDelete);
			pane.add(btnState);
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		/*
		 * JLabel lbl = new JLabel(""); lbl.setText("주문 날짜 : " + odatetime + " " +
		 * "배달 상태 : " + "주소 : " + address + " " + "전화번호 : " + tel); lbl.setBounds(posX,
		 * posY + (i * 50), 1000, 50); lbl.setFont(font3); JLabel lbl2 = new JLabel("");
		 * lbl2.setText("주문한 메뉴 : " + menuname + " " + "수량 : " + count + " " + "가격 : " +
		 * price + "원");
		 * 
		 * lbl2.setBounds(posX, posY + 100 + (i * 50), 1000, 50); lbl2.setFont(font3);
		 * pane.add(lbl); pane.add(lbl2);
		 */

	add(scroll);
		add(btnBack);
		setVisible(true);
	
	}

}
