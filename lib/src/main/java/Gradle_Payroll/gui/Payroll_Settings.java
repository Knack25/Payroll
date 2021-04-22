package Gradle_Payroll.gui;


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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Address;
import Gradle_Payroll.data.Employee;
import Gradle_Payroll.data.Name;
import Gradle_Payroll.fileIO.Config;


public class Payroll_Settings {
	
	
	static JInternalFrame frame;
	static JLabel settingsL,payFreqL,payPeriodL,slashL,dashL;
	static JComboBox<String> payFreqD,startDateD,endDateD;
	//static JTextField stMnthT,stDayT,stYrT,endMnthT,endDayT,endYrT;
	
	
	//GridBagLayout works very well for what needs to be done here
	
	 //Create a new internal frame.
    protected static JInternalFrame createFrame() throws Exception{
        frame = new JInternalFrame();
       
        System.out.println("Launching Frame");
		
        frame.setVisible(true); //necessary as of 1.3
       
        try {
        	frame.setSelected(true);
        } catch (java.beans.PropertyVetoException exceptSelected) {}
        
        
      //  employee = new JComboBox<String>();
       // employee.addItemListener(employeeSel);
        
        //department = new JComboBox<String>();
        //department.addItemListener(departmentSel);
        
       // sqlPullDeptListRequest();
      
        setLabels();
       
    	
    	//JButton saveB = new JButton("Save");
    	//saveB.addActionListener(saveEmp);
    	
    	JButton viewRangesB = new JButton("View Yearly Pay Ranges");
		JButton deptB = new JButton("View/Edit Departments");
		JButton taxTableB = new JButton("View/Edit Default Tax Table");
		JButton termEmpB = new JButton("View/Edit Terminated Employees ");
		JButton developerB = new JButton("Developer Mode");
    	
    	
   
    	System.out.println("Querrying DB...");
    	
    	//sqlPullEmpListRequest();
		
		System.out.println("Creating Edit Frame");
		
		frame.setSize(1535, 820);
    	
    	
    	frame.setLayout(new GridBagLayout());
    	
    	
   
    	GridBagConstraints b1 = new GridBagConstraints();
    	b1.gridx = 1;
    	b1.gridy = 0;

    	GridBagConstraints f1 = new GridBagConstraints();
    	f1.gridx = 5;
    	f1.gridy = 0;
    	
    	GridBagConstraints a2 = new GridBagConstraints();
    	a2.gridx = 0;
    	a2.gridy = 1;
    	
    	GridBagConstraints a3 = new GridBagConstraints();
    	a3.gridx = 0;
    	a3.gridy = 2;
    	
    	GridBagConstraints a4 = new GridBagConstraints();
    	a4.gridx = 0;
    	a4.gridy = 3;
    	
    	GridBagConstraints b2 = new GridBagConstraints();
    	b2.gridx = 1;
    	b2.gridy = 1;
    	
    	GridBagConstraints b3 = new GridBagConstraints();
    	b3.gridx = 1;
    	b3.gridy = 2;
    	
    	GridBagConstraints b4 = new GridBagConstraints();
    	b4.gridx = 1;
    	b4.gridy = 3;
    	
    	GridBagConstraints c2e2 = new GridBagConstraints();
    	c2e2.gridx = 2;
    	c2e2.gridy = 1;
    	c2e2.gridwidth = 3;
    	
    	GridBagConstraints c3 = new GridBagConstraints();
    	a3.gridx = 2;
    	a3.gridy = 2;
    	GridBagConstraints d3 = new GridBagConstraints();
    	a3.gridx = 3;
    	a3.gridy = 2;
    	GridBagConstraints e3 = new GridBagConstraints();
    	a3.gridx = 4;
    	a3.gridy = 2;
 
 
    	frame.add(settingsL,b1);
    	frame.add(payFreqL,a2);
    	frame.add(payFreqD,a3);
    	frame.add(viewRangesB,a4);
    	frame.add(payPeriodL,c2e2);
    	frame.add(deptB,b2);
    	frame.add(taxTableB,b3);
    	frame.add(termEmpB,b4);
    	frame.add(developerB,f1);
    	frame.add(startDateD,c3);
    	frame.add(dashL,d3);
    	frame.add(endDateD,e3);
    	
    
    	
    	frame.setClosable(true);
    	frame.setMaximizable(true);
    	frame.setLocation(0, 0);
    	
    	//frame.repaint();
    	frame.setVisible(true);
    	
       	
    	
        
		return frame;
    }

	private static void setLabels() {
		settingsL = new JLabel("<HTML><U> Settings </U></HTML>");
        payFreqL =  new JLabel("<HTML><U> Current Pay Frequency </U></HTML>");
        payPeriodL =  new JLabel("<HTML><U> Current Pay Period </U></HTML>");
        slashL = new JLabel("/");
        dashL = new JLabel("-");
	}
};

   