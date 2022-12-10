package com.java.ex.customer;

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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.java.ex.business.Menu;
import com.java.ex.dao.BusinessDAO;
import com.java.ex.dao.SearchDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;
//import com.java.management.test.TestEditor;
import com.java.ex.login.Login;
import com.java.ex.order.OrderList;

public class Customer extends JFrame {
	
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	int posX = 20, posY = 20;
	
	JTextField txtSearch;
	JPanel pane;
	JScrollPane scroll;
	
	
	public Customer() {
		MemberDTO member = (MemberDTO)Session.getSession("member");
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,300);
		
		setTitle("고객용");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		txtSearch = new JTextField();
		txtSearch.setBounds(posX+150, posY, 400, 30);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(600, 10, 100, 50);
		
		SearchDAO SearchDao = new SearchDAO();
			
		btnSearch.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {
				ArrayList<Map<String, Object>>BusinessName = SearchDao.SearchName(txtSearch.getText());
				pane.removeAll();
				repaint();
				for(int i = 0; i < BusinessName.size(); i++) {
					HashMap<String, Object> hashmap = (HashMap<String, Object>) BusinessName.get(i);
					
					String bid = (String) hashmap.get("b_id");
					JLabel lblBname = new JLabel((String) hashmap.get("bname"));
					lblBname.setBounds(posX, posY+(i*50), 300, 50);
					lblBname.setFont(font);
					JButton btnBusiness = new JButton("보기");
					btnBusiness.setBounds(posX+700, posY + (i*50), 100, 45);
					btnBusiness.setFont(font2);
					pane.add(lblBname);
					pane.add(btnBusiness);
					setVisible(true);
					btnBusiness.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							new Menu(bid);
						}
					});
				}
				Dimension di = pane.getPreferredSize();
				di.height += 60;
				pane.setPreferredSize(di);
			}		
		});		
	
		JButton btnUserInfoModify = new JButton("회원정보");
		btnUserInfoModify.setBounds(780, 10, 100, 50);
		btnUserInfoModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CustomerModify();
			}
		});
		
		
		JButton btnOrder = new JButton("주문내역");

		btnOrder.setBounds(900, 10, 100, 50);
		btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrderList();
			}
		});
		
		BusinessDAO dao = new BusinessDAO();
		ArrayList<BusinessDTO> dtos = new ArrayList<BusinessDTO>();
		//배열리스트 dtos를 생성
		
		//업체목록 불러서 띄우기	
		dtos = dao.selectAllBusiness();
		
		for(int i = 0; i < dtos.size(); i++) {
			BusinessDTO dtos2 = dtos.get(i); //dtos2에 배열dtos i번째 값을 저장
			String bid = String.valueOf(dtos.get(i).getId());
			
			JLabel lbl = new JLabel(dtos.get(i).getName());
			
			lbl.setBounds(posX, posY + (i * 50), 300, 50);
			lbl.setFont(font);
			JButton btnBusiness = new JButton("보기");
			btnBusiness.setBounds(posX+700, posY + (i*50), 100, 45);
			btnBusiness.setFont(font2);
			btnBusiness.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Menu(dtos2.getId());
					//
				}
			});
					
			pane.add(lbl);
			pane.add(btnBusiness);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		
		
		JButton btnBaguni = new JButton("장바구니");
		btnBaguni.setBounds(780, 400, 150, 100);
		btnBaguni.setFont(font2);
		btnBaguni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Baguni();
			}
		});
		
		/*
		JButton btnOrderCancle = new JButton("주문취소");
		btnOrderCancle.setBounds(530, 400, 150, 100);
		btnOrderCancle.setFont(font2);
		btnOrderCancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//new Test(memberDTO, testId);
			}
		});
		*/
		
		add(txtSearch);
		add(btnSearch);
		//add(btnOrderCancle);
		add(btnBaguni);
		add(btnUserInfoModify);
		add(btnBack);
		add(btnOrder);
		add(scroll);
		setVisible(true);
		
		}
	
}
