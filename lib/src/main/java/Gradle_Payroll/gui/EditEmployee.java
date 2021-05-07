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

public class EditEmployee {
	
	static JComboBox<String> employee;
	static JComboBox<String> department;
	static JInternalFrame frame;
	static JLabel empNumL,statusL,nameL,addressL,cityL,stateL,zipL,emailL,ssnL,jobtitleL,dobL,dohL,dotL,localtaxcodeL,addstatetaxL,addfedtaxL,salaryL,reghourL,
	regpayL,othourL,otpayL,ptohourL,ptopayL,departmentL,teleL,sexL,vacationAvailL,vacationUsedL;
	static JTextField empNumT,statusT,nameT,addressT,cityT,stateT,zipT,emailT,ssnT,jobtitleT,dobT,dohT,dotT,localtaxcodeT,addstatetaxT,addfedtaxT,salaryT,reghourT,
	regpayT,othourT,otpayT,ptohourT,ptopayT,departmentT,teleT,sexT,vacationAvailT,vacationUsedT;
	static Employee emp;
	static Name empName;
	static Address empAddress;
	static String fName,mName,lName,fullName,dept;

	
	
	 //Create a new internal frame.
    protected static JInternalFrame createFrame() throws Exception{
        frame = new JInternalFrame();
        emp = new Employee();
		empAddress = new Address();
		empName = new Name();
		
		empNumT = new JTextField();
		statusT  = new JTextField();
		nameT = new JTextField();
		addressT = new JTextField();
		cityT = new JTextField();
		stateT = new JTextField();
		zipT = new JTextField();
		emailT = new JTextField();
		ssnT = new JTextField();
		jobtitleT = new JTextField();
		dobT = new JTextField();
		dohT = new JTextField();
		dotT = new JTextField();
		localtaxcodeT = new JTextField();
		addstatetaxT = new JTextField();
		addfedtaxT = new JTextField();
		salaryT = new JTextField();
		regpayT = new JTextField();
		otpayT = new JTextField();
		ptopayT = new JTextField();
		departmentT = new JTextField();
		teleT = new JTextField();
		sexT = new JTextField();
		vacationAvailT = new JTextField();
		vacationUsedT = new JTextField();
		
        frame.setVisible(true); //necessary as of 1.3
       
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException exceptSelected) {}
        
        
        employee = new JComboBox<String>();
        employee.addItemListener(employeeSel);
        
        department = new JComboBox<String>();
        //department.addItemListener(departmentSel);
        
        sqlPullDeptListRequest();
      
        setLabels();
       
    	//TODO: Add another button that brings of the individual tax table for this specific employee
        JButton taxTableB = new JButton("Edit Tax Table");
        taxTableB.addActionListener(taxTB);
        
    	JButton saveB = new JButton("Save");
    	saveB.addActionListener(saveEmp);
    	
    	JLabel name = new JLabel("Selected Employee:");
    	JLabel employeeDataL = new JLabel("<HTML><U> Employee Data </U></HTML>"); 
    	
    	System.out.println("Querrying DB...");
    	
    	sqlPullEmpListRequest();
		
		System.out.println("Creating Edit Frame");
		
		frame.setSize(1535, 820);
    	
    	
    	frame.setLayout(new GridBagLayout());
    	
    
    	//need to figure out if this can be moved to a separate class
    	GridBagConstraints t1 = new GridBagConstraints();
    	t1.gridx = 15;
    	t1.gridy = 15;
    	
