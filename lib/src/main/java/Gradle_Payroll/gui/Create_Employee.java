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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Tax;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.sql.MySQL;
 
 
 public class Create_Employee extends JDialog{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810663888408123313L;
	static String[] Name;
	static List<Tax> Taxes;
	static Tax tax;
	static JDialog createMenu;
	static JTextField enterFirst;
	static JTextField enterMiddle;
	static JTextField enterLast;
	
	 
	 public static JDialog CreateMenu() {
		 createMenu = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 createMenu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 createMenu.setSize(400, 150);	
		 
		 tax = new Tax();
		 Name = new String[3];
		 Taxes = new ArrayList<Tax>();
		 
		
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

	 
	 //********************************************************Create Listener******************************************************************************
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
		//********************************************************SQL Push Request*****************************************************************
		private void sqlPushRequest() throws Exception, SQLException {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			int id = 0;
			
			System.out.println("Connecting to DB...");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			conn.setAutoCommit(false);
			
			System.out.println("Connected.");
			
			/*TODO: Add method to trim any loose spaces before and after each field.
			 * If this is not done, SQL cannot find the correct employee*/
			
			 Name[0] = enterFirst.getText();
			 Name[1] = enterMiddle.getText();
			 Name[2] = enterLast.getText();
			 
			String statement = "INSERT INTO employee(firstname,middlename,lastname,telNum,email,sex,ssn,jobTitle,dob,doh,dot,salary,regularPay,regularHour,"
						+ "otPay,otHour,ptoPay,ptoHour,localTaxCode,addStateTax,addFedTax,vacationtimeAvail,vacationtimeUsed,Department,enabled) "
						+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(statement);
			
			System.out.println("Created Prepared Statement");
			
			pstmt.setString(1, Name[0]);
			pstmt.setString(2, Name[1]);
			pstmt.setString(3, Name[2]);
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
			 
			id = MySQL.sqlPullEmpID(Name);
			
			sqlNewEmpAddreessRequest(id);
			sqlPullDefaultTax(id);
			//sqlPushEmpTax();
			
			
			 createMenu.dispose();
		}
		
		
		//*************************************************SQL New Employee Address Request*********************************************************
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
		
		
		//***************************************************SQL Pull Default Tax Info******************************************************************
		private void sqlPullDefaultTax(int empID) throws Exception{
			String[] SQL;
			int i = 0;
			SQL = Config.PullSQLConfig();
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			String insertStatement = "Insert into tax(employee_id,taxname,taxtype,ammount,fedTaxExempt,stateTaxExempt,statePATaxExempt,SSCTaxExempt,"
					+ "medicareTaxExempt,localTaxExempt) values(?,?,?,?,?,?,?,?,?,?)";
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			Connection conn2 = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			Statement st = conn.createStatement();
			
			PreparedStatement pstmt = conn.prepareStatement(insertStatement);
			
			ResultSet rs = st.executeQuery("Select * from tax_defaults");
		
			
			
			while(rs.next()) {
				tax.setName(rs.getString("Name"));
				tax.setType(rs.getString("type"));
				tax.setAmmount(rs.getDouble("ammount"));
				tax.setFedTaxExempt(rs.getBoolean("fedTaxExempt"));
				tax.setStateTaxExempt(rs.getBoolean("stateTaxExempt"));
				tax.setStatePATaxExempt(rs.getBoolean("statePATaxExempt"));
				tax.setSscTaxExempt(rs.getBoolean("SSCTaxExempt"));
				tax.setMedicareTaxeExempt(rs.getBoolean("medicareTaxExempt"));
				tax.setLocalTaxExempt(rs.getBoolean("localTaxExempt"));
				tax.setEmployee_id(empID);
				System.out.println(tax.getName());
				//Taxes.add(tax);
				//System.out.println(Taxes.get(i).getName());
				//i++;
				
				pstmt.setInt(1, tax.getEmployee_id());
				pstmt.setString(2,tax.getName());
				pstmt.setString(3, tax.getType());
				pstmt.setDouble(4, tax.getAmmount());
				pstmt.setBoolean(5, tax.isFedTaxExempt());
				pstmt.setBoolean(6, tax.isStateTaxExempt());
				pstmt.setBoolean(7, tax.isStatePATaxExempt());
				pstmt.setBoolean(8, tax.isSscTaxExempt());
				pstmt.setBoolean(9, tax.isMedicareTaxeExempt());
				pstmt.setBoolean(10, tax.isLocalTaxExempt());
				
				int rs2 = pstmt.executeUpdate(); 
				i += rs2;
			}
			
			System.out.println("Inserted " + i + " rows into table.");
			
			
			conn.close();
			conn2.close();
			
			return;
		}
		
	};	 
 }