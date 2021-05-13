package Gradle_Payroll.gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Check;
import Gradle_Payroll.data.Name;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.fileIO.Excel_Out;

public class Check_Edit {
	
	static JInternalFrame frame;
	static int CHECKNUM,EMPNUM;
	static JButton printB,cancelB;
	static JLabel chkNumL,chkDateL,payPeriodL,hourRateL,salaryL,regHrsL,ptoHrsL,otHrsL,otherL,grossPayL,currentL,YTDL;
	static JTextField nameT,addressT,cityStateZipT,dateT,amntT;
	static Address addr;
	static Name name;
	static Check check;
	
	static String amntSpellOut;
	
	protected static JInternalFrame createDialog(int checkNum) {
		frame = new JInternalFrame();
		frame.setSize(320, 320);
    	frame.setLayout(new GridBagLayout());
    	
    	printB = new JButton("Print and Save");
    	printB.addActionListener(printBListener);
    	
    	cancelB = new JButton("Cancel");
    	cancelB.addActionListener(cancelBListener);
    	
    	addr = new Address();
    	name = new Name();
    	check = new Check();
    	
    	CHECKNUM = checkNum;
    	
    	
    	
		
		
    	
    	
    	//TODO: Add method to calculate taxes for check
    	
		pullData();
		calcGross();
		calcNet();
		calcTaxes();
			//Dante: can you run it through a calculator that deducts from each field that is applicable and send it?
		//TODO: Add method to format and print check
    	
    	
    	
    	
		
    	
    	frame.setVisible(true);
    	frame.setResizable(true);
		return frame;
	}

	private static void pullData() {
		try {
			pullCheckData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			pullEmpData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	private static void pullEmpData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from employee " + 
		"inner join address on employee.id = address.employee_id " +
		"WHERE id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		
		pstmt.setInt(1, EMPNUM);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		
		
		rs.next();
		name.setFirst(rs.getString("firstname"));
		name.setMiddle(rs.getString("middlename"));
		name.setLast(rs.getString("lastname"));
		
		addr.setStreet(rs.getString("street"));
		addr.setCity(rs.getString("city"));
		addr.setState(rs.getString("state"));
		addr.setZip(rs.getString("zip"));
		
		check.setID(rs.getInt("id"));
		check.setAddStateTax(rs.getDouble("addStateTax"));
		check.setAddFedTax(rs.getDouble("addFedTax"));
		check.setName(name);
		check.setAddress(addr);
		
		pstmt.close();
		conn.close();
		
	}

	private static void pullCheckData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from checks " + "WHERE id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		
		pstmt.setInt(1, CHECKNUM);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		EMPNUM = rs.getInt("employee_id");
		check.setRegHours(rs.getDouble("regHours"));
		check.setRegRate(rs.getDouble("regRate"));
		check.setPtoHours(rs.getDouble("ptoHours"));
		check.setPtoRate(rs.getDouble("ptoRate"));
		check.setOtHours(rs.getDouble("otHours"));
		check.setOtRate(rs.getDouble("otRate"));
		check.setSalRate(rs.getDouble("salRate"));
		check.setAdvRate(rs.getDouble("advRate"));
		check.setRoyaltyRate(rs.getDouble("royaltyRate"));
	}

	private static void calcGross() {
		// TODO Calculate Gross amount form all inputs
		
	}

	private static void calcNet() {
		// TODO Calculate Net from Gross and total Taxes
		
	}

	private static void calcTaxes() {
		// TODO Calculate Taxes based on exemptions and ammounts
		
	}
	
	static ActionListener printBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Call Excel funciton and send it off to be printed.
			try {
				Excel_Out.create("Dante", 12, 52);
				Excel_Out.formatColwidth(1, 17);
				Excel_Out.formatColwidth(2, 12);
				Excel_Out.formatColwidth(3, 7);
				Excel_Out.formatColwidth(4, 17);
				Excel_Out.formatColwidth(5, 17);
				Excel_Out.formatColwidth(6, 17);
				Excel_Out.formatColwidth(7, 17);
				Excel_Out.formatColwidth(8, 17);
				Excel_Out.formatColwidth(9, 17);
				Excel_Out.formatColwidth(10, 17);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Save the data from the check to an entry in the DB.
			
		}
	};
	
	static ActionListener cancelBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Dispose this screen and remove the check in progress from the database
			
		}
	};

}
