package Gradle_Payroll.gui.Reporting;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Check;
import Gradle_Payroll.data.Name;
import Gradle_Payroll.data.Tax;
import Gradle_Payroll.data.YTD;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.fileIO.Excel_Out;
import Gradle_Payroll.fileIO.NumberToWords;
import Gradle_Payroll.sql.MySQL;

public class Pay_Journal_Edit {

	static JInternalFrame frame;
	static int CHECKNUM, EMPID, NUMTAXAMNT;
	static JButton printB, cancelB;
	static JLabel chkNumL, chkDateL, payPeriodL, hourRateL, salaryL, regHrsL, ptoHrsL, otHrsL, otherL, grossPayL,
			netPayL, currentL, YTDL;
	static JTextField nameT, addressT, cityStateZipT, dateT, amntT, spelledAmntT, periodDateT, hourRateT, salaryCurrT,
			salaryYTDT;
	static JTextField regularHrsCurrT, regularHrsYTDT, ptoHrsCurrT, ptoHrsYTDT, otHrsCurrT, otHrsYTDT;
	static JTextField regCalcT, ptoCalcT, otCalcT, salCalcT;
	static JTextField grossPayCurrT, grossPayYTDT, netPayCurrT, netPayYTDT, advAmmntT, royalAmmntT;
	static Address addr;
	static Name name;
	static Check check;
	static List<Tax> tax;
	static List<JTextField> TaxCurrT, TaxYTDT;
	static String amntSpellOut, fullName, address, cityStateZip, payPeriodDate;
	static LocalDate date;
	static SimpleDateFormat dateFormat;
	static YTD yTD_Initial, yTD_Calc;
	static int YEAR;
	static JComboBox<String> employee;
	static JDialog dialog;
	static JLabel rangeL, employeeL;
	static JTextField startempT, endempT, startdateT, enddateT;
	static String fName, mName, lName;
	static Dimension minTextSize;
	static YTD ytd;
	static int empID;
	static int checkID;
	static String[] selname;
	static LocalDate currDate;
	static DatePicker startDate,endDate;

	protected static JInternalFrame createDialog() {
		frame = new JInternalFrame();
		frame.setSize(400, 400);
		frame.setLayout(new GridBagLayout());

		printB = new JButton("Print and Save");
		printB.addActionListener(printBListener);

		cancelB = new JButton("Cancel");
		cancelB.addActionListener(cancelBListener);

		
		startdateT = new JTextField();
		startdateT.setPreferredSize(minTextSize);
		enddateT = new JTextField();
		enddateT.setPreferredSize(minTextSize);
		startempT = new JTextField();
		startempT.setPreferredSize(minTextSize);
		endempT = new JTextField();
		endempT.setPreferredSize(minTextSize);
		
		
		addr = new Address();
		name = new Name();
		check = new Check();
		yTD_Initial = new YTD();
		yTD_Calc = new YTD();

		YEAR = year;
		tax = new ArrayList<Tax>();

		CHECKNUM = checkNum;

		sqlPullData(); // Done
		calcGross(); // Done
		calcTaxes(); // Done
		calcNet();
		calcYTD();
		createLabels();
		drawData();
		

		/*TODO: going to need the following:
		 * emp. name & no.
		 * pay range
		 * check date & no.
		 * gross pay
		 * net pay
		 * withholdings
		 * company benefits
		 * dept.
		 */
		
		
		
		
		


		System.out.println("Creating Dialog Box");

		setLabels();

		dialog.setSize(550, 350);
		dialog.setLayout(new GridBagLayout());

		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 2;
		c11.gridy = 10;

	




		


	
		//setCheckNum();


		dialog.repaint();

		
		frame.add(cancelB);
		frame.add(printB);

		frame.setVisible(true);
		frame.setResizable(true);
		return frame;
	}

