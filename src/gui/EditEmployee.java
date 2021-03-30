package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.Address;
import data.Employee;
import data.Name;
import fileIO.Config;

public class EditEmployee {
	
	static JComboBox<String> employee;
	static JInternalFrame frame;
	static JLabel empNumL,statusL,nameL,addressL,cityL,stateL,zipL,emailL,ssnL,jobtitleL,dobL,dohL,dotL,localtaxcodeL,addstatetaxL,addfedtaxL,salaryL,reghourL,
	regpayL,othourL,otpayL,ptohourL,ptopayL,departmentL;
	static JTextField empNumT,statusT,nameT,addressT,cityT,stateT,zipT,emailT,ssnT,jobtitleT,dobT,dohT,dotT,localtaxcodeT,addstatetaxT,addfedtaxT,salaryT,reghourT,
	regpayT,othourT,otpayT,ptohourT,ptopayT,departmentT;
	static Employee emp;
	static Name empName;
	static Address empAddress;
	static String fName,mName,lName,fullName;
	private static JLabel teleL;

		
	//Will use the GridBagLayout as the layout manager before nesting panels
	
	
	 //Create a new internal frame.
    protected static JInternalFrame createFrame() throws Exception{
        frame = new JInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
       
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException exceptSelected) {}
        
      /*
        //main panel to put the elements in
        JPanel mainToppane = new JPanel();
        JPanel mainLeftpane = new JPanel();
        JPanel mainCenterpane = new JPanel();
        JPanel mainRightpane = new JPanel();
        
    
        //sub-panels to put the elements in for the left main panel
        JPanel empNoP = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel nameP = new JPanel(new FlowLayout(FlowLayout.LEADING));
        */
        
        employee = new JComboBox<String>();
        employee.addItemListener(employeeSel);
      
        setLabels();
    	
    	JButton saveB = new JButton("Save");
    	saveB.addActionListener(saveEmp);
    	
    	JLabel name = new JLabel("Selected Employee");
    	
    	System.out.println("Querrying DB...");
    	
    	//sqlPullEmpListRequest();
		
		System.out.println("Creating Edit Frame");
    	
		
    	frame.setSize(1535, 820);
    	
    	frame.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = 12;
    	c.gridy = 14;
    	frame.add(name);
    	frame.add(employee);
    	frame.add(empNumL);
    	frame.add(nameL);
    	frame.add(statusL);
    	frame.add(departmentL);
    	frame.add(addressL);
    	frame.add(cityL);
    	frame.add(stateL);
    	frame.add(zipL);
    	frame.add(dobL);
    	
    	
    	//mainToppane.setLayout(new FlowLayout(FlowLayout.CENTER));
    	//mainLeftpane.setLayout(new FlowLayout());
    	//mainCenterpane.setLayout(null);
    	//mainRightpane.setLayout(null);
    	
 /*
    	frame.add(mainToppane,BorderLayout.NORTH);
    	frame.add(saveB,BorderLayout.PAGE_END);
    	frame.add(mainLeftpane,BorderLayout.WEST);
    	frame.add(mainCenterpane,BorderLayout.CENTER);
    	frame.add(mainRightpane,BorderLayout.EAST);
    	
    	
    	mainToppane.add(name);
    	mainToppane.add(employee);
    	
    	
    	mainLeftpane.add(empNoP);
    		empNoP.add(empNumL);
    	mainLeftpane.add(nameP);
    		nameP.add(nameL);
    	//leftPanel.add(nameL);
    	//leftPanel.add(statusL);
    	//leftPanel.add(departmentL);
    	*/
    	
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
    
    static ItemListener employeeSel = new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			fullName = (String) employee.getSelectedItem();
        	String[] name = fullName.split(" ");
        	System.out.println("The value of fullName is: " + fullName);
        	
        	try {
        		sqlSelEmpPullRequest(name);
        	}catch (Exception SelEmpPull) {
        		SelEmpPull.printStackTrace();
        	}
			
		}

		private void sqlSelEmpPullRequest(String[] name) throws Exception, SQLException {
			String[] SQL;
			SQL = Config.SQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * " + "from employee "+ "inner join address on employee.id = address.employee_id " + "WHERE firstname = ? and lastname = ?";
			
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
			
			emp = new Employee();
			empAddress = new Address();
			empName = new Name();
			
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
			emp.setStatus("Active");
			emp.setName(empName);
			emp.setAddress(empAddress);
			
			pstmt.close();
			conn.close();
			
			empNumT = new JTextField(emp.getID());
			statusT  = new JTextField(emp.getStatus());
			nameT = new JTextField(emp.getName().getFirst() + " " + emp.getName().getMiddle() + " " + emp.getName().getLast());
			addressT = new JTextField(emp.getAddress().getStreet());
			cityT = new JTextField(emp.getAddress().getCity());
			stateT = new JTextField(emp.getAddress().getState());
			zipT = new JTextField(emp.getAddress().getZip());
			emailT = new JTextField(emp.getEmail());
			ssnT = new JTextField(emp.getSsn());
			jobtitleT = new JTextField(emp.getJobTitle());
			dobT = new JTextField(emp.getDOB());
			dohT = new JTextField(emp.getDOH());
			dotT = new JTextField(emp.getDOT());
			localtaxcodeT = new JTextField(String.valueOf(emp.getLocalTaxCode()));
			addstatetaxT = new JTextField( String.valueOf(emp.getAddStateTax()));
			addfedtaxT = new JTextField(String.valueOf(emp.getAddFedTax()));
			salaryT = new JTextField(String.valueOf(emp.getSalary()));
			regpayT = new JTextField(String.valueOf(emp.getRegPay()));
			otpayT = new JTextField(String.valueOf(emp.getOtPay()));
			ptopayT = new JTextField(String.valueOf(emp.getPtoPay()));
			departmentT = new JTextField(emp.getDepartment());
		}
	};

	static ActionListener saveEmp = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Push the updated file to here
		}
	};
}
