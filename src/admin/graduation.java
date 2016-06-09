package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class graduation implements ActionListener {

	private JTextField classJTextField;
	private JButton sureJButton;
	private JTable jTable;
	private DefaultTableModel tableModel;

	public JPanel graduationJPanel() {

		JPanel graduationJPanel = new JPanel();
		JLabel classJLabel = new JLabel("班级：", JLabel.CENTER);
		graduationJPanel.add(classJLabel);
		classJTextField = new JTextField(10);
		graduationJPanel.add(classJTextField);
		sureJButton = new JButton("确定");
		sureJButton.addActionListener(this);
		graduationJPanel.add(sureJButton);

		String[] columnName = { "姓名", "性别", "楼号", "寝室号", "床号", };
		String[][] rowdate = {};
		jTable = new JTable(new DefaultTableModel(rowdate, columnName));
		tableModel = (DefaultTableModel) jTable.getModel();
		JScrollPane jsp = new JScrollPane(jTable);
		graduationJPanel.add(jsp);
		return graduationJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		String stulnum;
		String sturnum;
		String stubnum;
		String sname;
		String sex;
		if (e.getSource().equals(sureJButton)) {
			tableModel.setRowCount(0);

			String classString = classJTextField.getText();
			String stuInfo = MySql.select("*", "student", "class='"
					+ classString + "'");
			try {
				Statement st = Conn.getst();
				ResultSet rs = st.executeQuery(stuInfo);
				while (rs.next()) {
					sname = rs.getString("sname");
					sex = rs.getString("sex");
					stulnum = rs.getString("lnum");
					sturnum = rs.getString("rnum");
					stubnum = rs.getString("bnum");
					String[] s = { sname, sex, stulnum, sturnum, stubnum };
					tableModel.addRow(s);

				}
			} catch (Exception e1) {
				dialog.showMessage("出现了一个神奇的错误，Error：graduation");
			}
			int res = JOptionPane.showConfirmDialog(null, "确认删除？", "提示",
					JOptionPane.YES_NO_OPTION);

			if (res == JOptionPane.YES_OPTION) {
				try {
					Statement st = Conn.getst();
					ResultSet rs = st.executeQuery(stuInfo);
					while (rs.next()) {
						sname = rs.getString("sname");
						sex = rs.getString("sex");
						stulnum = rs.getString("lnum");
						sturnum = rs.getString("rnum");
						stubnum = rs.getString("bnum");

						String updateroomString = MySql.update("room",
								"scount=scount-1", "lnum='" + stulnum
										+ "' and rnum='" + sturnum + "'");// 更改寝室表
						String updatebedString = MySql.update("bed",
								"status='0'", "lnum='" + stulnum
										+ "' and rnum='" + sturnum
										+ "' and bnum='" + stubnum + "'");// 更改床位表
						Statement st2 = Conn.getst();

						st2.execute(updateroomString);
						st2.execute(updatebedString);

					}
					String deleteStuString = MySql.delete("student", "class='"
							+ classString + "'");// 更改学生表
					st.execute(deleteStuString);
					dialog.showMessage("毕业成功！");
				} catch (Exception e1) {
					dialog.showMessage("出现了一个神奇的错误，Error：graduation");
				}
			}
		}
	}
}