    	GridBagConstraints g1 = new GridBagConstraints();
    	g1.gridx = 6;
    	g1.gridy = 0;
    	GridBagConstraints h1 = new GridBagConstraints();
    	h1.gridx = 7;
    	h1.gridy = 0;
    	GridBagConstraints m15 = new GridBagConstraints();
    	m15.gridx = 13;
    	m15.gridy = 14;
    	GridBagConstraints b2c2 = new GridBagConstraints();
    	b2c2.gridx = 1;
    	b2c2.gridy = 1;
    	b2c2.gridwidth = 2;
    	
    	
    	GridBagConstraints a3 = new GridBagConstraints();
    	a3.gridx = 0;
    	a3.gridy = 2;
    	GridBagConstraints a4 = new GridBagConstraints();
    	a4.gridx = 0;
    	a4.gridy = 3;
    	GridBagConstraints a5 = new GridBagConstraints();
    	a5.gridx = 0;
    	a5.gridy = 4;
    	GridBagConstraints a6 = new GridBagConstraints();
    	a6.gridx = 0;
    	a6.gridy = 5;
    	GridBagConstraints a7 = new GridBagConstraints();
    	a7.gridx = 0;
    	a7.gridy = 6;
    	GridBagConstraints a8 = new GridBagConstraints();
    	a8.gridx = 0;
    	a8.gridy = 7;
    	GridBagConstraints a9 = new GridBagConstraints();
    	a9.gridx = 0;
    	a9.gridy = 8;
    	GridBagConstraints a10 = new GridBagConstraints();
    	a10.gridx = 0;
    	a10.gridy = 9;
    	GridBagConstraints a11 = new GridBagConstraints();
    	a11.gridx = 0;
    	a11.gridy = 10;
    	GridBagConstraints a12 = new GridBagConstraints();
    	a12.gridx = 0;
    	a12.gridy = 11;
    	GridBagConstraints a13 = new GridBagConstraints();
    	a13.gridx = 0;
    	a13.gridy = 12;
    	GridBagConstraints a14 = new GridBagConstraints();
    	a14.gridx = 0;
    	a14.gridy = 13;
    	
    	
    	GridBagConstraints b3 = new GridBagConstraints();
    	b3.gridx = 1;
    	b3.gridy = 2;
    	GridBagConstraints b4 = new GridBagConstraints();
    	b4.gridx = 1;
    	b4.gridy = 3;
    	GridBagConstraints b5 = new GridBagConstraints();
    	b5.gridx = 1;
    	b5.gridy = 4;
    	GridBagConstraints b6 = new GridBagConstraints();
    	b6.gridx = 1;
    	b6.gridy = 5;
    	GridBagConstraints b7 = new GridBagConstraints();
    	b7.gridx = 1;
    	b7.gridy = 6;
    	GridBagConstraints b8 = new GridBagConstraints();
    	b8.gridx = 1;
    	b8.gridy = 7;
    	GridBagConstraints b9 = new GridBagConstraints();
    	b9.gridx = 1;
    	b9.gridy = 8;
    	GridBagConstraints b10 = new GridBagConstraints();
    	b10.gridx = 1;
    	b10.gridy = 9;
    	GridBagConstraints b11 = new GridBagConstraints();
    	b11.gridx = 1;
    	b11.gridy = 10;
    	GridBagConstraints b12 = new GridBagConstraints();
    	b12.gridx = 1;
    	b12.gridy = 11;
    	GridBagConstraints b13 = new GridBagConstraints();
    	b13.gridx = 1;
    	b13.gridy = 12;
    	GridBagConstraints b14 = new GridBagConstraints();
    	b14.gridx = 1;
    	b14.gridy = 13;
    	
    	
    	GridBagConstraints c3 = new GridBagConstraints();
    	c3.gridx = 2;
    	c3.gridy = 2;
    	GridBagConstraints c4 = new GridBagConstraints();
    	c4.gridx = 2;
    	c4.gridy = 3;
    	GridBagConstraints c5 = new GridBagConstraints();
    	c5.gridx = 2;
    	c5.gridy = 4;
    	GridBagConstraints c6 = new GridBagConstraints();
    	c6.gridx = 2;
    	c6.gridy = 5;
    	GridBagConstraints c7 = new GridBagConstraints();
    	c7.gridx = 2;
    	c7.gridy = 6;
    	GridBagConstraints c8 = new GridBagConstraints();
    	c8.gridx = 2;
    	c8.gridy = 7;
    	GridBagConstraints c9 = new GridBagConstraints();
    	c9.gridx = 2;
    	c9.gridy = 8;
    	GridBagConstraints c10 = new GridBagConstraints();
    	c10.gridx = 2;
    	c10.gridy = 9;
    	GridBagConstraints c11 = new GridBagConstraints();
    	c11.gridx = 2;
    	c11.gridy = 10;
    	GridBagConstraints c12 = new GridBagConstraints();
    	c12.gridx = 2;
    	c12.gridy = 11;
    	GridBagConstraints c13 = new GridBagConstraints();
    	c13.gridx = 2;
    	c13.gridy = 12;
    	GridBagConstraints c14 = new GridBagConstraints();
    	c14.gridx = 2;
    	c14.gridy = 13;
    	GridBagConstraints c15 = new GridBagConstraints();
    	c15.gridx = 2;
    	c15.gridy = 14;
    	
