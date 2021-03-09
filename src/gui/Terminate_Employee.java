package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import fileIO.Config;

public class Terminate_Employee {

	 protected static JDialog createEmployeeTerminateDialog() throws Exception {
	    	JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
	    	
	    	JLabel name = new JLabel("Employee To Remove");
	    	
	    	String[] SQL = Config.SQLConfig();
	    	
	    	final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
	    	
	    	Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			Statement stmt = conn.createStatement();
				
			ResultSet rs = stmt.executeQuery("select * from employee");
			
			String fName,mName,lName,fullName;
			
			int i = 0;
			JComboBox<String> employee = new JComboBox<String>();
			
			while(rs.next()) {
				fName = rs.getString("firstname");
				mName = rs.getString("middlename");
				lName = rs.getString("lastname");
				fullName = fName + " " + mName + " " + lName;
				employee.addItem(fullName);
				i++;
			}
			
			
	    	
	    	dialog.setSize(200, 200);
	    	dialog.setLayout(new FlowLayout());
	    	dialog.add(name);
	    	dialog.add(employee);
	    	
	    	
	    	
			return dialog;
	    }
}
