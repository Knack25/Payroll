package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.fileIO.Config;

public class Edit_Departments {

	static List<JTextField> Name;
	static Dialog dialog;
	static JPanel panel;
	static ResultSet rs;

	protected static Dialog createDialog() throws Exception {

		JButton saveB = new JButton("Save");
		saveB.addActionListener(saveBListener);

		JButton addB = new JButton("Add");
		addB.addActionListener(addBListener);

		JButton removeB = new JButton("Remove");
		removeB.addActionListener(removeBListener);

		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);

		dialog.setLocation(50, 50);
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		SQLPullDeps();
		dialog.setSize(1400, 700);

		Name = new ArrayList<JTextField>();

		int autoX = 0;
		int autoY = 0;
		c.weightx = 0.09;
		for (int i = 0; i < rs.getFetchSize() ; i++) {

			// On the first run, build the labels
			if (i == 0) {
				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel idL = new JLabel("#");
				panel.add(idL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel nameL = new JLabel("Name");
				panel.add(nameL, c);
				autoX++;
				c.gridx = autoX;

				autoX = 0;
				autoY++;
			}
			// On the last run, add the save button
			else if (i == Name.size()) {
				autoX = 15;
				c.gridx = autoX;
				c.gridy = autoY;

				panel.add(saveB, c);
				autoX++;
				c.gridx = autoX;
				panel.add(addB, c);
				autoX++;
				c.gridx = autoX;
				panel.add(removeB, c);

				autoX++;
				autoY++;
			}
			// On all the middle runs, build the table
			else {
				rs.next();

				c.gridx = autoX;
				c.gridy = autoY;
				panel.add(new JLabel(String.valueOf(i)), c);
				autoX++;
				c.gridx = autoX;

				// Name of the tax
				c.fill = GridBagConstraints.HORIZONTAL;
				JTextField name = new JTextField();
				name.setText(rs.getString("depName"));
				name.setColumns(3);
				panel.add(name, c);
				Name.add(name);
				autoX++;
				c.gridx = autoX;

				autoX = 0;
				c.gridx = autoX;
				autoY++;
			}

		}

		dialog.add(panel);

		dialog.setVisible(true);

		return dialog;
	}

	static ActionListener saveBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SQLPushTable();
			} catch (Exception SQLPush) {
				SQLPush.printStackTrace();
			}

		}

		private void SQLPushTable() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int result = 0;
			int result2 = 0;

			System.out.println("Querrying DB for selected Employee");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);
			Connection connytd = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String insertStatement = "update departments set depName = ? where id = ?";

			PreparedStatement pstmt = conn.prepareStatement(insertStatement);

			for (int i = 0; i < Name.size(); i++) {
				// Tax Name
				System.out.println(Name.get(i).getText());
				pstmt.setString(1, Name.get(i).getText());
				// Tax Type

				pstmt.setInt(2, i + 1);

				result += pstmt.executeUpdate();
			}

			System.out.println("Updated " + result + " Entries.");
			conn.close();
		}
	};

	static ActionListener addBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SQLAddDep();
			} catch (Exception SQLTaxAdd) {
				SQLTaxAdd.printStackTrace();
			}

		}

		private void SQLAddDep() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int result = 0;

			System.out.println("Querrying DB for selected Employee");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String insertStatement = "INSERT INTO " + "departments(depName)" + "Values(?)";

			PreparedStatement pstmt = conn.prepareStatement(insertStatement);

			pstmt.setString(1, "Name");

			result = pstmt.executeUpdate();

			System.out.println("Added " + result + "entry(s) to tax table.");
			System.out.println("Refreshing GUI...");
			dialog.dispose();
			createDialog();
		}
	};

	static ActionListener removeBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Department_Remove.createDialog();
				dialog.dispose();
				createDialog();
			} catch (Exception SQLTaxRemove) {
				SQLTaxRemove.printStackTrace();
			}
		}
	};

	// This will take all the current taxes for the selected employee and return
	// them as a ResultSet...
	private static void SQLPullDeps() throws Exception {

		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for selected Employee");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String insertStatement = "select * from departments";

		PreparedStatement pstmt = conn.prepareStatement(insertStatement);

		rs = pstmt.executeQuery();

		return;
	}
}
