package admin;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import layout.gridBagConstraints;
import JDBC.Conn;
import Sql.MySql;
import Sql.getData;
import dialog.dialog;

public class exroom implements ActionListener {

	JTextField snumJTextField;
	JButton searchJButton;
	JTextField oldlnumJTextField;
	JTextField newlnumJTextField;
	JButton resetJButton;
	JTextField oldrnumJTextField;
	JTextField newrnumJTextField;
	JTextField oldbnumJTextField;
	JTextField newbnumJTextField;
	JButton sureJButton;

	public JPanel exroomJPanel() {
		JPanel exroomJPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		exroomJPanel.setLayout(gridBagLayout);

		JLabel snumJLabel = new JLabel("学号：", JLabel.CENTER);
		gridBagLayout.setConstraints(snumJLabel, new gridBagConstraints(0, 0,
				1, 1));
		exroomJPanel.add(snumJLabel);

		snumJTextField = new JTextField(10);
		gridBagLayout.setConstraints(snumJTextField, new gridBagConstraints(1,
				0, 2, 1));
		exroomJPanel.add(snumJTextField);

		searchJButton = new JButton("检索");

		gridBagLayout.setConstraints(searchJButton, new gridBagConstraints(4,
				0, 1, 1));
		exroomJPanel.add(searchJButton);

		JLabel oldJLabel = new JLabel("原寝室信息：", JLabel.CENTER);
		gridBagLayout.setConstraints(oldJLabel, new gridBagConstraints(0, 2, 2,
				1));
		exroomJPanel.add(oldJLabel);

		JLabel newJLabel = new JLabel("更改为:", JLabel.CENTER);
		gridBagLayout.setConstraints(newJLabel, new gridBagConstraints(3, 2, 1,
				1));
		exroomJPanel.add(newJLabel);

		JLabel oldlnumJLabel = new JLabel("楼号：", JLabel.CENTER);
		gridBagLayout.setConstraints(oldlnumJLabel, new gridBagConstraints(0,
				4, 1, 1));
		exroomJPanel.add(oldlnumJLabel);

		oldlnumJTextField = new JTextField(10);
		oldlnumJTextField.setEditable(false);
		gridBagLayout.setConstraints(oldlnumJTextField, new gridBagConstraints(
				1, 4, 1, 1));
		exroomJPanel.add(oldlnumJTextField);

		newlnumJTextField = new JTextField(10);
		gridBagLayout.setConstraints(newlnumJTextField, new gridBagConstraints(
				3, 4, 1, 1));
		exroomJPanel.add(newlnumJTextField);

		resetJButton = new JButton("重置");
		gridBagLayout.setConstraints(resetJButton, new gridBagConstraints(5, 4,
				1, 1));
		exroomJPanel.add(resetJButton);

		JLabel rnumJLabel = new JLabel("寝室号：", JLabel.CENTER);
		gridBagLayout.setConstraints(rnumJLabel, new gridBagConstraints(0, 5,
				1, 1));
		exroomJPanel.add(rnumJLabel);

		oldrnumJTextField = new JTextField(10);
		oldrnumJTextField.setEditable(false);
		gridBagLayout.setConstraints(oldrnumJTextField, new gridBagConstraints(
				1, 5, 1, 1));
		exroomJPanel.add(oldrnumJTextField);

		newrnumJTextField = new JTextField(10);
		gridBagLayout.setConstraints(newrnumJTextField, new gridBagConstraints(
				3, 5, 1, 1));
		exroomJPanel.add(newrnumJTextField);

		JLabel bnumJLabel = new JLabel("床位号：", JLabel.CENTER);
		gridBagLayout.setConstraints(bnumJLabel, new gridBagConstraints(0, 6,
				1, 1));
		exroomJPanel.add(bnumJLabel);

		oldbnumJTextField = new JTextField(10);
		oldbnumJTextField.setEditable(false);
		gridBagLayout.setConstraints(oldbnumJTextField, new gridBagConstraints(
				1, 6, 1, 1));
		exroomJPanel.add(oldbnumJTextField);

		newbnumJTextField = new JTextField(10);
		gridBagLayout.setConstraints(newbnumJTextField, new gridBagConstraints(
				3, 6, 1, 1));
		exroomJPanel.add(newbnumJTextField);

		sureJButton = new JButton("确定");
		gridBagLayout.setConstraints(sureJButton, new gridBagConstraints(5, 6,
				1, 1));
		exroomJPanel.add(sureJButton);

		searchJButton.addActionListener(this);

		sureJButton.addActionListener(this);

		resetJButton.addActionListener(this);
		return exroomJPanel;
	}

	public void actionPerformed(ActionEvent e) {

		String snum = snumJTextField.getText();

		String oldlnumString = getData.getstulnum(snum);
		String oldrnumString = getData.getsturnum(snum);
		String oldbnumString = getData.getstubnum(snum);

		if (e.getSource().equals(searchJButton)) {

			if (oldlnumString.equals("")) {
				dialog.showMessage("查无此生！");

			} else {
				oldlnumJTextField.setText(oldlnumString);
				oldrnumJTextField.setText(oldrnumString);
				oldbnumJTextField.setText(oldbnumString);

			}
		}
		if (e.getSource().equals(sureJButton)) {

			String newlnumString = newlnumJTextField.getText();
			String newrnumString = newrnumJTextField.getText();
			String newbnumString = newbnumJTextField.getText();
			String sex = getData.getStuSex(snum);
			if (sex.equals(getData.getlSex(newlnumString))) {// 性别无误
				if (getData.isExistBed(newlnumString, newrnumString,
						newbnumString)) {// 床位是否存在
					if (!getData.isExistStu(newlnumString, newrnumString,
							newbnumString)) {// 床位是否有人

						StringBuffer updatStudentesb = new StringBuffer();// 改学生表
						updatStudentesb.append("lnum='");
						updatStudentesb.append(newlnumString + "',");
						updatStudentesb.append("rnum='");
						updatStudentesb.append(newrnumString + "',");
						updatStudentesb.append("bnum='");
						updatStudentesb.append(newbnumString + "'");

						StringBuffer updateOldRoomsb = new StringBuffer();
						updateOldRoomsb.append("lnum='");
						updateOldRoomsb.append(oldlnumJTextField + "'");
						updateOldRoomsb.append("and rnum='");
						updateOldRoomsb.append(oldrnumJTextField + "'");

						StringBuffer updateNewRoomsb = new StringBuffer();
						updateNewRoomsb.append("lnum='");
						updateNewRoomsb.append(newlnumString + "'");
						updateNewRoomsb.append("and rnum='");
						updateNewRoomsb.append(newrnumString + "'");

						try {
							Statement st = Conn.getst();
							snum = snumJTextField.getText();
							String updateStudentString = MySql.update(
									"student", updatStudentesb.toString(),
									"snum='" + snum + "'");// 改学生表
							String updateNewRoomString = MySql.update("room",
									"scount=scount+1",
									updateNewRoomsb.toString());// 改新房间表
							String updateOldRoomString = MySql.update("room",
									"scount=scount-1",
									updateOldRoomsb.toString());// 改新房间表

							st.execute(updateStudentString);
							st.execute(updateNewRoomString);
							st.execute(updateOldRoomString);
							dialog.showMessage("调动成功！");
						} catch (Exception ex) {
							dialog.showMessage("出现一个神奇的错误，Error:exroom");
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
		if (e.getSource().equals(resetJButton)) {
			newbnumJTextField.setText("");
			newlnumJTextField.setText("");
			newrnumJTextField.setText("");
		}
	}
}
