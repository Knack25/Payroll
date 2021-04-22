package Gradle_Payroll.gui;


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

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Employee;
import Gradle_Payroll.data.Name;
import Gradle_Payroll.fileIO.Config;


public class Payroll_Settings {
	
	
	static JInternalFrame frame;
	static JLabel settingsL,payFreqL,payPeriodL,slashL,dashL;
	static JComboBox<String> payFreqD;
	static JTextField stMnthT,stDayT,stYrT,endMnthT,endDayT,endYrT;
	
	
	//GridBagLayout works very well for what needs to be done here
	
	 //Create a new internal frame.
    protected static JInternalFrame createFrame() throws Exception{
        frame = new JInternalFrame();
       
        System.out.println("Launching Frame");
		
        frame.setVisible(true); //necessary as of 1.3
       
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException exceptSelected) {}
        
        
      //  employee = new JComboBox<String>();
       // employee.addItemListener(employeeSel);
        
        //department = new JComboBox<String>();
        //department.addItemListener(departmentSel);
        
       // sqlPullDeptListRequest();
      
        setLabels();
       
    	
    	//JButton saveB = new JButton("Save");
    	//saveB.addActionListener(saveEmp);
    	
    	JButton viewRangesB = new JButton("View Yearly Pay Ranges");
		JButton deptB = new JButton("View/Edit Departments");
		JButton taxTableB = new JButton("View/Edit Default Tax Table");
		JButton termEmpB = new JButton("View/Edit Terminated Employees ");
		JButton developerB = new JButton("Developer Mode");
    	
    	
   
    	System.out.println("Querrying DB...");
    	
    	//sqlPullEmpListRequest();
		
		System.out.println("Creating Edit Frame");
		
		frame.setSize(1535, 820);
    	
    	
    	frame.setLayout(new GridBagLayout());
    	
    	//TODO: Configure constraints for vacationUsed and available
   
    	GridBagConstraints b1 = new GridBagConstraints();
    	b1.gridx = 1;
    	b1.gridy = 0;

    	GridBagConstraints n1 = new GridBagConstraints();
    	n1.gridx = 13;
    	n1.gridy = 0;
    	
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
    	
    	GridBagConstraints b4 = new GridBagConstraints();
    	b4.gridx = 1;
    	b4.gridy = 3;
    	
    	GridBagConstraints c2m2 = new GridBagConstraints();
    	c2m2.gridx = 2;
    	c2m2.gridy = 1;
    	c2m2.gridwidth = 11;
    	
    	GridBagConstraints c3 = new GridBagConstraints();
    	a3.gridx = 2;
    	a3.gridy = 2;
    	GridBagConstraints d3 = new GridBagConstraints();
    	a3.gridx = 3;
    	a3.gridy = 2;
    	GridBagConstraints e3 = new GridBagConstraints();
    	a3.gridx = 4;
    	a3.gridy = 2;
    	GridBagConstraints f3 = new GridBagConstraints();
    	a3.gridx = 5;
    	a3.gridy = 2;
    	GridBagConstraints g3 = new GridBagConstraints();
    	a3.gridx = 6;
    	a3.gridy = 2;
    	GridBagConstraints h3 = new GridBagConstraints();
    	a3.gridx = 7;
    	a3.gridy = 2;
    	GridBagConstraints i3 = new GridBagConstraints();
    	a3.gridx = 8;
    	a3.gridy = 2;
    	GridBagConstraints j3 = new GridBagConstraints();
    	a3.gridx = 9;
    	a3.gridy = 2;
    	GridBagConstraints k3 = new GridBagConstraints();
    	a3.gridx = 10;
    	a3.gridy = 2;
    	GridBagConstraints l3 = new GridBagConstraints();
    	a3.gridx = 11;
    	a3.gridy = 2;
    	GridBagConstraints m3 = new GridBagConstraints();
    	a3.gridx = 12;
    	a3.gridy = 2;
    	
    	

