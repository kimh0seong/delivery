package com.java.ex.management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.dao.MemberDAO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;

public class UserManager extends JFrame{
	
	Font font = new Font("돋움", Font.BOLD, 20);
	int posX = 20, posY = 0;
	
	JPanel pane;
	JScrollPane scroll;
	
	public UserManager() {
		MemberDTO manager = (MemberDTO)Session.getSession("manager");
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 10));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,1500,600);
		
		setTitle("고객 관리");
		setSize(1700, 900);
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
				new Management();
			}
		});
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(posX+120, posY+20, 150, 50);
		lblID.setFont(font);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(posX + 320, posY+20, 150, 50);
		lblName.setFont(font);
		
		JLabel lblAddress = new JLabel("주소");
		lblAddress.setBounds(posX + 420, posY+20, 300, 50);
		lblAddress.setFont(font);
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setBounds(posX + 720, posY+20, 300, 50);
		lblTel.setFont(font);
		
		JLabel lblNickn = new JLabel("닉네임");
		lblNickn.setBounds(posX + 920, posY+20, 300, 50);
		lblNickn.setFont(font);
		
		JLabel lblRegdate = new JLabel("가입 날짜");
		lblRegdate.setBounds(posX +1120, posY+20, 300, 50);
		lblRegdate.setFont(font);
		
		
		add(lblID);
		add(lblName);
		add(lblAddress);
		add(lblTel);
		add(lblNickn);
		add(lblRegdate);
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		dtos = dao.selectAllMember();
		
		int cnt = 0;
		for(int i = 0; i < dtos.size(); i++) {
			if(dtos.get(i).getAuthority() == 1) {
				String m_id = dtos.get(i).getId();
				
				System.out.println(m_id);
				
				JLabel lblID2 = new JLabel(dtos.get(i).getId());
				lblID2.setBounds(posX + 40, posY + 10 + (cnt * 60), 150, 50);
				lblID2.setFont(font);
				
				JLabel lblName2 = new JLabel(dtos.get(i).getName());
				lblName2.setBounds(posX + 240, posY + 10 + (cnt * 60), 150, 50);
				lblName2.setFont(font);
				
				JLabel lblAddress2 = new JLabel(dtos.get(i).getAddress());
				lblAddress2.setBounds(posX + 340, posY + 10 + (cnt * 60), 300, 50);
				lblAddress2.setFont(font);
				
				JLabel lblTel2 = new JLabel(dtos.get(i).getTel());
				lblTel2.setBounds(posX + 640, posY + 10 + (cnt * 60), 300, 50);
				lblTel2.setFont(font);
				
				JLabel lblNickn2 = new JLabel(dtos.get(i).getNickn());
				lblNickn2.setBounds(posX + 840, posY + 10 + (cnt * 60), 300, 50);
				lblNickn2.setFont(font);
				
				Date date = dtos.get(i).getReg_date();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String date2= dateFormat.format(date);
				
				JLabel lblRegdate2 = new JLabel(date.toString());
				lblRegdate2.setBounds(posX + 1040, posY + 10 + (cnt * 60), 300, 50);
				lblRegdate2.setFont(font);
				
				
				JButton btnModify = new JButton("세부 정보 확인");
				btnModify.setBounds(posX + 1240, posY + 10 + (cnt * 60), 130, 50);
				btnModify.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new UserRecord(m_id);
					}
				});
				
				pane.add(lblID2);
				pane.add(lblName2);
				pane.add(lblAddress2);
				pane.add(lblTel2);
				pane.add(lblNickn2);
				pane.add(lblRegdate2);
				pane.add(btnModify);
				cnt++;
				
				Dimension di = pane.getPreferredSize();
				di.height += 60;
				pane.setPreferredSize(di);
			}
		}
		
		
		add(scroll);
		add(btnBack);
		setVisible(true);
	}
}
