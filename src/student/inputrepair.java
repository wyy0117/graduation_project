package student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.interfaces.RSAKey;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;
import Sql.getData;

public class inputrepair implements ActionListener {

	private JTextField reasonJTextField;
	private JButton sureJButton;

	public JPanel inputJPanel() {

		JPanel inputJPanel = new JPanel();
		JLabel reasonJLabel = new JLabel("报修原因：");
		inputJPanel.add(reasonJLabel);
		reasonJTextField = new JTextField(20);
		inputJPanel.add(reasonJTextField);

		sureJButton = new JButton("提交");
		sureJButton.addActionListener(this);
		inputJPanel.add(sureJButton);
		return inputJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		String reasonString = reasonJTextField.getText();
		String stulunString = getData.getstulnum(getAccount.getaccout());
		String sturnumString = getData.getsturnum(getAccount.getaccout());

		if (e.getSource().equals(sureJButton)) {
			String insertrepairString = MySql.insert("repair",
					"lnum,rnum,content,start", "'" + stulunString + "','"
							+ sturnumString + "','" + reasonString + "' ,curdate()");
			try {
				Statement st = Conn.getst();
				st.execute(insertrepairString);
				dialog.showMessage("报修成功！");

			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error：inputrepair");
			}

		}
	}
}
