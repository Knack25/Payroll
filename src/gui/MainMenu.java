package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import data.Employee;

public class MainMenu extends JFrame implements ActionListener{


	 /**
	 * 
	 */
	private static final long serialVersionUID = -8356941478722720686L;

	JDesktopPane MainMenu;
	
	
	public MainMenu() {
		super("ACI Payroll");
		
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset,inset,screenSize.width - inset*2,screenSize.height - inset*2);
		
		MainMenu = new JDesktopPane();
		createFrame();
		setContentPane(MainMenu);
		setJMenuBar(createMenuBar());
		
		MainMenu.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
	}
	
	
	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Employee");
		//menu.setMnemonic(KeyEvent.VK_D);
		
		
		JMenuItem menuItem = new JMenuItem("Create Employee");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("create_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		
		menuItem = new JMenuItem("Edit Employee");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("edit_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		
		menuItem = new JMenuItem("Terminate Employee");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("terminate_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		
		menuItem = new JMenuItem("View Employees by Department");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("dept_employee");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		
		
		
		menu = new JMenu("Payroll");
		//menu.setMnemonic(KeyEvent.VK_D);
		
		
		menuItem = new JMenuItem("Process Payroll");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("process_payroll");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Payroll History");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("history_payroll");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Print Reports");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("print_reports");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Void Check");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("void_check");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		
		menu = new JMenu("Setup");
		//menu.setMnemonic(KeyEvent.VK_D);
		
		
		menuItem = new JMenuItem("Payroll Settings");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("payroll_settings");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Program Settings");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("program_settings");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("MySQL Settings");
		//menuItem.setMnemonic(KeyEvent.VK_E);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.setActionCommand("sql_settings");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		
		return menuBar;
	}
	
	
	
	//React to menu selections.
    public void actionPerformed(ActionEvent e) {
    	 System.out.println(e.getActionCommand() + " JMenuItem clicked.");
        if ("create_employee".equals(e.getActionCommand())) { //new
            //Open A Dialogue To Make a new Employee
        	//createFrame();
        } 
        else if("edit_employee".equals(e.getActionCommand())){
        	createFrame();
        }
        else if("terminate_employee".equals(e.getActionCommand())){
        	
        }
        else if("dept_employee".equals(e.getActionCommand())){
        	
        }
        else if("process_payroll".equals(e.getActionCommand())){
        	
        }
        else if("history_payroll".equals(e.getActionCommand())){
        	
        }
        else if("print_reports".equals(e.getActionCommand())){
        	
        }
        else if("void_check".equals(e.getActionCommand())){
        	
        }
        else if("payroll_settings".equals(e.getActionCommand())){
        	
        }
        else if("program_settings".equals(e.getActionCommand())){
        	
        }
        else if("sql_settings".equals(e.getActionCommand())){
        	
        }
        
        else { //quit
            quit();
        }
    }
    
    
    
	
		

	
	
	 //Create a new internal frame.
    protected void createFrame() {
        JInternalFrame frame = new JInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        MainMenu.add(frame);
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }
	
    
	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		MainMenu frame = new MainMenu();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
/*	public static void createMainMenu() throws Exception {
		
		
		
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


	
	//When the Config/Settings button is Pressed
	static ActionListener configBListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
			try {
			
				//ConfigMenu.createConfigScreen();
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
	*/
}
