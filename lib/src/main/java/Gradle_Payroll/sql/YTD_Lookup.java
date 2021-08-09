package Gradle_Payroll.sql;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.github.lgooddatepicker.components.DatePicker;

import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.gui.ErrorDialog;

public class YTD_Lookup {

	static JDialog dialog;
	static LocalDate currDate;
	static DatePicker startDate;
	static JButton submit,cancel;
	
	
	
	
	public static void startupCheck() throws Exception {
		int size = 0;
		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for table data");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String insertStatement = "select * from ytdConfig";

		PreparedStatement pstmt = conn.prepareStatement(insertStatement);

		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			size = rs.getRow();
		}

		System.out.println("Current YTD_Table size is: " + size);
		
		if(size == 0) {
			ErrorDialog.createError("Pay Period is not Initialized");
		}
		return;
	}
	
	
	public static Dialog createTable() {
		
		startDate = new DatePicker();
		cancel = new JButton("Cancel");
		cancel.addActionListener(cancelBListener);
		submit = new JButton("Submit");
		submit.addActionListener(submitBListener);
		
		
		JComboBox<String> periodTypeComboBox = new JComboBox<String>();
		periodTypeComboBox.addItem("Annually");
		periodTypeComboBox.addItem("Quarterly");
		periodTypeComboBox.addItem("Monthly");
		periodTypeComboBox.addItem("4-Weekly");
		periodTypeComboBox.addItem("Semi-Monthly");
		periodTypeComboBox.addItem("Weekly");
		
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(800, 800);
		
		dialog.add(periodTypeComboBox);
		
		JLabel startingDate = new JLabel("Please enter a starting date");
		dialog.add(startingDate);
		dialog.add(startDate);
		dialog.add(submit);
		dialog.add(cancel);
		
		dialog.setSize(550, 350);
		dialog.setVisible(true);
		
		return dialog;
	}
	
	static ActionListener cancelBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	static ActionListener submitBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
}
