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

public class classQuery implements ActionListener {

	JTextField classNameJTextField;
	JButton suerJButton;
	JTable jTable;
	DefaultTableModel tableModel;

	public JPanel classQueryJPanel() {
		JPanel classQuery = new JPanel();
		JLabel classJLabel = new JLabel("班级：");
		classQuery.add(classJLabel);
		classNameJTextField = new JTextField(15);
		classQuery.add(classNameJTextField);
		suerJButton = new JButton("查询");
		suerJButton.addActionListener(this);
		classQuery.add(suerJButton);

		String[] columnName = { "姓名", "班级", "楼号", "寝室号", "床位号", "电话" };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();

		JScrollPane jsp = new JScrollPane(jTable);
		classQuery.add(jsp);

		return classQuery;

	}

	public void actionPerformed(ActionEvent e) {
		String classNameString = classNameJTextField.getText();
		if (e.getSource().equals(suerJButton)) {
			tableModel.setRowCount(0);
			String selectStu = MySql.select("sname,class,lnum,rnum,bnum,stel",
					"student",  "class like '%" + classNameString + "%'");

			try {
				Statement st = Conn.getst();
				ResultSet rs = st.executeQuery(selectStu);
				
				while (rs.next()) {
					String sname = rs.getString(1);
					String classString = rs.getString(2);
					String lnum = rs.getString(3);
					String rnum = rs.getString(4);
					String bnum = rs.getString(5);
					String stel = rs.getString(6);
					String s[] = { sname, classString, lnum, rnum, bnum, stel };
					tableModel.addRow(s);

					jTable.invalidate();// 刷新
				}

			} catch (Exception e1) {
				e1.printStackTrace();
				dialog.showMessage("出现了一个神奇的错误，Error:classQuery");
			}

		}
	}
}
