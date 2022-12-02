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

import com.java.ex.customer.Customer;
import com.java.ex.dao.MenuDAO;
import com.java.ex.dto.BaguniDTO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.MenuDTO;
import com.java.ex.dto.Session;

public class Menu extends JFrame{
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	MemberDTO member = (MemberDTO)Session.getSession("member");
	
	public Menu(String bid) { //Customer에서 dtos2.getId()값을 받아옴
		
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,300);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Customer();
			}
		});
		
		setTitle("메뉴");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		
		MenuDAO dao = new MenuDAO();
		ArrayList<MenuDTO> dtos = new ArrayList<MenuDTO>();
		
		dtos = dao.selectAllMenu(bid);
		
		for(int i = 0; i < dtos.size(); i++) {
			MenuDTO menu = dtos.get(i);				
			
			String menuName = menu.getMenuname();
			JLabel lbl = new JLabel(menu.getMenuname());
			lbl.setBounds(posX, posY + (i * 50), 300, 50);
			lbl.setFont(font);
			String menuPrice = Integer.toString(menu.getMenuprice());
			JLabel price = new JLabel(Integer.toString(menu.getMenuprice()) + "원");
			price.setBounds(posX+200, posY + (i * 50), 300, 50);
			price.setFont(font);
			JButton btnTake = new JButton("담기");
			btnTake.setBounds(posX+500, posY + (i*50), 100, 50);
			btnTake.setFont(font2);
			
			btnTake.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(dao.isAnotherBusinessInBasket(member.getId(), bid) == true) {
						JOptionPane.showMessageDialog(null, "다른 가게 메뉴가 존재합니다.");
					}
					else if(dao.SameMenu(member.getId(), bid, menu.getMenuname()) == true){
						JOptionPane.showMessageDialog(null, "같은 메뉴가 장바구니에 존재합니다.");
					}
					else {
					setVisible(false);
					MenuDAO dao = new MenuDAO();
					BaguniDTO dto = new BaguniDTO();
					dto.setMenu_no(menu.getMenu_no());
					dto.setM_id(member.getId());
					dao.insertBaguni(dto);
					setVisible(true);
					JOptionPane.showMessageDialog(null, "장바구니에 담았습니다.", "Success", JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			
			pane.add(btnTake);
			pane.add(lbl);
			pane.add(price);
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}	
			
		add(scroll);
		add(btnBack);
		setVisible(true);
	}

}