    	GridBagConstraints d3 = new GridBagConstraints();
    	d3.gridx = 3;
    	d3.gridy = 2;
    	GridBagConstraints d4 = new GridBagConstraints();
    	d4.gridx = 3;
    	d4.gridy = 3;
    	GridBagConstraints d5 = new GridBagConstraints();
    	d5.gridx = 3;
    	d5.gridy = 4;
    	GridBagConstraints d6 = new GridBagConstraints();
    	d6.gridx = 3;
    	d6.gridy = 5;
    	GridBagConstraints d7 = new GridBagConstraints();
    	d7.gridx = 3;
    	d7.gridy = 6;
    	GridBagConstraints d8 = new GridBagConstraints();
    	d8.gridx = 3;
    	d8.gridy = 7;
    	GridBagConstraints d9 = new GridBagConstraints();
    	d9.gridx = 3;
    	d9.gridy = 8;
    	GridBagConstraints d10 = new GridBagConstraints();
    	d10.gridx = 3;
    	d10.gridy = 9;
    	GridBagConstraints d11 = new GridBagConstraints();
    	d11.gridx = 3;
    	d11.gridy = 10;
    	GridBagConstraints d12 = new GridBagConstraints();
    	d12.gridx = 3;
    	d12.gridy = 11;
    	GridBagConstraints d13 = new GridBagConstraints();
    	d13.gridx = 3;
    	d13.gridy = 12;
    	GridBagConstraints d14 = new GridBagConstraints();
    	d14.gridx = 3;
    	d14.gridy = 13;
    	GridBagConstraints d15 = new GridBagConstraints();
    	d15.gridx = 3;
    	d15.gridy = 14;
    	
    	
    	frame.add(name,g1);
    	frame.add(employee,h1);
    	frame.add(saveB,m15);
    	
    	frame.add(employeeDataL,b2c2);
    
