package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class inputwe implements ActionListener {

	public JPanel inputweJPanel;
	private JTextField lnumJTextField;
	private JTextField rnumJTextField;
	private JTextField waterJTextField;
	private JTextField elecJTextField;
	private JButton sureJButton;

	public JPanel inputweJPanel() {

		JPanel inputJPanel = new JPanel();
		JLabel lnumJLabel = new JLabel("楼号：", JLabel.CENTER);
		inputJPanel.add(lnumJLabel);
		lnumJTextField = new JTextField(5);
		inputJPanel.add(lnumJTextField);

		JLabel rnumJLabel = new JLabel("寝室号：", JLabel.CENTER);
		inputJPanel.add(rnumJLabel);
		rnumJTextField = new JTextField(5);
		inputJPanel.add(rnumJTextField);

		JLabel waterJLabel = new JLabel("水费：", JLabel.CENTER);
		inputJPanel.add(waterJLabel);
		waterJTextField = new JTextField(5);
		inputJPanel.add(waterJTextField);

		JLabel elecJLabel = new JLabel("电费：", JLabel.CENTER);
		inputJPanel.add(elecJLabel);
		elecJTextField = new JTextField(5);
		inputJPanel.add(elecJTextField);
		sureJButton = new JButton("提交");
		sureJButton.addActionListener(this);
		inputJPanel.add(sureJButton);

		return inputJPanel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(sureJButton)) {
			String lnumString = lnumJTextField.getText();
			String rnumString = rnumJTextField.getText();
			String waterString = waterJTextField.getText();
			String elecString = elecJTextField.getText();

			String updateString = MySql.update("room", "water='" + waterString
					+ "',elec='" + elecString + "'", "lnum='" + lnumString
					+ "' and rnum='" + rnumString + "'");
			try {
				Statement st = Conn.getst();
				st.execute(updateString);
				dialog.showMessage("录入成功！");

			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error:inputwe");
			}
		}
	}
}
