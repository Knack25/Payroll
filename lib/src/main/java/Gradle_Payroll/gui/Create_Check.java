package Gradle_Payroll.gui;



import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Check;
import Gradle_Payroll.data.Tax;
import Gradle_Payroll.data.YTD;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.sql.MySQL;




public class Create_Check {

	static JComboBox<String> employee;
	static JDialog dialog;
	static JLabel regularL,ptoL,overtimeL,salaryL,advanceL,royaltiesL,checkNoL,hoursL,rateL,dateL,fixedPayL;
	static JTextField regHoursT,regRateT,ptoHoursT,ptoRateT,otHoursT,otRateT,salpayT,advpayT,royalpayT,checkNoT,dateT,startT,endT;
	static String fName,mName,lName,fullName;
	static Dimension minTextSize;
	static Check check;
	static YTD ytd;
	static Tax tax;
	static int empID;
	static int checkID; 
	static String[] selname;
	

	
	 protected static JDialog createCheckmenu()  throws Exception {
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		
		 	employee = new JComboBox<String>();
		 	
		 	minTextSize = new Dimension();
		 	minTextSize.setSize(50, 20);
		 	
		 	check = new Check();
		 	ytd = new YTD();
		 	tax = new Tax();
		 	
		 	
		 	
		 	
		 	
		 	sqlPullEmpListRequest();
		 
			JButton createB = new JButton("Create");
			createB.addActionListener(createCheck);
	    	//loadB.setActionCommand("TermSubmit");
			//createB.addActionListener(submit);
	    	regHoursT = new JTextField();
	    	regHoursT.setPreferredSize(minTextSize);
	    	regRateT = new JTextField();
	    	regRateT.setPreferredSize(minTextSize);
	    	ptoHoursT = new JTextField();
	    	ptoHoursT.setPreferredSize(minTextSize);
	    	ptoRateT = new JTextField();
	    	ptoRateT.setPreferredSize(minTextSize);
	    	otHoursT = new JTextField();
	    	otHoursT.setPreferredSize(minTextSize);
	    	otRateT = new JTextField();
	    	otRateT.setPreferredSize(minTextSize);
	    	salpayT = new JTextField();
	    	salpayT.setPreferredSize(minTextSize);
	    	advpayT = new JTextField();
	    	advpayT.setPreferredSize(minTextSize);
	    	royalpayT = new JTextField();
	    	royalpayT.setPreferredSize(minTextSize);
	    	checkNoT = new JTextField();
	    	checkNoT.setPreferredSize(minTextSize);
	    	checkNoT.setSize(minTextSize);
	    	dateT = new JTextField();
	    	dateT.setPreferredSize(minTextSize);
	    	startT = new JTextField();
	    	startT.setPreferredSize(minTextSize);
	    	endT = new JTextField();
	    	endT.setPreferredSize(minTextSize);
			
			System.out.println("Creating Dialog Box");
			
			setLabels();
	    	
	    	dialog.setSize(400,400);
	    	dialog.setLayout(new GridBagLayout());
	    	
	    	/*TODO: Created all elements required to set Check Date...
	    	need to create location on dialog to place it.
	    	*/ 
	    	
	    	
	    	
	    	GridBagConstraints c2d2 = new GridBagConstraints();
	    	c2d2.gridx = 2;
	    	c2d2.gridy = 1;
	    	c2d2.gridwidth = 2;
	    	
	    	GridBagConstraints a6c6 = new GridBagConstraints();
	    	a6c6.gridx = 0;
	    	a6c6.gridy = 5;
	    	a6c6.gridwidth = 3;
	    	
	    	GridBagConstraints b7c7 = new GridBagConstraints();
	    	b7c7.gridx = 1;
	    	b7c7.gridy = 6;
	    	b7c7.gridwidth = 2;

	    	GridBagConstraints b8c8 = new GridBagConstraints();
	    	b8c8.gridx = 1;
	    	b8c8.gridy = 7;
	    	b8c8.gridwidth = 2;
	    	
	    	GridBagConstraints b9c9 = new GridBagConstraints();
	    	b9c9.gridx = 1;
	    	b9c9.gridy = 8;
	    	b9c9.gridwidth = 2;
	  
	    	GridBagConstraints c11 = new GridBagConstraints();
	    	c11.gridx = 2;
	    	c11.gridy = 10;
	    	
	    	dialog.add(createB,c11);
	    	

	    	dialog.add(rateL,c2d2);
	    	dialog.add(fixedPayL,a6c6);
	    	dialog.add(salpayT,b7c7);
	    	dialog.add(advpayT,b8c8);
	    	dialog.add(royalpayT,b9c9);
	    
	    	GridBagConstraints boom = new GridBagConstraints();
	    	for(int x = 0; x < 3; x++) {
	    		boom.gridx = x;
	    		for(int y = 0; y < 10; y++) {
	    			if(x==0) {
	    				switch(y) {
	    				case 0:
	    					boom.gridy = y;
	    					dialog.add(employee,boom);
	    					break;
	    				case 2:
	    					boom.gridy = y;
	    					dialog.add(regularL,boom);
	    					break;
	    				case 3:
	    					boom.gridy = y;
	    					dialog.add(ptoL,boom);
	    					break;
	    				case 4:
	    					boom.gridy = y;
	    					dialog.add(overtimeL,boom);
	    					break;
	    				case 6:
	    					boom.gridy = y;
	    					dialog.add(salaryL,boom);
	    					break;
	    				case 7:
	    					boom.gridy = y;
	    					dialog.add(advanceL,boom);
	    					break;
	    				case 8: 
	    					boom.gridy = y;
	    					dialog.add(royaltiesL,boom);
	    					break;
	    				case 9: 
	    					boom.gridy = y;
	    					dialog.add(dateL,boom);
	    					break;
	    				}
	    			}
	    			if(x==1) {
	    				switch(y) {
	    				
	    				case 0:
	    					boom.gridy = y;
	    					dialog.add(checkNoL,boom);
	    					break;
	    				case 1:
	    					boom.gridy = y;
	    					dialog.add(hoursL,boom);
	    					break;
	    				case 2:
	    					boom.gridy = y;
	    					dialog.add(regHoursT,boom);
	    					break;
	    				case 3:
	    					boom.gridy = y;
	    					dialog.add(ptoHoursT,boom);
	    					break;
	    				case 4:
	    					boom.gridy = y;
	    					dialog.add(otHoursT,boom);
	    					break;
	    				case 9:
	    					boom.gridy = y;
	    					dialog.add(startT,boom);
	    					break;
	    				}
	    			}
	    			if(x==2) {
	    				switch(y) {
	    				
	    				case 0:
	    					boom.gridy = y;
	    					dialog.add(checkNoT,boom);
	    					break;
	    				case 2:
	    					boom.gridy = y;
	    					dialog.add(regRateT,boom);
	    					break;
	    				case 3:
	    					boom.gridy = y;
	    					dialog.add(ptoRateT,boom);
	    					break;
	    				case 4:
	    					boom.gridy = y;
	    					dialog.add(otRateT,boom);
	    					break;
	    				case 9:
	    					boom.gridy = y;
	    					dialog.add(endT,boom);
	    					break;
	    				}
	    			}
	    		}
	    	}
	    	
	    	

	    	setCheckNum();
	    	
	    	preLoadData();
	    	employee.addActionListener(selUpdateListener);
	  
	    	dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
	    	dialog.setResizable(false);
			return dialog;
	 }
	 
