package admin;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class help {

	public static JPanel about(){
	JPanel aboutJPanel=new JPanel();
	JLabel versionJLabel=new JLabel("version: 1.0.0     builder: wyy");
	aboutJPanel.add(versionJLabel);

	return aboutJPanel;
	}
	
	
}
