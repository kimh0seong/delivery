package com.java.ex.login;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.ex.business.Business;
import com.java.ex.customer.Customer;
import com.java.ex.dao.BusinessDAO;
import com.java.ex.dao.MemberDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;
//import com.java.management.Management;
//import com.java.user.UserTestList;
import com.java.ex.dto.Session;

public class Login extends JFrame {
	
	private JLabel lblID, lblPW;
	private JTextField txtFieldID;
	private JPasswordField txtFieldPW;
	private JButton btnSignIn, btnSignUp, btnBSignIn;
	private JButton btnBSignUp;
	
	Point loginPoint;
	
	public Login() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		System.out.println("utilDate:" + utilDate);
		System.out.println("sqlDate:" + sqlDate);
		
		setTitle("로그인");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		loginPoint = new Point();
		loginPoint.setLocation(350, 300);
		
		lblID = new JLabel("ID");
		lblID.setBounds(loginPoint.x, loginPoint.y, 100, 30);
		
		lblPW = new JLabel("Password");
		lblPW.setBounds(loginPoint.x, loginPoint.y+50, 100, 30);
		
		txtFieldID = new JTextField(20);
		txtFieldID.setBounds(loginPoint.x+100, loginPoint.y, 200, 30);
		
		txtFieldPW = new JPasswordField(20);
		txtFieldPW.setBounds(loginPoint.x+100, loginPoint.y+50, 200, 30);
		
		btnSignIn = new JButton("고객 로그인");
		btnSignIn.setBounds(loginPoint.x, loginPoint.y+100, 150, 40);
		btnSignIn.addActionListener(new SignInEvent());
		
		btnBSignIn = new JButton("업체 로그인");
		btnBSignIn.setBounds(loginPoint.x+150, loginPoint.y+100, 150, 40);
		btnBSignIn.addActionListener(new BSignInEvent());
		
		btnSignUp = new JButton("고객용 회원가입");
		btnSignUp.setBounds(loginPoint.x, loginPoint.y+150, 300, 30);
		btnSignUp.addActionListener(new SignUpEvent());
		
		btnBSignUp = new JButton("업체용 회원가입");
		btnBSignUp.setBounds(loginPoint.x, loginPoint.y+190, 300, 30);
		btnBSignUp.addActionListener(new BSignUpEvent());
		
		
		add(lblID);
		add(lblPW);
		add(txtFieldID);
		add(txtFieldPW);
		add(btnSignIn);
		add(btnSignUp);
		add(btnBSignUp);
		add(btnBSignIn);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	//고객 로그인 이벤트	
	class SignInEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//setVisible(false);
			//new Management();
			
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = new MemberDTO();
			BusinessDAO dao2 = new BusinessDAO();
			BusinessDTO dto2 = new BusinessDTO();
			
			dto = dao.selectMember(txtFieldID.getText());
			dto2 = dao2.selectBusiness(txtFieldID.getText());
			
			if(dto==null && dto2==null) {
				JOptionPane.showMessageDialog(null, "잘못된 ID 또는 Password입니다.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(dto.getPw().equals(txtFieldPW.getText())) {
				if(dto.getAuthority()==1) {
					// 고객 창으로 넘어가기
					setVisible(false);
					new Customer();
					System.out.println("고객");
					Session.setSession("member", dto);
				}
				else if(dto.getAuthority()==2) {
					// 관리자 창으로 넘어가기
					setVisible(false);
					//new Management(dto);
					System.out.println("관리자");
					Session.setSession("member", dto);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "잘못된 ID 또는 Password입니다.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	//업체 로그인 이벤트	
		class BSignInEvent implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				//setVisible(false);
				//new Management();		
				
				MemberDAO dao = new MemberDAO();
				MemberDTO dto = new MemberDTO();
				BusinessDAO dao2 = new BusinessDAO();
				BusinessDTO dto2 = new BusinessDTO();
				
				dto = dao.selectMember(txtFieldID.getText());
				dto2 = dao2.selectBusiness(txtFieldID.getText());
				
				if(dto==null && dto2==null) {
					JOptionPane.showMessageDialog(null, "잘못된 ID 또는 Password입니다.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(dto2.getPw().equals(txtFieldPW.getText())) {
						setVisible(false);
						new Business();
						System.out.println("업체");
						Session.setSession("business", dto2);
					}
				
				else {
					JOptionPane.showMessageDialog(null, "잘못된 ID 또는 Password입니다.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	
	// 회원가입 이벤트
	class SignUpEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SignUp();
		}
	}
	
	class BSignUpEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BSignUp();
		}
	}
}
