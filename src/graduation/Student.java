package graduation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import student.classmates;
import student.getAccount;
import student.inputrepair;
import student.updatepwd;
import student.wequery;
import student.repairrecord;

import admin.help;
import layout.menu;

public class Student extends JFrame implements ActionListener {

	JPanel jp;
	JMenuBar jmb;
	JMenu queryJMenu;
	JMenu repairJMenu;
	JMenu pwdJMenu;
	JMenu heleJMenu;
	public Student() {
		jp = new JPanel();
		jmb = new JMenuBar();
		queryJMenu = new JMenu("查询");
		menu.addMenuItem(queryJMenu, "同学资料", this);
		menu.addMenuItem(queryJMenu, "水电费", this);
		jmb.add(queryJMenu);

		repairJMenu = new JMenu("报修");
		menu.addMenuItem(repairJMenu, "寝室报修", this);
		//menu.addMenuItem(repairJMenu, "报修记录", this);
		jmb.add(repairJMenu);

		pwdJMenu=new JMenu("密码");
		menu.addMenuItem(pwdJMenu, "修改密码", this);
		jmb.add(pwdJMenu);
		
		heleJMenu = new JMenu("帮助");
		menu.addMenuItem(heleJMenu, "关于", this);
		menu.addMenuItem(heleJMenu, "退出", this);
		jmb.add(heleJMenu);

		JLabel welcome = new JLabel("欢迎使用宿舍管理系统", JLabel.CENTER);
		welcome.setFont(new Font("宋体", Font.BOLD, 30));
		
		jp.add(welcome);
		this.setTitle("学生系统");
		this.add(jmb, BorderLayout.NORTH);
		this.add(jp);
		this.setBounds(400, 50, 600, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("同学资料")) {
			this.remove(jp);
			jp=new classmates().classmateJPanel();
			this.add(jp);
			this.setVisible(true);

		}
		if (e.getActionCommand().equals("水电费")) {
			this.remove(jp);
			jp=new wequery().weQueryJPanel();
			this.add(jp);
			this.setVisible(true);

		}
		if (e.getActionCommand().equals("修改密码")) {
			this.remove(jp);
			jp=new updatepwd().updatepwdJPanel();
			this.add(jp);
			this.setVisible(true);

		}
		if (e.getActionCommand().equals("寝室报修")) {
			this.remove(jp);
			jp=new inputrepair().inputJPanel();
			this.add(jp);
			this.setVisible(true);

		}
		/*if (e.getActionCommand().equals("报修记录")) {
			this.remove(jp);
			jp=new repairrecord().repairRecord();
			this.add(jp);
			this.setVisible(true);

		}*/
		if (e.getActionCommand().equals("关于")) {
			this.remove(jp);
			jp=help.about();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("退出")) {
			this.dispose();
		}
	}
	public static void main(String[] args) {
		new Student();
	}

}
