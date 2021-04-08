package gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fileIO.Config;

/*For the View Employee dialog, the gui contains these elements to search employees,
 * department
 * title
 * sex
 * state
 * a list of the resulting employees
 		-some form of drop-down or text field
 * load button 
 */

public class View_Employees {

	static JComboBox<String> deptD,titleD,sexD,stateD;
	static JDialog dialog;
	static String fName,mName,lName;
	static String fullName;
	static String department;
	static JTextField enterFirst;
	static JTextField enterMiddle;
	static JTextField enterLast;
	static JLabel deptL,jobtitleL,sexL,stateL;
	static JList employee;
	
	 protected static JDialog createViewemployeeMenu()  throws Exception {
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 dialog.addWindowListener(DialogListener);
	    	
		 
		 String labels[] = { "Dante G. Parente", "B", "C", "D","E", "F", "G", "H","I", "J" };
	    	
		 JList employee = new JList(/* insert full name strings here */  labels );
	    	
	    	JScrollPane scrollPane = new JScrollPane(employee);
	    	scrollPane.setPreferredSize(new Dimension(150,150));
	    	
	    	JButton loadB = new JButton("Load");
	    	//loadB.setActionCommand("TermSubmit");
			loadB.addActionListener(submit);
			
			JLabel name = new JLabel("Employees:");
	    	
	    	System.out.println("Querrying DB...");
	    	
	    	//int i = sqlPullRequest();
			
			//System.out.println("Data Retreived Successfull for " + i + " entries.");
			
			System.out.println("Creating Dialog Box");
			
			setLabels();
	    	
	    	dialog.setSize(400, 350);
	    	dialog.setLayout(new GridBagLayout());
	    	
	    	JLabel viewEmployeesL = new JLabel("View Employees"); 
	    	
	    	GridBagConstraints b1c1 = new GridBagConstraints();
	    	b1c1.gridx = 1;
	    	b1c1.gridy = 0;
	    	b1c1.gridwidth = 2;
	    	
	    	GridBagConstraints c2 = new GridBagConstraints();
	    	c2.gridx = 2;
	    	c2.gridy = 1;
	    	
	    	GridBagConstraints d2d5 = new GridBagConstraints();
	    	d2d5.gridx = 3;
	    	d2d5.gridy = 1;
	    	d2d5.gridheight = 4;
	    	
	    	GridBagConstraints a6b6 = new GridBagConstraints();
	    	a6b6.gridx = 0;
	    	a6b6.gridy = 5;
	    	a6b6.gridwidth = 2;
	    	
	    	GridBagConstraints a2 = new GridBagConstraints();
	    	a2.gridx = 0;
	    	a2.gridy = 1;
	    	
	    	GridBagConstraints a3 = new GridBagConstraints();
	    	a3.gridx = 0;
	    	a3.gridy = 2;
	    	
	    	GridBagConstraints a4 = new GridBagConstraints();
	    	a4.gridx = 0;
	    	a4.gridy = 3;

	    	GridBagConstraints a5 = new GridBagConstraints();
	    	a5.gridx = 0;
	    	a5.gridy = 4;
	    	
	    	GridBagConstraints b2 = new GridBagConstraints();
	    	b2.gridx = 1;
	    	b2.gridy = 1;
	    	
	    	GridBagConstraints b3 = new GridBagConstraints();
	    	b3.gridx = 1;
	    	b3.gridy = 2;
	    	
	    	GridBagConstraints b4 = new GridBagConstraints();
	    	b4.gridx = 1;
	    	b4.gridy = 3;
	    	
	    	GridBagConstraints b5 = new GridBagConstraints();
	    	b5.gridx = 1;
	    	b5.gridy = 4;
	    	
	    	
	    	dialog.add(viewEmployeesL,b1c1);
	    	dialog.add(name,c2);
	    	dialog.add(scrollPane,d2d5);
	    	dialog.add(loadB,a6b6);
	    	
	    	dialog.add(deptL,a2);
	    	dialog.add(deptD,b2);
	    	dialog.add(jobtitleL,a3);
	    	dialog.add(titleD,b3);
	    	dialog.add(sexL,a4);
	    	dialog.add(sexD,b4);
	    	dialog.add(stateL,a5);
	    	dialog.add(stateD,b5);
	    	dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
			return dialog;
	 }
	 
	 private static void setLabels() {
		 	deptL = new JLabel("Department: ");
	        stateL = new JLabel("State: ");
	        jobtitleL = new JLabel("Job Title: ");
	    	sexL = new JLabel("Sex: ");
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 private static int sqlPullRequest() throws Exception, SQLException {
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
			
			stmt.close();
			conn.close();
			return i;
		}
	 
	 
	 static ActionListener submit = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Connect to SQL and save new column in employee
	        	
	        	fullName = (String) employee.getSelectedItem();
	        	int selindex = employee.getSelectedIndex();
	        	System.out.println("The value of fullName is: " + fullName);
	        	 String[] name = fullName.split(" ");
	        	for(int i = 0; i < name.length; i++) {
	        		System.out.println(name[i]);
	        	}
	      
				try {
					sqlPushRequest(name);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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
