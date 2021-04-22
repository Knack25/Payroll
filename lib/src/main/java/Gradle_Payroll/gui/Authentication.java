package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Authentication {
	static JDialog dialog;
	static JLabel test,usernameL,passwordL,ipAddL,dbNameL;
	static JTextField usernameT,passwordT,ipAddT,dbNameT;
	
	 protected static JDialog createFrame() throws Exception{
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 
		 System.out.println("Creating Dialog Box");
		 
		 
		 JButton submitB = new JButton("Submit");
		 
		 
		 setLabels();
		 dialog.setSize(220, 220);
	    	dialog.setLayout(new GridBagLayout());
		 
		 //TODO: test
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
	    	
	    	dialog.add(usernameL,a3);
	    	dialog.add(usernameT,b3);
	    	dialog.add(passwordL,a4);
	    	dialog.add(passwordT,b4);
	    	dialog.add(ipAddL,a5);
	    	dialog.add(ipAddT,b5);
	    	dialog.add(dbNameL,a6);
	    	dialog.add(dbNameT,b6);
	    	dialog.add(submitB,a7);
		 
		 
		 
		 
		 dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
			return dialog;
	 }
	 private static void setLabels() {
		 test = new JLabel("<HTML><U> Rate </U></HTML>");
		 usernameL = new JLabel("Username: ");
		 passwordL = new JLabel("Password: ");
		 ipAddL = new JLabel("I.P. Address: ");
		 dbNameL = new JLabel("DataBase Name: ");
		}
	
	
	
	
	
	
	
	
	
	
	
	
};