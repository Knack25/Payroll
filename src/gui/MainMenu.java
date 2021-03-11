package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		//place the default frame here
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
			JDialog createDia;
			try{
				createDia = CreateEmployee.CreateMenu();
			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } 
        else if("edit_employee".equals(e.getActionCommand())){
        	//JInternalFrame frame = Frame_Template.createFrame();
        	//MainMenu.add(frame);
        }
        else if("terminate_employee".equals(e.getActionCommand())){
        	JDialog termDia;
			try {
				termDia = Terminate_Employee.createEmployeeTerminateDialog();
				termDia.setVisible(true);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
    
    
    
	
		

	
	// Change this to the default frame
	 //Create a new internal frame.
    protected JInternalFrame createFrame() {
        JInternalFrame frame = new JInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        MainMenu.add(frame);
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
		return frame;
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
}
