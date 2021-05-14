package Gradle_Payroll.gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Check;
import Gradle_Payroll.data.Name;
import Gradle_Payroll.data.Tax;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.fileIO.Excel_Out;
import Gradle_Payroll.fileIO.NumberToWords;
import Gradle_Payroll.sql.MySQL;

public class Check_Edit {
	
	static JInternalFrame frame;
	static int CHECKNUM,EMPID,NUMTAXAMNT;
	static JButton printB,cancelB;
	static JLabel chkNumL,chkDateL,payPeriodL,hourRateL,salaryL,regHrsL,ptoHrsL,otHrsL,otherL,grossPayL,currentL,YTDL;
	static JTextField nameT,addressT,cityStateZipT,dateT,amntT,spelledAmntT,periodDateT,hourRateT,salaryCurrT,salaryYTDT;
	static JTextField regularHrsCurrT,regularHrsYTDT,ptoHrsCurrT,ptoHrsYTDT,otHrsCurrT,otHrsYTDT,otherHrsCurrT,otherHrsYTDT;
	static JTextField grossPayCurrT,grossPayYTDT,netPayCurrT,netPayYTDT;
	static Address addr;
	static Name name;
	static Check check;
	static List<Tax> tax;
	static List<JTextField> TaxCurrT,TaxYTDT;
	static String amntSpellOut,fullName,address,cityStateZip,payPeriodDate;
	static LocalDate date;
	static SimpleDateFormat dateFormat;
	static YTD ytd;
	
	
	
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
    	
    	tax = new ArrayList<Tax>();
    	
    	CHECKNUM = checkNum;
		
		
		sqlPullData(); //Done
		calcGross(); //Done
		calcTaxes(); //Done
		calcNet();
		calcYTD();
		createLabels();
		drawData();
			//Dante: can you run it through a calculator that deducts from each field that is applicable and send it?

    	
    	
    	
    	
		
    	
