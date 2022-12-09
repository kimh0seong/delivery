package com.java.ex.businessmanagerment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.java.ex.dao.BusinessDAO;
import com.java.ex.dao.MemberDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;
import com.java.ex.login.Login;
import com.java.ex.login.SignUp;
import com.java.ex.usermanagement.Management;

public class BusinessManager extends JFrame {

	int posX = 20, posY = 0;

	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	Font font3 = new Font("돋움", Font.BOLD, 15);

	JTextField txtName;
	JTextField txtID;
	JPasswordField txtPW;
	JTextField txtNickn;
	JTextField txtAddress;
	JTextField txtTel;

	JPanel pane;
	JScrollPane scroll;
	ArrayList<JLabel> lblIDList = new ArrayList<JLabel>();
	ArrayList<JLabel> lblNameList = new ArrayList<JLabel>();
	ArrayList<JLabel> lblAddressList = new ArrayList<JLabel>();
	ArrayList<JLabel> lblTelList = new ArrayList<JLabel>();
	ArrayList<JLabel> lblRegdateList = new ArrayList<JLabel>();
	ArrayList<JButton> btnRecentOrderList = new ArrayList<JButton>();
	ArrayList<JButton> btnDeleteList = new ArrayList<JButton>();

	public BusinessManager() {
		MemberDTO manager = (MemberDTO)Session.getSession("manager");
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 10));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,1500,600);
		
		setTitle("업체 관리");
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
		lblID.setFont(font2);
		
		JLabel lblName = new JLabel("업체명");
		lblName.setBounds(posX + 320, posY+20, 150, 50);
		lblName.setFont(font2);
		
		JLabel lblAddress = new JLabel("주소");
		lblAddress.setBounds(posX + 420, posY+20, 300, 50);
		lblAddress.setFont(font2);
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setBounds(posX + 720, posY+20, 300, 50);
		lblTel.setFont(font2);
		
		JLabel lblRegdate = new JLabel("가입 날짜");
		lblRegdate.setBounds(posX +970, posY+20, 300, 50);
		lblRegdate.setFont(font2);
		
		
		add(lblID);
		add(lblName);
		add(lblAddress);
		add(lblTel);
		add(lblRegdate);
		
		BusinessDAO dao = new BusinessDAO();
		ArrayList<BusinessDTO> dtos = new ArrayList<BusinessDTO>();
		dtos = dao.selectAllBusiness();
		
		int cnt = 0;
		for(int i = 0; i < dtos.size(); i++) {
				Date date = dtos.get(i).getReg_date();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				//String date2= dateFormat.format(date);
				
				
				String b_id = dtos.get(i).getId();
				
				System.out.println(b_id);
			
				JLabel lblID2 = new JLabel(dtos.get(i).getId());
				lblID2.setBounds(posX + 40, posY + 10 + (cnt * 60), 150, 50);
				lblID2.setFont(font2);
				lblIDList.add(lblID2);
				
				JLabel lblName2 = new JLabel(dtos.get(i).getName());
				lblName2.setBounds(posX + 240, posY + 10 + (cnt * 60), 150, 50);
				lblName2.setFont(font2);
				lblNameList.add(lblName2);
				
				JLabel lblAddress2 = new JLabel(dtos.get(i).getAddress());
				lblAddress2.setBounds(posX + 340, posY + 10 + (cnt * 60), 300, 50);
				lblAddress2.setFont(font2);
				lblAddressList.add(lblAddress2);
				
				JLabel lblTel2 = new JLabel(dtos.get(i).getTel());
				lblTel2.setBounds(posX + 640, posY + 10 + (cnt * 60), 300, 50);
				lblTel2.setFont(font2);
				lblTelList.add(lblTel2);
				
				JLabel lblRegdate2 = new JLabel(date.toString());
				lblRegdate2.setBounds(posX + 1040, posY + 10 + (cnt * 60), 300, 50);
				lblRegdate2.setFont(font2);
				lblRegdateList.add(lblRegdate2);
				
				JButton btnRecentOrder = new JButton("최근 주문");
				btnRecentOrder.setBounds(posX + 1200, posY + 10 + (cnt * 60), 130, 50);
				btnRecentOrder.setFont(font3);
				btnRecentOrderList.add(btnRecentOrder);
				btnRecentOrder.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						//new UserRecord(m_id);
					}
				});
				
				MemberDAO deletedao = new MemberDAO();
				
				JButton btnDeletemember = new JButton("업체 삭제");
				btnDeletemember.setBounds(posX + 1340, posY + 10 + (cnt * 60), 130, 50);
				btnDeletemember.setFont(font3);
				btnDeleteList.add(btnDeletemember);
				btnDeletemember.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int delete = JOptionPane.showConfirmDialog(null, "고객을 삭제 하시겠습니까?", "고객 삭제",
								JOptionPane.YES_NO_OPTION);
						if (delete == JOptionPane.YES_OPTION) {
							deletedao.mDelete(b_id);
							lblID2.setVisible(false);
							lblName2.setVisible(false);
							lblAddress2.setVisible(false);
							lblTel2.setVisible(false);
							lblRegdate2.setVisible(false);
							btnDeletemember.setVisible(false);
							btnRecentOrder.setVisible(false);
							
							int index = lblIDList.indexOf(lblID2);
							
							lblIDList.remove(lblID2);
							lblNameList.remove(lblName2);
							lblAddressList.remove(lblAddress2);
							lblTelList.remove(lblTel2);
							lblRegdateList.remove(lblRegdate2);
							btnDeleteList.remove(btnDeletemember);
							btnRecentOrderList.remove(btnRecentOrder);
							
							for (int i = index; i < lblIDList.size(); i++) {
								Point IDPoint = lblIDList.get(i).getLocation();
								IDPoint.y -= 55;
								Point NamePoint = lblNameList.get(i).getLocation();
								NamePoint.y -= 55;
								Point AddressPoint = lblAddressList.get(i).getLocation();
								AddressPoint.y -= 55;
								Point TelPoint = lblTelList.get(i).getLocation();
								TelPoint.y -= 55;
								Point RegdatePoint = lblRegdateList.get(i).getLocation();
								RegdatePoint.y -= 55;
								Point OrderPoint = btnRecentOrderList.get(i).getLocation();
								OrderPoint.y -= 60;
								Point deletePoint = btnDeleteList.get(i).getLocation();
								deletePoint.y -= 60;

								lblIDList.get(i).setLocation(IDPoint);
								lblNameList.get(i).setLocation(NamePoint);
								lblAddressList.get(i).setLocation(AddressPoint);
								lblTelList.get(i).setLocation(TelPoint);
								lblRegdateList.get(i).setLocation(RegdatePoint);
								btnRecentOrderList.get(i).setLocation(OrderPoint);
								btnDeleteList.get(i).setLocation(deletePoint);
								
							}

							Dimension di = pane.getPreferredSize();
							di.height -= 60;
							pane.setPreferredSize(di);
							
						}
					}
				});

				
				pane.add(lblID2);
				pane.add(lblName2);
				pane.add(lblAddress2);
				pane.add(lblTel2);
				pane.add(lblRegdate2);
				pane.add(btnRecentOrder);
				pane.add(btnDeletemember);
				cnt++;
				
				Dimension di = pane.getPreferredSize();
				di.height += 60;
				pane.setPreferredSize(di);
			
		}
		
		JButton btnAddmember = new JButton("업체 추가");
		btnAddmember.setBounds(1330, 700, 250, 100);
		btnAddmember.setFont(font);
		btnAddmember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//new AddCustomer();
			}
			});
				
		
		add(btnAddmember);
		add(scroll);
		add(btnBack);
		setVisible(true);
	}
}
