package Gradle_Payroll.gui.Reporting;

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
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import Gradle_Payroll.data.Check;
import Gradle_Payroll.data.Tax;
import Gradle_Payroll.data.YTD;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.gui.ErrorDialog;
import Gradle_Payroll.gui.Main_Menu;
import Gradle_Payroll.sql.MySQL;

//TODO: Need to add the new elements for pay journal

/*going to need the following:
 * emp. name & no.
 * pay range
 * check date & no.
 * gross pay
 * net pay
 * withholdings
 * company benefits
 * dept.
 */

//should allow user to filter by employee and date ranges

public class Create_Pay_Journal {

	static JComboBox<String> employee;
	static JDialog dialog;
	static JLabel rangeL, employeeL;
	static JTextField startempT, endempT, startdateT, enddateT;
	static String fName, mName, lName, fullName;
	static Dimension minTextSize;
	static Check check;
	static YTD ytd;
	static Tax tax;
	static int empID;
	static int checkID;
	static String[] selname;
	static LocalDate currDate;
	static DatePicker startDate,endDate;

	protected static JDialog createJournalmenu() throws Exception {
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setLocation(80, 120);
		employee = new JComboBox<String>();
		startDate = new DatePicker();
		endDate = new DatePicker();
		
		

		minTextSize = new Dimension();
		minTextSize.setSize(50, 20);

		check = new Check();
		ytd = new YTD();
		tax = new Tax();
		currDate = LocalDate.now();

		sqlPullEmpListRequest();

		JButton createB = new JButton("Create");
		createB.addActionListener(createCheck);
		// loadB.setActionCommand("TermSubmit");
		// createB.addActionListener(submit);
		startdateT = new JTextField();
		startdateT.setPreferredSize(minTextSize);
		enddateT = new JTextField();
		enddateT.setPreferredSize(minTextSize);
		startempT = new JTextField();
		startempT.setPreferredSize(minTextSize);
		endempT = new JTextField();
		endempT.setPreferredSize(minTextSize);


		System.out.println("Creating Dialog Box");

		setLabels();

		dialog.setSize(550, 350);
		dialog.setLayout(new GridBagLayout());

		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 2;
		c11.gridy = 10;

	




		dialog.add(createB, c11);


	
		//setCheckNum();

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
		public void actionPerformed(ActionEvent e) {
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
		// System.out.println(fullname);
		// System.out.println(selname[0]);
		id = MySQL.sqlPullEmpID(selname);

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "select * " + "from employee " + "WHERE id = ?";

		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		// System.out.println(id);

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		rs.next();
		regRateT.setText(String.valueOf(rs.getDouble("regularPay")));
		// System.out.println("Regular rate Obtained: " + regRateT.getText());
		otRateT.setText(String.valueOf(rs.getDouble("otPay")));
		ptoRateT.setText(String.valueOf(rs.getDouble("ptoPay")));
		salpayT.setText(String.valueOf(rs.getDouble("salary")));
		royalpayT.setText(String.valueOf(rs.getDouble("royalty")));

	}

	private static void setCheckNum() throws Exception {
		String[] SQL = Config.PullSQLConfig();

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from generalconfig where id = 1");
		rs.next();
		checkNoT.setText(String.valueOf(rs.getInt("nextCheckNum")));
	}

	private static void setLabels() {
		rangeL  = new JLabel("Range: ");
		employeeL = new JLabel("Employee: ");
		
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
				Main_Menu.year = currDate.getYear();
				Main_Menu.processPayrollEdit();

			} catch (Exception PushCheckInitRequest) {
				PushCheckInitRequest.printStackTrace();
			}

		}
	};

	private static void sqlPullEmpListRequest() throws Exception, SQLException {
		String[] SQL = Config.PullSQLConfig();

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from employee where enabled = true");

		int i = 0;

		while (rs.next()) {
			fName = rs.getString("firstname");
			mName = rs.getString("middlename");
			lName = rs.getString("lastname");
			fullName = fName + " " + mName + " " + lName;
			employee.addItem(fullName);
			i++;
		}

		System.out.println("Data Retreived Successfull for " + i + " Employee entries.");

		if (i < 1) {
			ErrorDialog.createError("No Employees found in Database. Please create them if none exist.");
		}

		rs.close();
		conn.close();
	}

	private static int sqlPushCheckInitRequest(int ID) throws Exception, SQLException {
		String[] SQL = Config.PullSQLConfig();
		int checkID = 0;
		checkID = Integer.parseInt(checkNoT.getText());

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "Insert into checks(checknum,payrollStartDate,payrollEndDate,year,regHours,regRate,ptoHours,ptoRate,otHours,"
				+ "otRate,salRate,advRate,royaltyRate,employee_id) Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		// checkNum
		pstmt.setDouble(1, checkID);
		// Payroll Start Date
		pstmt.setString(2, startDate.getDateStringOrEmptyString());
		// Payroll End Date
		pstmt.setString(3, endDate.getDateStringOrEmptyString());
		// Year
		pstmt.setDouble(4, currDate.getYear());
		// regHours
		pstmt.setDouble(5, Double.parseDouble(regHoursT.getText()));
		// regRate
		pstmt.setDouble(6, Double.parseDouble(regRateT.getText()));
		// ptoHours
		pstmt.setDouble(7, Double.parseDouble(ptoHoursT.getText()));
		// ptoRate
		pstmt.setDouble(8, Double.parseDouble(ptoRateT.getText()));
		// otHours
		pstmt.setDouble(9, Double.parseDouble(otHoursT.getText()));
		// otRate
		pstmt.setDouble(10, Double.parseDouble(otRateT.getText()));
		// salRate
		pstmt.setDouble(11, Double.parseDouble(salpayT.getText()));
		// advRate
		pstmt.setDouble(12, Double.parseDouble(advpayT.getText()));
		// royaltyRate
		pstmt.setDouble(13, Double.parseDouble(royalpayT.getText()));
		// employee_id
		pstmt.setInt(14, ID);

		pstmt.executeUpdate();

		conn.close();

		updateNextCheckNum(checkID);

		return checkID;
	}

	private static void updateNextCheckNum(int checkID) throws Exception {
		String[] SQL = Config.PullSQLConfig();
		checkID = checkID + 1;

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String updateStatement = "update generalconfig set nextCheckNum = ? where id = 1";

		PreparedStatement pstmt = conn.prepareStatement(updateStatement);

		// checkNum
		pstmt.setInt(1, checkID);

		pstmt.executeUpdate();

	}

};
