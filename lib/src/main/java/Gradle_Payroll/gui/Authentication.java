package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Authentication {
	static JDialog dialog;
	static JLabel test;
	
	 protected static JDialog createFrame() throws Exception{
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 
		 System.out.println("Creating Dialog Box");
		 
		 
		 JButton submitB = new JButton("Submit");
		 
		 
		 setLabels();
		 dialog.setSize(220, 220);
	    	dialog.setLayout(new GridBagLayout());
		 
		 
	    	
	    	
		 
	    	GridBagConstraints a3 = new GridBagConstraints();
	    	a3.gridx = 0;
	    	a3.gridy = 2;
	    	GridBagConstraints a4 = new GridBagConstraints();
	    	a4.gridx = 0;
	    	a4.gridy = 3;
	    	GridBagConstraints a5 = new GridBagConstraints();
	    	a5.gridx = 0;
	    	a5.gridy = 4;
	    	GridBagConstraints a6 = new GridBagConstraints();
	    	a6.gridx = 0;
	    	a6.gridy = 5;
	    	GridBagConstraints a7 = new GridBagConstraints();
	    	a7.gridx = 0;
	    	a7.gridy = 6;
	    	GridBagConstraints a8 = new GridBagConstraints();
	    	a8.gridx = 0;
	    	a8.gridy = 7;
	    	GridBagConstraints a9 = new GridBagConstraints();
	    	a9.gridx = 0;
	    	a9.gridy = 8;
	    	
	    	GridBagConstraints b3 = new GridBagConstraints();
	    	b3.gridx = 1;
	    	b3.gridy = 2;
	    	GridBagConstraints b4 = new GridBagConstraints();
	    	b4.gridx = 1;
	    	b4.gridy = 3;
	    	GridBagConstraints b5 = new GridBagConstraints();
	    	b5.gridx = 1;
	    	b5.gridy = 4;
	    	GridBagConstraints b6 = new GridBagConstraints();
	    	b6.gridx = 1;
	    	b6.gridy = 5;
	    	GridBagConstraints b7 = new GridBagConstraints();
	    	b7.gridx = 1;
	    	b7.gridy = 6;
	    	GridBagConstraints b8 = new GridBagConstraints();
	    	b8.gridx = 1;
	    	b8.gridy = 7;
	    	GridBagConstraints b9 = new GridBagConstraints();
	    	b9.gridx = 1;
	    	b9.gridy = 8;
		 
		 
		 
		 
		 
		 
		 dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
			return dialog;
	 }
	 private static void setLabels() {
		 test = new JLabel("<HTML><U> Rate </U></HTML>");
		}
	
	
	
	
	
	
	
	
	
	
	
	
};