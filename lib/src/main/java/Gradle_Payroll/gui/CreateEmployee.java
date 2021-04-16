package Gradle_Payroll.gui;


import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		private void sqlPushRequest() throws Exception, SQLException {
			String[] SQL;
			SQL = Config.SQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			conn.setAutoCommit(false);
			
			
			 fName = enterFirst.getText();
			 mName = enterMiddle.getText();
			 lName = enterLast.getText();
			
/*			String statement = "INSERT INTO employee(firstname,middlename,lastname,telNum,email,sex,ssn,jobTitle,dob,doh,dot,salary,regularPay,regularHour,"
						+ "otPay,otHour,ptoPay,ptoHour,localTaxCode,addStateTax,addFedTax,vacationtimeAvail,vacationtimeUsed,Department,enabled) "
						+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 
			PreparedStatement pstmt = conn.prepareStatement(statement);
			
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
			pstmt.setNull(11, ABORT);
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
			pstmt.setString(24, "Department");
			pstmt.setBoolean(25, true);
				
			System.out.println(pstmt);
			
			java.util.Date now = new java.util.Date();
			
			Timestamp ts = new Timestamp(now.getTime());
			
			pstmt.setTimestamp(3, ts);
			int rs = pstmt.executeUpdate();
			conn.commit();
		
			conn.setAutoCommit(true);
			System.out.println(rs + " Rows updated."); */
			 
			 Statement statement = conn.createStatement();
			 
			/* statement.executeUpdate("INSERT INTO employee(firstname,middlename,lastname,telNum,email,sex,ssn,jobTitle,dob,doh,dot,salary,regularPay,regularHour,"
						+ "otPay,otHour,ptoPay,ptoHour,localTaxCode,addStateTax,addFedTax,vacationtimeAvail,vacationtimeUsed,Department,enabled) "
						+ "Values('dfsd','dsf','dsfds','Phone Number','Email','Sex','SSN','Job Title','Date Of Birth','Date of Hire','null',00.00,12.00,00.00,12.00,00.00,12.00,00.00,44541,00.00,00.00,60.00,00.00,'?')");
			*/
			 createMenu.dispose();
		}
		
	};	 
 }