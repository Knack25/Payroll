package Gradle_Payroll.gui;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class Edit_Employee {
	
	static JComboBox<String> employee;
	static JComboBox<String> department;
	static JInternalFrame frame;
	static JLabel empNumL,statusL,nameL,addressL,cityL,stateL,zipL,emailL,ssnL,jobtitleL,dobL,dohL,dotL,localtaxcodeL,addstatetaxL,addfedtaxL,salaryL,reghourL,
	regpayL,othourL,otpayL,ptohourL,ptopayL,royaltyL,departmentL,teleL,sexL,vacationAvailL,vacationUsedL;
	static JTextField empNumT,statusT,nameT,addressT,cityT,stateT,zipT,emailT,ssnT,jobtitleT,dobT,dohT,dotT,localtaxcodeT,addstatetaxT,addfedtaxT,salaryT,reghourT,
	regpayT,othourT,otpayT,ptohourT,ptopayT,royaltyT,departmentT,teleT,sexT,vacationAvailT,vacationUsedT;
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
		
		
		
        frame.setVisible(true); //necessary as of 1.3
       
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException exceptSelected) {}
        
        
        employee = new JComboBox<String>();
        employee.addItemListener(employeeSel);
        
        department = new JComboBox<String>();
        
        
        sqlPullDeptListRequest();
        setTextFields();
        setLabels();
       
        JButton taxTableB = new JButton("Edit Tax Table");
        taxTableB.addActionListener(taxTB);
        
        //JButton createCheckB = new JButton("Create Check");
        //taxTableB.addActionListener(createCheck);
        
    	JButton saveB = new JButton("Save");
    	saveB.addActionListener(saveEmp);
    	
    	JButton ytdB = new JButton("View YTD");
    	ytdB.addActionListener(ytdTB);
    	
    	JLabel name = new JLabel("Selected Employee:");
    	JLabel employeeDataL = new JLabel("<HTML><U> Employee Data </U></HTML>"); 
    	
    	System.out.println("Querrying DB...");
    	
    	sqlPullEmpListRequest();
		
		System.out.println("Creating Edit Frame");
		
		frame.setSize(1300, 650);
    	
    	
    	frame.setLayout(new GridBagLayout());
    	
    	
    	// edge 1
    	GridBagConstraints t1 = new GridBagConstraints();
    	t1.gridx = 15;
    	t1.gridy = 15;
    	
    	//block 2
    	GridBagConstraints g1 = new GridBagConstraints();
    	g1.gridx = 6;
    	g1.gridy = 0;
    	GridBagConstraints h1 = new GridBagConstraints();
    	h1.gridx = 7;
    	h1.gridy = 0;
    	GridBagConstraints i1 = new GridBagConstraints();
    	i1.gridx = 8;
    	i1.gridy = 0;
    	
    	//edge 2
    	GridBagConstraints m15 = new GridBagConstraints();
    	m15.gridx = 13;
    	m15.gridy = 14;
    	
    	//edge 3
    	GridBagConstraints b2c2 = new GridBagConstraints();
    	b2c2.gridx = 1;
    	b2c2.gridy = 1;
    	b2c2.gridwidth = 2;
    	
    	
    	//edge 4
    	GridBagConstraints e3 = new GridBagConstraints();
    	e3.gridx = 4;
    	e3.gridy = 2;
    	GridBagConstraints e4 = new GridBagConstraints();
    	e4.gridx = 4;
    	e4.gridy = 3;
    	
    	frame.add(name,g1);
    	frame.add(employee,h1);
    	frame.add(saveB,m15);
    	frame.add(employeeDataL,b2c2);
    	frame.add(taxTableB,e3);
    	frame.add(ytdB,e4);
    	
    	
    	GridBagConstraints boom = new GridBagConstraints();
    	for(int x = 0; x < 4; x++) {
    		boom.gridx = x;
    		for(int y = 2; y < 16; y++) {
    			if(x==0) {
    				switch(y) {
					case 2:
						boom.gridy = y;
						frame.add(empNumL,boom);
						break;
					case 3:
						boom.gridy = y;
						frame.add(nameL,boom);
						break;
					case 4:
						boom.gridy = y;
						frame.add(statusL,boom);
						break;
					case 5:
						boom.gridy = y;
						frame.add(departmentL,boom);
						break;
					case 6:
						boom.gridy = y;
						frame.add(addressL,boom);
						break;
					case 7:
						boom.gridy = y;
						frame.add(cityL,boom);
						break;
					case 8:
						boom.gridy = y;
						frame.add(stateL,boom);
						break;
					case 9:
						boom.gridy = y;
						frame.add(zipL,boom);
						break;
					case 10:
						boom.gridy = y;
						frame.add(teleL,boom);
						break;
					case 11:
						boom.gridy = y;
						frame.add(emailL,boom);
						break;
					case 12:
						boom.gridy = y;
						frame.add(sexL,boom);
						break;
					case 13:
						boom.gridy = y;
						frame.add(ssnL,boom);
						break;
					case 14:
						boom.gridy = y;
						frame.add(jobtitleL,boom);
						break;
    				}
    			}
    			if(x==1) {
    				switch(y) {
					case 2:
						boom.gridy = y;
						frame.add(empNumT,boom);
						break;
					case 3:
						boom.gridy = y;
						frame.add(nameT,boom);
						break;
					case 4:
						boom.gridy = y;
						frame.add(statusT,boom);
						break;
					case 5:
						boom.gridy = y;
						frame.add(department,boom);
						break;
					case 6:
						boom.gridy = y;
						frame.add(addressT,boom);
						break;
					case 7:
						boom.gridy = y;
						frame.add(cityT,boom);
						break;
					case 8:
						boom.gridy = y;
						frame.add(stateT,boom);
						break;
					case 9:
						boom.gridy = y;
						frame.add(zipT,boom);
						break;
					case 10:
						boom.gridy = y;
						frame.add(teleT,boom); 
						break;
					case 11:
						boom.gridy = y;
						frame.add(emailT,boom);
						break;
					case 12:
						boom.gridy = y;
						frame.add(sexT,boom);
						break;
					case 13:
						boom.gridy = y;
						frame.add(ssnT,boom);
						break;
					case 14:
						boom.gridy = y;
						frame.add(jobtitleT,boom);
						break;
    				}
    			}
    			if(x==2) {
    				switch(y) {
					case 2:
						boom.gridy = y;
						frame.add(dobL,boom);
						break;
					case 3:
						boom.gridy = y;
						frame.add(dohL,boom);
						break;
					case 4:
						boom.gridy = y;
						frame.add(dotL,boom);
						break;
					case 5:
						boom.gridy = y;
						frame.add(localtaxcodeL,boom);
						break;
					case 6:
						boom.gridy = y;
						frame.add(addstatetaxL,boom);
						break;
					case 7:
						boom.gridy = y;
						frame.add(addfedtaxL,boom);
						break;
					case 8:
						boom.gridy = y;
						frame.add(salaryL,boom);
						break;
					case 9:
						boom.gridy = y;
						frame.add(regpayL,boom);
						break;
					case 10:
						boom.gridy = y;
						frame.add(otpayL,boom);
						break;
					case 11:
						boom.gridy = y;
						frame.add(ptopayL,boom);
						break;
					case 12:
						boom.gridy = y;
						frame.add(royaltyL,boom);
						break;
					case 13:
						boom.gridy = y;
						frame.add(vacationAvailL,boom);
						break;
					case 14:
						boom.gridy = y;
						frame.add(vacationUsedL,boom);
						break;
    				}
    			}
    			if(x==3) {
    				switch(y) {
					case 2:
						boom.gridy = y;
						frame.add(dobT,boom);
						break;
					case 3:
						boom.gridy = y;
						frame.add(dohT,boom);
						break;
					case 4:
						boom.gridy = y;
						frame.add(dotT,boom);
						break;
					case 5:
						boom.gridy = y;
						frame.add(localtaxcodeT,boom);
						break;
					case 6:
						boom.gridy = y;
						frame.add(addstatetaxT,boom);
						break;
					case 7:
						boom.gridy = y;
						frame.add(addfedtaxT,boom);
						break;
					case 8:
						boom.gridy = y;
						frame.add(salaryT,boom);
						break;
					case 9:
						boom.gridy = y;
						frame.add(regpayT,boom);
						break;
					case 10:
						boom.gridy = y;
						frame.add(otpayT,boom);
						break;
					case 11:
						boom.gridy = y;
						frame.add(ptopayT,boom);
						break;
					case 12:
						boom.gridy = y;
						frame.add(royaltyT,boom);
						break;
					case 13:
						boom.gridy = y;
						frame.add(vacationAvailT,boom);
						break;
					case 14:
						boom.gridy = y;
						frame.add(vacationUsedT,boom);
						break;
    				}
    			}
    		}
    	}
    	
    	frame.setClosable(true);
    	frame.setMaximizable(true);
    	frame.setLocation(0, 0);
    	
    	frame.setVisible(true);
    	
        
		return frame;
    }
    
    
    private static void setTextFields() {
    	empNumT = new JTextField();
    	empNumT.addFocusListener(textFocusListener);
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
		royaltyT = new JTextField();
		departmentT = new JTextField();
		teleT = new JTextField();
		sexT = new JTextField();
		vacationAvailT = new JTextField();
		vacationUsedT = new JTextField();
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
    	royaltyL = new JLabel("Royalty: ");
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
		
		if(i < 1) {
			ErrorDialog.createError("No employees found. If none exist, please create a new one.");
		}
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
			"WHERE firstname = ? and lastname = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			
			pstmt.setString(1, name[0]);
			pstmt.setString(2, name[name.length-1]);
			
			
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
			emp.setRoyalty(rs.getDouble("royalty"));
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
			royaltyT.setText(String.valueOf(emp.getRoyalty()));
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
			emp.setRoyalty(Double.parseDouble(royaltyT.getText()));
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
			"otHour = ?, ptoPay = ?, ptoHour = ?,royalty = ?, localTaxCode = ?, " +
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
			pstmt.setDouble(19, emp.getRoyalty());
			pstmt.setInt(20, emp.getLocalTaxCode());
			pstmt.setDouble(21, emp.getAddStateTax());
			pstmt.setDouble(22, emp.getAddFedTax());
			pstmt.setDouble(23, emp.getVacationTimeRemaining());
			pstmt.setDouble(24, emp.getVacationTimeUsed());
			pstmt.setInt(25, emp.getDepartment());
			pstmt.setString(26, empName.getFirst());
			pstmt.setString(27, empName.getLast());

			System.out.println(pstmt);
			
			int rs = pstmt.executeUpdate();
			System.out.println("Updated " + rs + " rows.");
			
		}
	};
	
	static ActionListener taxTB = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println("Edit Tax Table Pressed.");
				Edit_Tax_Table.createDialog(emp.getID());
			}catch (Exception taxTablePull) {
				
			}
			
		}
		
	};
static ActionListener ytdTB = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println("Edit Tax Table Pressed.");
				Edit_YTD.createDialog(emp.getID());
			}catch (Exception taxTablePull) {
				
			}
			
		}
		
	};
	
	static FocusListener textFocusListener = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			this = e.getComponent();
		}
	};
}


