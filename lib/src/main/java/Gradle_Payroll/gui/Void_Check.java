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
	static JLabel nameL,checkNoL;
	static JButton voidB,cancelB;
	static String fName,mName,lName,fullName;
	static Double CHECKNUM;
	static int EMPID;
	static JTextField checkNetAmmntT,checkDateT;
	static YTD yTD_Initial,yTD_Calc;
	static List<Tax> tax;
	static Check check;

	
	 protected static JDialog createVoidcheckMenu()  throws Exception {
		 dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		 //dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 
		 nameL = new JLabel("Name: ");
		 checkNoL = new JLabel("Check No. : ");
		 
		 checkNetAmmntT = new JTextField();
		 checkDateT = new JTextField();
	    	
		 	
    	voidB = new JButton("Void");
		voidB.addActionListener(submitBListener);
		
		cancelB = new JButton("Cancel");
		cancelB.addActionListener(cancelBListener);
		
		dialog.setSize(300, 100);
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
    	
    	sqlPullEmpListRequest();
    	
    	
    	
    	dialog.add(voidCheckL,b1c1);
    	dialog.add(nameL,a2);
    	dialog.add(employee,b2);
    	dialog.add(checkNoL,c2);
    	dialog.add(checkNo,d2);
    	dialog.add(voidB,a3);
    	dialog.repaint();
    	
    	
    	System.out.println("Created Dialog");
    	
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
			yTD_Calc.setAdvAmmntYTD(yTD_Initial.getAdvAmmntYTD() - check.getAdvAmmnt());
			yTD_Calc.setGrossAmmntYTD(yTD_Initial.getGrossAmmntYTD() - check.getGrossAmmnt());
			yTD_Calc.setNetAmmntYTD(yTD_Initial.getNetAmmntYTD() - check.getNetAmmnt());
			yTD_Calc.setOtAmmntYTD(yTD_Initial.getOtAmmntYTD() - check.getOtAmmnt());
			yTD_Calc.setOtHoursYTD(yTD_Initial.getOtHoursYTD() - check.getOtHours());
			yTD_Calc.setPtoAmmntYTD(yTD_Initial.getPtoAmmntYTD() - check.getPtoHours());
			yTD_Calc.setPtoHoursYTD(yTD_Initial.getPtoHoursYTD() - check.getPtoHours());
			yTD_Calc.setRegAmmntYTD(yTD_Initial.getRegAmmntYTD() - check.getRegAmmnt());
			yTD_Calc.setRegHoursYTD(yTD_Initial.getRegHoursYTD() - check.getRegHours());
			yTD_Calc.setRoyaltyAmmntYTD(yTD_Initial.getRoyaltyAmmntYTD() - check.getRoyaltyAmmnt());
			yTD_Calc.setSalAmmntYTD(yTD_Initial.getSalAmmntYTD() - check.getSalAmmnt());
			
			for(int i = 0; i < tax.size();i++) {
				tax.get(i).setFinalYTD(tax.get(i).getInitYTD() - tax.get(i).getNetAmmount());
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
			
			System.out.println("Pushig Check Tax to DB");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * from check_tax where check_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setDouble(1, CHECKNUM);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tax.get(i).setNetAmmount(rs.getDouble("netammount"));
				i++;
			}
		}

		private void sqlPushYTD() {
			sqlPushEmpYTD();
			sqlPushTaxYTD();
		}

		private void sqlPushEmpYTD() {
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

		private void sqlPushTaxYTD() {
			// TODO Auto-generated method stub
			
		}

		private void voidCheck() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			System.out.println("Querrying DB for selected Employee");
			
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
        	
        	try {
        		sqlCheckNumPullRequest(name);
        	}catch (Exception SelEmpPull) {
        		SelEmpPull.printStackTrace();
        	}
			
		}

		private void sqlCheckNumPullRequest(String[] name) throws Exception, SQLException {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			
			EMPID = MySQL.sqlPullEmpID(name);
			double checkNum = 0;
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * " + "from checks " + 
			"WHERE employee_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
			pstmt.setInt(1, EMPID);

		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				checkNum = rs.getDouble("checknum");
				checkNo.addItem(checkNum);
			}
			
			
			
			pstmt.close();
			conn.close();
			
			
			
			
			
			
			dialog.repaint();
		}
	};
	
	static ItemListener checkNoListener = new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			 CHECKNUM = (Double) checkNo.getSelectedItem();
			
			 try {
				sqlPullCheckInfo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			 
			
		}

		private void sqlPullCheckInfo() throws Exception {
			String[] SQL = Config.PullSQLConfig();
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			
			String updateStatement = "select * from checks where checknum = ?";
					
			PreparedStatement pstmt = conn.prepareStatement(updateStatement);
			
				
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			checkNetAmmntT.setText(String.valueOf(rs.getDouble("netAmmt")));
			checkDateT.setText(rs.getString("date"));
			
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
	 
	private static void sqlPullTaxYTD() throws Exception {
		int i  = 0;
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
		
		System.out.println("Querrying DB for Check Data");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from checks " + "WHERE checknum = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		
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
	}
	
}
