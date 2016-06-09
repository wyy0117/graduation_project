package admin;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import layout.gridBagConstraints;
import JDBC.Conn;
import Sql.MySql;
import Sql.getData;
import dialog.dialog;

public class init implements ActionListener {

	JTextField lnumJTextField;
	JTextField rnumJTextField;
	JTextField countJTextField;
	JButton resetButton;
	JButton sureJButton;

	public JPanel initJPanel() {
		JPanel initJPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();// 网格包布局
		initJPanel.setLayout(gridBagLayout);

		JLabel lnumjJLabel = new JLabel("楼号：", JLabel.CENTER);
		gridBagLayout.setConstraints(lnumjJLabel, new gridBagConstraints(0, 0,
				2, 1));
		initJPanel.add(lnumjJLabel);

		lnumJTextField = new JTextField(15);
		gridBagLayout.setConstraints(lnumJTextField, new gridBagConstraints(2,
				0, 3, 1));
		initJPanel.add(lnumJTextField);

		JLabel rnumJLabel = new JLabel("寝室号：", JLabel.CENTER);
		gridBagLayout.setConstraints(rnumJLabel, new gridBagConstraints(0, 1,
				2, 1));
		initJPanel.add(rnumJLabel);

		rnumJTextField = new JTextField(15);
		gridBagLayout.setConstraints(rnumJTextField, new gridBagConstraints(2,
				1, 3, 1));
		initJPanel.add(rnumJTextField);

		JLabel countJLabel = new JLabel("床位数：", JLabel.CENTER);
		gridBagLayout.setConstraints(countJLabel, new gridBagConstraints(0, 2,
				2, 1));
		initJPanel.add(countJLabel);

		countJTextField = new JTextField(15);
		gridBagLayout.setConstraints(countJTextField, new gridBagConstraints(2,
				2, 3, 1));
		initJPanel.add(countJTextField);

		resetButton = new JButton("重置");
		resetButton.addActionListener(this);
		gridBagLayout.setConstraints(resetButton, new gridBagConstraints(2, 4,
				1, 1));
		initJPanel.add(resetButton);

		sureJButton = new JButton("确定");
		sureJButton.addActionListener(this);
		gridBagLayout.setConstraints(sureJButton, new gridBagConstraints(4, 4,
				1, 1));
		initJPanel.add(sureJButton);
		return initJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(resetButton)) {
			lnumJTextField.setText("");
			rnumJTextField.setText("");
			countJTextField.setText("");
		}
		if (e.getSource().equals(sureJButton)) {
			String lnum = lnumJTextField.getText();
			String rnum = rnumJTextField.getText();
			String countString = countJTextField.getText();
			int getscount = getData.getcount("scount", lnum, rnum);
			if (getData.isExistBuild(lnum)) {
				if (getscount == 0) {// 寝室内无学生
					int count = 0;
					if (init.isnum(countString)) {// 判断床位数是否是数字
						count = Integer.valueOf(countString);
					} else {
						dialog.showMessage("床位数只能是数字！");
						return;
					}
					StringBuffer sb = new StringBuffer();

					sb.append(lnum);
					sb.append(", ");
					sb.append(rnum);
					sb.append(", ");
					sb.append(count);

					String insertroom = MySql.insert("room", "lnum,rnum,count", "'"
							+ lnum + "','" + rnum + "'," + count);// 寝室表
					try {

						Statement st = Conn.getst();
						st.execute(insertroom);

						for (int i = 1; i <= count; i++) {
							String insertbed = MySql.insert("bed", "*", "'" + lnum
									+ "','" + rnum + "','" + i + "','0'");
							st.execute(insertbed);// 床位表
						}
						dialog.showMessage("初始化成功！");

						st.close();
					} catch (Exception ex) {
						dialog.showMessage("出现一个神奇的错误，Error:init");
					}
				} else {
					dialog.showMessage("寝室内有学生，无法初始化！");
				}

			}else {
				dialog.showMessage("寝室楼不存在！");
			}

		}
	}

	public static boolean isnum(String string) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(string).matches();
	}
}
