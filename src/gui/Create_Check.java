package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fileIO.Config;


 //this should be connected to edit employee and could be the basework for process payroll

public class Create_Check {

	static JComboBox<String> employee;
	static JDialog dialog;
	static JLabel regularL,ptoL,overtimeL,salaryL,advanceL,royaltiesL,checkNoL,hoursL,rateL;
	static JTextField regHoursT,regRateT,ptoHoursT,ptoRateT,salHoursT,salRateT,advHoursT,advRateT,royalHoursT,royalRateT,checkNoT;

	
	 protected static JDialog createCheckmenu()  throws Exception {
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		// dialog.addWindowListener(DialogListener);
	    	
			JButton createB = new JButton("create");
	    	//loadB.setActionCommand("TermSubmit");
			//createB.addActionListener(submit);
			
			
	    	
	    	System.out.println("Querrying DB...");
	    	
	    	//int i = sqlPullRequest();
			
			//System.out.println("Data Retreived Successfull for " + i + " entries.");
			
			System.out.println("Creating Dialog Box");
			
			setLabels();
	    	
	    	dialog.setSize(400, 350);
	    	dialog.setLayout(new GridBagLayout());
	    	
	    	GridBagConstraints a1= new GridBagConstraints();
	    	a1.gridx = 0;
	    	a1.gridy = 0;
	    	
	    	GridBagConstraints b2= new GridBagConstraints();
	    	b2.gridx = 1;
	    	b2.gridy = 1;

	    	GridBagConstraints c2d2 = new GridBagConstraints();
	    	c2d2.gridx = 2;
	    	c2d2.gridy = 1;
	    	c2d2.gridwidth = 2;
	    	
	    	GridBagConstraints a9 = new GridBagConstraints();
	    	a9.gridx = 0;
	    	a9.gridy = 8;
	    	
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
	    	
	    	GridBagConstraints c3 = new GridBagConstraints();
	    	c3.gridx = 2;
	    	c3.gridy = 2;
	    	
	    	GridBagConstraints c4 = new GridBagConstraints();
	    	c4.gridx = 2;
	    	c4.gridy = 3;
	    	
	    	GridBagConstraints c5 = new GridBagConstraints();
	    	c5.gridx = 2;
	    	c5.gridy = 4;
	    	
	    	GridBagConstraints c6 = new GridBagConstraints();
	    	c6.gridx = 2;
	    	c6.gridy = 5;
	    	
	    	GridBagConstraints c7 = new GridBagConstraints();
	    	c7.gridx = 2;
	    	c7.gridy = 6;
	    	
	    	GridBagConstraints c8 = new GridBagConstraints();
	    	c8.gridx = 2;
	    	c8.gridy = 7;
	    
	    	
	    	
	    	
	    	dialog.add(rateL,c2d2);
	    	dialog.add(hoursL,b2);
	    	dialog.add(createB,a9);
	    	
	    	
	    	dialog.add(regularL,a3);
	    	dialog.add(ptoL,a4);
	    	dialog.add(overtimeL,a5);
	    	dialog.add(salaryL,a6);
	    	dialog.add(advanceL,a7);
	    	dialog.add(royaltiesL,a8);
	    	dialog.add(checkNoL,a1);
	    	
	    	dialog.add(regHoursT,b3);
	    	dialog.add(regRateT,c3);
	    	dialog.add(ptoHoursT,b4);
	    	dialog.add(ptoRateT,c4);
	    	dialog.add(advHoursT,b5);
	    	dialog.add(advRateT,c5);
	    	dialog.add(salHoursT,b6);
	    	dialog.add(salRateT,c6);
	    	dialog.add(advHoursT,b7);
	    	dialog.add(advRateT,c7);
	    	dialog.add(royalHoursT,b8);
	    	dialog.add(royalRateT,c8);
	    	dialog.add(checkNoT);
	    	dialog.add(employee,a1);
	    
	    	dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
			return dialog;
	 }
	 
	 private static void setLabels() {
		 regularL = new JLabel("Regular: ");
		 ptoL = new JLabel("P.T.O: ");
		 overtimeL = new JLabel("Overtime: ");
		 salaryL = new JLabel("Salary: ");
		 advanceL = new JLabel("Advance: ");
		 royaltiesL = new JLabel("Royalties: ");
		 checkNoL = new JLabel("Check #: ");
		 hoursL = new JLabel("<HTML><U> Hours </U></HTML>");
		 rateL = new JLabel("<HTML><U> Rate </U></HTML>");
		}
	 
}; 
	 