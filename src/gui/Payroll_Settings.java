package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.Address;
import data.Employee;
import data.Name;
import fileIO.Config;

public class Payroll_Settings {

	static JInternalFrame frame;
	static JLabel num;

	
	
	//GridBagLayout works very well for what needs to be done here
	
	 //Create a new internal frame.
    protected static JInternalFrame createFrame() throws Exception{
   
       
		
		System.out.println("Creating Edit Frame");
		
		frame.setSize(1535, 820);
    	
    	
    	frame.setLayout(new GridBagLayout());
   
    	//need to figure out if this can be moved to a separate class
    	GridBagConstraints g1 = new GridBagConstraints();
    	g1.gridx = 6;
    	g1.gridy = 0;

    	
    	frame.add(null);
    
    	
    	frame.setClosable(true);
    	frame.setMaximizable(true);
    	frame.setLocation(0, 0);
    	
    	//frame.repaint();
    	frame.setVisible(true);
    	
    
    	
        
    	/* need the following features,
    	-tax tables for the employees
    	-YTDs 
    	-function to create one check for the individual employee.
    	*/
    	
    	
        
		return frame;
    }

	private static void setLabels() {
		empNumL = new JLabel("Employee ID Number:");
        statusL = new JLabel("Status: ");
    	sexL = new JLabel("Sex: ");
	}
    
    private static void sqlPullEmpListRequest() throws Exception, SQLException {
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
    
    private static void sqlPullDeptListRequest() throws Exception,SQLException{
    	String[] SQL = Config.SQLConfig();
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		Statement stmt = conn.createStatement();
			
		ResultSet rs = stmt.executeQuery("select * from departments");
		
		
		
		int i = 0;
		
		while(rs.next()) {
			dept = rs.getString("depName");
			department.addItem(dept);
			i++;
		}
		
		
		System.out.println("Data Retreived Successfull for " + i + " entries.");
		
		rs.close();
		conn.close();
		
    }
    
    static ItemListener employeeSel = new ItemListener() {


		@Override
		public void itemStateChanged(ItemEvent e) {
			fullName = (String) employee.getSelectedItem();
        	String[] name = fullName.split(" ");
        	System.out.println("New Employee Selected...");
        	System.out.println("The value of fullName is: " + fullName);
        	
        	try {
        		sqlSelEmpPullRequest(name);
        	}catch (Exception SelEmpPull) {
        		SelEmpPull.printStackTrace();
        	}
        	frame.updateUI();
			
		}

		private void sqlSelEmpPullRequest(String[] name) throws Exception, SQLException {
			String[] SQL;
			SQL = Config.SQLConfig();
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * " + "from employee " + 
			"inner join address on employee.id = address.employee_id " +
			//"inner join departments on employee.Department = departments.id" + 
			"WHERE firstname = ? and lastname = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			if(name.length == 3) {
				pstmt.setString(1,name[0]);
				pstmt.setString(2, name[2]);
			}
			if(name.length == 2) {
				pstmt.setString(1,name[0]);
				pstmt.setString(2, name[1]);
			}
			
			//int output = pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			
			
			rs.next();
			empName.setFirst(rs.getString("firstname"));
			empName.setMiddle(rs.getString("middlename"));
			empName.setLast(rs.getString("lastname"));
			
			empAddress.setStreet(rs.getString("street"));
			empAddress.setCity(rs.getString("city"));
			empAddress.setState(rs.getString("state"));
			empAddress.setZip(rs.getString("zip"));
			
			emp.setID(rs.getInt("id"));
			emp.setTelnum(rs.getString("telNum"));
			emp.setEmail(rs.getString("email"));
			emp.setSex(rs.getString("sex"));
			emp.setSsn(rs.getString("ssn"));
			emp.setJobTitle(rs.getString("jobTitle"));
			emp.setDOB(rs.getString("dob"));
			emp.setDOH(rs.getString("doh"));
			emp.setDOT(rs.getString("dot"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setRegPay(rs.getDouble("regularPay"));
			emp.setRegHour(rs.getDouble("regularHour"));
			emp.setOtPay(rs.getDouble("otPay"));
			emp.setOtHour(rs.getDouble("otHour"));
			emp.setPtoPay(rs.getDouble("ptoPay"));
			emp.setPtoHour(rs.getDouble("ptoHour"));
			emp.setLocalTaxCode(rs.getInt("localTaxCode"));
			emp.setAddStateTax(rs.getDouble("addStateTax"));
			emp.setAddFedTax(rs.getDouble("addFedTax"));
			emp.setVacationTimeRemaining(rs.getDouble("vacationtimeAvail"));
			emp.setVacationTimeUsed(rs.getDouble("vacationtimeUsed"));
			emp.setDepartment(rs.getInt("Department"));
			emp.setStatus("Active");
			emp.setName(empName);
			emp.setAddress(empAddress);
			
			pstmt.close();
			conn.close();
			
			
			empNumT.setText(String.valueOf(emp.getID()));
			statusT.setText(emp.getStatus());
			nameT.setText(emp.getName().getFirst() + " " + emp.getName().getMiddle() + " " + emp.getName().getLast());
			addressT.setText(emp.getAddress().getStreet());
			cityT.setText(emp.getAddress().getCity());
			stateT.setText(emp.getAddress().getState());
			zipT.setText(emp.getAddress().getZip());
			emailT.setText(emp.getEmail());
			ssnT.setText(emp.getSsn());
			jobtitleT.setText(emp.getJobTitle());
			dobT.setText(emp.getDOB());
			dohT.setText(emp.getDOH());
			dotT.setText(emp.getDOT());
			localtaxcodeT.setText(String.valueOf(emp.getLocalTaxCode()));
			addstatetaxT.setText( String.valueOf(emp.getAddStateTax()));
			addfedtaxT.setText(String.valueOf(emp.getAddFedTax()));
			salaryT.setText(String.valueOf(emp.getSalary()));
			regpayT.setText(String.valueOf(emp.getRegPay()));
			otpayT.setText(String.valueOf(emp.getOtPay()));
			ptopayT.setText(String.valueOf(emp.getPtoPay()));
			//departmentT.setText(String.valueOf(emp.getDepartment()));
			department.setSelectedIndex(emp.getDepartment());
			teleT.setText(emp.getTelnum());
			sexT.setText(emp.getSex());
			
			
			
			frame.repaint();
		}
	};

	static ActionListener saveEmp = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fullName = (String) employee.getSelectedItem();
        	String[] name = fullName.split(" ");
        	System.out.println("Employee Save Requested...");
        	System.out.println("The value of fullName is: " + fullName);
        	
        	try {
        		sqlPushRequest(name);
        	}catch (Exception UpdateEmpPush) {
        		UpdateEmpPush.printStackTrace();
        	}
		}
		
		private void sqlPushRequest(String name[]) throws Exception {
			String[] SQL;
			System.out.println("Executing Update");
			
			SQL = Config.SQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String updateStatement = "select * " + "from employee "+ "inner join address on employee.id = address.employee_id " + "WHERE firstname = ? and lastname = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			
		}
	};
}
