package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import student.getAccount;
import JDBC.Conn;
import Sql.MySql;
import dialog.dialog;

public class updatepwd implements ActionListener{
	JTextField oldpwdJTextField;
	JTextField newpwdJTextField;
	JButton sureJButton;

	public JPanel updatepwdJPanel() {

		JPanel updatepwd = new JPanel();
		JLabel oldpwdJLabel = new JLabel("旧密码");
		updatepwd.add(oldpwdJLabel);
		oldpwdJTextField = new JTextField(10);
		updatepwd.add(oldpwdJTextField);

		JLabel newJLabel = new JLabel("新密码");
		updatepwd.add(newJLabel);
		newpwdJTextField = new JTextField(10);
		updatepwd.add(newpwdJTextField);

		sureJButton = new JButton("确定");
		sureJButton.addActionListener(this);
		updatepwd.add(sureJButton);

		return updatepwd;
	}

	public void actionPerformed(ActionEvent e) {

		String oldpwdString = oldpwdJTextField.getText();
		String newpwdString = newpwdJTextField.getText();
		String snumString = getAccount.getaccout();

		if (e.getSource().equals(sureJButton)) {
			String selectoldpwd = MySql.select("pwd", "admin", "account='"
					+ snumString + "'");
			Statement st;
			try {
				st = Conn.getst();
				ResultSet rs = st.executeQuery(selectoldpwd);
				if (rs.next()) {
					String getoldpwd = rs.getString(1);
					if (oldpwdString.equals(getoldpwd)) {// 旧密码输入正确
						String update = MySql.update("admin", "pwd='"
								+ newpwdString + "'", "account='" + snumString
								+ "'");
						st.execute(update);
						dialog.showMessage("修改成功！");
					} else {
						dialog.showMessage("密码错误，请重新输入！");
					}
				} else {
					dialog.showMessage("出现了一个神奇的错误，Errpr:updatepwd");
				}

			} catch (Exception e1) {
			}

		}
	}
}
