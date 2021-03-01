package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class MainMenu {

	public static JFrame createMainMenu() {
		
		JFrame MainMenu = new JFrame("ACI Payroll");
		MainMenu.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("lib/ACI.png");
		JLabel optionL = new JLabel("Options");
		JPanel panelOption = new JPanel(new BorderLayout());
		JPanel panelPayroll = new JPanel(new BorderLayout());
		JPanel panelEmployee = new JPanel(new BorderLayout());
		
		JButton configB = new JButton();
		configB.setText("View Configuration");
		
		JLabel payrollL = new JLabel("Payroll");
		JButton processPayroll = new JButton();
		processPayroll.setText("Process Payroll");
		JButton historyPayroll = new JButton("View Payroll History");
		JButton voidPayroll = new JButton("Void a Check");
		JButton printReports = new JButton("Print Reports");
		
		
		JLabel empL = new JLabel("Employee History");
		JButton createEmployee = new JButton("Create Employee");
		JButton editEmployee = new JButton("Edit existing Employee");
		JButton removeEmployee = new JButton("Remove existing Employee");
		
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
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu.setSize(800,800);
		MainMenu.setIconImage(icon.getImage());
		
		
		MainMenu.setVisible(true);
		return MainMenu;
		}	
}