    	frame.add(settingsL,b1);
    	frame.add(payFreqL,a2);
    	frame.add(payFreqD,a3);
    	frame.add(viewRangesB,a4);
    	frame.add(payPeriodL,c2m2);
    	frame.add(deptB,b2);
    	frame.add(taxTableB,b3);
    	frame.add(termEmpB,b4);
    	frame.add(developerB,n1);
    	frame.add(stMnthT,c3);
    	frame.add(slashL,d3);
    	frame.add(stDayT,e3);
    	frame.add(slashL,f3);
    	frame.add(stYrT,g3);
    	frame.add(dashL,h3);
    	frame.add(endMnthT,i3);
    	frame.add(slashL,j3);
    	frame.add(endDayT,k3);
    	frame.add(slashL,l3);
    	frame.add(endYrT,m3);
    	
    
    	
    	frame.setClosable(true);
    	frame.setMaximizable(true);
    	frame.setLocation(0, 0);
    	
    	//frame.repaint();
    	frame.setVisible(true);
    	
       	
    	
        
		return frame;
    }

	private static void setLabels() {
		settingsL = new JLabel("<HTML><U> Settings </U></HTML>");
        payFreqL =  new JLabel("<HTML><U> Current Pay Frequency </U></HTML>");
        payPeriodL =  new JLabel("<HTML><U> Current Pay Period </U></HTML>");
        slashL = new JLabel("/");
        dashL = new JLabel("-");
	}
    
