package admin;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;
import Sql.getData;

import layout.gridBagConstraints;

public class newStu implements ActionListener {

	JTextField snameJTextField;
	JTextField snumJTextField;
	ButtonGroup sexButtonGroup;
	JTextField classJTextField;
	JTextField stelJTextField;
	JTextField lnumJTextField;
	JTextField rnumJTextField;
	JTextField bnumJTextField;
	JButton resetJButton;
	JButton sureJButton;
	JRadioButton maleJRadioButton;
	JRadioButton femaleJRadioButton;

	public JPanel newStuJPanel() {
		JPanel newStuJPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		newStuJPanel.setLayout(gridBagLayout);

		JLabel snameJLabel = new JLabel("姓名：", JLabel.CENTER);
		gridBagLayout.setConstraints(snameJLabel, new gridBagConstraints(0, 0,
				2, 1));
		newStuJPanel.add(snameJLabel);

		snameJTextField = new JTextField(15);
		gridBagLayout.setConstraints(snameJTextField, new gridBagConstraints(2,
				0, 3, 1));
		newStuJPanel.add(snameJTextField);

		JLabel snumJLabel = new JLabel("学号：", JLabel.CENTER);
		gridBagLayout.setConstraints(snumJLabel, new gridBagConstraints(0, 1,
				2, 1));
		newStuJPanel.add(snumJLabel);

		snumJTextField = new JTextField(15);
		gridBagLayout.setConstraints(snumJTextField, new gridBagConstraints(2,
				1, 3, 1));
		newStuJPanel.add(snumJTextField);

		JLabel sexJLabel = new JLabel("性别：", JLabel.CENTER);
		gridBagLayout.setConstraints(sexJLabel, new gridBagConstraints(0, 2, 2,
				1));
		newStuJPanel.add(sexJLabel);

		sexButtonGroup = new ButtonGroup();
		maleJRadioButton = new JRadioButton("男");
		sexButtonGroup.add(maleJRadioButton);
		gridBagLayout.setConstraints(maleJRadioButton, new gridBagConstraints(
				2, 2, 1, 1));
		newStuJPanel.add(maleJRadioButton);

		femaleJRadioButton = new JRadioButton("女");
		sexButtonGroup.add(femaleJRadioButton);

		gridBagLayout.setConstraints(femaleJRadioButton,
				new gridBagConstraints(3, 2, 3, 1));
		newStuJPanel.add(femaleJRadioButton);

		JLabel classsJLabel = new JLabel("班级：", JLabel.CENTER);
		gridBagLayout.setConstraints(classsJLabel, new gridBagConstraints(0, 3,
				2, 1));
		newStuJPanel.add(classsJLabel);

		classJTextField = new JTextField(15);
		gridBagLayout.setConstraints(classJTextField, new gridBagConstraints(2,
				3, 3, 1));
		newStuJPanel.add(classJTextField);

		JLabel stelJLabel = new JLabel("电话：", JLabel.CENTER);
		gridBagLayout.setConstraints(stelJLabel, new gridBagConstraints(0, 4,
				2, 1));
		newStuJPanel.add(stelJLabel);

		stelJTextField = new JTextField(15);
		gridBagLayout.setConstraints(stelJTextField, new gridBagConstraints(2,
				4, 3, 1));
		newStuJPanel.add(stelJTextField);

		JLabel lnumJLabel = new JLabel("楼号：", JLabel.CENTER);
		gridBagLayout.setConstraints(lnumJLabel, new gridBagConstraints(0, 5,
				2, 1));
		newStuJPanel.add(lnumJLabel);

		lnumJTextField = new JTextField(15);
		gridBagLayout.setConstraints(lnumJTextField, new gridBagConstraints(2,
				5, 3, 1));
		newStuJPanel.add(lnumJTextField);

		JLabel rnumJLabel = new JLabel("寝室号：", JLabel.CENTER);
		gridBagLayout.setConstraints(rnumJLabel, new gridBagConstraints(0, 6,
				2, 1));
		newStuJPanel.add(rnumJLabel);

		rnumJTextField = new JTextField(15);
		gridBagLayout.setConstraints(rnumJTextField, new gridBagConstraints(2,
				6, 3, 1));
		newStuJPanel.add(rnumJTextField);

		JLabel bnumJLabel = new JLabel("床位号:");
		gridBagLayout.setConstraints(bnumJLabel, new gridBagConstraints(0, 7,
				2, 1));
		newStuJPanel.add(bnumJLabel);

		bnumJTextField = new JTextField(15);
		gridBagLayout.setConstraints(bnumJTextField, new gridBagConstraints(2,
				7, 3, 1));
		newStuJPanel.add(bnumJTextField);

		resetJButton = new JButton("重置");
		resetJButton.addActionListener(this);
		gridBagLayout.setConstraints(resetJButton, new gridBagConstraints(2, 8,
				1, 1));
		newStuJPanel.add(resetJButton);

		sureJButton = new JButton("确定");
		sureJButton.addActionListener(this);
		gridBagLayout.setConstraints(sureJButton, new gridBagConstraints(4, 8,
				1, 1));
		newStuJPanel.add(sureJButton);
		return newStuJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		String sname = snameJTextField.getText();
		String snum = snumJTextField.getText();
		String sex = maleJRadioButton.isSelected() ? "男" : "女";
		String classString = classJTextField.getText();
		String stel = stelJTextField.getText();
		String lnum = lnumJTextField.getText();
		String rnum = rnumJTextField.getText();
		String bnum = bnumJTextField.getText();

		if (e.getSource().equals(resetJButton)) {
			snameJTextField.setText("");
			snumJTextField.setText("");
			classJTextField.setText("");
			stelJTextField.setText("");
			lnumJTextField.setText("");
			rnumJTextField.setText("");
			bnumJTextField.setText("");

		}
		if (e.getSource().equals(sureJButton)) {

			if (sex.equals(getData.getlSex(lnum))) {// 性别无误
				if (getData.isExistBed(lnum, rnum, bnum)) {// 床位是否存在
					if (!getData.isExistStu(lnum, rnum, bnum)) {// 床位是否有人

						StringBuffer student = new StringBuffer();// 改学生表
						StringBuffer bed = new StringBuffer();// 改床位表
						StringBuffer room = new StringBuffer();// 改寝室表

						student.append("'" + sname + "','");
						student.append(snum + "','");
						student.append(sex + "','");
						student.append(classString + "','");
						student.append(stel + "','");
						student.append(lnum + "','");
						student.append(rnum + "','");
						student.append(bnum + "','");
						student.append("1'");

						bed.append("lnum='");
						bed.append(lnum + "' and ");
						bed.append("rnum='");
						bed.append(rnum + "' and ");
						bed.append("bnum='");
						bed.append(bnum + "'");

						room.append("lnum='");
						room.append(lnum + "' and ");
						room.append("rnum='");
						room.append(rnum + "'");

						try {
							Statement st = Conn.getst();
							String insertString = MySql.insert("student", "*",
									student.toString());// 学生表
							st.execute(insertString);

							String updatetBed = MySql.update("bed",
									"status='1'", bed.toString());// 床位表
							st.execute(updatetBed);

							String updateRoom = MySql.update("room",
									"scount=scount+1", room.toString());// 寝室表
							st.execute(updateRoom);

							dialog.showMessage("分配成功！");
						} catch (Exception e1) {
							e1.printStackTrace();
							dialog.showMessage("出现一个神奇的错误，Error:newStu");
						}

					} else {
						dialog.showMessage("该床位已有人！");
					}
				} else {
					dialog.showMessage("床位不存在！");
				}

			} else {
				dialog.showMessage("性别有误");
			}

		}
	}
}
