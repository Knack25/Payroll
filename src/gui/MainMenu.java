package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener{


	 /**
	 * 
	 */
	private static final long serialVersionUID = -8356941478722720686L;

	
	
	public static void createMainMenu() {
		
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
		
		return;
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
	//When the Config/Settings button is Pressed
	static ActionListener configBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
			try {
				ConfigMenu.createConfigScreen();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	//When the Process Payroll Button is Pressed
	static ActionListener processPayrollBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
		}
	};
	
	//When the View Payroll History Button is Pressed
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