	// *********************************************Update
	// Data***********************************************************
	private static void updateData() {

		check.setGrossAmmnt(Double.parseDouble(grossPayCurrT.getText()));
		check.setRegHours(Double.parseDouble(regularHrsCurrT.getText()));
		check.setRegRate(Double.parseDouble(hourRateT.getText()));
		double reg = check.getRegRate() * check.getRegHours();
		check.setOtHours(Double.parseDouble(otHrsCurrT.getText()));
		check.setOtRate(Double.parseDouble(hourRateT.getText()));
		double ot = check.getOtRate() * check.getOtHours();
		check.setPtoHours(Double.parseDouble(ptoHrsCurrT.getText()));
		check.setPtoRate(Double.parseDouble(hourRateT.getText()));
		double pto = check.getPtoRate() * check.getPtoHours();
		check.setSalAmmnt(Double.parseDouble(salaryCurrT.getText()));
		check.setAdvAmmnt(Double.parseDouble(advAmmntT.getText()));
		check.setRoyaltyAmmnt(Double.parseDouble(royalAmmntT.getText()));
		check.setNetAmmnt(Double.parseDouble(netPayCurrT.getText()));

		check.setRegAmmnt(reg);
		check.setOtAmmnt(ot);
		check.setPtoAmmnt(pto);

		// Format all string data
		formatData();
		calcGross();
		calcTaxes();
		calcNet();
		calcYTD();

		updateFields();

	}
	
	private static void setLabels() {
		rangeL  = new JLabel("Range: ");
		employeeL = new JLabel("Employee: ");
		
	}

	// *********************************************Update
	// Fields***********************************************************
	private static void updateFields() {
		nameT.setText(fullName);

		addressT.setText(address);

		cityStateZipT.setText(cityStateZip);
		dateT.setText(date.toString());
		amntT.setText(String.valueOf(check.getNetAmmnt()));
		spelledAmntT.setText(amntSpellOut);
		periodDateT.setText(payPeriodDate);
		hourRateT.setText(String.valueOf(check.getRegRate()));
		salaryCurrT.setText(String.valueOf(check.getSalAmmnt()));
		salaryYTDT.setText(String.valueOf(yTD_Calc.getSalAmmntYTD()));
		regularHrsCurrT.setText(String.valueOf(check.getRegHours()));
		regularHrsYTDT.setText(String.valueOf(yTD_Calc.getRegHoursYTD()));
		ptoHrsCurrT.setText(String.valueOf(check.getPtoHours()));
		ptoHrsYTDT.setText(String.valueOf(yTD_Calc.getPtoHoursYTD()));
		otHrsCurrT.setText(String.valueOf(check.getOtHours()));
		otHrsYTDT.setText(String.valueOf(yTD_Calc.getOtHoursYTD()));
		grossPayCurrT.setText(String.valueOf(check.getGrossAmmnt()));
		grossPayYTDT.setText(String.valueOf(yTD_Calc.getGrossAmmntYTD()));
		netPayCurrT.setText(String.valueOf(check.getNetAmmnt()));
		netPayYTDT.setText(String.valueOf(yTD_Calc.getNetAmmntYTD()));
		advAmmntT.setText(String.valueOf(check.getAdvAmmnt()));
		royalAmmntT.setText(String.valueOf(check.getRoyaltyAmmnt()));

		regCalcT.setText(String.valueOf(check.getRegAmmnt()));
		ptoCalcT.setText(String.valueOf(check.getPtoAmmnt()));
		otCalcT.setText(String.valueOf(check.getOtAmmnt()));
		salCalcT.setText(String.valueOf(check.getSalAmmnt()));
	}

	// *********************************************Calculate
	// YTD***********************************************************
	private static void calcYTD() {
		// System.out.println("Gross YTD Init: " + yTD_Initial.getGrossAmmntYTD());
		yTD_Calc.setGrossAmmntYTD(yTD_Initial.getGrossAmmntYTD() + check.getGrossAmmnt());
		// System.out.println("Gross YTD Calc: " + yTD_Calc.getGrossAmmntYTD());
		yTD_Calc.setRegHoursYTD(yTD_Initial.getRegHoursYTD() + check.getRegHours());
		yTD_Calc.setRegAmmntYTD(yTD_Initial.getRegAmmntYTD() + check.getRegAmmnt());
		yTD_Calc.setPtoHoursYTD(yTD_Initial.getPtoHoursYTD() + check.getPtoHours());
		yTD_Calc.setPtoAmmntYTD(yTD_Initial.getPtoAmmntYTD() + check.getPtoAmmnt());
		yTD_Calc.setOtHoursYTD(yTD_Initial.getOtHoursYTD() + check.getOtHours());
		yTD_Calc.setOtAmmntYTD(yTD_Initial.getOtAmmntYTD() + check.getOtAmmnt());
		yTD_Calc.setSalAmmntYTD(yTD_Initial.getSalAmmntYTD() + check.getSalAmmnt());
		yTD_Calc.setAdvAmmntYTD(yTD_Initial.getAdvAmmntYTD() + check.getAdvAmmnt());
		yTD_Calc.setRoyaltyAmmntYTD(yTD_Initial.getRoyaltyAmmntYTD() + check.getRoyaltyAmmnt());
		yTD_Calc.setNetAmmntYTD(yTD_Initial.getNetAmmntYTD() + check.getNetAmmnt());

	}

