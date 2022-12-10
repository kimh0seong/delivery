package com.java.ex.business;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.java.ex.dao.MenuDAO;
import com.java.ex.dto.BaguniDTO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MenuDTO;
import com.java.ex.dto.Session;

public class AddMenu extends JFrame {
	int posX = 50, posY = 50;

	JPanel pane;
	JScrollPane scroll;
	JTextField txtMenuName;
	JTextField txtMenuPrice;

	public AddMenu() {
		BusinessDTO business = (BusinessDTO) Session.getSession("business");
		
		setTitle("�޴� �߰�");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);

		JButton btnBack = new JButton("�ڷΰ���");
		btnBack.setBounds(210, 380, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Business();
			}
		});

		JLabel lblMenuName = new JLabel("�޴� �̸� : ");
		lblMenuName.setBounds(posX, posY, 80, 40);

		txtMenuName = new JTextField();
		txtMenuName.setBounds(posX + 70, posY + 5, 150, 30);

		JLabel lblMenuPrice = new JLabel("�޴� ���� : ");
		lblMenuPrice.setBounds(posX, posY + 35, 100, 40);

		txtMenuPrice = new JTextField();
		txtMenuPrice.setBounds(posX + 70, posY + 40, 200, 30);

		JButton btnAddMenu = new JButton("�޴� �߰�");
		btnAddMenu.setBounds(80, 380, 100, 50);

		MenuDAO dao = new MenuDAO();
		
		btnAddMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dao.SameAddMenu(business.getId(), txtMenuName.getText().trim()) == true) {
					JOptionPane.showMessageDialog(null, "���� �޴��� �����մϴ�.");
					
				} else {
					setVisible(false);
					MenuDAO dao = new MenuDAO();
					MenuDTO dto = new MenuDTO();
					dto.setB_id(business.getId());
					dto.setMenuname(txtMenuName.getText().trim());
					dto.setMenuprice(Integer.parseInt(txtMenuPrice.getText().trim()));
					dao.insertMenu(dto);
					setVisible(true);
					JOptionPane.showMessageDialog(null, "�޴��� �߰��Ͽ����ϴ�", "Success", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
				
		//pane.add(lblMenuName);
		//pane.add(txtMenuName);
		//pane.add(lblMenuPrice);
		//pane.add(txtMenuPrice);
		//pane.add(btnAddMenu);
		//pane.add(btnBack);
			
			
		
		add(lblMenuName);
		add(txtMenuName);
		add(lblMenuPrice);
		add(txtMenuPrice);
		add(btnAddMenu);
		add(btnBack);
		setVisible(true);
	}

	boolean isBlanks() {
		if (txtMenuName.getText().equals("") || txtMenuPrice.getText().equals("")) {
			return true;
		}
		return false;
	}
}