    	frame.setVisible(true);
    	frame.setResizable(true);
		return frame;
	}

	private static void calcYTD() {
		// TODO Load in YTD Values
		
	}

	private static void drawData() {
		formatData();
		instantiateFields();
	}

	private static void formatData() {
		fullName = check.getName().getFirst() + " " + check.getName().getLast();
		address = check.getAddress().getStreet();
		cityStateZip = check.getAddress().getCity() + " " + check.getAddress().getState() + " " + check.getAddress().getZip();
		date = LocalDate.now();
		amntSpellOut = NumberToWords.convert((long) check.getNetAmmnt());
		payPeriodDate = "Pay Period Date goes Here";
		
	}

	private static void instantiateFields() {
		nameT = new JTextField(fullName);
		addressT = new JTextField(address);
		cityStateZipT = new JTextField(cityStateZip);
		dateT = new JTextField(date.toString());
		amntT = new JTextField(String.valueOf(check.getNetAmmnt()));
		spelledAmntT = new JTextField(amntSpellOut);
		periodDateT = new JTextField(payPeriodDate);
		hourRateT = new JTextField(String.valueOf(check.getRegRate()));
		salaryCurrT = new JTextField(String.valueOf(check.getSalRate()));
		salaryYTDT
		regularHrsCurrT
		regularHrsYTDT
		ptoHrsCurrT
		ptoHrsYTDT
		otHrsCurrT
		otHrsYTDT
		otherHrsCurrT
		otherHrsYTDT
		grossPayCurrT
		grossPayYTDT
		netPayCurrT
		netPayYTDT
	}

	private static void createLabels() {
		chkNumL = new JLabel("Check Number: ");
		chkDateL = new JLabel("Date: ");
		payPeriodL = new JLabel("Pay Period: ");
		hourRateL = new JLabel("Rate: ");
		salaryL = new JLabel("Salary: ");
		regHrsL = new JLabel("Regular Hours: ");
		ptoHrsL = new JLabel("P.T.O. Hours: ");
		otHrsL = new JLabel("O.T. Hours: ");
		otherL = new JLabel("Other: ");
		grossPayL = new JLabel("Gross Amnt: ");
		currentL  = new JLabel("Current: ");
		YTDL  = new JLabel("Y.T.D: ");
		
	}

	private static void sqlPullData() {
		try {
			sqlPullCheckData();
		} catch (Exception CheckData) {
			CheckData.printStackTrace();
		}
		try {
			sqlPullEmpData();
		} catch (Exception EmpData) {
			EmpData.printStackTrace();
		}
		try {
			NUMTAXAMNT = MySQL.SQLTaxNum(EMPID);
		} catch (Exception TaxNum) {
			TaxNum.printStackTrace();
		}
		try {
			sqlPullTaxData();
		} catch (Exception TaxData) {
			TaxData.printStackTrace();
		}
		try {
			sqlPullYTDData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private static void sqlPullYTDData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from ytd " + "WHERE employee_id = ? and name = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
		
		pstmt.setInt(1, EMPID);
		pstmt.setString(2, "");
		
		ResultSet rs = pstmt.executeQuery();
		
		
		
	}

	private static void sqlPullTaxData() throws Exception {
		Tax tempTax = new Tax();
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from tax " + "WHERE employee_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);
		
		pstmt.setInt(1, EMPID);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			tempTax.setName(rs.getString("taxname"));
			tempTax.setType(rs.getString("taxtype"));
			tempTax.setAmmount(rs.getDouble("ammount"));
			tempTax.setFedTaxExempt(rs.getBoolean("fedTaxExempt"));
			tempTax.setStateTaxExempt(rs.getBoolean("stateTaxExempt"));
			tempTax.setState2TaxExempt(rs.getBoolean("state2TaxExempt"));
			tempTax.setSscTaxExempt(rs.getBoolean("SSCTaxExempt"));
			tempTax.setMedicareTaxeExempt(rs.getBoolean("medicareTaxExempt"));
			tempTax.setLocalTaxExempt(rs.getBoolean("localTaxExempt"));
			tempTax.setPrimaryTax(rs.getBoolean("primaryTax"));
			tax.add(tempTax);
		}
	}

	private static void sqlPullEmpData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from employee " + 
		"inner join address on employee.id = address.employee_id " +
		"WHERE id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		
		pstmt.setInt(1, EMPID);
		
		
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

	private static void sqlPullCheckData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		
		String updateStatement = "select * " + "from checks " + "WHERE id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		
		pstmt.setInt(1, CHECKNUM);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		EMPID = rs.getInt("employee_id");
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
		double reg,ot,pto,salary,other;
		reg = check.getRegRate() * check.getRegHours();
		ot = check.getOtRate() * check.getOtHours();
		pto = check.getPtoRate() * check.getPtoHours();
		salary =  check.getSalRate();
		other = check.getAdvRate() + check.getRoyaltyRate();
		check.setGrossAmmnt(reg + ot + pto + salary + other);
		check.setFedGrossAmmnt(check.getGrossAmmnt());
		check.setStateGrossAmmnt(check.getGrossAmmnt());
		check.setState2GrossAmmnt(check.getGrossAmmnt());
		check.setSscGrossAmmnt(check.getGrossAmmnt());
		check.setMedicareGrossAmmnt(check.getGrossAmmnt());
		check.setLocalGrossAmmnt(check.getGrossAmmnt());
	}

	private static void calcNet() {
		// TODO Calculate Net from Gross and total Taxes
		check.setNetAmmnt(check.getGrossAmmnt());
		
		//Get all of the taxes
		for(int i = 0; i < NUMTAXAMNT; i++) {
			check.setNetAmmnt(check.getNetAmmnt() - tax.get(i).getNetAmmount());
		}
		
		//TODO: Subtract all extra values
		check.setNetAmmnt(check.getNetAmmnt() - check.getAddFedTax());
		check.setNetAmmnt(check.getNetAmmnt() - check.getAddStateTax());
	}

	private static void calcTaxes() {
		
		
		
		for(int i = 0; i < NUMTAXAMNT;i++) {
			if(!tax.get(i).isPrimaryTax() && tax.get(i).getType() == "%") {
				tax.get(i).setNetAmmount(tax.get(i).getAmmount() * check.getGrossAmmnt());
			}else {
				tax.get(i).setNetAmmount(tax.get(i).getAmmount());
			}
		}
		
		
		
		for(int i = 0; i < NUMTAXAMNT;i++) {
			if(!tax.get(i).isPrimaryTax()) {
				if(tax.get(i).isFedTaxExempt()) {
					check.setFedGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if(tax.get(i).isStateTaxExempt()) {
					check.setStateGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if(tax.get(i).isState2TaxExempt()) {
					check.setState2GrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if(tax.get(i).isSscTaxExempt()) {
					check.setSscGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if(tax.get(i).isMedicareTaxeExempt()) {
					check.setMedicareGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if(tax.get(i).isLocalTaxExempt()) {
					check.setLocalGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
			}
				
		}
		
		tax.get(0).setNetAmmount(check.getFedGrossAmmnt() * tax.get(0).getAmmount());
		tax.get(1).setNetAmmount(check.getStateGrossAmmnt() * tax.get(1).getAmmount());
		tax.get(2).setNetAmmount(check.getState2GrossAmmnt() * tax.get(2).getAmmount());
		tax.get(3).setNetAmmount(check.getSscGrossAmmnt() * tax.get(3).getAmmount());
		tax.get(4).setNetAmmount(check.getMedicareGrossAmmnt() * tax.get(4).getAmmount());
		tax.get(5).setNetAmmount(check.getLocalGrossAmmnt() * tax.get(5).getAmmount());
		
	}
	
	static ActionListener printBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				formatExcel();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// TODO Save the data from the check to an entry in the DB.
			sqlPushCheck();
		}

		private void sqlPushCheck() {
			
			
		}

		private void formatExcel() throws Exception {
			Excel_Out.create("Test v1", 12, 52);
			
			Excel_Out.formatColwidth(1, 17);
			Excel_Out.formatColwidth(2, 12);
			Excel_Out.formatColwidth(3, 7);
			Excel_Out.formatColwidth(4, 13);
			Excel_Out.formatColwidth(5, 3);
			Excel_Out.formatColwidth(6, 13);
			Excel_Out.formatColwidth(7, 10);
			Excel_Out.formatColwidth(8, 18);
			Excel_Out.formatColwidth(9, 16);
			Excel_Out.formatColwidth(10, 8);
			Excel_Out.formatColwidth(11, 16);
			Excel_Out.formatColwidth(12, 15);
			
			
			for(int i = 1;i < 53; i++)
				Excel_Out.formatRowHeight(i, (float)17.25);

			Excel_Out.formatRowHeight(4, 36);
			Excel_Out.formatRowHeight(5, (float)34.5);
			for(int i = 6; i < 38; i++) {
					if(i==8 || i==17 || i==18)
						continue;
					Excel_Out.formatRowHeight(i, (float)19.5);
			}
			Excel_Out.formatRowHeight(8, (float)16.5);
			Excel_Out.formatRowHeight(17, (float)16.5);
			Excel_Out.formatRowHeight(18, (float)16.5);
			
			
			Excel_Out.writeToCell(3, 7, amntSpellOut);
			Excel_Out.writeToCell(9, 9, dateT.getText());
			Excel_Out.writeToCell(12, 9, amntT.getText());
			Excel_Out.writeToCell(3, 1, nameT.getText());
			Excel_Out.writeToCell(3,12,addressT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			
			
			Excel_Out.writeToCell(9,21,"Current");
			Excel_Out.writeToCell(11,21,"YTD");
			Excel_Out.writeToCell(2,22,"Check Date");
			Excel_Out.writeToCell(4,22, null/* Need that date that the check was made*/);
			
			Excel_Out.writeToCell(9,22,amntT.getText());
			Excel_Out.writeToCell(11,22,null/*Gross YTD*/);
			
			Excel_Out.writeToCell(2,24,"Pay Period");
			Excel_Out.writeToCell(4,24,null/*beginning date for period*/);
			Excel_Out.writeToCell(5,24,"-");
			Excel_Out.writeToCell(6,24,null/*end date for period*/);
			
			
			Excel_Out.writeToCell(8,22,"Gross Pay");
			int i;
			for(i = 0;i < 14; i++) {
				Excel_Out.writeToCell(8,(23+i),null/*Name of the tax*/);
				if(i == 13)
					Excel_Out.writeToCell(8,(23+i),"Other");
			}
			Excel_Out.writeToCell(8,23,"Federal");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Social Security");
			Excel_Out.writeToCell(8,24,"Net Pay");
			
			
			Excel_Out.writeToCell(9,24,cityStateZipT.getText());
			Excel_Out.writeToCell(11,24,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
			Excel_Out.writeToCell(3,13,cityStateZipT.getText());
		}
	};
	
	static ActionListener cancelBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Dispose this screen and remove the check in progress from the database
			
		}
	};

}
