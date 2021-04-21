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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.fileIO.Config;
 
 
 public class CreateEmployee extends JDialog{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810663888408123313L;
	static String fName;
	static String mName;
	static String lName;
	static JDialog createMenu;
	static JTextField enterFirst;
	static JTextField enterMiddle;
	static JTextField enterLast;
	
	 
	 public static JDialog CreateMenu() {
		 createMenu = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 createMenu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 createMenu.setSize(400, 150);	
		 
		 
		
		 JPanel fields = new JPanel();
		 
		 createMenu.setLayout(new FlowLayout());
		 JLabel employeeName = new JLabel("Enter Employee Name (First, Middle, Last):");
	
		 
		 JButton createB = new JButton("Create"); 
		 createB.addActionListener(CreateListener);
		
		 enterFirst = new JFormattedTextField("");
		 enterMiddle = new JFormattedTextField("");
		 enterLast = new JFormattedTextField("");
		 
		 enterFirst.setColumns(10);
		 enterMiddle.setColumns(10);
		 enterLast.setColumns(10);

	
		 createMenu.add(employeeName);
		 fields.add(enterFirst);
		 fields.add(enterMiddle);
		 fields.add(enterLast);
		 createMenu.add(createB);
		 
		 createMenu.add(fields,FlowLayout.CENTER);
		
		 
		 //https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
		createMenu.setVisible(true);
			 return createMenu;
	 }

	 
	static ActionListener CreateListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Connect to SQL and save new column in employee
			try {
				sqlPushRequest();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		private void sqlPushRequest() throws Exception, SQLException {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			int id = 0;
			
			System.out.println("Connecting to DB...");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			conn.setAutoCommit(false);
			
			System.out.println("Connected.");
			
			 fName = enterFirst.getText();
			 mName = enterMiddle.getText();
			 lName = enterLast.getText();
			 
			String statement = "INSERT INTO employee(firstname,middlename,lastname,telNum,email,sex,ssn,jobTitle,dob,doh,dot,salary,regularPay,regularHour,"
						+ "otPay,otHour,ptoPay,ptoHour,localTaxCode,addStateTax,addFedTax,vacationtimeAvail,vacationtimeUsed,Department,enabled) "
						+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(statement);
			
			System.out.println("Created Prepared Statement");
			
			pstmt.setString(1, fName);
			pstmt.setString(2, mName);
			pstmt.setString(3, lName);
			pstmt.setString(4, "Phone Number");
			pstmt.setString(5, "Email");
			pstmt.setString(6, "Sex");
			pstmt.setString(7, "SSN");
			pstmt.setString(8, "Job Title");
			pstmt.setString(9, "Date Of Birth");
			pstmt.setString(10, "Date of Hire");
			pstmt.setString(11, "N/A");
			pstmt.setDouble(12, 00.00);
			pstmt.setDouble(13, 12.00);
			pstmt.setDouble(14, 00.00);
			pstmt.setDouble(15, 12.00);
			pstmt.setDouble(16, 00.00);
			pstmt.setDouble(17, 12.00);
			pstmt.setDouble(18, 00.00);
			pstmt.setInt(19, 44545);
			pstmt.setDouble(20, 00.00);
			pstmt.setDouble(21, 00.00);
			pstmt.setDouble(22, 60.00);
			pstmt.setDouble(23, 00.00);
			pstmt.setDouble(24, 1);
			pstmt.setBoolean(25, true);
				
			//System.out.println(pstmt);

			try {
			int rs = pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("Update Sent");
			
			System.out.println(rs + " rows updated");
			}
			catch(SQLException e){
				System.out.println(e);
			}
			
			conn.setAutoCommit(true);
			
			 conn.close();
			 
			id = sqlIDPullRequest(fName, mName, lName);
			
			sqlNewEmpAddreessRequest(id);
			
			
			 createMenu.dispose();
		}
		
		private int sqlIDPullRequest(String fName,String mName,String lName) throws Exception, SQLException {
			
			int ID = 0;
			String[] SQL;
			SQL = Config.PullSQLConfig();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Querring for new employee ID.");
			System.out.println("");
			System.out.println("Connecting to DB...");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			conn.setAutoCommit(false);
			
			System.out.println("Connected.");
			
			String querry = "select * from employee " + "where " + "firstname = ? and " + "middlename = ? and " + "lastname = ?";
			
			
			PreparedStatement pstmt = conn.prepareStatement(querry);
			
			pstmt.setString(1, fName);
			pstmt.setString(2, mName);
			pstmt.setString(3, lName);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				ID = rs.getInt("id");
			}
			System.out.println("ID is: " + ID);
			return ID;
			
			
			
		}
		
		private int sqlNewEmpAddreessRequest(int ID) throws Exception, SQLException {
			
			String[] SQL;
			SQL = Config.PullSQLConfig();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Creating new entry in address table.");
		
			System.out.println("Connecting to DB...");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			conn.setAutoCommit(false);
			
			System.out.println("Connected.");
			
			String statement = "INSERT INTO address(street,city,state,zip,employee_id) "
					+ "Values(?,?,?,?,?)";
			
			
			PreparedStatement pstmt = conn.prepareStatement(statement);
			
			pstmt.setString(1, "Street");
			pstmt.setString(2, "City");
			pstmt.setString(3, "State");
			pstmt.setInt(4, 12345);
			pstmt.setInt(5, ID);
			
			//System.out.println(pstmt);
			
			int rs = pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("Inserted " + rs + " rows into address table.");
			
			conn.setAutoCommit(true);
			return rs;
		}
		
	};	 
 }