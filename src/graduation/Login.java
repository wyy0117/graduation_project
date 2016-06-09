package graduation;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import student.getAccount;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class Login extends JFrame implements ActionListener {

	JLabel typeJLabel;
	JComboBox<String> typeJComboBox;
	JLabel accountJLabel;
	JTextField accountJTextField;
	JLabel pwdJLabel;
	JPasswordField pwdJPasswordField;
	JPanel jp;
	JButton cancelJButton;
	JButton lojinJButton;

	public Login() {
		jp = new JPanel(new GridLayout(4, 2, 2, 2));
		typeJLabel = new JLabel("请选择：", JLabel.CENTER);
		jp.add(typeJLabel);
		typeJComboBox = new JComboBox<String>();
		typeJComboBox.addItem("管理员");
		typeJComboBox.addItem("学生");
		jp.add(typeJComboBox);
		accountJLabel = new JLabel("账号:", JLabel.CENTER);
		jp.add(accountJLabel);
		accountJTextField = new JTextField(15);
		jp.add(accountJTextField);
		pwdJLabel = new JLabel("密码:", JLabel.CENTER);
		jp.add(pwdJLabel);
		pwdJPasswordField = new JPasswordField(15);
		jp.add(pwdJPasswordField);
		cancelJButton = new JButton("取消");
		cancelJButton.addActionListener(this);
		jp.add(cancelJButton);
		lojinJButton = new JButton("登录");
		lojinJButton.addActionListener(this);
		jp.add(lojinJButton);
		jp.setBackground(Color.cyan);
		this.setTitle("登录");
		this.add(jp);
		this.setBounds(400, 200, 300, 200);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		Statement st = null;
		ResultSet rs = null;

		String name = accountJTextField.getText();
		String pwd = new String(pwdJPasswordField.getPassword());
		if (e.getSource().equals(cancelJButton)) {
			this.dispose();
		}
		if (e.getSource().equals(lojinJButton)) {

			getAccount.setaccount(name);//存储当前登录者的信息
			
			if (typeJComboBox.getSelectedItem().toString().equals("管理员")) {
				try {
					st = Conn.getst();
					String select_admin = "select * from admin;";
					rs = st.executeQuery(select_admin);
					while (rs.next()) {
						String getname = rs.getString(1);
						String getpwd = rs.getString(2);
						if (getname.equals(name)) {// 账号存在
							if (getpwd.equals(pwd)) {//密码正确
								new Admin();
								this.dispose();//关闭窗口
								return;//退出程序
							} else {
								dialog.showMessage("密码错误，请重新输入！");
								return;
							}
						} 

					}
					dialog.showMessage("账号不存在，请重新输入!");
					
				} catch (Exception e1) {
					dialog.showMessage("出现了一个神奇的错误，Error：Login");
				}
			}
			if (typeJComboBox.getSelectedItem().toString().equals("学生")) {

				try {
					st = Conn.getst();
					String select_stu = MySql.select("snum,pwd", "student", "snum='"+name+"'");
					rs = st.executeQuery(select_stu);
					while (rs.next()) {
						String getname = rs.getString(1);
						String getpwd = rs.getString(2);
						if (getname.equals(name)) {// 账号存在
							if (getpwd.equals(pwd)) {//密码正确
								new Student();
								this.dispose();//关闭窗口
								return;//退出程序
							} else {
								dialog.showMessage("密码错误，请重新输入！");
								return;
							}
						} else {
							dialog.showMessage("账号不存在，请重新输入!");
							return;
						}

					}
					
				} catch (Exception e1) {
					dialog.showMessage("出现了一个神奇的错误，Error：Login");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Login();
	}

}
