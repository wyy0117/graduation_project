package layout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class gridBagConstraints extends GridBagConstraints {
	
	//设置位置和大小
	public gridBagConstraints(int gridx, int gridy, int gridwidth,
			int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	//设置填充方式
	public gridBagConstraints setFill(int fill) {
		this.fill=gridBagConstraints.BOTH;;
		return this;
	}
	//缩小方式
	public gridBagConstraints setAnchor(int anchor) {
		this.anchor=anchor;
		return this;
	}
	//增量大小
	public gridBagConstraints setWeight(double weightx, double weighty)  
	   {  
	      this.weightx = weightx;  
	      this.weighty = weighty;  
	      return this;  
	   } 

}
