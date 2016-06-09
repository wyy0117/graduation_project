package admin;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;
import Sql.getData;

import layout.gridBagConstraints;

public class xiuxue implements ActionListener {

	private JTextField snumJTextField;
	private JButton checkJButton;
	private JTextField snameJTextField;
	private JTextField classJTextField;
	private JButton sureJButton;

	public JPanel xieXue() {
		JPanel xiuXueJPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		xiuXueJPanel.setLayout(gridBagLayout);
		JLabel snumJLabel = new JLabel("学号：");
		gridBagLayout.setConstraints(snumJLabel, new gridBagConstraints(0, 0,
				1, 1));
		xiuXueJPanel.add(snumJLabel);
		snumJTextField = new JTextField(10);
		gridBagLayout.setConstraints(snumJTextField, new gridBagConstraints(1,
				0, 2, 1));
		xiuXueJPanel.add(snumJTextField);

		checkJButton = new JButton("核对");
		checkJButton.addActionListener(this);
		gridBagLayout.setConstraints(checkJButton, new gridBagConstraints(4, 0,
				1, 1));
		xiuXueJPanel.add(checkJButton);

		JLabel snameJLabel = new JLabel("姓名：");
		gridBagLayout.setConstraints(snameJLabel, new gridBagConstraints(0, 1,
				1, 1));
		xiuXueJPanel.add(snameJLabel);
		snameJTextField = new JTextField(5);
		snameJTextField.setEditable(false);
		gridBagLayout.setConstraints(snameJTextField, new gridBagConstraints(1,
				1, 1, 1));
		xiuXueJPanel.add(snameJTextField);

		JLabel classJLabel = new JLabel("班级：");
		gridBagLayout.setConstraints(classJLabel, new gridBagConstraints(2, 1,
				1, 1));
		xiuXueJPanel.add(classJLabel);
		classJTextField = new JTextField(10);
		classJTextField.setEditable(false);
		gridBagLayout.setConstraints(classJTextField, new gridBagConstraints(3,
				1, 1, 1));
		xiuXueJPanel.add(classJTextField);

		sureJButton = new JButton("确定");
		sureJButton.addActionListener(this);
		gridBagLayout.setConstraints(sureJButton, new gridBagConstraints(4, 1,
				1, 1));
		xiuXueJPanel.add(sureJButton);

		return xiuXueJPanel;
	}

	public void actionPerformed(ActionEvent e) {
		String snum = snumJTextField.getText();

		String stunameString = getData.getstuname(snum);
		String stuclassString = getData.getstuclass(snum);
		String stulnumString = getData.getstulnum(snum);
		String sturnumString = getData.getsturnum(snum);
		String stubnumString = getData.getstubnum(snum);

		if (e.getSource().equals(checkJButton)) {

			if (!stunameString.equals("")) {
				snameJTextField.setText(stunameString);
				classJTextField.setText(stuclassString);

			} else {
				dialog.showMessage("查无此生！");

			}

		}
		if (e.getSource().equals(sureJButton)) {

			String deletestuString = MySql.delete("student", "snum='" + snum
					+ "'");// 删除学生表中的信息
			String updateroomString = MySql.update("room", "scount=scount-1",
					"lnum='" + stulnumString + "' and room='" + sturnumString
							+ "'");// 修改寝室表
			String updatebedString = MySql.update("bed", "status='0'", "lnum='"
					+ stulnumString + "' and rnum='" + sturnumString
					+ "' and bnum='" + stubnumString + "'");// 修改床位表

			System.out.println(deletestuString);
			System.out.println(updatebedString);
			System.out.println(updateroomString);

			Statement st2;
			try {
				st2 = Conn.getst();
				st2.execute(deletestuString);
				st2.execute(updatebedString);
				st2.execute(updateroomString);
				dialog.showMessage("修学成功！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}
