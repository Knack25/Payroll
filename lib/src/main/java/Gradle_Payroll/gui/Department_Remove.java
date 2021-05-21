package Gradle_Payroll.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import Gradle_Payroll.fileIO.Config;

public class Department_Remove {

	
	static JDialog dialog;
	static JComboBox<String> Name;
	static List<String> ComboList;

	public static JDialog createDialog() {
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(350, 120);
		JPanel comboPanel, buttonPanel;
		comboPanel = new JPanel();
		buttonPanel = new JPanel();

	
		Name = new JComboBox<String>();
		ComboList = new ArrayList<String>();

		JButton submitB = new JButton("Submit");
		submitB.addActionListener(submitBListener);

		JButton cancelB = new JButton("Cancel");
		cancelB.addActionListener(cancelBListener);

		try {
			SQLPullDeps();
		} catch (Exception e) {
			e.printStackTrace();
		}

		comboPanel.add(Name);
		buttonPanel.add(submitB, BorderLayout.WEST);
		buttonPanel.add(cancelB, BorderLayout.EAST);

		dialog.add(comboPanel, BorderLayout.NORTH);
		dialog.add(buttonPanel, BorderLayout.SOUTH);

		dialog.setVisible(true);

		return dialog;
	}

	private static void SQLPullDeps() throws Exception {
		String[] SQL;
		SQL = Config.PullSQLConfig();
		String name;
		int id;
		String output;

		System.out.println("Querrying DB for selected Employee");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String insertStatement = "Select * from departments";

		PreparedStatement pstmt = conn.prepareStatement(insertStatement);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			name = rs.getString("depName");
			id = rs.getInt("id");
			output = id + ": " + name;
			ComboList.add(output);
			Name.addItem(output);
		}

		conn.close();

	}

	static ActionListener submitBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Update();
				dialog.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		private void Update() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int sel = Name.getSelectedIndex();
			String name = ComboList.get(sel);
			String[] temp = name.split(":");
			int id = Integer.valueOf(temp[0]);

			System.out.println("Querrying DB for selected Employee");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String insertStatement = "Delete from departments where id = ?";

			PreparedStatement pstmt = conn.prepareStatement(insertStatement);

			pstmt.setInt(1, id);

			int rs = pstmt.executeUpdate();

			System.out.println("Deleted " + rs + " entry with id: " + id + ".");
		}
	};

	static ActionListener cancelBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.dispose();
		}
	};
}
