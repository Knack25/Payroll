package gui;

 import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import fileIO.Config;
 
 
 public class CreateEmployee extends JDialog implements ActionListener{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810663888408123313L;
	static String fName;
	static String mName;
	static String lName;
	static JDialog createMenu = new JDialog();
	
	 
	 public static JDialog CreateMenu() {
		 
		 createMenu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 createMenu.setVisible(true);
		 
		 createMenu.setSize(300, 300);	
		 JPanel labels = new JPanel();
		 JPanel fields = new JPanel();
		// GroupLayout create = new GroupLayout(createMenu);
		 createMenu.setLayout(new BorderLayout());
		 JLabel create_EmployeeL = new JLabel("Create Employee");
		 JLabel employeeName = new JLabel("Employee Name:");
		 JLabel employeeFirst = new JLabel("First");
		 JLabel employeeMiddle = new JLabel("Middle");
		 JLabel employeeLast = new JLabel("Last");		 
		 JButton createB = new JButton("Create"); 
		 createB.setActionCommand("Submit");
		 //JButton cancelB = new JButton("Cancel"); 
		 //cancelB.setActionCommand("Cancel");
		 JTextField enterFirst = new JTextField("First");
		 JTextField enterMiddle = new JTextField("Middle");
		 JTextField enterLast = new JTextField("Last");
		 
	
		 
		 fName = enterFirst.getText();
		 mName = enterMiddle.getText();
		 lName = enterLast.getText();
		 
		 
		// labels.add(employeeFirst);
		// labels.add(employeeMiddle);
		// labels.add(employeeLast);
		 
		 fields.add(enterFirst);
		 fields.add(enterMiddle);
		 fields.add(enterLast);
		 
		// createMenu.add(labels,BorderLayout.WEST);
		 createMenu.add(fields,BorderLayout.EAST);
		
		 
//		 
//		 create.setAutoCreateGaps(true);
//		 create.setAutoCreateContainerGaps(true);
//		
//		 create.setVerticalGroup(
//			 create.createSequentialGroup()
//		//	.addGroup(create.createParallelGroup(GroupLayout.Alignment.BASELINE)
//				.addComponent(employeeName)
//		   		.addComponent(employeeFirst)
//           		.addComponent(employeeMiddle)
//           		.addComponent(employeeLast)
//		//	.addGroup(create.createParallelGroup(GroupLayout.Alignment.CENTER)
//				.addComponent(employeeName)
//		   		.addComponent(enterFirst)
//           		.addComponent(enterMiddle)
//           		.addComponent(enterLast)
//			.addComponent(createB)
//
//
//		 );	
	/*	create.setHorizontalGroup(
			create.createSequentialGroup()
			 .addGroup(create.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(employeeFirst)
				.addComponent(enterFirst)
			 )
			 .addGroup(create.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(employeeMiddle)
				.addComponent(enterMiddle)
			 )
			 .addGroup(create.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(employeeLast)
				.addComponent(enterLast)
			 )
			


		 );
		 */

		 //https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
		createMenu.setVisible(true);
			 return createMenu;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		 System.out.println(e.getActionCommand() + " clicked.");
	        if ("Submit".equals(e.getActionCommand())) { //new
	            //Connect to SQL and save new column in employee
	        	String[] SQL;
				try {
					SQL = Config.SQLConfig();
					
					final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			    	
			    	Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
					
					
					Statement stmt = conn.createStatement();
						
					ResultSet rs = stmt.executeQuery("insert into employee(firstname,middlename,lastname) values("
							+fName+","+mName+","+lName+");");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
		    	
				
				
	        } 
	}
	 
	 
	 
	 
	 
 }