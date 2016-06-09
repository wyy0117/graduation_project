package student;

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

public class classmates {

	private JTable jTable;
	private DefaultTableModel tableModel;

	public JPanel classmateJPanel() {
		JPanel classmatesJPanel = new JPanel();

		String[] columnName = { "姓名", "电话", "楼号", "寝室号", "床号", };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();
		JScrollPane jsp = new JScrollPane(jTable);
		String stuclassString = getData.getClass(getAccount.getaccout());
		String selectString = MySql.select("sname,stel,lnum,rnum,bnum",
				"student", "class='" + stuclassString + "'");
		classmatesJPanel.add(jsp);

		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectString);
			while (rs.next()) {
				String nameString = rs.getString(1);
				String telString = rs.getString(2);
				String lnumString = rs.getString(3);
				String rnumString = rs.getString(4);
				String bnumString = rs.getString(5);
				String[] s = { nameString, telString, lnumString, rnumString,
						bnumString };
				tableModel.addRow(s);
			}

		} catch (Exception e) {
			dialog.showMessage("出现了一个神奇的错误，Errpr:classmates");
		}

		return classmatesJPanel;
	}
}
