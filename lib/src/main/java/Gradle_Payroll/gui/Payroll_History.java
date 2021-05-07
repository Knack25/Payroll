package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.fileIO.Config;

public class Payroll_History {
	
	static JDialog dialog;
	static JLabel nameL,dateL,payrollHisL,dashL;
	static JComboBox<String> employee,startDateD,endDateD; 
	static JButton loadB;
	static String fName,mName,lName,fullName;
	
	protected static JDialog createFrame() throws Exception{
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		
		System.out.println("Creating Dialog Box");
		 
		 
		 loadB = new JButton("Load");
		 employee = new JComboBox<String>();
		 startDateD = new JComboBox<String>();
		 endDateD = new JComboBox<String>();
		 
		 
		 setLabels();
		 sqlPullEmpListRequest();
		 dialog.setSize(220, 220);
	    	dialog.setLayout(new GridBagLayout());
		 
	    	GridBagConstraints b1 = new GridBagConstraints();
	    	b1.gridx = 1;
	    	b1.gridy = 0;
	    	
	    	GridBagConstraints a2 = new GridBagConstraints();
	    	a2.gridx = 0;
	    	a2.gridy = 1;
	    	
	    	GridBagConstraints a3 = new GridBagConstraints();
	    	a3.gridx = 0;
	    	a3.gridy = 2;
	    	
	    	GridBagConstraints a4 = new GridBagConstraints();
	    	a4.gridx = 0;
	    	a4.gridy = 3;
	    	
	    	GridBagConstraints b2 = new GridBagConstraints();
	    	b2.gridx = 1;
	    	b2.gridy = 1;
	    	
	    	GridBagConstraints b3 = new GridBagConstraints();
	    	b3.gridx = 1;
	    	b3.gridy = 2;
	    	
	    	GridBagConstraints c3 = new GridBagConstraints();
	    	c3.gridx = 2;
	    	c3.gridy = 2;
	    	
	    	GridBagConstraints d3 = new GridBagConstraints();
	    	b1.gridx = 3;
	    	b1.gridy = 2;
	    	
	    	GridBagConstraints a1d1 = new GridBagConstraints();
	    	a1d1.gridx = 0;
	    	a1d1.gridy = 0;
	    	a1d1.gridwidth = 4;		
	    	   	
		
		dialog.add(payrollHisL,a1d1);
		dialog.add(nameL,a2);
		dialog.add(employee,b2);
		dialog.add(dateL,a3);
		dialog.add(startDateD,b3);
		dialog.add(dashL,c3);
		dialog.add(endDateD,d3);
		dialog.add(loadB,a4);
		
		System.out.println("Done.");
		dialog.setVisible(true);
		return dialog;
	}
	
	 private static void setLabels() {
		 System.out.println("Ceating Labels...");
		 payrollHisL = new JLabel("<HTML><U> Payroll History </U></HTML>");
		 nameL = new JLabel("Name: ");
		 dateL = new JLabel("Date:" );
		 dateL = new JLabel("Date:");
		 dashL = new JLabel("-");

		}
	
	 private static void sqlPullEmpListRequest() throws Exception, SQLException {
			String[] SQL = Config.PullSQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			Statement stmt = conn.createStatement();
				
			ResultSet rs = stmt.executeQuery("select * from employee");
			
			
			
			int i = 0;
			
			
			while(rs.next()) {
				fName = rs.getString("firstname");
				mName = rs.getString("middlename");
				lName = rs.getString("lastname");
				fullName = fName + " " + mName + " " + lName;
				employee.addItem(fullName);
				i++;
			}
			
			
			System.out.println("Data Retreived Successfull for " + i + " Employee entries.");
			
			rs.close();
			conn.close();
		}
	
	
	
	
};