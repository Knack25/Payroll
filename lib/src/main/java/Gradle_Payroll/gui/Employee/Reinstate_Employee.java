package Gradle_Payroll.gui.Employee;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Gradle_Payroll.fileIO.Config;

public class Reinstate_Employee {

	static JComboBox<String> employee;
	static JDialog dialog;
	static String fName, mName, lName, fullName;

	public static JDialog createEmployeeReinsateDialog() throws Exception {
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		dialog.addWindowListener(DialogListener);
		dialog.setLocation(80, 120);
		employee = new JComboBox<String>();
		employee.setPreferredSize(new Dimension(150, 30));

		JButton submitB = new JButton("Submit");
		// submitB.setActionCommand("TermSubmit");
		submitB.addActionListener(submit);
		JLabel name = new JLabel("Employee To Reinstate");

		System.out.println("Querrying DB...");

		int i = sqlPullRequest();

		System.out.println("Data Retreived Successfull for " + i + " entries.");

		System.out.println("Creating Dialog Box");

		dialog.setSize(400, 90);
		dialog.setLayout(new FlowLayout());
		dialog.add(name);
		dialog.add(employee);
		dialog.add(submitB);
		dialog.repaint();

		System.out.println("Created Dialog");

		dialog.setVisible(true);

		return dialog;
	}

	private static int sqlPullRequest() throws Exception, SQLException {
		String[] SQL = Config.PullSQLConfig();

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from employee where enabled = false");

		int i = 0;

		while (rs.next()) {
			fName = rs.getString("firstname");
			mName = rs.getString("middlename");
			lName = rs.getString("lastname");
			fullName = fName + " " + mName + " " + lName;
			employee.addItem(fullName);
			i++;
		}

		stmt.close();
		conn.close();
		return i;
	}

	static ActionListener submit = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Connect to SQL and save new column in employee

			fullName = (String) employee.getSelectedItem();
			System.out.println("The value of fullName is: " + fullName);
			String[] name = fullName.split(" ");
			for (int i = 0; i < name.length; i++) {
				System.out.println(name[i]);
			}

			try {
				sqlPushRequest(name);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dialog.setVisible(false);
			dialog.dispose();
		}

		private void sqlPushRequest(String[] name) throws Exception, SQLException {
			String[] SQL;
			System.out.println("Executing Update");

			SQL = Config.PullSQLConfig();

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String updateStatement = "update employee " + "set enabled = true "
					+ "WHERE firstname = ? AND lastname = ?";

			PreparedStatement pstmt = conn.prepareStatement(updateStatement);

			if (name.length == 3) {
				pstmt.setString(1, name[0]);
				pstmt.setString(2, name[2]);
			}
			if (name.length == 2) {
				pstmt.setString(1, name[0]);
				pstmt.setString(2, name[1]);
			}

			int output = pstmt.executeUpdate();

			System.out.println("Affected " + output + " rows.");

			pstmt.close();
			conn.close();
		}
	};

	static WindowListener DialogListener = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			dialog.setVisible(false);
			dialog.dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowActivated(WindowEvent e) {

		}
	};
}
