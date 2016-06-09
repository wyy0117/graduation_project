package admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.lang.model.element.Element;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;

public class repairQuery implements ActionListener {

	private JTable jTable;
	private DefaultTableModel tableModel;
	JButton printJButton;
	JButton repairedJButton;
	

	public JPanel repairQueryJPanel() {
		JPanel queryJPanel = new JPanel();
		String[] columnName = { "楼号", "寝室号", "原因", "报修时间" };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();

		String selectrepairString = MySql.select("*", "repair",
				"start is not null and end is null");// 只有已报修且未修的才会显示出来
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectrepairString);
			while (rs.next()) {
				String lnumString = rs.getString(1);
				String rnumString = rs.getString(2);
				String reasonString = rs.getString(3);
				Date date = rs.getDate(4);
				Object[] o = { lnumString, rnumString, reasonString, date };
				tableModel.addRow(o);
			}
		} catch (Exception e) {
			dialog.showMessage("出现了一个神奇的错误，Error:repairQuery");
		}

		JScrollPane jsp = new JScrollPane(jTable);
		queryJPanel.add(jsp);
		JPanel buttonJPanel = new JPanel(new GridLayout(2, 1, 2, 2));

		printJButton = new JButton("打印");
		printJButton.addActionListener(this);
		repairedJButton = new JButton("已修理");
		repairedJButton.addActionListener(this);
		

		buttonJPanel.add(printJButton);
		buttonJPanel.add(repairedJButton);
		queryJPanel.add(buttonJPanel);
		return queryJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(printJButton)) {
			WritableWorkbook wwb = null;
			try {
				wwb = Workbook.createWorkbook(new File("D:/repair.xls"));
				if (wwb != null) {
					WritableSheet ws = wwb.createSheet("sheet1", 0);
					Label lnumLabel;
					Label rnumLabel;
					Label content;
					Label start;
					lnumLabel = new Label(0, 0, "楼号");
					rnumLabel = new Label(1, 0, "寝室号");
					content = new Label(2, 0, "原因");
					start = new Label(3, 0, "时间");
					ws.addCell(lnumLabel);
					ws.addCell(rnumLabel);
					ws.addCell(content);
					ws.addCell(start);
					int i = 1;
					String selectString = MySql.select("*", "repair",
							"start is not null and end is null");// 只有已报修且未修的才会打印出来
					Statement st = Conn.getst();
					ResultSet rs = st.executeQuery(selectString);
					while (rs.next()) {
						lnumLabel = new Label(0, i, rs.getString("lnum"));
						rnumLabel = new Label(1, i, rs.getString("rnum"));
						content = new Label(2, i, rs.getString("content"));
						start = new Label(3, i, rs.getString("start"));

						ws.addCell(lnumLabel);
						ws.addCell(rnumLabel);
						ws.addCell(content);
						ws.addCell(start);
						i++;

					}

					wwb.write();
					dialog.showMessage("打印成功");
					wwb.close();

				}

			} catch (Exception e2) {
			}
		}
		if (e.getSource().equals(repairedJButton)) {
			int row = jTable.getSelectedRow();
			String lnumString = (String) jTable.getValueAt(row, 0);
			String rnumString = (String) jTable.getValueAt(row, 1);
			String updaterepairString = MySql.update("repair", "end=curdate()",
					"lnum='" + lnumString + "' and rnum='" + rnumString
							+ "' and end is null");
			Statement st;
			try {
				st = Conn.getst();
				st.execute(updaterepairString);
				dialog.showMessage("登记成功！");
			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error:repairedJButton");
			}
		}
		
	}
}
