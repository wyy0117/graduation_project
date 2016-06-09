package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class roomQuery implements ActionListener {

	private JTextField lnumJTextField;
	private JTextField rnumJTextField;
	private JButton searchJButton;
	private JTable jTable;
	private DefaultTableModel tableModel;

	public JPanel roomQueryJPanel() {

		JPanel roomJPanel = new JPanel();
		JLabel lnumJLabel = new JLabel("楼号：", JLabel.CENTER);
		roomJPanel.add(lnumJLabel);
		lnumJTextField = new JTextField(5);
		roomJPanel.add(lnumJTextField);
		JLabel rnumJLabel = new JLabel("寝室号：", JLabel.CENTER);
		roomJPanel.add(rnumJLabel);
		rnumJTextField = new JTextField(5);
		roomJPanel.add(rnumJTextField);
		searchJButton = new JButton("查询");
		searchJButton.addActionListener(this);
		roomJPanel.add(searchJButton);
		JPanel jp = new JPanel();
		jp.add(roomJPanel);

		String[] columnName = { "姓名", "学号", "班级", "电话" };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();

		JScrollPane jsp = new JScrollPane(jTable);
		jp.add(jsp);

		return jp;
	}

	public void actionPerformed(ActionEvent e) {

		String lnumString = lnumJTextField.getText();
		String rnumString = rnumJTextField.getText();

		if (e.getSource().equals(searchJButton)) {
			tableModel.setRowCount(0);
			try {
				Statement st = Conn.getst();
				String selectString = MySql.select("sname,snum,class,stel",
						"student", "lnum='" + lnumString + "'and " + " rnum='"
								+ rnumString + "'");
				ResultSet rs = st.executeQuery(selectString);

				while (rs.next()) {
					String sname = rs.getString(1);
					String snum = rs.getString(2);
					String classString = rs.getString(3);
					String stel = rs.getString(4);
					String s[] = { sname, snum, classString, stel };
					tableModel.addRow(s);

					jTable.invalidate();// 刷新
				}

			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error:roomQuery");
			}

		}
	}
}
