package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dialog.dialog;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import JDBC.Conn;
import Sql.MySql;

public class qianfei implements ActionListener {

	private JTable jTable;
	private DefaultTableModel tableModel;
	JButton printJButton;
	private Object writablet;

	public JPanel qianFeiJPanel() {
		JPanel qfJPanel = new JPanel();
		String[] columnName = { "楼号", "寝室号", "电费", "水费" };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();
		String selectString = MySql.select("lnum,rnum,water,elec", "room",
				"water>'0' or elec>'0'");
		try {
			Statement st = Conn.getst();
			ResultSet rs = st.executeQuery(selectString);
			while (rs.next()) {
				String lnumString = rs.getString(1);
				String rnumString = rs.getString(2);
				String waterString = rs.getString(3);
				String elecString = rs.getString(4);
				String[] s = { lnumString, rnumString, waterString, elecString };
				tableModel.addRow(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JScrollPane jsp = new JScrollPane(jTable);
		qfJPanel.add(jsp);
		printJButton = new JButton("打印");
		printJButton.addActionListener(this);
		qfJPanel.add(printJButton);

		return qfJPanel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(printJButton)) {
			WritableWorkbook wwb = null;
			try {
				wwb = Workbook.createWorkbook(new File("D:/qianfei.xls"));
				if (wwb != null) {
					WritableSheet ws = wwb.createSheet("sheet1", 0);

					Label lnumLabel;
					Label rnumLabel;
					Label waterLabel;
					Label elecLabel;
					lnumLabel = new Label(0, 0, "楼号");
					rnumLabel = new Label(1, 0, "寝室号");
					waterLabel = new Label(2, 0, "水费");
					elecLabel = new Label(3, 0, "电费");
					ws.addCell(lnumLabel);
					ws.addCell(rnumLabel);
					ws.addCell(waterLabel);
					ws.addCell(elecLabel);
					
					String selectString = MySql.select("lnum,rnum,water,elec",
							"room", "water>'0' or elec>'0'");
					Statement st = Conn.getst();
					ResultSet rs = st.executeQuery(selectString);
					
					int i = 1;
					while (rs.next()) {

						lnumLabel = new Label(0, i, rs.getString("lnum"));
						rnumLabel = new Label(1, i, rs.getString("rnum"));
						waterLabel = new Label(2, i, rs.getString("water"));
						elecLabel = new Label(3, i, rs.getString("elec"));

						ws.addCell(lnumLabel);
						ws.addCell(rnumLabel);
						ws.addCell(waterLabel);
						ws.addCell(elecLabel);
						i++;
					}

					wwb.write();
					dialog.showMessage("打印成功");
					wwb.close();

				}

			} catch (Exception e2) {
			}
		}
	}
}
