package Gradle_Payroll.gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Employee;
import Gradle_Payroll.data.Name;
import Gradle_Payroll.fileIO.Config;

public class Check_Edit {
	
	static JInternalFrame frame;
	static int CHECKNUM,EMPNUM;
	static JButton printB,cancelB;
	static JLabel chkNumL,chkDateL,payPeriodL,hourRateL,salaryL,regHrsL,ptoHrsL,otHrsL,otherL,grossPayL,currentL,YTDL;
	static JTextField nameT,addressT,cityStateZipT,dateT,amntT;
	static Employee emp;
	static Address addr;
	static Name name;
	
	static String amntSpellOut;
	
	protected static JInternalFrame createDialog(int checkNum) {
		frame = new JInternalFrame();
		frame.setSize(320, 320);
    	frame.setLayout(new GridBagLayout());
    	
    	printB = new JButton("Print and Save");
    	printB.addActionListener(printBListener);
    	
    	cancelB = new JButton("Cancel");
    	cancelB.addActionListener(cancelBListener);
    	
    	emp = new Employee();
    	addr = new Address();
    	name = new Name();
    	
    	CHECKNUM = checkNum;
    	
    	
    	
		
		
    	
    	
    	//TODO: Add method to calculate taxes for check
    	try {
			pullData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calcGross();
		calcNet();
		calcTaxes();
			//Dante: can you run it through a calculator that deducts from each field that is applicable and send it?
		//TODO: Add method to format and print check
    	
    	
    	
    	
		
    	
    	frame.setVisible(true);
    	frame.setResizable(true);
		return frame;
	}

	private static void pullData() throws Exception {
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
		emp.setRegHour(rs.getDouble("regHours"));
		emp.setRegPay(rs.getDouble("regRate"));
		emp.setPtoHour(rs.getDouble("ptoHours"));
		emp.setPtoPay(rs.getDouble("ptoRate"));
		emp.setOtHour(rs.getDouble("otHours"));
		emp.setOtPay(rs.getDouble("otRate"));
		emp.setSalary(rs.getDouble("salRate"));
		emp.setAdvance(rs.getDouble("advRate"));
		emp.setRoyalty(rs.getDouble("royaltyRate"));
		
		
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
