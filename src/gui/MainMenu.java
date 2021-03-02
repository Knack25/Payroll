package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.*;

import sql.MySQL;

public class MainMenu extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void createMainMenu() {
		
		
		//Connect to the database
		Connection conn = MySQL.Connect();
				
		System.out.println("Successful Connectin to DB!");
		
		JFrame MainMenu = new JFrame("ACI Payroll");
		MainMenu.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("lib/ACI.png");
		JLabel optionL = new JLabel("Options");
		JPanel panelOption = new JPanel(new BorderLayout());
		JPanel panelPayroll = new JPanel(new BorderLayout());
		JPanel panelEmployee = new JPanel(new BorderLayout());
		
		JButton configB = new JButton("View Configuration");
		configB.addActionListener(configBListener);
		
		JLabel payrollL = new JLabel("Payroll");
		JButton processPayroll = new JButton("Process Payroll");
		processPayroll.addActionListener(processPayrollBListener);
		JButton historyPayroll = new JButton("View Payroll History");
		historyPayroll.addActionListener(historyPayrollBListener);
		JButton voidPayroll = new JButton("Void a Check");
		voidPayroll.addActionListener(voidPayrollBListener);
		JButton printReports = new JButton("Print Reports");
		printReports.addActionListener(printReportsBListener);
		
		
		JLabel empL = new JLabel("Employee History");
		JButton createEmployee = new JButton("Create Employee");
		createEmployee.addActionListener(createEmpBListener);
		JButton editEmployee = new JButton("Edit existing Employee");
		editEmployee.addActionListener(editEmpBListener);
		JButton removeEmployee = new JButton("Remove existing Employee");
		removeEmployee.addActionListener(removeEmpBListener);
		
		
		
		panelOption.add(optionL,BorderLayout.NORTH);
		panelOption.add(configB,BorderLayout.EAST);
		panelPayroll.add(payrollL,BorderLayout.NORTH);
		panelPayroll.add(processPayroll,BorderLayout.WEST);
		panelPayroll.add(historyPayroll,BorderLayout.CENTER);
		panelPayroll.add(voidPayroll,BorderLayout.EAST);
		panelPayroll.add(printReports,BorderLayout.SOUTH);
		panelEmployee.add(empL,BorderLayout.NORTH);
		panelEmployee.add(createEmployee,BorderLayout.WEST);
		panelEmployee.add(editEmployee,BorderLayout.CENTER);
		panelEmployee.add(removeEmployee,BorderLayout.EAST);
		
		MainMenu.add(panelOption,BorderLayout.NORTH);
		MainMenu.add(panelPayroll,BorderLayout.CENTER);
		MainMenu.add(panelEmployee,BorderLayout.SOUTH);
		MainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MainMenu.setSize(800,800);
		MainMenu.setIconImage(icon.getImage());
		
		
		
		
		MainMenu.setVisible(true);
		
		MainMenu.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Main Window Clossed...");
				System.out.println("Disconnecting from Database...");
				//Close the connection
				MySQL.CloseConnection(conn);
			}
		});
		
		return;
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
	static ActionListener configBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener processPayrollBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener historyPayrollBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener voidPayrollBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener printReportsBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener createEmpBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener editEmpBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	static ActionListener removeEmpBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
}