	// *********************************************Draw
	// Data***********************************************************
	private static void drawData() {
		formatData();
		instantiateFields();
	}

	// *********************************************Format
	// Data***********************************************************
	private static void formatData() {
		float whole, decimal;
		int length = 0;
		String NetAmmnt = " ";
		String[] ammount = null;
		fullName = check.getName().getFirst() + " " + check.getName().getLast();
		address = check.getAddress().getStreet();
		cityStateZip = check.getAddress().getCity() + " " + check.getAddress().getState() + " "
				+ check.getAddress().getZip();
		date = LocalDate.now();
		NetAmmnt = Double.toString(check.getNetAmmnt());
		ammount = NetAmmnt.split("\\.");
		ammount[1] = ammount[1].substring(0, 1);
		length = ammount[1].length();
		whole = Integer.valueOf(ammount[0]);
		decimal = Integer.valueOf(ammount[1]);

		// If we have a value less than 10 and the length is 1, we know it was a tens
		// value
		if (length == 1 && decimal < 10) {
			// Convert it to a tens multiple
			decimal = decimal * 10;
		}
		// Else we assume that is was actually less than 10

		amntSpellOut = NumberToWords.convert((long) whole) + " and " + NumberToWords.convert((long) decimal);
		payPeriodDate = check.getEndDate();

	}

	// *********************************************Text Field
	// Inst***********************************************************
	private static void instantiateFields() {
		nameT = new JTextField(fullName);
		nameT.addActionListener(textListener);
		addressT = new JTextField(address);
		addressT.addActionListener(textListener);
		cityStateZipT = new JTextField(cityStateZip);
		cityStateZipT.addActionListener(textListener);
		dateT = new JTextField(date.toString());
		dateT.addActionListener(textListener);
		amntT = new JTextField(String.valueOf(check.getNetAmmnt()));
		amntT.addActionListener(textListener);
		spelledAmntT = new JTextField(amntSpellOut);
		spelledAmntT.addActionListener(textListener);
		periodDateT = new JTextField(payPeriodDate);
		periodDateT.addActionListener(textListener);
		hourRateT = new JTextField(String.valueOf(check.getRegRate()));
		hourRateT.addActionListener(textListener);
		salaryCurrT = new JTextField(String.valueOf(check.getSalAmmnt()));
		salaryCurrT.addActionListener(textListener);
		salaryYTDT = new JTextField(String.valueOf(yTD_Calc.getSalAmmntYTD()));
		salaryYTDT.addActionListener(textListener);
		regularHrsCurrT = new JTextField(String.valueOf(check.getRegHours()));
		regularHrsCurrT.addActionListener(textListener);
		regularHrsYTDT = new JTextField(String.valueOf(yTD_Calc.getRegHoursYTD()));
		regularHrsYTDT.addActionListener(textListener);
		ptoHrsCurrT = new JTextField(String.valueOf(check.getPtoHours()));
		ptoHrsCurrT.addActionListener(textListener);
		ptoHrsYTDT = new JTextField(String.valueOf(yTD_Calc.getPtoHoursYTD()));
		ptoHrsYTDT.addActionListener(textListener);
		otHrsCurrT = new JTextField(String.valueOf(check.getOtHours()));
		otHrsCurrT.addActionListener(textListener);
		otHrsYTDT = new JTextField(String.valueOf(yTD_Calc.getOtHoursYTD()));
		otHrsYTDT.addActionListener(textListener);
		grossPayCurrT = new JTextField(String.valueOf(check.getGrossAmmnt()));
		grossPayCurrT.addActionListener(textListener);
		grossPayYTDT = new JTextField(String.valueOf(yTD_Calc.getGrossAmmntYTD()));
		grossPayYTDT.addActionListener(textListener);
		netPayCurrT = new JTextField(String.valueOf(check.getNetAmmnt()));
		netPayCurrT.addActionListener(textListener);
		netPayYTDT = new JTextField(String.valueOf(yTD_Calc.getNetAmmntYTD()));
		netPayYTDT.addActionListener(textListener);
		advAmmntT = new JTextField(String.valueOf(check.getAdvAmmnt()));
		advAmmntT.addActionListener(textListener);
		royalAmmntT = new JTextField(String.valueOf(check.getRoyaltyAmmnt()));
		royalAmmntT.addActionListener(textListener);

		regCalcT = new JTextField(String.valueOf(check.getRegAmmnt()));
		regCalcT.addActionListener(textListener);
		ptoCalcT = new JTextField(String.valueOf(check.getPtoAmmnt()));
		ptoCalcT.addActionListener(textListener);
		otCalcT = new JTextField(String.valueOf(check.getOtAmmnt()));
		otCalcT.addActionListener(textListener);
		salCalcT = new JTextField(String.valueOf(check.getSalAmmnt()));
		salCalcT.addActionListener(textListener);

	}

