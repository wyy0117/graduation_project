package student;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialog.dialog;

import JDBC.Conn;
import Sql.MySql;
import Sql.getData;

public class wequery {

	private JTextField waterJTextField;
	private JTextField elecJTextField;

	public JPanel weQueryJPanel() {
		JPanel weQueryJPanel=new JPanel();
		JLabel waterJLabel = new JLabel("水费：");
		weQueryJPanel.add(waterJLabel);

		waterJTextField = new JTextField(5);
		waterJLabel.setEnabled(false);
		weQueryJPanel.add(waterJTextField);

		JLabel eledJLabel = new JLabel("电费：");
		weQueryJPanel.add(eledJLabel);
		
		elecJTextField = new JTextField(5);
		eledJLabel.setEnabled(false);
		weQueryJPanel.add(elecJTextField);
		
		String stulnumString = getData.getstulnum(getAccount.getaccout());
		String sturnumString = getData.getsturnum(getAccount.getaccout());
		
		String selectString = MySql.select("water,elec", "room", "lnum='"
				+ stulnumString + "' and rnum='" + sturnumString + "'");
		try {
			Statement st = Conn.getst();
			System.out.println(selectString);
			ResultSet rs = st.executeQuery(selectString);
			if (rs.next()) {
				String waterString = rs.getString(1);
				String elecString = rs.getString(2);
				
				waterJTextField.setText(waterString);
				elecJTextField.setText(elecString);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			dialog.showMessage("出现了一个神奇的错误，Error:wequery");
		}
		return weQueryJPanel;
	}
}
