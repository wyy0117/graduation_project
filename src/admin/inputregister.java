package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class inputregister implements ActionListener {

	private JTextField nameJTextField;
	private JTextField telJTextField;
	private JTextArea contentJTextArea;
	private JButton sureJButton;
	private JTextField lnumJTextField;
	private JTextField rnumJTextField;

	public JPanel inputJPanel() {
		JPanel inputJPanel = new JPanel();
		JLabel nameJLabel = new JLabel("姓名：", JLabel.CENTER);
		inputJPanel.add(nameJLabel);
		nameJTextField = new JTextField(5);
		inputJPanel.add(nameJTextField);

		JLabel telJLabel = new JLabel("电话", JLabel.CENTER);
		inputJPanel.add(telJLabel);
		telJTextField = new JTextField(10);
		inputJPanel.add(telJTextField);

		JLabel lnumJLabel = new JLabel("楼号");
		inputJPanel.add(lnumJLabel);
		lnumJTextField = new JTextField(10);
		inputJPanel.add(lnumJTextField);

		JLabel rnumJLabel = new JLabel("寝室号");
		inputJPanel.add(rnumJLabel);
		rnumJTextField = new JTextField(10);
		inputJPanel.add(rnumJTextField);

		JLabel contentJLabel = new JLabel("备注：");
		inputJPanel.add(contentJLabel);
		contentJTextArea = new JTextArea(2, 15);
		inputJPanel.add(contentJTextArea);

		sureJButton = new JButton("登记");
		sureJButton.addActionListener(this);

		inputJPanel.add(sureJButton);

		return inputJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(sureJButton)) {
			String getname = nameJTextField.getText();
			String gettel = telJTextField.getText();
			String getlnumString = lnumJTextField.getText();
			String getrnumString = rnumJTextField.getText();
			String content = contentJTextArea.getText();
			String insert = MySql.insert("register", "*", "'" + getname + "','"
					+ gettel + "', curdate() ,'" + getlnumString + "','"
					+ getrnumString + "','" + content + "'");
			dialog.showMessage("登记成功！");
			try {
				Statement st = Conn.getst();
				st.execute(insert);
			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error:inputregister");
			}
		}
	}
}
