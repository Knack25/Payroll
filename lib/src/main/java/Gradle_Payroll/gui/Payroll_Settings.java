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

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

//import fileIO.Config;

public class Payroll_Settings {

	static JInternalFrame frame;
	static JLabel settingsL,payFreqL,payPeriodL;
	static JComboBox<String> payFreqD;
	
	
	//GridBagLayout works very well for what needs to be done here
	
	 //Create a new internal frame.
    protected static JInternalFrame createFrame() throws Exception{
   
       
		
		System.out.println("Creating Edit Frame");
		
		frame.setSize(1535, 820);
    	
    	
    	frame.setLayout(new GridBagLayout());
    	
    	setLabels();
   
    	//need to figure out if this can be moved to a separate class
    	GridBagConstraints b1 = new GridBagConstraints();
    	b1.gridx = 1;
    	b1.gridy = 0;

    	GridBagConstraints d1 = new GridBagConstraints();
    	d1.gridx = 3;
    	d1.gridy = 0;
    	
    	GridBagConstraints a2 = new GridBagConstraints();
    	a2.gridx = 0;
    	a2.gridy = 1;
    	
    	GridBagConstraints g1 = new GridBagConstraints();
    	g1.gridx = 6;
    	g1.gridy = 0;
    	
    	
    	
    	
    	frame.add(settingsL,b1);
    
    	
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
	}
};
   