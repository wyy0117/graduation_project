package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class registerrecord implements ActionListener {

	private JTable jTable;
	private DefaultTableModel tableModel;
	private TableColumn column;
	JButton delJButton;

	public JPanel record() {
		JPanel recordJPanel = new JPanel();
		String[] columnName = { "姓名", "电话", "日期", "楼号", "寝室号", "备注", };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));

		tableModel = (DefaultTableModel) jTable.getModel();
		JScrollPane jsp = new JScrollPane(jTable);
		recordJPanel.add(jsp);
		try {
			String selectString = "select * from register;";
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectString);
			while (rs.next()) {
				String nameString = rs.getString(1);
				String telString = rs.getString(2);
				Date date = rs.getDate(3);
				String lnumString = rs.getString(4);
				String rnumString = rs.getString(5);
				String contentString = rs.getString(6);
				Object[] columnObjects = { nameString, telString, date,
						lnumString, rnumString, contentString };
				tableModel.addRow(columnObjects);
			}
		} catch (Exception e) {
		}
		delJButton = new JButton("删除");
		delJButton.addActionListener(this);
		recordJPanel.add(delJButton);
		return recordJPanel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(delJButton)) {
			int row = jTable.getSelectedRow();
			String nameString = (String) jTable.getValueAt(row, 0);
			String telString = (String) jTable.getValueAt(row, 1);
			Date date = (Date) jTable.getValueAt(row, 2);
			String lnumString = (String) jTable.getValueAt(row, 3);
			String rnumString = (String) jTable.getValueAt(row, 4);
			String content = (String) jTable.getValueAt(row, 5);
			String delString = MySql.delete("register", "name='" + nameString
					+ "' and tel='" + telString + "' and date='" + date
					+  "' and lnum='" + lnumString + "' and rnum='" + rnumString
					+ "' and content='" + content + "'");
			int res = JOptionPane.showConfirmDialog(null, "确认删除？", "提示",
					JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				try {
					Statement st = Conn.getst();
					st.execute(delString);
					dialog.showMessage("删除成功！");
				} catch (Exception e1) {
					dialog.showMessage("出现了一个神奇的错误，Error：registerrecord");
				}

			}
		}
	}
}
