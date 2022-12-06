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
	Font font = new Font("µ¸¿ò", Font.BOLD, 30);
	Font font2 = new Font("µ¸¿ò", Font.BOLD, 20);
	Font font3 = new Font("µ¸¿ò", Font.BOLD, 15);
	Font font4 = new Font("µ¸¿ò", Font.BOLD, 12);
	Font font5 = new Font("µ¸¿ò", Font.BOLD, 14);
	int posX = 20, posY = 20;

	JPanel pane;
	JScrollPane scroll;

	public SaleList() {
		BusinessDTO bussiness = (BusinessDTO) Session.getSession("business");

		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));

		scroll = new JScrollPane(pane);
		scroll.setBounds(80, 80, 1500, 600);

		JButton btnBack = new JButton("µÚ·Î°¡±â");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Business();
			}
		});	

		setTitle("¸ÅÃâ ³»¿ª");
		setSize(1700, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // Àý´ë À§Ä¡
		setLocationRelativeTo(null); // È­¸é Áß¾Ó ¹èÄ¡
		setResizable(false);

		
	add(scroll);
		add(btnBack);
		setVisible(true);
	
	}

}
