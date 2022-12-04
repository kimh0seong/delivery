package com.java.ex.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.ex.dao.BusinessDAO;
import com.java.ex.dao.MemberDAO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;
import com.java.ex.login.Login;

public class CustomerModify extends JFrame {
	int posX = 50, posY = 50;
	
	JTextField txtName;
	JTextField txtID;
	JPasswordField txtPW;
	JTextField txtNickn;
	JTextField txtAddress;
	JTextField txtTel;
	
	public CustomerModify() {
		MemberDTO member = (MemberDTO)Session.getSession("member");
		setTitle("정보 수정");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JLabel lblID = new JLabel("아이디");
		lblID.setBounds(posX, posY, 80, 40);
		
		txtID = new JTextField();
		txtID.setBounds(posX+70, posY, 150, 30);
		txtID.setText(member.getId());
		txtID.setEditable(false);
		
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setBounds(posX, posY+35, 100, 40);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(posX+70, posY+35, 200, 30);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(posX, posY + 70, 100, 40);
		
		txtName = new JTextField();
		txtName.setBounds(posX+70, posY + 70, 100, 30);
		txtName.setText(member.getName());
		
		JLabel lblNickn = new JLabel("닉네임");
		lblNickn.setBounds(posX, posY + 110, 100, 40);
		
		txtNickn = new JTextField();
		txtNickn.setBounds(posX+70, posY + 110, 100, 30);
		txtNickn.setText(member.getNickn());
		
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
		txtAddress.setText(member.getAddress());
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setBounds(posX, posY + 195, 100, 40);
		
		txtTel = new JTextField();
		txtTel.setBounds(posX+70, posY + 200, 200, 30);
		txtTel.setText(member.getTel());
		
		JButton btnModify = new JButton("회원 수정");
		btnModify.setBounds(80, 380, 100, 50);
		btnModify.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isBlanks()) {
				JOptionPane.showMessageDialog(null, "빈 칸이 있습니다", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			MemberDAO dao = new MemberDAO();
			
			MemberDTO dto = dao.selectMember(txtID.getText());
			
			dto.setPw(txtPW.getText());
			dto.setName(txtName.getText());
			dto.setNickn(txtNickn.getText());
			dto.setAddress(txtAddress.getText());
			dto.setTel(txtTel.getText());
			
			dao.updateMember(dto);
			
			setVisible(false);
			new Login();
			
			JOptionPane.showMessageDialog(null, "정보 수정을 완료했습니다.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		});	
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(210, 380, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Customer();
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
		add(btnModify);
		add(btnBack);
		add(overBtn2);
		setVisible(true);
	}
		boolean isBlanks() {
			if(txtID.getText().equals("") || txtPW.getText().equals("") || txtName.getText().equals("") || txtNickn.getText().equals("") || txtAddress.getText().equals("") || txtTel.getText().equals("")) {
				return true;
			}
			return false;
	}
}