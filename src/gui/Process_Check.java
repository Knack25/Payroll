package gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.Address;
import data.Employee;
import data.Name;
import fileIO.Config;



public class Process_Check {
	static JLabel regularL,ptoL,overtimeL,salaryL,advanceL,royaltiesL;
	static JTextField regHoursT,regRateT,ptoHoursT,ptoRateT,salHoursT,salRateT,advHoursT,advRateT,royalHoursT,royalRateT;
	static JComboBox<String> hr_or_sdol;
	/*
	 * needed for the process check
	 * default rates from employees
	 * or per hour of dollar amount
	 * types of payments
	 * create button
	 * maybe YTD's
	 * check No.
	 * name of employee
	 */
	
	public static JDialog CreateMenu() {
		JDialog createMenu = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		
		
		
		
		return createMenu;
	}
	
	
	
}



