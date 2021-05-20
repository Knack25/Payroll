package Gradle_Payroll.gui;



import java.awt.Dialog;
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
import java.util.ArrayList;
import java.util.List;

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




public class Void_Check {

	static JComboBox<String> employee;
	static JComboBox<Double> checkNo;
	static JDialog dialog;
	static JLabel nameL,checkNoL,dateL,netPayL;
	static JButton voidB,cancelB;
	static String fName,mName,lName,fullName;
	static Double CHECKNUM;
	static int EMPID;
	static JTextField checkNetAmmntT,checkDateT;
	static YTD yTD_Initial,yTD_Calc;
	static List<Tax> tax;
	static Check check;
	static Double YEAR;
	static List<Double> checkNumbers;
	static List<Double> taxNetAmmount;

	
	 protected static JDialog createVoidcheckMenu()  throws Exception {
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 //dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 dialog.setLocation(80, 120);
		 nameL = new JLabel("Name: ");
		 checkNoL = new JLabel("Check No. : ");
		 netPayL  = new JLabel("Net Pay: ");
		 dateL  = new JLabel("Date: ");
		 
		 checkNetAmmntT = new JTextField();
		 checkNetAmmntT.setEditable(false);
		 checkDateT = new JTextField();
		 checkDateT.setEditable(false);
		 checkNumbers = new ArrayList<Double>();

		 
		 taxNetAmmount = new ArrayList<Double>();
	    	
    	voidB = new JButton("Void");
		voidB.addActionListener(submitBListener);
		
		cancelB = new JButton("Cancel");
		cancelB.addActionListener(cancelBListener);
		
		dialog.setSize(400, 250);
    	dialog.setLayout(new GridBagLayout());
    	
    	JLabel voidCheckL = new JLabel("<HTML><U> Void Check </U></HTML>"); 
    	
    	
    	employee = new JComboBox<String>();
    	employee.addItemListener(employeeSel);
    	
    	checkNo = new JComboBox<Double>();
    	checkNo.addItemListener(checkNoListener);


    	
    	yTD_Initial = new YTD();
    	yTD_Calc = new YTD();
    	
    	check = new Check();
    	
    	tax = new ArrayList<Tax>();
    	
    	sqlPullEmpListRequest();
    	
    	
    	
    	GridBagConstraints b1c1 = new GridBagConstraints();
    	b1c1.gridx = 1;
    	b1c1.gridy = 0;
    	b1c1.gridwidth = 2;

    	GridBagConstraints a2 = new GridBagConstraints();
    	a2.gridx = 0;
    	a2.gridy = 1;
    	
    	GridBagConstraints b2 = new GridBagConstraints();
    	b2.gridx = 1;
    	b2.gridy = 1;
    	
    	GridBagConstraints c2 = new GridBagConstraints();
    	c2.gridx = 2;
    	c2.gridy = 1;
    	
    	GridBagConstraints d2 = new GridBagConstraints();
    	d2.gridx = 3;
    	d2.gridy = 1;
    	
    	GridBagConstraints a3= new GridBagConstraints();
    	a3.gridx = 0;
    	a3.gridy = 2;

    	GridBagConstraints b3= new GridBagConstraints();
    	b3.gridx = 1;
    	b3.gridy = 2;
    	
    	GridBagConstraints c3= new GridBagConstraints();
    	c3.gridx = 2;
    	c3.gridy = 2;
    	
    	GridBagConstraints d3= new GridBagConstraints();
    	d3.gridx = 3;
    	d3.gridy = 2;
    	
    	GridBagConstraints e3= new GridBagConstraints();
    	e3.gridx = 4;
    	e3.gridy = 2;
    	
    	
    	
    	
    	dialog.add(voidCheckL,b1c1);
    	dialog.add(nameL,a2);
    	dialog.add(employee,b2);
    	dialog.add(checkNoL,c2);
    	dialog.add(checkNo,d2);
    	dialog.add(voidB,a3);
    	dialog.add(dateL,b3);
    	dialog.add(checkDateT,c3);
    	dialog.add(netPayL,d3);
    	dialog.add(checkNetAmmntT,e3);
    	dialog.repaint();
    	
    	
    	
    	
    	dialog.setVisible(true);
		return dialog;
	 }
	 
	 
	static ActionListener cancelBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.dispose();
		}
	};
	
	static ActionListener submitBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			removeYTD();
			try {
				voidCheck();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dialog.dispose();
		}

		private void removeYTD() {
			getCurrYTD();
			getCheck();
			removeCheckVals();
			sqlPushYTD();
		}

		private void removeCheckVals() {
			check.setRegAmmnt(check.getRegHours() * check.getRegRate());
			yTD_Calc.setAdvAmmntYTD(yTD_Initial.getAdvAmmntYTD() - check.getAdvAmmnt());
			//System.out.println(yTD_Calc.getAdvAmmntYTD());
			yTD_Calc.setGrossAmmntYTD(yTD_Initial.getGrossAmmntYTD() - check.getGrossAmmnt());
			//System.out.println(yTD_Calc.getGrossAmmntYTD());
			yTD_Calc.setNetAmmntYTD(yTD_Initial.getNetAmmntYTD() - check.getNetAmmnt());
			//System.out.println(yTD_Calc.getNetAmmntYTD());
			yTD_Calc.setOtAmmntYTD(yTD_Initial.getOtAmmntYTD() - check.getOtAmmnt());
			yTD_Calc.setOtHoursYTD(yTD_Initial.getOtHoursYTD() - check.getOtHours());
			yTD_Calc.setPtoAmmntYTD(yTD_Initial.getPtoAmmntYTD() - check.getPtoHours());
			yTD_Calc.setPtoHoursYTD(yTD_Initial.getPtoHoursYTD() - check.getPtoHours());
			yTD_Calc.setRegAmmntYTD(yTD_Initial.getRegAmmntYTD() - check.getRegAmmnt());
			yTD_Calc.setRegHoursYTD(yTD_Initial.getRegHoursYTD() - check.getRegHours());
			yTD_Calc.setRoyaltyAmmntYTD(yTD_Initial.getRoyaltyAmmntYTD() - check.getRoyaltyAmmnt());
			yTD_Calc.setSalAmmntYTD(yTD_Initial.getSalAmmntYTD() - check.getSalAmmnt());
			
			for(int i = 0; i < tax.size();i++) {
				System.out.println(i);
				System.out.println("Tax Init YTD: " + tax.get(i).getInitYTD() + " Subtract: " + taxNetAmmount.get(i));
				tax.get(i).setFinalYTD(tax.get(i).getInitYTD() - taxNetAmmount.get(i));
				System.out.println("Tax Final YTD: " + tax.get(i).getFinalYTD());
			}
		}

		private void getCurrYTD() {
			try {
				sqlPullTaxYTD();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sqlPullYTDData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void getCheck() {
			try {
				sqlPullCheckData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sqlPullCheckTax();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void sqlPullCheckTax() throws Exception {
			String[] SQL;
			int i = 0;
			SQL = Config.PullSQLConfig();
			
			System.out.println("Pulling Check Tax from DB");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * from check_tax where check_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setDouble(1, CHECKNUM);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				taxNetAmmount.add(rs.getDouble("netammount"));
				System.out.println("Tax Net Ammount: " + taxNetAmmount.get(i));
				i++;
			}
		}

		private void sqlPushYTD() {
			try {
				sqlPushEmpYTD();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sqlPushTaxYTD();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void sqlPushEmpYTD() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			System.out.println("Pushing YTD to DB");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "update ytd set  " + "ammount = ? " + "WHERE employee_id = ? and name = ? and year = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			
			pstmt.setInt(2, EMPID);
			pstmt.setDouble(4, YEAR);
	
			
			pstmt.setDouble(1, yTD_Calc.getGrossAmmntYTD());
			pstmt.setString(3, "grossAmmnt");
			@SuppressWarnings("unused")
			int rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getNetAmmntYTD());
			pstmt.setString(3, "netAmmnt");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1,yTD_Calc.getRegHoursYTD());
			pstmt.setString(3, "regHours");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getRegAmmntYTD());
			pstmt.setString(3, "regAmmnt");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getPtoHoursYTD());
			pstmt.setString(3, "ptoHours");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getPtoAmmntYTD());
			pstmt.setString(3, "ptoAmmnt");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getOtHoursYTD());
			pstmt.setString(3, "otHours");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getOtAmmntYTD());
			pstmt.setString(3, "otAmmnt");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getSalAmmntYTD());
			pstmt.setString(3, "salAmmnt");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getAdvAmmntYTD());
			pstmt.setString(3, "advAmmnt");
			rs = pstmt.executeUpdate();
			
			pstmt.setDouble(1, yTD_Calc.getRoyaltyAmmntYTD());
			pstmt.setString(3, "royaltyAmmnt");
			rs = pstmt.executeUpdate();
	
			
		}

		private void sqlPushTaxYTD() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			System.out.println("Pushing Tax YTD to DB");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String updateStatement = "update tax_ytd set  " + "ammount = ? " + "WHERE employee_id = ? and name = ? and year = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setInt(2, EMPID);
			pstmt.setDouble(4, YEAR);
			
			
			for(int i = 0; i < tax.size();i++) {
				pstmt.setDouble(1, tax.get(i).getFinalYTD());
				pstmt.setString(3, tax.get(i).getName());
				
				pstmt.executeUpdate();
			}
			
		}

		private void voidCheck() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "update checks set isVoid = true " + 
			"WHERE checknum = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setDouble(1, CHECKNUM);

		
			int rs = pstmt.executeUpdate();
			
			
			
		}
	};
	
	
	
	static ItemListener employeeSel = new ItemListener() {


		@Override
		public void itemStateChanged(ItemEvent e) {
			fullName = (String) employee.getSelectedItem();
        	String[] name = fullName.split(" ");
        	System.out.println("New Employee Selected...");
        	System.out.println("The value of fullName is: " + fullName);
        	checkNo.removeItemListener(checkNoListener);
        	try {
        		sqlCheckNumPullRequest(name);
        	}catch (Exception SelEmpPull) {
        		SelEmpPull.printStackTrace();
        	}
        	checkNo.addItemListener(checkNoListener);
			
		}

		private void sqlCheckNumPullRequest(String[] name) throws Exception, SQLException {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			
			EMPID = MySQL.sqlPullEmpID(name);
			double checkNum = 0;
			int i = 0;
			
			
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * " + "from checks " + 
			"WHERE employee_id = ? and isVoid = false";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setInt(1, EMPID);
			

		
			ResultSet rs = pstmt.executeQuery();
			//checkNo.removeItemListener(checkNoListener);
			//I Hate this line... It has caused me nothing but suffering... Why is there this, but it suggests removeAll before
			//this, when that doesn't do anything in most cases. 
			checkNo.removeAllItems();
			checkNumbers.clear();
			while(rs.next()) {
				checkNum = rs.getDouble("checknum");
				System.out.println("checkNum:" + checkNum);
				checkNo.addItem(checkNum);
				checkNumbers.add(checkNum);
				i++;
			}
			//checkNo.addItemListener(checkNoListener);
			
			
			pstmt.close();
			conn.close();
			
			
			if(i < 1) {
				ErrorDialog.createError("No Checks exist for this employee. Please select a different employee.");
				employee.setSelectedIndex(0);
				System.out.println("Null Checks Found");
				voidB.setEnabled(false);
			}
			else {
				voidB.setEnabled(true);
			}
			
			
			
			
		}
	};
	
	static ItemListener checkNoListener = new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			int checkIndex;
			 checkIndex = checkNo.getSelectedIndex();
			 CHECKNUM = checkNumbers.get(checkIndex).doubleValue();
			 System.out.println("CHECKNUM is: " + CHECKNUM);
			
			 try {
				sqlPullCheckInfo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			 
			
		}

		private void sqlPullCheckInfo() throws Exception {
			String[] SQL = Config.PullSQLConfig();
			int checkIndex;
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * from checks where checknum = ?";
			checkIndex = checkNo.getSelectedIndex();
			CHECKNUM = checkNumbers.get(checkIndex);
					
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setDouble(1, CHECKNUM);
				
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			checkNetAmmntT.setText(String.valueOf(rs.getDouble("netAmmt")));
			checkDateT.setText(rs.getString("date"));
			YEAR = rs.getDouble("year");
			
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
		
		
		
		
		rs.close();
		conn.close();
		
		if(i < 0) {
			ErrorDialog.createError("No Employees found. If no employees exist, please create one.");
			System.out.println("Null Employee Selected");
		}
	}
	 
	private static void sqlPullTaxYTD() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		Tax temptax = new  Tax();
		
		System.out.println("Querrying DB for Tax YTD Data");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from tax_ytd " + "WHERE employee_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
		
		pstmt.setInt(1, EMPID);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			temptax.setInitYTD(rs.getDouble("ammount"));
			System.out.println("Tax Init YTD: " + temptax.getInitYTD());
			tax.add(temptax);
		}
		
	}
	
	private static void sqlPullYTDData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from ytd " + "WHERE employee_id = ? and name = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
		
		pstmt.setInt(1, EMPID);
		pstmt.setString(2, "grossAmmnt");
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setGrossAmmntYTD(rs.getDouble("ammount"));
		//System.out.println(yTD_Initial.getGrossAmmntYTD());
		YEAR = rs.getDouble("year");
		
		pstmt.setString(2, "netAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setNetAmmntYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "regHours");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setRegHoursYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "regAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setRegAmmntYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "ptoHours");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setPtoHoursYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "ptoAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setPtoAmmntYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "otHours");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setOtHoursYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "otAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setOtAmmntYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "salAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setSalAmmntYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "advAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setAdvAmmntYTD(rs.getDouble("ammount"));
		
		pstmt.setString(2, "royaltyAmmnt");
		rs = pstmt.executeQuery();
		rs.next();
		yTD_Initial.setRoyaltyAmmntYTD(rs.getDouble("ammount"));
	}
	
	private static void sqlPullCheckData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		int checkIndex;
		
		System.out.println("Querrying DB for Check Data");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		checkIndex = checkNo.getSelectedIndex();
		CHECKNUM = checkNumbers.get(checkIndex);
		
		String updateStatement = "select * " + "from checks " + "WHERE checknum = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		System.out.println("CEHCKNUM: " + CHECKNUM);
		pstmt.setDouble(1, CHECKNUM);
		
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		EMPID = rs.getInt("employee_id");
		check.setRegHours(rs.getDouble("regHours"));
		check.setRegRate(rs.getDouble("regRate"));
		check.setPtoHours(rs.getDouble("ptoHours"));
		check.setPtoRate(rs.getDouble("ptoRate"));
		check.setOtHours(rs.getDouble("otHours"));
		check.setOtRate(rs.getDouble("otRate"));
		check.setSalAmmnt(rs.getDouble("salRate"));
		check.setAdvAmmnt(rs.getDouble("advRate"));
		check.setRoyaltyAmmnt(rs.getDouble("royaltyRate"));
		check.setStartDate(rs.getString("payrollStartDate"));
		check.setEndDate(rs.getString("payrollEndDate"));
		check.setGrossAmmnt(rs.getDouble("grossAmmnt"));
		//System.out.println(check.getGrossAmmnt());
		check.setNetAmmnt(rs.getDouble("netAmmt"));
	}
	
}
