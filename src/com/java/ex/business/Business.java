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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.dao.BusinessDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;
//import com.java.management.test.TestEditor;
import com.java.ex.login.Login;

public class Business extends JFrame {
	
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	BusinessDTO business = (BusinessDTO)Session.getSession("business");
	
	public Business() {
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
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		JButton btnUserInfoModify = new JButton("회원정보");
		btnUserInfoModify.setBounds(780, 10, 100, 50);
		btnUserInfoModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BusinessModify();
			}
		});
		
		
		JButton btnTestRecord = new JButton("주문내역");
		btnTestRecord.setBounds(900, 10, 100, 50);
		btnTestRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//new UserTestRecord(memberDTO, memberDTO.getId());
			}
		});
		
		BusinessDAO dao = new BusinessDAO();
		ArrayList<BusinessDTO> dtos = new ArrayList<BusinessDTO>();
		
		//업체목록 불러서 띄우기
		
		BusinessDAO dao2 = new BusinessDAO();
		ArrayList<BusinessDTO> dtos2 = new ArrayList<BusinessDTO>();
		
		//dtos2 = dao2.selectAllTest();
		
		for(int i = 0; i < dtos.size(); i++) {
			String testId = String.valueOf(dtos.get(i).getId());
			
			JLabel lbl = new JLabel(dtos.get(i).getName());
			lbl.setBounds(posX, posY + (i * 60), 300, 50);
			lbl.setFont(font);
			
			JButton btnTestStart = new JButton("시험 시작");
			btnTestStart.setBounds(posX + 650, posY + (i * 60), 150, 50);
			btnTestStart.setFont(font2);
			btnTestStart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					//new Test(memberDTO, testId);
				}
			});
			
			pane.add(lbl);
			pane.add(btnTestStart);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		
		add(btnUserInfoModify);
		add(btnBack);
		add(btnTestRecord);
		add(scroll);
		setVisible(true);
	}
}
