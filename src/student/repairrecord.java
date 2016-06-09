package student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;
import Sql.getData;

public class repairrecord {

	private JTable jTable;
	private DefaultTableModel tableModel;

	public JPanel repairRecord() {
		JPanel recordJPanel = new JPanel();

		String[] columnName = { "原因", "报修时间", "完成时间" };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();

		String snumString = getAccount.getaccout();
		String getstulnumString = getData.getstulnum(snumString);
		String getsturnumString = getData.getsturnum(snumString);
		String selectrepairString = MySql.select("content,start,end", "repair",
				"lnum='" + getstulnumString + "' and rnum='" + getsturnumString
						+ "'");
		try {
			Statement st = Conn.getst();
			System.out.println(selectrepairString);
			ResultSet rs = st.executeQuery(selectrepairString);
			while (rs.next()) {
				String contentString = rs.getString(1);
				Date start = rs.getDate(2);
				Date end = rs.getDate(3);
				Object[] o = { contentString, start, end };
				tableModel.addRow(o);

			}
		} catch (Exception e) {
		}

		JScrollPane jsp = new JScrollPane(jTable);
		recordJPanel.add(jsp);
		return recordJPanel;
	}
}
