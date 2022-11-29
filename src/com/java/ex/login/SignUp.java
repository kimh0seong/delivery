package com.java.ex.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.java.ex.dao.BusinessDAO;
import com.java.ex.dao.MemberDAO;
import com.java.ex.dto.MemberDTO;

public class SignUp extends JFrame {
	int posX = 50, posY = 50;
	
	JTextField txtName;
	JTextField txtID;
	JPasswordField txtPW;
	JTextField txtNickn;
	JTextField txtAddress;
	JTextField txtTel;
	
	SignUp() {
		setTitle("회원 가입");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JLabel lblID = new JLabel("아이디");
		lblID.setBounds(posX, posY, 80, 40);
		
		txtID = new JTextField();
		txtID.setBounds(posX+70, posY, 150, 30);
		
		JButton overBtn = new JButton("중복확인");
		overBtn.setBounds(posX+230, posY+5, 100, 20);
		
		overBtn.addActionListener(new ActionListener() {						
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemberDAO dao = new MemberDAO();
				BusinessDAO dao2 = new BusinessDAO();
				if(dao.selectMember(txtID.getText()) !=  null || dao2.selectBusiness(txtID.getText()) != null) {
					JOptionPane.showMessageDialog(null, "존재하는 아이디입니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "Success", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setBounds(posX, posY+35, 100, 40);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(posX+70, posY+35, 200, 30);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(posX, posY + 70, 100, 40);
		
		txtName = new JTextField();
		txtName.setBounds(posX+70, posY + 70, 100, 30);
		
		
		JLabel lblNickn = new JLabel("닉네임");
		lblNickn.setBounds(posX, posY + 110, 100, 40);
		
		txtNickn = new JTextField();
		txtNickn.setBounds(posX+70, posY + 110, 100, 30);
		
		JButton overBtn2 = new JButton("중복확인");
		overBtn2.setBounds(posX+230, posY+115, 100, 20);
		
		overBtn2.addActionListener(new ActionListener() {						
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemberDAO dao = new MemberDAO();
				if(dao.selectMemberName(txtNickn.getText()) !=  null) {
					JOptionPane.showMessageDialog(null, "존재하는 닉네임입니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					JOptionPane.showMessageDialog(null, "사용 가능한 닉네임입니다.", "Success", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		
		
		
		JLabel lblAddress = new JLabel("주소");
		lblAddress.setBounds(posX, posY + 155, 100, 40);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(posX+70, posY + 160, 200, 30);
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setBounds(posX, posY + 195, 100, 40);
		
		txtTel = new JTextField();
		txtTel.setBounds(posX+70, posY + 200, 200, 30);
	
		JButton btnSignUp = new JButton("회원가입");
		btnSignUp.setBounds(80, 380, 100, 50);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isBlanks()) {
					JOptionPane.showMessageDialog(null, "빈 칸이 있습니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MemberDAO dao = new MemberDAO();
				
				Date date = new Date(new java.util.Date().getTime()); // 현재 날짜 가져오기
				MemberDTO dto = new MemberDTO(txtID.getText(), txtPW.getText(), txtName.getText(), txtAddress.getText(), txtTel.getText(), txtNickn.getText(), date, 1);
				dao.signUpMember(dto);
				setVisible(false);
				new Login();
				
				JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(210, 380, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		add(lblName);
		add(txtName);
		add(lblID);
		add(txtID);
		add(lblPW);
		add(txtPW);
		add(lblNickn);
		add(txtNickn);
		add(lblAddress);
		add(txtAddress);
		add(lblTel);
		add(txtTel);
		add(btnSignUp);
		add(btnBack);
		add(overBtn);
		add(overBtn2);
		setVisible(true);
	}
	
	boolean isBlanks() {
		if(txtID.getText().equals("") || txtPW.getText().equals("") || txtName.getText().equals("") || txtAddress.getText().equals("")) {
			return true;
		}
		return false;
	}

}
