package gui;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import fileIO.Config;

public class Terminate_Employee {

	static JComboBox<String> employee;
	static JDialog dialog;

	static String fName,mName,lName,fullName;
	
	
	 protected static JDialog createEmployeeTerminateDialog()  throws Exception {
	    	
		 	dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
	    	dialog.addWindowListener(DialogListener);
	    	
	    	employee = new JComboBox<String>();
	    	
	    	JButton submitB = new JButton("Submit");
	    	//submitB.setActionCommand("TermSubmit");
			submitB.addActionListener(submit);
	    	JLabel name = new JLabel("Employee To Remove");
	    	
	    	System.out.println("Querrying DB...");
	    	
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
			
			
			System.out.println("Data Retreived Successfull for " + i + " entries.");
			
			rs.close();
			conn.close();
			
			System.out.println("Creating Dialog Box");
	    	
	    	dialog.setSize(200, 200);
	    	dialog.setLayout(new FlowLayout());
	    	dialog.add(name);
	    	dialog.add(employee);
	    	dialog.add(submitB);
	    	dialog.repaint();
	    	dialog.setVisible(true);
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
			return dialog;
	    }

	 static ActionListener submit = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Connect to SQL and save new column in employee
	        	
	        	fullName = (String) employee.getSelectedItem();
	        	int Index = employee.getSelectedIndex() + 1;
	        	System.out.println("The value of fullName is: " + fullName);
	        	/* String[] name = fullName.split(" ");
	        	for(int i = 0; i < name.length; i++) {
	        		System.out.println(name[i]);
	        	}*/
	        	
	        	String[] SQL;
				try {
					SQL = Config.SQLConfig();
					
					final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			    	
			    	Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
					
					
					Statement stmt = conn.createStatement();
				
					
					int rs = stmt.executeUpdate("update employee set enabled = false where id = " + Index);
				
					System.out.println("Affected " + rs + " rows.");
					
					stmt.close();
					conn.close();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
				System.out.println("Disposing of Dialog box");
				dialog.setVisible(false);
				dialog.dispose();
			}
		};

		static WindowListener DialogListener = new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
				dialog.dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
}