//    private static void sqlPullEmpListRequest() throws Exception, SQLException {
//		String[] SQL = Config.PullSQLConfig();
//		
//		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
//		
//		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
//		
//		
//		Statement stmt = conn.createStatement();
//			
//		ResultSet rs = stmt.executeQuery("select * from employee where enabled = true");
//		
//		
//		
//		int i = 0;
//		
//		
//		while(rs.next()) {
//			fName = rs.getString("firstname");
//			mName = rs.getString("middlename");
//			lName = rs.getString("lastname");
//			fullName = fName + " " + mName + " " + lName;
//			employee.addItem(fullName);
//			i++;
//		}
//		
//		
//		System.out.println("Data Retreived Successfull for " + i + " entries.");
//		
//		rs.close();
//		conn.close();
//	}
//    
//    private static void sqlPullDeptListRequest() throws Exception,SQLException{
//    	String[] SQL = Config.PullSQLConfig();
//		
//		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
//		
//		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
//		
//		
//		Statement stmt = conn.createStatement();
//			
//		ResultSet rs = stmt.executeQuery("select * from departments");
//		
//		
//		
//		int i = 0;
//		
//		while(rs.next()) {
//			dept = rs.getString("depName");
//			department.addItem(dept);
//			i++;
//		}
//		
//		
//		System.out.println("Data Retreived Successfull for " + i + " entries.");
//		
//		rs.close();
//		conn.close();
//		
//    }
//    
//    static ItemListener employeeSel = new ItemListener() {
//
//
//		@Override
//		public void itemStateChanged(ItemEvent e) {
//			fullName = (String) employee.getSelectedItem();
//        	String[] name = fullName.split(" ");
//        	System.out.println("New Employee Selected...");
//        	System.out.println("The value of fullName is: " + fullName);
//        	
//        	try {
//        		sqlSelEmpPullRequest(name);
//        	}catch (Exception SelEmpPull) {
//        		SelEmpPull.printStackTrace();
//        	}
//        	frame.updateUI();
//			
//		}
//
//		private void sqlSelEmpPullRequest(String[] name) throws Exception, SQLException {
//			String[] SQL;
//			SQL = Config.PullSQLConfig();
//			
//			System.out.println("Querrying DB for selected Employee");
//			
//			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
//			
//			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
//			
//			
//			String updateStatement = "select * " + "from employee " + 
//			"inner join address on employee.id = address.employee_id " +
//			//"inner join departments on employee.Department = departments.id " + 
//			"WHERE firstname = ? and lastname = ?";
//			
//			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
//
//			if(name.length == 3) {
//				pstmt.setString(1,name[0]);
//				pstmt.setString(2, name[2]);
//			}
//			if(name.length == 2) {
//				pstmt.setString(1,name[0]);
//				pstmt.setString(2, name[1]);
//			}
//			
//			//int output = pstmt.executeUpdate();
//			ResultSet rs = pstmt.executeQuery();
//			
//			
//			
//			rs.next();
//			empName.setFirst(rs.getString("firstname"));
//			empName.setMiddle(rs.getString("middlename"));
//			empName.setLast(rs.getString("lastname"));
//			//TODO: This needs to be assigned to the address table instead of the employee table
//			empAddress.setStreet(rs.getString("street"));
//			empAddress.setCity(rs.getString("city"));
//			empAddress.setState(rs.getString("state"));
//			empAddress.setZip(rs.getString("zip"));
//			
//			emp.setID(rs.getInt("id"));
//			emp.setTelnum(rs.getString("telNum"));
//			emp.setEmail(rs.getString("email"));
//			emp.setSex(rs.getString("sex"));
//			emp.setSsn(rs.getString("ssn"));
//			emp.setJobTitle(rs.getString("jobTitle"));
//			emp.setDOB(rs.getString("dob"));
//			emp.setDOH(rs.getString("doh"));
//			emp.setDOT(rs.getString("dot"));
//			emp.setSalary(rs.getDouble("salary"));
//			emp.setRegPay(rs.getDouble("regularPay"));
//			emp.setRegHour(rs.getDouble("regularHour"));
//			emp.setOtPay(rs.getDouble("otPay"));
//			emp.setOtHour(rs.getDouble("otHour"));
//			emp.setPtoPay(rs.getDouble("ptoPay"));
//			emp.setPtoHour(rs.getDouble("ptoHour"));
//			emp.setLocalTaxCode(rs.getInt("localTaxCode"));
//			emp.setAddStateTax(rs.getDouble("addStateTax"));
//			emp.setAddFedTax(rs.getDouble("addFedTax"));
//			emp.setVacationTimeRemaining(rs.getDouble("vacationtimeAvail"));
//			emp.setVacationTimeUsed(rs.getDouble("vacationtimeUsed"));
//			emp.setDepartment(rs.getInt("Department"));
//			emp.setStatus("Active");
//			emp.setName(empName);
//			emp.setAddress(empAddress);
//			
//			pstmt.close();
//			conn.close();
//			
//			
//			empNumT.setText(String.valueOf(emp.getID()));
//			statusT.setText(emp.getStatus());
//			nameT.setText(emp.getName().getFirst() + " " + emp.getName().getMiddle() + " " + emp.getName().getLast());
//			addressT.setText(emp.getAddress().getStreet());
//			cityT.setText(emp.getAddress().getCity());
//			stateT.setText(emp.getAddress().getState());
//			zipT.setText(emp.getAddress().getZip());
//			emailT.setText(emp.getEmail());
//			ssnT.setText(emp.getSsn());
//			jobtitleT.setText(emp.getJobTitle());
//			dobT.setText(emp.getDOB());
//			dohT.setText(emp.getDOH());
//			dotT.setText(emp.getDOT());
//			localtaxcodeT.setText(String.valueOf(emp.getLocalTaxCode()));
//			addstatetaxT.setText( String.valueOf(emp.getAddStateTax()));
//			addfedtaxT.setText(String.valueOf(emp.getAddFedTax()));
//			salaryT.setText(String.valueOf(emp.getSalary()));
//			regpayT.setText(String.valueOf(emp.getRegPay()));
//			otpayT.setText(String.valueOf(emp.getOtPay()));
//			ptopayT.setText(String.valueOf(emp.getPtoPay()));
//			//departmentT.setText(String.valueOf(emp.getDepartment()));
//			//Note: Department is off by one index when it is loaded in from 
//			department.setSelectedIndex(emp.getDepartment());
//			teleT.setText(emp.getTelnum());
//			sexT.setText(emp.getSex());
//			vacationAvailT.setText(String.valueOf(emp.getVacationTimeRemaining()));
//			vacationUsedT.setText(String.valueOf(emp.getVacationTimeUsed()));
//			
//			
//			
//			frame.repaint();
//		}
//	};
//
//	static ActionListener saveEmp = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			fullName = (String) employee.getSelectedItem();
//        	String[] name = fullName.split(" ");
//        	System.out.println("Employee Save Requested...");
//        	System.out.println("The value of fullName is: " + fullName);
//        	
//        	try {
//        		sqlPushRequest(name);
//        	}catch (Exception UpdateEmpPush) {
//        		UpdateEmpPush.printStackTrace();
//        	}
//		}
//		
//		private void sqlPushRequest(String name[]) throws Exception {
//			String[] SQL;
//			System.out.println("Executing Update");
//			
//			
//			String fullName = nameT.getText();
//			name = fullName.split(fullName);
//			empName.setFirst(name[0]);
//			empName.setMiddle(name[1]);
//			empName.setLast(name[2]);
//			
//			
//			empAddress.setStreet(addressT.getText());
//			empAddress.setCity(cityT.getText());
//			empAddress.setState(stateT.getText());
//			empAddress.setZip(zipT.getText());
//			
//			emp.setID(Integer.parseInt(empNumT.getText()));
//			emp.setTelnum(teleT.getText());
//			emp.setEmail(emailT.getText());
//			emp.setSex(sexT.getText());
//			emp.setSsn(ssnT.getText());
//			emp.setJobTitle(jobtitleT.getText());
//			emp.setDOB(dobT.getText());
//			emp.setDOH(dohT.getText());
//			emp.setDOT(dotT.getText());
//			emp.setSalary(Double.parseDouble(salaryT.getText()));
//			emp.setRegPay(Double.parseDouble(regpayT.getText()));
//			//emp.setRegHour();
//			emp.setOtPay(Double.parseDouble(otpayT.getText()));
//			//emp.setOtHour();
//			emp.setPtoPay(Double.parseDouble(ptopayT.getText()));
//			//emp.setPtoHour();
//			emp.setLocalTaxCode(Integer.parseInt(localtaxcodeT.getText()));
//			emp.setAddStateTax(Double.parseDouble(addstatetaxT.getText()));
//			emp.setAddFedTax(Double.parseDouble(addfedtaxT.getText()));
//			emp.setVacationTimeRemaining(Double.parseDouble(vacationAvailT.getText()));
//			emp.setVacationTimeUsed(Double.parseDouble(vacationUsedT.getText()));
//			emp.setDepartment(department.getSelectedIndex());
//			emp.setStatus("Active");
//			emp.setName(empName);
//			emp.setAddress(empAddress);
//			
//			
//			SQL = Config.PullSQLConfig();
//			
//			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
//			
//			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
//			//TODO: Finish pushing updated employee to DB
//			String updateStatement = "update employee " + "set " +
//			"firstname = ?, middlename = ?, lastname = ?, telNum = ?, email = ?, " + 
//			"sex = ?, ssn = ?, jobTitle = ?, dob = ?, doh = ?, dot = ?, " +
//			"salary = ?, regularPay = ?, regularHour = ?, otPay = ?, " +
//			"otHour = ?, ptoPay = ?, ptoHour = ?, localTaxCode = ?, " +
//			"addStateTax = ?, addFedTax = ?, vacationtimeAvail = ?, " +
//			"vacationtimeUsed = ?, Department = ? " + "WHERE firstname = ? and lastname = ?";
//			
//			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
//
//			pstmt.setString(0, empName.getFirst());
//			pstmt.setString(1, empName.getMiddle());
//			pstmt.setString(2, empName.getLast());
//			pstmt.setString(3, emp.getTelnum());
//			pstmt.setString(4, emp.getEmail());
//		}
//	};
};

   