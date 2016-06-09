package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class stuQuery implements ActionListener {

	private JTextField snameJTextField;
	private JButton searchJButton;
	private JTable jTable;
	private DefaultTableModel tableModel;

	public JPanel stuQuery() {

		JPanel stuJPanel = new JPanel();
		JLabel snameJLabel = new JLabel("姓名：");
		stuJPanel.add(snameJLabel);
		snameJTextField = new JTextField(5);
		stuJPanel.add(snameJTextField);
		searchJButton = new JButton("查询");
		stuJPanel.add(searchJButton);
		searchJButton.addActionListener(this);
		String[] columnName = { "学号", "性别", "班级", "电话", "楼号", "寝室号", "床号", };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		jTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		tableModel = (DefaultTableModel) jTable.getModel();
		JScrollPane jsp = new JScrollPane(jTable);
		stuJPanel.add(jsp);

		return stuJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(searchJButton)) {

			tableModel.setRowCount(0);
			String snameString = snameJTextField.getText();
			String seleString = MySql.select(
					"sname,sex,class,stel,lnum,rnum,bnum", "student", "sname like'%"
							+ snameString + "%'");

			try {
				Statement st = Conn.getst();
				ResultSet rs = st.executeQuery(seleString);
				while (rs.next()) {
					String sname = rs.getString(1);
					String sex = rs.getString(2);
					String classString = rs.getString(3);
					String stel = rs.getString(4);
					String lnum = rs.getString(5);
					String rnum = rs.getString(6);
					String bnum = rs.getString(7);
					String[] s = { sname, sex, classString, stel, lnum, rnum,
							bnum };
					tableModel.addRow(s);

					jTable.invalidate();// 刷新
				
				}
			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error：stuQuery");
			}
		}
	}
}
