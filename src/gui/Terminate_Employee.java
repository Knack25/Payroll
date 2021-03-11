package gui;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import fileIO.Config;

public class Terminate_Employee implements ActionListener{

	static JComboBox<String> employee = new JComboBox<String>();
	static String fName,mName,lName,fullName;
	
	 protected static int createEmployeeTerminateDialog()  throws Exception {
	    	JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
	    	JButton submitB = new JButton("Submit");
	    	submitB.setActionCommand("TermSubmit");
	    	JLabel name = new JLabel("Employee To Remove");
	    	
	    	String[] SQL = Config.SQLConfig();
	    	
	    	final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
	    	
	    	Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			Statement stmt = conn.createStatement();
				
			ResultSet rs = stmt.executeQuery("select * from employee where enabled = true");
			
			
			
			int i = 0;
			
			
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
	    	dialog.add(submitB);
	    	dialog.setVisible(true);
	    	
	    	System.out.println("Created Dialog");
	    	
			return 0;
	    }
	 
	 public void actionPerformed(ActionEvent e) {
			 System.out.println(e.getActionCommand() + " clicked.");
		        if ("Submit".equals(e.getActionCommand())) { //new
		            //Connect to SQL and save new column in employee
		        	
		        	fullName = (String) employee.getSelectedItem();
		        	System.out.println("The value of fullName is: " + fullName);
		        	String[] name = fullName.split(" ");
		        	for(int i = 0; i < name.length; i++) {
		        		System.out.println(name[i]);
		        	}
		        	
		        	String[] SQL;
					try {
						SQL = Config.SQLConfig();
						
						final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
				    	
				    	Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
						
						
						Statement stmt = conn.createStatement();
						if(name.length == 3) {
							ResultSet rs = stmt.executeQuery("update employee set enabled = false where firstname = " 
									+ name[0] + "and lastname = " + name[2]);
						}
						if(name.length == 2) {
							ResultSet rs = stmt.executeQuery("update employee set enabled = false where firstname = " 
									+ name[0] + "and lastname = " + name[1]);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    	
					
					
		        } 
		}
}
