package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import fileIO.Config;




public class Void_Check {

	static JComboBox<String> employee,checkNo;
	static JDialog dialog;
	static JLabel nameL = new JLabel("Name: "),
	checkNoL = new JLabel("Check No.: ");
	static JButton voidB;

	
	 protected static JDialog createVoidcheckMenu()  throws Exception {
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 //dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 dialog.addWindowListener(DialogListener);
		 

	    	
		 	
	    	voidB = new JButton("Load");
			//voidB.addActionListener(submit);
			
	    	System.out.println("Querrying DB...");
	    	
			
			System.out.println("Creating Dialog Box");
			
			dialog.setSize(300, 100);
	    	dialog.setLayout(new GridBagLayout());
	    	
	    	JLabel voidCheckL = new JLabel("<HTML><U> Void Check </U></HTML>"); 
	    	
	    	//sqlPullRequest();
	    	
	    	GridBagConstraints b1c1 = new GridBagConstraints();
	    	b1c1.gridx = 1;
	    	b1c1.gridy = 0;
	    	b1c1.gridwidth = 2;

	    	GridBagConstraints a2 = new GridBagConstraints();
	    	a2.gridx = 0;
	    	a2.gridy = 1;
	    	
	    	GridBagConstraints b2 = new GridBagConstraints();
	    	b2.gridx = 1;
	    	b2.gridy = 1;
	    	
	    	GridBagConstraints c2 = new GridBagConstraints();
	    	c2.gridx = 2;
	    	c2.gridy = 1;
	    	
	    	GridBagConstraints d2 = new GridBagConstraints();
	    	d2.gridx = 3;
	    	d2.gridy = 1;
	    	
	    	GridBagConstraints a3= new GridBagConstraints();
	    	a3.gridx = 0;
	    	a3.gridy = 2;
	    	
	    	
	    	
	    	dialog.add(voidCheckL,b1c1);
	    	dialog.add(nameL,a2);
	    	//dialog.add(employee,b2);
	    	dialog.add(checkNoL,c2);
	    	//dialog.add(checkNo,d2);
	    	dialog.add(voidB,a3);
	    	dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
			return dialog;
	 }
	 
	 
	 
	 
	 
	 /*
	 private static int sqlPullRequest() throws Exception, SQLException {
			String[] SQL = Config.SQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String statement = "select * from employee " + "where " + 
								"enabled = ? " + "department = ? " + "state = ? " + 
								"jobTitle = ?" + "sex = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(statement);
				
			ResultSet rs = pstmt.executeQuery();
			
			
			
			int i = 0;
			
			
			while(rs.next()) {
				fName = rs.getString("firstname");
				mName = rs.getString("middlename");
				lName = rs.getString("lastname");
				fullName = fName + " " + mName + " " + lName;
				employee.addItem(fullName);
				i++;
			}
			
			pstmt.close();
			conn.close();
			return i;
		}
	 
	 
	 static ActionListener submit = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Connect to SQL and save new column in employee
	        	
	        	fullName = (String) employee.getSelectedItem();
	        	@SuppressWarnings("unused")
				int selindex = employee.getSelectedIndex();
	        	System.out.println("The value of fullName is: " + fullName);
	        	 String[] name = fullName.split(" ");
	        	for(int i = 0; i < name.length; i++) {
	        		System.out.println(name[i]);
	        	}
	      
				try {
					sqlPushRequest(name);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.setVisible(false);
				dialog.dispose();
			}

			private void sqlPushRequest(String[] name) throws Exception, SQLException {
				String[] SQL;
				System.out.println("Executing Update");
				
				SQL = Config.SQLConfig();
				
				final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
				
				Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
				
				String updateStatement = "update employee " + "set enabled = true "+ "WHERE firstname = ? AND lastname = ?";
				
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
		*/
	  static WindowListener DialogListener = new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				dialog.setVisible(false);
				dialog.dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
				
			}
		};
	
}
