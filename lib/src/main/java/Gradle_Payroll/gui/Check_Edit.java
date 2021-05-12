package Gradle_Payroll.gui;

import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class Check_Edit {
	
	static JInternalFrame frame;
	static int CHECKNUM;
	
	protected static JInternalFrame createDialog(int checkNum) {
		frame = new JInternalFrame();
		frame.setSize(320, 320);
    	frame.setLayout(new GridBagLayout());
    	
    	CHECKNUM = checkNum;
    	
    	
    	
		
		
    	
    	
    	
    	
    	
    	
    	
		
    	
    	frame.setVisible(true);
    	frame.setResizable(true);
		return frame;
	}

}
