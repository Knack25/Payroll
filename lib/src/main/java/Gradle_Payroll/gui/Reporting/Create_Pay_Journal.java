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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import Gradle_Payroll.data.Check;
import Gradle_Payroll.data.Tax;
import Gradle_Payroll.data.YTD;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.gui.ErrorDialog;
import Gradle_Payroll.gui.Main_Menu;
import Gradle_Payroll.sql.MySQL;

//TODO: Need to add the new elements for pay journal
/*new pay types:
 * commission
 * tips
 * other
 * benefits:
 * fica
 * futa
 * suta
 * any deductions
 * any withholdings
 * check No.
 * employee No.
 * pay period.
 * dept.
 * */

//should allow user to filter my employee and pay periods

public class Create_Pay_Journal {

	static JComboBox<String> employee;
	static JDialog dialog;
	static JLabel regularL, ptoL, overtimeL, salaryL, advanceL, royaltiesL, checkNoL, hoursL, rateL, dateL, fixedPayL;

	static String fName, mName, lName, fullName;
	static JLabel dashL, slash1L, slash2L, slash3L, slash4L;
	static Dimension minTextSize;
	static Check check;
	static YTD ytd;
	static Tax tax;
	static int empID;
	static int checkID;
	static String[] selname;
	static LocalDate currDate;
	static JPanel payPeriodP, labelsP, checkP;
	static DatePicker startDate, endDate;
	static DatePickerSettings dateSettings;

	protected static JDialog createJournalmenu() throws Exception {
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);

		employee = new JComboBox<String>();

		minTextSize = new Dimension();
		minTextSize.setSize(50, 20);

		check = new Check();
		ytd = new YTD();
		tax = new Tax();
		currDate = LocalDate.now();

		JButton createB = new JButton("Create");
		createB.addActionListener(createReport);
		// loadB.setActionCommand("TermSubmit");
		// createB.addActionListener(submit);

		JPanel labelsP = new JPanel();
		JPanel payPeriodP = new JPanel();
		JPanel regPayP = new JPanel();
		JPanel checkP = new JPanel();

		System.out.println("Creating Dialog Box");

		dialog.setSize(550, 350);
		dialog.setLayout(new GridBagLayout());

		GridBagConstraints b6c6 = new GridBagConstraints();
		b6c6.gridx = 1;
		b6c6.gridy = 5;
		b6c6.gridwidth = 1;

		GridBagConstraints b7c7 = new GridBagConstraints();
		b7c7.gridx = 1;
		b7c7.gridy = 6;
		b7c7.gridwidth = 1;

		GridBagConstraints b8c8 = new GridBagConstraints();
		b8c8.gridx = 1;
		b8c8.gridy = 7;
		b8c8.gridwidth = 1;

		GridBagConstraints b9c9 = new GridBagConstraints();
		b9c9.gridx = 1;
		b9c9.gridy = 8;
		b9c9.gridwidth = 1;

		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 2;
		c11.gridy = 10;

		GridBagConstraints b10c10 = new GridBagConstraints();
		b10c10.gridx = 1;
		b10c10.gridy = 9;
		b10c10.gridwidth = 1;

		JPanel ptoPayP = new JPanel();
		JPanel otPayP = new JPanel();

		labelsP.add(hoursL);
		labelsP.add(rateL);

		dialog.add(createB, c11);

		dialog.add(fixedPayL, b6c6);

		dialog.add(payPeriodP, b10c10);

		GridBagConstraints boom = new GridBagConstraints();
		for (int x = 0; x < 3; x++) {
			boom.gridx = x;
			for (int y = 0; y < 10; y++) {
				if (x == 0) {
					switch (y) {
					case 0:
						boom.gridy = y;
						dialog.add(employee, boom);
						break;
					case 2:
						boom.gridy = y;
						dialog.add(regularL, boom);
						break;
					case 3:
						boom.gridy = y;
						dialog.add(ptoL, boom);
						break;
					case 4:
						boom.gridy = y;
						dialog.add(overtimeL, boom);
						break;
					case 6:
						boom.gridy = y;
						dialog.add(salaryL, boom);
						break;
					case 7:
						boom.gridy = y;
						dialog.add(advanceL, boom);
						break;
					case 8:
						boom.gridy = y;
						dialog.add(royaltiesL, boom);
						break;
					case 9:
						boom.gridy = y;
						dialog.add(dateL, boom);
						break;
					}
				}
				if (x == 1) {
					switch (y) {

					case 0:
						boom.gridy = y;
						dialog.add(checkP, boom);
						break;
					case 1:
						boom.gridy = y;
						dialog.add(labelsP, boom);
						break;
					case 2:
						boom.gridy = y;
						dialog.add(regPayP, boom);
						break;
					case 3:
						boom.gridy = y;
						dialog.add(ptoPayP, boom);
						break;
					case 4:
						boom.gridy = y;
						dialog.add(otPayP, boom);
						break;

					}
				}
			}
		}

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
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	static ActionListener createReport = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	};

}
