package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import fileIO.Config;

public class Terminate_Employee {

	static JComboBox<String> employee;
	static JDialog dialog;

	static String fName,mName,lName,fullName;
	
	
	 protected static JDialog createEmployeeTerminateDialog()  throws Exception {
	    	
		 	dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
	    	dialog.addWindowListener(DialogListener);
	    	
	    	employee = new JComboBox<String>();
	    	employee.setPreferredSize(new Dimension(150,30));
	    	JButton submitB = new JButton("Submit");
	    	//submitB.setActionCommand("TermSubmit");
			submitB.addActionListener(submit);
	    	JLabel name = new JLabel("Employee To Remove");
	    	
	    	System.out.println("Querrying DB...");
	    	
	    	sqlPullRequest();
			System.out.println("Creating Dialog Box");
	    	
	    	dialog.setSize(400, 100);
	    	dialog.setLayout(new FlowLayout());
	    	dialog.add(name);
	    	dialog.add(employee);
	    	dialog.add(submitB);
	    	dialog.repaint();
	    	dialog.setVisible(true);
	    	
	    	//for the submit button, should we have a warning before terminating the employee?
	    	
	  
	    	 
	    	 
	    	 
	    	System.out.println("Created Dialog");
	    	
			return dialog;
	    }

	private static void sqlPullRequest() throws Exception, SQLException {
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
	}

	 static ActionListener submit = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Connect to SQL and save new column in employee
	        	
	        	fullName = (String) employee.getSelectedItem();
	        	String[] name = fullName.split(" ");
	        	System.out.println("The value of fullName is: " + fullName);
	        	
	        
				try {
					sqlPushRequest(name);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
				System.out.println("Disposing of Dialog box");
				dialog.setVisible(false);
				dialog.dispose();
			}

			private void sqlPushRequest(String[] name) throws Exception, SQLException {
				String[] SQL;
				SQL = Config.SQLConfig();
				
				final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
				
				Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
				
				
				String updateStatement = "update employee " + "set enabled = false "+ "WHERE firstname = ? AND lastname = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(updateStatement);

				if(name.length == 3) {
					pstmt.setString(1,name[0]);
					pstmt.setString(2, name[2]);
				}
				if(name.length == 2) {
					pstmt.setString(1,name[0]);
					pstmt.setString(2, name[1]);
				}
				
				int output = pstmt.executeUpdate();

				System.out.println("Affected " + output + " rows.");
				
				pstmt.close();
				conn.close();
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
