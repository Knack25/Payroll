package Gradle_Payroll.gui;

import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class Check_Edit {
	
	static JInternalFrame dialog;
	static int EMPID;
	
	protected static JInternalFrame createDialog(int empID) {
		dialog = new JInternalFrame();
		dialog.setSize(320, 320);
    	dialog.setLayout(new GridBagLayout());
    	
    	EMPID = empID;
    	
    	
		
		
    	
    	
    	
    	
    	
    	
    	
		
    	
    	dialog.setVisible(true);
    	dialog.setResizable(false);
		return dialog;
	}

}
