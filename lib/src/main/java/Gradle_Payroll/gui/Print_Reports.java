package Gradle_Payroll.gui;


import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Tax;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.sql.MySQL;




public class Print_Reports {
	static JDialog dialog;
	
	
	
	protected static JDialog createFrame() throws Exception{
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
	
		 
		// dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 dialog.setSize(400, 150);	
		
		JButton RegisterB = new JButton("Register");
		//addB.addActionListener(addBListener);
		
		JButton w2B = new JButton("W2 Forms");
		//addB.addActionListener(addBListener);
		
		JButton payJournalB = new JButton("Pay Journal & Tax Summary");
		//addB.addActionListener(addBListener);
		
		dialog.setLayout(new FlowLayout());
		
		
		
		
 JPanel fields = new JPanel();
		 
		 dialog.setLayout(new FlowLayout());
		 JLabel report = new JLabel("Select Report");
	
	//TODO: after selecting report, figure out what employee it is, and other credentials that would be needed. Ex. date
		
		 dialog.add(report);
		 fields.add(RegisterB);
		fields.add(w2B);
		fields.add(payJournalB);
		 
		 dialog.add(fields,FlowLayout.CENTER);
		
		
		
		dialog.setVisible(true);
		
		
		return dialog;
	}
	
	
	
	
	
	
	
	
	
	
};