package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class repaired implements ActionListener {

	private JButton sureJButton;
	JButton delJButton;
	private JTable jTable;
	private DefaultTableModel tableModel;

	public JPanel repaired() {
		JPanel repairedJPanel = new JPanel();
		String[] columnName = { "楼号", "寝室号", "原因", "报修时间", "修理时间" };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();
		JScrollPane jsp = new JScrollPane(jTable);
		repairedJPanel.add(jsp);
		String selectrepairString = MySql.select("*", "repair",
				"start is not null and end is not null");// 只有已修的才会显示出来

		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectrepairString);
			while (rs.next()) {
				String lnumString = rs.getString(1);
				String rnumString = rs.getString(2);
				String reasonString = rs.getString(3);
				Date start = rs.getDate(4);
				Date end = rs.getDate(5);
				Object[] o = { lnumString, rnumString, reasonString, start, end };
				tableModel.addRow(o);
			}
		} catch (Exception e) {
			dialog.showMessage("出现了一个神奇的错误，Error:repairQuery");
		}
		delJButton = new JButton("删除");
		delJButton.addActionListener(this);
		repairedJPanel.add(delJButton);

		return repairedJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(delJButton)) {
			int row = jTable.getSelectedRow();
			String lnumString = (String) jTable.getValueAt(row, 0);
			String rnumString = (String) jTable.getValueAt(row, 1);
			String content = (String) jTable.getValueAt(row, 2);
			Date start = (Date) jTable.getValueAt(row, 3);
			Date end = (Date) jTable.getValueAt(row, 4);
			String delString = MySql.delete("repair", "lnum='" + lnumString
					+ "' and rnum='" + rnumString + "' and content ='"
					+ content + "' and start='" + start + "' and end ='" + end+"'");
			int res = JOptionPane.showConfirmDialog(null, "确认删除？", "提示",
					JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				try {
					Statement st = Conn.getst();
					st.execute(delString);
					dialog.showMessage("删除成功！");

				} catch (Exception e1) {
					dialog.showMessage("出现了一个神奇的错误，Error：repaired");
				}

			}
		}
	}

}
