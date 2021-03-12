package gui;

 import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileIO.Config;
 
 
 public class CreateEmployee extends JDialog{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810663888408123313L;
	static String fName;
	static String mName;
	static String lName;
	static JDialog createMenu;
	
	 
	 public static JDialog CreateMenu() {
		 createMenu = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 createMenu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 createMenu.setSize(300, 300);	
		 
		 
		 JPanel labels = new JPanel();
		 JPanel fields = new JPanel();
		 
		 createMenu.setLayout(new BorderLayout());
		 JLabel create_EmployeeL = new JLabel("Create Employee");
		 JLabel employeeName = new JLabel("Employee Name:");
		 JLabel employeeFirst = new JLabel("First");
		 JLabel employeeMiddle = new JLabel("Middle");
		 JLabel employeeLast = new JLabel("Last");	
		 
		 
		 JButton createB = new JButton("Create"); 
		 createB.addActionListener(CreateListener);
		
		 JTextField enterFirst = new JTextField("First");
		 JTextField enterMiddle = new JTextField("Middle");
		 JTextField enterLast = new JTextField("Last");
		 
	
		 
		 fName = enterFirst.getText();
		 mName = enterMiddle.getText();
		 lName = enterLast.getText();
		 
		 
	
		 
		 fields.add(enterFirst);
		 fields.add(enterMiddle);
		 fields.add(enterLast);
		 
		// createMenu.add(labels,BorderLayout.WEST);
		 createMenu.add(fields,BorderLayout.EAST);
		
		 
		 //https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
		createMenu.setVisible(true);
			 return createMenu;
	 }

	 
	static ActionListener CreateListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Connect to SQL and save new column in employee
        	String[] SQL;
			try {
				SQL = Config.SQLConfig();
				
				final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		    	
		    	Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
				
				
				
				String statement = "insert into employee(firstname,lastname,middlename,telNum,email,sex,ssn,jobTitle,dob,doh,dot,salary,regularPay,regularHour,"
						+ "otPay,otHour,ptoPay,ptoHour,localTaxCode,addStateTax,addFedTax,vacationtimeAvail,vacationtimeUsed,Department,enabled) "
						+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement pstmt = conn.prepareStatement(statement,Statement.RETURN_GENERATED_KEYS);
				
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
					
				
				int rs = pstmt.executeUpdate();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
			
		}
		
	};	 
 }