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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
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
	static JLabel regularL,ptoL,overtimeL,salaryL,advanceL,royaltiesL,checkNoL,hoursL,rateL,dateL;
	static JTextField regHoursT,regRateT,ptoHoursT,ptoRateT,otHoursT,otRateT,salHoursT,
	salRateT,advHoursT,advRateT,royalHoursT,royalRateT,checkNoT,dateT;
	static String fName,mName,lName,fullName;
	static Dimension minTextSize;
	static Check check;
	static YTD ytd;
	static Tax tax;
	static int empID;
	static int checkID; 
	

	
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
	    	advHoursT = new JTextField();
	    	advHoursT.setPreferredSize(minTextSize);
	    	advRateT = new JTextField();
	    	advRateT.setPreferredSize(minTextSize);
	    	salHoursT = new JTextField();
	    	salHoursT.setPreferredSize(minTextSize);
	    	salRateT = new JTextField();
	    	salRateT.setPreferredSize(minTextSize);
//	    	royalHoursT = new JTextField();
//	    	royalHoursT.setPreferredSize(minTextSize);
	    	royalRateT = new JTextField();
	    	royalRateT.setPreferredSize(minTextSize);
	    	checkNoT = new JTextField();
	    	checkNoT.setPreferredSize(minTextSize);
	    	checkNoT.setSize(minTextSize);
	    	dateT = new JTextField();
	    	dateT.setPreferredSize(minTextSize);
			
			System.out.println("Creating Dialog Box");
			
			setLabels();
	    	
	    	dialog.setSize(320, 320);
	    	dialog.setLayout(new GridBagLayout());
	    	
	    	/*TODO: Created all elements required to set Check Date...
	    	need to create location on dialog to place it.
	    	*/ 
	    	
	    	GridBagConstraints a1= new GridBagConstraints();
	    	a1.gridx = 0;
	    	a1.gridy = 0;
	    	
	    	GridBagConstraints b1= new GridBagConstraints();
	    	b1.gridx = 1;
	    	b1.gridy = 0;
	    	
	    	GridBagConstraints c1= new GridBagConstraints();
	    	c1.gridx = 2;
	    	c1.gridy = 0;
	    	
	    	GridBagConstraints b2= new GridBagConstraints();
	    	b2.gridx = 1;
	    	b2.gridy = 1;

	    	GridBagConstraints c2d2 = new GridBagConstraints();
	    	c2d2.gridx = 2;
	    	c2d2.gridy = 1;
	    	c2d2.gridwidth = 2;
	    	
	    	GridBagConstraints a9 = new GridBagConstraints();
	    	a9.gridx = 0;
	    	a9.gridy = 8;
	    	
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
	    
	    	
	    	
	    	dialog.add(employee,a1);
	    	dialog.add(rateL,c2d2);
	    	dialog.add(hoursL,b2);
	    	dialog.add(createB,a9);
	    	
	    	
	    	dialog.add(regularL,a3);
	    	dialog.add(ptoL,a4);
	    	dialog.add(overtimeL,a5);
	    	dialog.add(salaryL,a6);
	    	dialog.add(advanceL,a7);
	    	dialog.add(royaltiesL,a8);
	    	dialog.add(checkNoL,b1);
	    	
	    	dialog.add(regHoursT,b3);
	    	dialog.add(regRateT,c3);
	    	dialog.add(ptoHoursT,b4);
	    	dialog.add(ptoRateT,c4);
	    	dialog.add(otHoursT,b5);
	    	dialog.add(otRateT,c5);
	    	dialog.add(salHoursT,b6);
	    	dialog.add(salRateT,c6);
	    	dialog.add(advHoursT,b7);
	    	dialog.add(advRateT,c7);
	    	//TODO: Royalty is a fixed amount based on IP or the like... There is not hours related to it.
	    		//Dante: Royalty is like a commission. It's a fixed rate. Some taxes still apply to it.
	    	//dialog.add(royalHoursT,b8);
	    	dialog.add(royalRateT,c8);
	    	dialog.add(checkNoT,c1);
	    	dialog.add(employee,a1);
	    	
	    	setCheckNum();
	    	
	    	//TODO: Preload data from employee file for autofill
	  
	    	dialog.repaint();
	    	
	    	
	    	System.out.println("Created Dialog");
	    	
	    	dialog.setVisible(true);
	    	dialog.setResizable(false);
			return dialog;
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
		 dateL = new JLabel("Check Date: ");
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
				Main_Menu.CheckNum = checkID;
				
				//TODO: Add method to calculate taxes for check
					//Dante: can you run it through a calculator that deducts from each field that is applicable and send it?
				//TODO: Add method to format and print check
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
		
		//TODO: Finish creating statement and insert values
		String updateStatement = "Insert into checks(checknum,regHours,regRate,ptoHours,ptoRate,otHours," + 
		"otRate,salHours,salRate,advHours,advRate,royaltyRate,employee_id) Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
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
		//salHours
		pstmt.setDouble(8, Double.parseDouble(salHoursT.getText()));
		//salRate
		pstmt.setDouble(9, Double.parseDouble(salRateT.getText()));
		//advHours
		pstmt.setDouble(10, Double.parseDouble(advHoursT.getText()));
		//advRate
		pstmt.setDouble(11, Double.parseDouble(advRateT.getText()));
		//Royalty
		pstmt.setDouble(12, Double.parseDouble(royalRateT.getText()));
		//employee_id
		pstmt.setInt(13, ID);
		
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
		
		//TODO: Finish creating statement and insert values
		String updateStatement = "update generalconfig set nextCheckNum = ? where id = 1";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
		//checkNum
		pstmt.setInt(1, checkID);
		
		pstmt.executeUpdate();
		
	}
	 
}; 
	 