	// *********************************************Create
	// Labels***********************************************************
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
		currentL = new JLabel("<HTML><U> Current </U></HTML>");
		YTDL = new JLabel("<HTML><U> YTD </U></HTML>");
		netPayL = new JLabel("Net Pay:");
	}

	// *********************************************SQL Pull
	// Data***********************************************************
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
			e.printStackTrace();
		}

	}

	// *********************************************SQL Pull YTD
	// Data***********************************************************
	private static void sqlPullYTDData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

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

	// *********************************************SQL Pull Tax
	// Data***********************************************************
	private static void sqlPullTaxData() throws Exception {
		Tax tempTax = new Tax();
		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for Tax Data");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "select * " + "from tax " + "WHERE employee_id = ?";

		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		pstmt.setInt(1, EMPID);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			tempTax = new Tax();
			tempTax.setName(rs.getString("taxname"));
			tempTax.setType(rs.getInt("taxtype"));
			tempTax.setAmmount(rs.getDouble("ammount"));
			tempTax.setFedTaxExempt(rs.getBoolean("fedTaxExempt"));
			tempTax.setStateTaxExempt(rs.getBoolean("stateTaxExempt"));
			tempTax.setState2TaxExempt(rs.getBoolean("state2TaxExempt"));
			tempTax.setSscTaxExempt(rs.getBoolean("SSCTaxExempt"));
			tempTax.setMedicareTaxeExempt(rs.getBoolean("medicareTaxExempt"));
			tempTax.setLocalTaxExempt(rs.getBoolean("localTaxExempt"));
			tempTax.setPrimaryTax(rs.getBoolean("primaryTax"));
			// System.out.println("Name: " + tempTax.getName() + " | Ammount: " +
			// tempTax.getAmmount());
			tax.add(tempTax);
		}
		sqlPullTaxYTD();
	}

	// *********************************************SQL Pull Tax YTD
	// Data***********************************************************
	private static void sqlPullTaxYTD() throws Exception {
		int i = 0;
		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for Tax YTD Data");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "select * " + "from tax_ytd " + "WHERE employee_id = ?";

		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		pstmt.setInt(1, EMPID);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			tax.get(i).setInitYTD(rs.getDouble("ammount"));
		}

	}

	// *********************************************SQL Pull Employee
	// Data***********************************************************
	private static void sqlPullEmpData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for Employee Data");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "select * " + "from employee "
				+ "inner join address on employee.id = address.employee_id " + "WHERE employee.id = ?";

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

	// *********************************************SQL Pull Check
	// Data***********************************************************
	private static void sqlPullCheckData() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for Check Data");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "select * " + "from checks " + "WHERE checknum = ?";

		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		pstmt.setInt(1, CHECKNUM);

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

	// *********************************************Calculate
	// Gross***********************************************************
	private static void calcGross() {
		double reg, ot, pto, salary, other;
		reg = check.getRegRate() * check.getRegHours();
		ot = check.getOtRate() * check.getOtHours();
		pto = check.getPtoRate() * check.getPtoHours();
		salary = check.getSalAmmnt();
		other = check.getAdvAmmnt() + check.getRoyaltyRate();
		check.setGrossAmmnt(reg + ot + pto + salary + other);
		// System.out.println("Check Gross: " + check.getGrossAmmnt());
		check.setFedGrossAmmnt(check.getGrossAmmnt());
		check.setStateGrossAmmnt(check.getGrossAmmnt());
		check.setState2GrossAmmnt(check.getGrossAmmnt());
		check.setSscGrossAmmnt(check.getGrossAmmnt());
		check.setMedicareGrossAmmnt(check.getGrossAmmnt());
		check.setLocalGrossAmmnt(check.getGrossAmmnt());
		check.setRegAmmnt(reg);
		check.setOtAmmnt(ot);
		check.setPtoAmmnt(pto);
	}

	// *********************************************Calculate
	// Net***********************************************************
	private static void calcNet() {
		double netAmmnt = check.getGrossAmmnt();

		// Get all of the taxes
		for (int i = 0; i < NUMTAXAMNT; i++) {
			netAmmnt = netAmmnt - tax.get(i).getNetAmmount();
		}

		netAmmnt = netAmmnt - check.getAddFedTax();
		netAmmnt = netAmmnt - check.getAddStateTax();

		check.setNetAmmnt(netAmmnt);
	}

	// *********************************************Calclate
	// Taxes***********************************************************
	private static void calcTaxes() {

		for (int i = 0; i < NUMTAXAMNT; i++) {
			if (!tax.get(i).isPrimaryTax() && tax.get(i).getType() == 1) {
				tax.get(i).setNetAmmount(tax.get(i).getAmmount() * 0.01 * check.getGrossAmmnt());
			} else {
				tax.get(i).setNetAmmount(tax.get(i).getAmmount());
			}
		}

		for (int i = 0; i < NUMTAXAMNT; i++) {
			if (!tax.get(i).isPrimaryTax()) {
				if (tax.get(i).isFedTaxExempt()) {
					// System.out.println("Federal Gross: " + check.getFedGrossAmmnt() + "
					// subtracting: " + tax.get(i).getNetAmmount());
					check.setFedGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
					// System.out.println("Resulting in: " + check.getFedGrossAmmnt());
				}
				if (tax.get(i).isStateTaxExempt()) {
					check.setStateGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if (tax.get(i).isState2TaxExempt()) {
					check.setState2GrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if (tax.get(i).isSscTaxExempt()) {
					check.setSscGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if (tax.get(i).isMedicareTaxeExempt()) {
					check.setMedicareGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
				if (tax.get(i).isLocalTaxExempt()) {
					check.setLocalGrossAmmnt(check.getGrossAmmnt() - tax.get(i).getNetAmmount());
				}
			}

		}

		// System.out.println("Federal Gross: " + check.getFedGrossAmmnt() + " mult: " +
		// tax.get(0).getAmmount());
		tax.get(0).setNetAmmount(check.getFedGrossAmmnt() * 0.01 * tax.get(0).getAmmount());
		// System.out.println("Resulting in: " + tax.get(0).getNetAmmount());
		tax.get(1).setNetAmmount(check.getStateGrossAmmnt() * 0.01 * tax.get(1).getAmmount());
		tax.get(2).setNetAmmount(check.getState2GrossAmmnt() * 0.01 * tax.get(2).getAmmount());
		tax.get(3).setNetAmmount(check.getSscGrossAmmnt() * 0.01 * tax.get(3).getAmmount());
		tax.get(4).setNetAmmount(check.getMedicareGrossAmmnt() * 0.01 * tax.get(4).getAmmount());
		tax.get(5).setNetAmmount(check.getLocalGrossAmmnt() * 0.01 * tax.get(5).getAmmount());

		for (int i = 0; i < NUMTAXAMNT; i++) {
			System.out.println("Tax Init YTD: " + tax.get(i).getInitYTD() + " Add: " + tax.get(i).getNetAmmount());
			tax.get(i).setFinalYTD(tax.get(i).getInitYTD() + tax.get(i).getNetAmmount());
			System.out.println("Resulting in: " + tax.get(i).getFinalYTD());
		}

	}

	// *********************************************Print Button
	// Listener***********************************************************
	static ActionListener printBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				formatExcel();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				sqlPushCheck();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				sqlPushYTD();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				sqlPushCheckTax();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				sqlPushTaxYTD();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			frame.dispose();
		}

		// *********************************************SQL Push Tax YTD
		// Data***********************************************************
		private void sqlPushTaxYTD() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();

			System.out.println("Pushing Tax YTD to DB");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "update tax_ytd set  " + "ammount = ? "
					+ "WHERE employee_id = ? and name = ? and year = ?";

			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			pstmt.setInt(2, EMPID);
			pstmt.setDouble(4, YEAR);

			for (int i = 0; i < NUMTAXAMNT; i++) {
				pstmt.setDouble(1, tax.get(i).getFinalYTD());
				pstmt.setString(3, tax.get(i).getName());
				System.out.println(pstmt);
				pstmt.executeUpdate();
			}

		}

		// *********************************************SQL Push Check
		// Data***********************************************************
		private void sqlPushCheckTax() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();

			System.out.println("Pushig Check Tax to DB");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "Insert into check_tax(check_id,taxname,taxtype,ammount,netammount,fedTaxExempt,"
					+ "stateTaxExempt,state2TaxExempt,SSCTaxExempt,medicareTaxExempt,localTaxExempt) Values(?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			for (int i = 0; i < NUMTAXAMNT; i++) {
				pstmt.setInt(1, CHECKNUM);
				pstmt.setString(2, tax.get(i).getName());
				pstmt.setInt(3, tax.get(i).getType());
				pstmt.setDouble(4, tax.get(i).getAmmount());
				pstmt.setDouble(5, tax.get(i).getNetAmmount());
				pstmt.setBoolean(6, tax.get(i).isFedTaxExempt());
				pstmt.setBoolean(7, tax.get(i).isStateTaxExempt());
				pstmt.setBoolean(8, tax.get(i).isState2TaxExempt());
				pstmt.setBoolean(9, tax.get(i).isSscTaxExempt());
				pstmt.setBoolean(10, tax.get(i).isMedicareTaxeExempt());
				pstmt.setBoolean(11, tax.get(i).isLocalTaxExempt());
				pstmt.executeUpdate();
			}
		}

		// *********************************************SQL Push
		// YTD*************************************************************
		private void sqlPushYTD() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();

			System.out.println("Pushing YTD to DB");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "update ytd set  " + "ammount = ? "
					+ "WHERE employee_id = ? and name = ? and year = ?";

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

			pstmt.setDouble(1, yTD_Calc.getRegHoursYTD());
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

		// *************************************************SQL Push
		// Check*******************************************************
		private void sqlPushCheck() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();

			System.out.println("Pushing Check to DB");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "update checks set  " + "payrollStartDate = ?, payrollEndDate = ?, date = ?, "
					+ "grossAmmnt = ?, netAmmt = ?, regHours = ?, regRate = ?, ptoHours = ?,ptoRate = ?, otHours = ?, otRate = ?, "
					+ "salRate = ?, advRate = ?, royaltyRate = ? " + "WHERE checknum = ?";

			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			pstmt.setString(1, check.getStartDate());
			pstmt.setString(2, check.getEndDate());
			pstmt.setString(3, check.getDate());

			pstmt.setDouble(4, check.getGrossAmmnt());
			pstmt.setDouble(5, check.getNetAmmnt());

			pstmt.setDouble(6, check.getRegHours());
			pstmt.setDouble(7, check.getRegRate());

			pstmt.setDouble(8, check.getPtoHours());
			pstmt.setDouble(9, check.getPtoRate());

			pstmt.setDouble(10, check.getOtHours());
			pstmt.setDouble(11, check.getOtRate());

			pstmt.setDouble(12, check.getSalAmmnt());

			pstmt.setDouble(13, check.getAdvAmmnt());

			pstmt.setDouble(14, check.getRoyaltyAmmnt());

			pstmt.setInt(15, CHECKNUM);

			// System.out.println(pstmt);

			@SuppressWarnings("unused")
			int rs = pstmt.executeUpdate();

		}

		// *********************************************Format
		// Excel***********************************************************
		private void formatExcel() throws Exception {
			// static YTD yTD_Initial,yTD_Calc;

			Excel_Out.create(nameT.getText(), 14, 52);

			Excel_Out.formatColwidth(0, 16 * 262);
			Excel_Out.formatColwidth(1, 14 * 265);
			Excel_Out.formatColwidth(2, 9 * 265);
			Excel_Out.formatColwidth(3, 14 * 265);
			Excel_Out.formatColwidth(4, 1 * 265);
			Excel_Out.formatColwidth(5, 16 * 265);
			Excel_Out.formatColwidth(6, 11 * 265);
			Excel_Out.formatColwidth(7, 1 * 265);
			Excel_Out.formatColwidth(8, 16 * 265);
			Excel_Out.formatColwidth(9, 11 * 265);
			Excel_Out.formatColwidth(10, 8 * 265);
			Excel_Out.formatColwidth(11, 7 * 265);
			Excel_Out.formatColwidth(12, 10 * 265);
			Excel_Out.formatColwidth(13, 9 * 265);

			for (int i = 0; i < 52; i++)
				Excel_Out.formatRowHeight(i, (float) 21);

			Excel_Out.formatRowHeight(0, 30);
			Excel_Out.formatRowHeight(3, (float) 23.25);



			for (int x = 0; x < 14; x++) {
				for (int y = 0; y < 52; y++) {
					Excel_Out.clearCellFormat(x, y);
				}
			}

			/*-----------------------Top Section--------------------*/
			
			Excel_Out.useFontSize24();
			Excel_Out.boldCell(8, 0);
			Excel_Out.writeToCell(8, 0, "Anatomical Concepts Inc.");
			
			Excel_Out.useFontSize18();
			Excel_Out.writeToCell(8, 3, "***PAYROLL JOURNAL***");
			
			Excel_Out.useFontSize16();
			Excel_Out.insertFormulaIntoCell(0, 0, "=NOW()");
			
			Excel_Out.writeToCell(12, 1, "Department:");
			Excel_Out.writeToCell(13, 1, null/*department*/);
			
			Excel_Out.writeToCell(0, 3, "Period:");
			Excel_Out.writeToCell(1, 3, "Department:");
			Excel_Out.writeToCell(2, 3, "-");
			Excel_Out.writeToCell(3, 3, null);
			
			Excel_Out.writeToCell(0, 4, "Employee:");
			Excel_Out.writeToCell(1, 4, null/*start employee number*/);
			Excel_Out.writeToCell(2, 4, "-");
			Excel_Out.writeToCell(3, 4, null/*end employee number*/);
			
			Excel_Out.writeToCell(5, 8, "Withholdings");
			
			for(int x = 0; x < 14; x++)
				Excel_Out.setCellBorder(x, 8);
			
			
			//on the excel sheet, payroll journal can only show 16 withholdings
			//for row looping, start at row 9 and add 16 when starting a new section
			
//			Excel_Out.writeToCell(11, 8, Double.parseDouble(amntT.getText()));
//			Excel_Out.changeNumberFormat(11, 8);
//			Excel_Out.writeToCell(2, 10, nameT.getText());
//			Excel_Out.writeToCell(2, 11, addressT.getText());
//			Excel_Out.writeToCell(2, 12, cityStateZipT.getText());
//
//			Excel_Out.writeToCell(8, 20, "Current");
//			Excel_Out.writeToCell(10, 20, "YTD");
//			Excel_Out.writeToCell(1, 21, "Check Date");
//			Excel_Out.writeToCell(3, 21, dateT.getText());
//
//			Excel_Out.writeToCell(8, 21, Double.parseDouble(amntT.getText()));
//			Excel_Out.writeToCell(10, 21, yTD_Calc.getGrossAmmntYTD());
//
//			Excel_Out.writeToCell(1, 23, "Pay Period");
//			Excel_Out.writeToCell(3, 23, periodDateT.getText());
//
//			Excel_Out.writeToCell(7, 21, "Gross Pay");
//			Excel_Out.writeToCell(8, 21, Double.parseDouble(grossPayCurrT.getText()));
//			Excel_Out.changeNumberFormat(8, 21);
//			Excel_Out.writeToCell(10, 21, Double.parseDouble(grossPayYTDT.getText()));
//			Excel_Out.changeNumberFormat(10, 21);
//
//			double otherAmntCurr = 0, otherAmntYTD = 0;
//			if (NUMTAXAMNT > 14) {
//				for (int j = 13; j < NUMTAXAMNT; j++) {
//					otherAmntCurr += tax.get(j).getNetAmmount();
//					otherAmntYTD += tax.get(j).getFinalYTD();
//				}
//			}
//
//			int i;
//			for (i = 0; i < NUMTAXAMNT; i++) {
//				if (i == 14) {
//					Excel_Out.writeToCell(7, (22 + i - 1), "Other");
//					Excel_Out.writeToCell(8, (22 + i - 1),
//							otherAmntCurr/* the sum of all unseen tax tables for current check */);
//
//					Excel_Out.writeToCell(10, (22 + i - 1), otherAmntYTD/* the sum of all unseen tax tables for YTD */);
//
//					break;
//				}
//				Excel_Out.writeToCell(7, (22 + i), tax.get(i).getName()/* Name of the tax */);
//				Excel_Out.writeToCell(8, (22 + i),
//						tax.get(i).getNetAmmount()/* value of the tax on the current check */);
//				Excel_Out.changeNumberFormat(8, (22 + i));
//				Excel_Out.writeToCell(10, (22 + i), tax.get(i).getFinalYTD()/* value of the tax for YTD */);
//				Excel_Out.changeNumberFormat(10, (22 + i));
//			}
//
//			Excel_Out.writeToCell(7, 22 + i, "Net Pay");
//			Excel_Out.writeToCell(8, 22 + i, Double.parseDouble(netPayCurrT.getText()));
//			Excel_Out.changeNumberFormat(8, 22 + i);
//			Excel_Out.writeToCell(10, 22 + i, Double.parseDouble(netPayYTDT.getText()));
//			Excel_Out.changeNumberFormat(10, 22 + i);
//
//			Excel_Out.setCellBorder(8, 20);
//			Excel_Out.setCellBorder(10, 20);
//			Excel_Out.setCellDataBorder(8, 22 + i - 1, 1);
//			Excel_Out.setCellDataBorder(10, 22 + i - 1, 1);
//
//			Excel_Out.writeToCell(3, 26, "Current");
//			Excel_Out.writeToCell(5, 26, "YTD");
//
//			Excel_Out.writeToCell(1, 27, "Regular");
//			Excel_Out.writeToCell(3, 27, check.getRegHours());
//			Excel_Out.writeToCell(5, 27, yTD_Calc.getRegHoursYTD() /* YTD hours worked */);
//
//			int j = 29;
//			Excel_Out.writeToCell(1, 28, "PTO");
//			Excel_Out.writeToCell(3, 28, check.getPtoHours());
//			Excel_Out.writeToCell(5, 28, yTD_Calc.getPtoHoursYTD()/* Total YTD PTO hours used */);
//
//			if (check.getOtHours() != 0) {
//				Excel_Out.writeToCell(1, j, "Overtime");
//				Excel_Out.writeToCell(3, j, check.getOtHours());
//				Excel_Out.writeToCell(5, j, yTD_Calc.getOtHoursYTD()/* Total YTD PTO hours used */);
//				j++;
//			}
//
//			if (check.getSalAmmnt() != 0) {
//				Excel_Out.writeToCell(1, j, "Salary");
//				Excel_Out.writeToCell(3, j, check.getSalAmmnt());
//				Excel_Out.writeToCell(5, j, yTD_Calc.getSalAmmntYTD());
//				j++;
//			}
//			if (check.getRoyaltyAmmnt() != 0) {
//				Excel_Out.writeToCell(1, j, "Royalties");
//				Excel_Out.writeToCell(3, j, check.getRoyaltyAmmnt());
//				Excel_Out.writeToCell(5, j, yTD_Calc.getRoyaltyAmmntYTD());
//				j++;
//			}
//			if (check.getAdvAmmnt() != 0) {
//				Excel_Out.writeToCell(1, j, "Advance");
//				Excel_Out.writeToCell(3, j, check.getAdvAmmnt());
//				Excel_Out.writeToCell(5, j, yTD_Calc.getAdvAmmntYTD());
//				j++;
//			}
//			Excel_Out.writeToCell(11, 51, "-");
//
//			Excel_Out.underlineCell(3, 26);
//			Excel_Out.underlineCell(5, 26);
//			Excel_Out.underlineCell(3, j);
//			Excel_Out.underlineCell(5, j);

			Excel_Out.createPrintArea(14, 52);

			Excel_Out.writeOut();
		}
	};

	// *********************************************Cancel Button
	// Listener***********************************************************
	static ActionListener cancelBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			try {
				deleteCheck();
				updateNextCheckNum(CHECKNUM);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		// *********************************************Delete
		// Check***********************************************************
		private void deleteCheck() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();

			System.out.println("Deleting Check");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "delete " + "from checks " + "WHERE checknum = ?";

			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			pstmt.setInt(1, CHECKNUM);

			int rs = pstmt.executeUpdate();

			System.out.println("Deleted " + rs + " entry(s) from checks.");

		}

		// *********************************************Update
		// Checknum***********************************************************
		private void updateNextCheckNum(int checkID) throws Exception {
			String[] SQL = Config.PullSQLConfig();

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "update generalconfig set nextCheckNum = ? where id = 1";

			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			// checkNum
			pstmt.setInt(1, checkID);

			pstmt.executeUpdate();

		}
	};

	// *********************************************Text Field Enter
	// Listener******************************************************
	static ActionListener textListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Enter Key Pressed.");
			updateData();
		}
	};
}
