package Gradle_Payroll.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Gradle_Payroll.gui.Employee.Create_Employee;
import Gradle_Payroll.gui.Employee.Edit_Employee;
import Gradle_Payroll.gui.Employee.Reinstate_Employee;
import Gradle_Payroll.gui.Employee.Terminate_Employee;
import Gradle_Payroll.gui.Employee.View_Employees;
import Gradle_Payroll.gui.Payroll.Check_Edit;
import Gradle_Payroll.gui.Payroll.Create_Check;
import Gradle_Payroll.gui.Reporting.Payroll_History;
import Gradle_Payroll.gui.Reporting.Print_Reports;

public class Main_Menu extends JFrame implements ActionListener {

	/**
	* 
	*/
	private static final long serialVersionUID = -8356941478722720686L;

	static JDesktopPane MainMenu;
	public static int CheckNum;
	public static int year;

	public Main_Menu() {

		super("ACI Payroll");

		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

		MainMenu = new JDesktopPane();
		// place the default frame here
		setContentPane(MainMenu);
		setJMenuBar(createMenuBar());

		MainMenu.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

	}

	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Employee");

		JMenuItem menuItem = new JMenuItem("Create Employee");
		menuItem.setActionCommand("create_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Edit Employee");
		menuItem.setActionCommand("edit_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Terminate Employee");
		menuItem.setActionCommand("terminate_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Reinstate Employee");
		menuItem.setActionCommand("reinstate_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("View Employees by Department");
		menuItem.setActionCommand("dept_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);

		menu = new JMenu("Payroll");

		menuItem = new JMenuItem("Process Payroll");
		menuItem.setActionCommand("process_payroll");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Payroll History");
		menuItem.setActionCommand("history_payroll");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Print Reports");
		menuItem.setActionCommand("print_reports");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Void Check");
		menuItem.setActionCommand("void_check");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);

		menu = new JMenu("Settings");

		menuItem = new JMenuItem("Program Settings");
		menuItem.setActionCommand("payroll_settings");
		menuItem.addActionListener(this);
		menu.add(menuItem);

//		menuItem = new JMenuItem("Program Settings");
//		menuItem.setActionCommand("program_settings");
//		menuItem.addActionListener(this);
//		menu.add(menuItem);

		menuItem = new JMenuItem("Developer Mode");
		menuItem.setActionCommand("sql_settings");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);

		return menuBar;
	}

	// React to menu selections.
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand() + " JMenuItem clicked.");
		switch (e.getActionCommand()) {
		case ("create_employee"):
			createEmployeeWindow();
			break;
		case ("edit_employee"):
			editEmployeeFrame();
			break;
		case ("terminate_employee"):
			terminateEmployeeDialog();
			break;
		case ("reinstate_employee"):
			reinstateEmployeeDialog();
			break;
		case ("dept_employee"):
			viewEmployeeWindow();
			break;
		case ("process_payroll"):
			processPayrollDialog();
			break;
		case ("history_payroll"):
			payrollHistoryWindow();
			break;
		case ("print_reports"):
			printReportsWindow();
			break;
		case ("void_check"):
			voidCheckDialog();
			break;
		case ("payroll_settings"):
			programSettingsWindow();
			break;
//    	case("program_settings"):
//    		
//    		break;
		case ("sql_settings"):
			developerModeWindow();
			break;
		default:
			quit();
		}
	}

	private void editEmployeeFrame() {
		try {
			JInternalFrame edit_emp = Edit_Employee.createFrame();
			MainMenu.add(edit_emp);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void reinstateEmployeeDialog() {
		// JDialog reinEmp = new JDialog();
		try {
			Reinstate_Employee.createEmployeeReinsateDialog();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// reinEmp.setVisible(true);
	}

	private void terminateEmployeeDialog() {
		try {
			Terminate_Employee.createEmployeeTerminateDialog();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void createEmployeeWindow() {
		try {
			Create_Employee.CreateMenu();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void viewEmployeeWindow() {
		try {
			View_Employees.createViewemployeeMenu();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void voidCheckDialog() {
		try {
			Void_Check.createVoidcheckMenu();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void processPayrollDialog() {
		try {
			Create_Check.createCheckmenu();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	static public void processPayrollEdit() {
		try {
			JInternalFrame edit_check = Check_Edit.createDialog(CheckNum, year);
			MainMenu.add(edit_check);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void programSettingsWindow() {
		try {
			JInternalFrame programSettings = Program_Settings.createFrame();
			MainMenu.add(programSettings);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void payrollHistoryWindow() {
		try {
			Payroll_History.createFrame();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void printReportsWindow() {
		try {
			Print_Reports.createFrame();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void developerModeWindow() {
		try {
			// Authentication.createFrame();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// Change this to the default frame
	// Create a new internal frame.
	protected JInternalFrame createFrame() {
		JInternalFrame frame = new JInternalFrame();
		frame.setVisible(true); // necessary as of 1.3
		MainMenu.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		return frame;
	}

	// Quit the application.
	protected void quit() {
		System.exit(0);
	}

	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		Main_Menu frame = new Main_Menu();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		frame.setVisible(true);
	}
}
