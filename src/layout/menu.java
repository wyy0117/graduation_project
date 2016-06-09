package layout;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class menu {

	public static void addMenuItem(JMenu jMenu, String menuName,
			ActionListener listener) {
		JMenuItem jmi = new JMenuItem(menuName);
		jmi.addActionListener(listener);
		jMenu.add(jmi);
	}
}