	 static ActionListener selUpdateListener = new ActionListener() {
		
		@Override
		public
		 void actionPerformed(ActionEvent e) {
			try {
				preLoadData();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	 
	 private static void preLoadData() throws Exception {
		 String[] SQL = Config.PullSQLConfig();
		 int id = 0;
		 @SuppressWarnings("unused")
		 String fullname = (String) employee.getSelectedItem();
		 String[] selname = fullname.split(" ");
		 System.out.println(fullname);
		 System.out.println(selname[0]);
		 id = MySQL.sqlPullEmpID(selname);
		
			
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		String updateStatement = "select * " + "from employee " + "WHERE id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
		
		System.out.println(id);
		
		pstmt.setInt(1, id);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		regRateT.setText(String.valueOf(rs.getDouble("regularPay")));
		System.out.println("Regular rate Obtained: " + regRateT.getText());
		otRateT.setText(String.valueOf(rs.getDouble("otPay")));
		ptoRateT.setText(String.valueOf(rs.getDouble("ptoPay")));
		salpayT.setText(String.valueOf(rs.getDouble("salary")));
		royalpayT.setText(String.valueOf(rs.getDouble("royalty")));
		
		
		
	}

	private static void setCheckNum() throws Exception {
		 String[] SQL = Config.PullSQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			Statement stmt = conn.createStatement();
				
			ResultSet rs = stmt.executeQuery("select * from generalconfig where id = 1");
			rs.next();
			checkNoT.setText(String.valueOf(rs.getInt("nextCheckNum")));
	}

	private static void setLabels() {
		 regularL = new JLabel("Regular: ");
		 ptoL = new JLabel("P.T.O: ");
		 overtimeL = new JLabel("Overtime: ");
		 salaryL = new JLabel("Salary: ");
		 advanceL = new JLabel("Advance: ");
		 royaltiesL = new JLabel("Royalties: ");
		 checkNoL = new JLabel("Check #: ");
		 hoursL = new JLabel("<HTML><U> Hours </U></HTML>");
		 rateL = new JLabel("<HTML><U> Rate </U></HTML>");
		 fixedPayL = new JLabel("<HTML><U> Fixed Pay</U></HTML>");
		 dateL = new JLabel("Check Date Range: ");
		}
	 
	 static ActionListener createCheck = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
			fullName = (String) employee.getSelectedItem();
			String[] name = fullName.split(" ");
			System.out.println("Check Creation Requested...");
			
			try {
				empID = MySQL.sqlPullEmpID(name);
				checkID = sqlPushCheckInitRequest(empID);
				dialog.dispose();
				System.out.println(checkID);
				Main_Menu.CheckNum = checkID;
				Main_Menu.year = Calendar.YEAR;
				Main_Menu.processPayrollEdit();
				
				
			}catch (Exception PushCheckInitRequest) {
				PushCheckInitRequest.printStackTrace();
			}
			
		}
	};
	 
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
	 
	 private static int sqlPushCheckInitRequest(int ID) throws Exception,SQLException{
		 String[] SQL = Config.PullSQLConfig();
		 int checkID = 0;
		 checkID =  Integer.parseInt(checkNoT.getText());
			
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		String updateStatement = "Insert into checks(checknum,regHours,regRate,ptoHours,ptoRate,otHours," + 
		"otRate,salRate,advRate,royaltyRate,employee_id) Values(?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
		//checkNum
		pstmt.setDouble(1, checkID);
		//regHours
		pstmt.setDouble(2, Double.parseDouble(regHoursT.getText()));
		//regRate
		pstmt.setDouble(3, Double.parseDouble(regRateT.getText()));
		//ptoHours
		pstmt.setDouble(4, Double.parseDouble(ptoHoursT.getText()));
		//ptoRate
		pstmt.setDouble(5, Double.parseDouble(ptoRateT.getText()));
		//otHours
		pstmt.setDouble(6, Double.parseDouble(otHoursT.getText()));
		//otRate
		pstmt.setDouble(7, Double.parseDouble(otRateT.getText()));
		//salRate
		pstmt.setDouble(8, Double.parseDouble(salpayT.getText()));
		//advRate
		pstmt.setDouble(9, Double.parseDouble(advpayT.getText()));
		//royaltyRate
		pstmt.setDouble(10, Double.parseDouble(royalpayT.getText()));
		//employee_id
		pstmt.setInt(11, ID);
		
		pstmt.executeUpdate();
		
		conn.close();
		
		updateNextCheckNum(checkID);
		
		 
		 return checkID;
	 }

	private static void updateNextCheckNum(int checkID) throws Exception {
		 String[] SQL = Config.PullSQLConfig();
		 checkID = checkID + 1;
			
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "update generalconfig set nextCheckNum = ? where id = 1";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
		//checkNum
		pstmt.setInt(1, checkID);
		
		pstmt.executeUpdate();
		
	}
	 
}; 
	 