    	frame.add(empNumL,a3);
    	//frame.add(test,b3); //testing the looks with text fields
    	frame.add(empNumT,b3);
    	frame.add(nameL,a4);
    	frame.add(nameT,b4);
    	frame.add(statusL,a5);
    	frame.add(statusT,b5);
    	frame.add(departmentL,a6);
    	frame.add(department,b6);
    	frame.add(addressL,a7);
    	frame.add(addressT,b7);
    	frame.add(cityL,a8);
    	frame.add(cityT,b8);
    	frame.add(stateL,a9);
    	frame.add(stateT,b9);
    	frame.add(zipL,a10);
    	frame.add(zipT,b10);
    	frame.add(teleL,a11);
    	frame.add(teleT,b11); 
    	frame.add(emailL,a12);
    	frame.add(emailT,b12);
    	frame.add(sexL,a13);
    	frame.add(sexT,b13);
    	frame.add(ssnL,a14);
    	frame.add(ssnT,b14);
    	frame.add(jobtitleL,c3);
    	frame.add(jobtitleT,d3);
    	frame.add(dobL,c4);
    	frame.add(dobT,d4);
    	frame.add(dohL,c5);
    	frame.add(dohT,d5);
    	frame.add(dotL,c6);
    	frame.add(dotT,d6);
    	frame.add(localtaxcodeL,c7);
    	frame.add(localtaxcodeT,d7);
    	frame.add(addstatetaxL,c8);
    	frame.add(addstatetaxT,d8);
    	frame.add(addfedtaxL,c9);
    	frame.add(addfedtaxT,d9);
    	frame.add(salaryL,c10);
    	frame.add(salaryT,d10);
    	frame.add(regpayL,c11);
    	frame.add(regpayT,d11);
    	frame.add(otpayL,c12);
    	frame.add(otpayT,d12);
    	frame.add(ptopayL,c13);
    	frame.add(ptopayT,d13);
    	frame.add(vacationAvailL,c14);
    	frame.add(vacationAvailT,d14);
    	frame.add(vacationUsedL,c15);
    	frame.add(vacationUsedT,d15);
    	frame.add(taxTableB,t1);
    	
    
    	
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
        nameL = new JLabel("Name: ");
        addressL = new JLabel("Address: ");
        cityL = new JLabel("City: ");
        stateL = new JLabel("State: ");
        zipL = new JLabel("Zip: ");
        emailL = new JLabel("Email: ");
        ssnL = new JLabel("SSN: ");
        jobtitleL = new JLabel("Job Title: ");
        dobL = new JLabel("Date of Birth: ");
        dohL = new JLabel("Date Hired: ");
        dotL = new JLabel("Date Terminated: ");
        localtaxcodeL = new JLabel("Local Tax Code: ");
        addstatetaxL = new JLabel("Additional State Tax: ");
        addfedtaxL = new JLabel("Additional Federal Tax: ");
        salaryL = new JLabel("Salary: ");
    	regpayL = new JLabel("Regular Time: ");
    	otpayL = new JLabel("Overtime: ");
    	ptopayL = new JLabel("P.T.O Time: ");
    	departmentL = new JLabel("Department: ");
    	teleL = new JLabel("Telephone: ");
    	sexL = new JLabel("Sex: ");
    	vacationAvailL = new JLabel("Vacation Time Available: ");
    	vacationUsedL = new JLabel("Vacation Time Used: ");
	}
    
    private static void sqlPullEmpListRequest() throws Exception, SQLException {
		String[] SQL = Config.PullSQLConfig();
		
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
		
		
		System.out.println("Data Retreived Successfull for " + i + " Employee entries.");
		
		rs.close();
		conn.close();
	}
    
    private static void sqlPullDeptListRequest() throws Exception,SQLException{
    	String[] SQL = Config.PullSQLConfig();
		
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
		
		
		System.out.println("Data Retreived Successfull for " + i + " Department entries.");
		
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
			SQL = Config.PullSQLConfig();
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * " + "from employee " + 
			"inner join address on employee.id = address.employee_id " +
			//"inner join departments on employee.Department = departments.id " + 
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
			emp.setDepartment(rs.getInt("Department")-1);
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
			//Note: Department is off by one index when it is loaded in from 
			department.setSelectedIndex(emp.getDepartment());
			teleT.setText(emp.getTelnum());
			sexT.setText(emp.getSex());
			vacationAvailT.setText(String.valueOf(emp.getVacationTimeRemaining()));
			vacationUsedT.setText(String.valueOf(emp.getVacationTimeUsed()));
			
			
			
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
        		sqlPushAddressRequest(emp.getID());
        		sqlPushEmpRequest(name);
        	}catch (Exception UpdateEmpPush) {
        		UpdateEmpPush.printStackTrace();
        	}
		}
		private void sqlPushAddressRequest(int id) throws Exception{
			String[] SQL;
			System.out.println("Executing Update");
			
			empAddress.setStreet(addressT.getText());
			empAddress.setCity(cityT.getText());
			empAddress.setState(stateT.getText());
			empAddress.setZip(zipT.getText());
			
			
			SQL = Config.PullSQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);

			String updateStatement = "update address " + "set " +
			"street = ?, city = ?, state = ?, zip = ? " + 
			 "WHERE employee_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setString(1, empAddress.getStreet());
			pstmt.setString(2, empAddress.getCity());
			pstmt.setString(3, empAddress.getState());
			pstmt.setInt(4, Integer.parseInt(empAddress.getZip()));
			pstmt.setInt(5, emp.getID());
			
			System.out.println(pstmt);
			
			int rs = pstmt.executeUpdate();
			System.out.println("Updated " + rs + " rows.");
			
		}
		
		
		private void sqlPushEmpRequest(String name[]) throws Exception {
			String[] SQL;
			System.out.println("Executing Update");
			
			
			String fullName = nameT.getText();
			System.out.println(fullName);
			name = fullName.split(" ");
			empName.setFirst(name[0]);
			empName.setMiddle(name[1]);
			empName.setLast(name[2]);
			
			
			emp.setID(Integer.parseInt(empNumT.getText()));
			emp.setTelnum(teleT.getText());
			emp.setEmail(emailT.getText());
			emp.setSex(sexT.getText());
			emp.setSsn(ssnT.getText());
			emp.setJobTitle(jobtitleT.getText());
			emp.setDOB(dobT.getText());
			emp.setDOH(dohT.getText());
			emp.setDOT(dotT.getText());
			emp.setSalary(Double.parseDouble(salaryT.getText()));
			emp.setRegPay(Double.parseDouble(regpayT.getText()));
			//emp.setRegHour();
			emp.setOtPay(Double.parseDouble(otpayT.getText()));
			//emp.setOtHour();
			emp.setPtoPay(Double.parseDouble(ptopayT.getText()));
			//emp.setPtoHour();
			emp.setLocalTaxCode(Integer.parseInt(localtaxcodeT.getText()));
			emp.setAddStateTax(Double.parseDouble(addstatetaxT.getText()));
			emp.setAddFedTax(Double.parseDouble(addfedtaxT.getText()));
			emp.setVacationTimeRemaining(Double.parseDouble(vacationAvailT.getText()));
			emp.setVacationTimeUsed(Double.parseDouble(vacationUsedT.getText()));
			emp.setDepartment(department.getSelectedIndex()+1);
			emp.setStatus("Active");
			emp.setName(empName);
			emp.setAddress(empAddress);
			
			
			SQL = Config.PullSQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);

			String updateStatement = "update employee " + "set " +
			"firstname = ?, middlename = ?, lastname = ?, telNum = ?, email = ?, " + 
			"sex = ?, ssn = ?, jobTitle = ?, dob = ?, doh = ?, dot = ?, " +
			"salary = ?, regularPay = ?, regularHour = ?, otPay = ?, " +
			"otHour = ?, ptoPay = ?, ptoHour = ?, localTaxCode = ?, " +
			"addStateTax = ?, addFedTax = ?, vacationtimeAvail = ?, " +
			"vacationtimeUsed = ?, Department = ? " + "WHERE firstname = ? and lastname = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			pstmt.setString(1, empName.getFirst());
			pstmt.setString(2, empName.getMiddle());
			pstmt.setString(3, empName.getLast());
			pstmt.setString(4, emp.getTelnum());
			pstmt.setString(5, emp.getEmail());
			pstmt.setString(6, emp.getSex());
			pstmt.setString(7, emp.getSsn());
			pstmt.setString(8, emp.getJobTitle());
			pstmt.setString(9, emp.getDOB());
			pstmt.setString(10, emp.getDOH());
			pstmt.setString(11, emp.getDOT());
			pstmt.setDouble(12, emp.getSalary());
			pstmt.setDouble(13, emp.getRegPay());
			pstmt.setDouble(14, emp.getRegHour());
			pstmt.setDouble(15, emp.getOtPay());
			pstmt.setDouble(16, emp.getOtHour());
			pstmt.setDouble(17, emp.getPtoPay());
			pstmt.setDouble(18, emp.getPtoHour());
			pstmt.setInt(19, emp.getLocalTaxCode());
			pstmt.setDouble(20, emp.getAddStateTax());
			pstmt.setDouble(21, emp.getAddFedTax());
			pstmt.setDouble(22, emp.getVacationTimeRemaining());
			pstmt.setDouble(23, emp.getVacationTimeUsed());
			pstmt.setInt(24, emp.getDepartment());
			pstmt.setString(25, empName.getFirst());
			pstmt.setString(26, empName.getLast());

			System.out.println(pstmt);
			
			int rs = pstmt.executeUpdate();
			System.out.println("Updated " + rs + " rows.");
			
		}
	};
	
	static ActionListener taxTB = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				EditTaxTable.createDialog();
			}catch (Exception taxTablePull) {
				
			}
			
		}
		
	};
}


