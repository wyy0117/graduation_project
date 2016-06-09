package graduation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import admin.classQuery;
import admin.exroom;
import admin.graduation;
import admin.help;
import admin.init;
import admin.inputregister;
import admin.inputwe;
import admin.repairQuery;
import admin.updatepwd;
import admin.xiuxue;
import admin.stuQuery;
import admin.registerrecord;
import admin.repaired;
import admin.newStu;
import admin.roomQuery;
import admin.qianfei;

import layout.gridBagConstraints;
import layout.menu;

public class Admin extends JFrame implements ActionListener {

	JPanel jp;
	JMenuBar jmb;
	JMenu roomJMenu;
	JMenu queryJMenu;
	JMenu wlJMenu;
	JMenu leaveJMenu;
	JMenu repairJMenu;
	JMenu registerJMenu;
	JMenu pwdJMenu;
	JMenu helpJMenu;

	public Admin() {
		jp = new JPanel();
		jmb = new JMenuBar();

		roomJMenu = new JMenu("寝室");
		menu.addMenuItem(roomJMenu, "初始化", this);
		menu.addMenuItem(roomJMenu, "新生分配", this);
		menu.addMenuItem(roomJMenu, "寝室调动", this);
		jmb.add(roomJMenu);

		queryJMenu = new JMenu("查询");
		menu.addMenuItem(queryJMenu, "学生查询", this);
		menu.addMenuItem(queryJMenu, "寝室查询", this);
		menu.addMenuItem(queryJMenu, "班级查询", this);
		jmb.add(queryJMenu);

		wlJMenu = new JMenu("水电费");
		menu.addMenuItem(wlJMenu, "录入水电费", this);
		menu.addMenuItem(wlJMenu, "欠费查询", this);
		jmb.add(wlJMenu);

		leaveJMenu = new JMenu("退寝");
		menu.addMenuItem(leaveJMenu, "毕业", this);
		menu.addMenuItem(leaveJMenu, "修学", this);
		jmb.add(leaveJMenu);

		repairJMenu = new JMenu("报修");
		menu.addMenuItem(repairJMenu, "报修查询", this);
		menu.addMenuItem(repairJMenu, "报修记录", this);
		jmb.add(repairJMenu);

		registerJMenu = new JMenu("登记");
		menu.addMenuItem(registerJMenu, "人员登记", this);
		menu.addMenuItem(registerJMenu, "登记记录", this);
		jmb.add(registerJMenu);
		
		pwdJMenu=new JMenu("密码");
		menu.addMenuItem(pwdJMenu, "密码修改", this);
		jmb.add(pwdJMenu);

		helpJMenu = new JMenu("帮助");
		menu.addMenuItem(helpJMenu, "关于", this);
		menu.addMenuItem(helpJMenu, "退出", this);
		jmb.add(helpJMenu);

		JLabel welcome = new JLabel("欢迎使用宿舍管理系统", JLabel.CENTER);
		welcome.setFont(new Font("宋体", Font.BOLD, 30));
		jp.add(welcome);
		
		this.setTitle("管理员系统");
		this.add(jmb, BorderLayout.NORTH);
		this.add(jp);
		this.setBounds(400, 50, 600, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("初始化")) {
			this.remove(jp);
			jp=new init().initJPanel();
			this.add(jp);
			this.setVisible(true);

		}
		if (e.getActionCommand().equals("新生分配")) {
			this.remove(jp);
			jp=new newStu().newStuJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("寝室调动")) {
			this.remove(jp);
			jp=new exroom().exroomJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("学生查询")) {
			this.remove(jp);
			jp=new stuQuery().stuQuery();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("寝室查询")) {
			this.remove(jp);
			jp=new roomQuery().roomQueryJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("班级查询")) {
			this.remove(jp);
			jp=new classQuery().classQueryJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("录入水电费")) {
			this.remove(jp);
			jp=new inputwe().inputweJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("欠费查询")) {
			this.remove(jp);
			jp=new qianfei().qianFeiJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("毕业")) {
			this.remove(jp);
			jp=new graduation().graduationJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("修学")) {
			this.remove(jp);
			jp=new xiuxue().xieXue();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("报修查询")) {
			this.remove(jp);
			jp=new repairQuery().repairQueryJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("报修记录")) {
			this.remove(jp);
			jp=new repaired().repaired();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("人员登记")) {
			this.remove(jp);
			jp=new inputregister().inputJPanel();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("登记记录")) {
			this.remove(jp);
			jp=new registerrecord().record();
			this.add(jp);
			this.setVisible(true);
		}
		if (e.getActionCommand().equals("密码修改")) {
			this.remove(jp);
			jp=new updatepwd().updatepwdJPanel();
			this.add(jp);
			this.setVisible(true);
		}
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
		new Admin();
	}

}
