package Gradle_Payroll.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Employee;
import Gradle_Payroll.data.Name;

public class Program_Settings {

	static JComboBox<String> employee;
	static JComboBox<String> department;
	static JInternalFrame frame;
	static JLabel empNumL, statusL, nameL, addressL, cityL, stateL, zipL, emailL, ssnL, jobtitleL, dobL, dohL, dotL,
			localtaxcodeL, addstatetaxL, addfedtaxL, salaryL, reghourL, regpayL, othourL, otpayL, ptohourL, ptopayL,
			departmentL, teleL, sexL, vacationAvailL, vacationUsedL;
	static JTextField empNumT, statusT, nameT, addressT, cityT, stateT, zipT, emailT, ssnT, jobtitleT, dobT, dohT, dotT,
			localtaxcodeT, addstatetaxT, addfedtaxT, salaryT, reghourT, regpayT, othourT, otpayT, ptohourT, ptopayT,
			departmentT, teleT, sexT, vacationAvailT, vacationUsedT;
	static Employee emp;
	static Name empName;
	static Address empAddress;
	static String fName, mName, lName, fullName, dept;
	static JLabel settingsL, payFreqL, payPeriodL, slashL, dashL;
	static JComboBox<String> payFreqD, startDateD, endDateD;
	static JTextField startDateT, endDateT;
	static JButton viewRangesB, deptB;
	static Dimension minTextSize;

	// Create a new internal frame.
	protected static JInternalFrame createFrame() throws Exception {
		frame = new JInternalFrame();
		emp = new Employee();
		minTextSize = new Dimension();
		minTextSize.setSize(50, 20);
		startDateT = new JTextField();
		startDateT.setPreferredSize(minTextSize);
		startDateT.setEditable(false);
		JTextField endDateT = new JTextField();
		endDateT.setPreferredSize(minTextSize);
		endDateT.setEditable(false);
		viewRangesB = new JButton("View Yearly Pay Ranges");

		deptB = new JButton("View/Edit Departments");
		deptB.addActionListener(DeptTableBListener);

		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException exceptSelected) {
		}

		setLabels();

		System.out.println("Querrying DB...");

		System.out.println("Creating Edit Frame");

		frame.setSize(1300, 650);

		frame.setLayout(new GridBagLayout());

		frame.add(dashL);

		frame.setSize(600, 300);

		frame.setLayout(new GridBagLayout());

		GridBagConstraints b1 = new GridBagConstraints();
		b1.gridx = 1;
		b1.gridy = 0;

		GridBagConstraints f1 = new GridBagConstraints();
		f1.gridx = 5;
		f1.gridy = 0;

		GridBagConstraints a2 = new GridBagConstraints();
		a2.gridx = 0;
		a2.gridy = 1;

		GridBagConstraints a3 = new GridBagConstraints();
		a3.gridx = 0;
		a3.gridy = 2;

		GridBagConstraints b4 = new GridBagConstraints();
		b4.gridx = 1;
		b4.gridy = 3;

		GridBagConstraints b2 = new GridBagConstraints();
		b2.gridx = 1;
		b2.gridy = 1;

		GridBagConstraints b3 = new GridBagConstraints();
		b3.gridx = 1;
		b3.gridy = 2;

		GridBagConstraints c2e2 = new GridBagConstraints();
		c2e2.gridx = 2;
		c2e2.gridy = 1;
		c2e2.gridwidth = 3;

		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 2;
		c3.gridy = 2;
		GridBagConstraints d3 = new GridBagConstraints();
		d3.gridx = 3;
		d3.gridy = 2;
		GridBagConstraints e3 = new GridBagConstraints();
		e3.gridx = 4;
		e3.gridy = 2;

		// TODO: Fix Edit Department incorrectly showing up

		// TODO: build a dialog to view the pay period for the year
		frame.add(settingsL, b1);
		frame.add(payFreqL, a2);
		// TODO: get the dropdown to work and get the different pay frequencies to work

//    	frame.add(payFreqD,a3);
		frame.add(viewRangesB, b3);
		frame.add(deptB, b2);

		frame.setClosable(true);
		frame.setMaximizable(true);
		frame.setLocation(0, 0);

		frame.setVisible(true);

		frame.add(payPeriodL, c2e2);
		frame.add(startDateT, c3);
		frame.add(dashL, d3);
		frame.add(endDateT, e3);

		frame.setClosable(true);
		frame.setMaximizable(true);
		frame.setLocation(0, 0);

		frame.setVisible(true);

		return frame;
	}

	private static void setLabels() {
		settingsL = new JLabel("<HTML><U> Settings </U></HTML>");
		payFreqL = new JLabel("<HTML><U> Current Pay Frequency </U></HTML>");
		payPeriodL = new JLabel("<HTML><U> Current Pay Period </U></HTML>");
		slashL = new JLabel("/");
		dashL = new JLabel("-");
	}

	static ActionListener DeptTableBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Edit_Departments.createDialog();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

}
