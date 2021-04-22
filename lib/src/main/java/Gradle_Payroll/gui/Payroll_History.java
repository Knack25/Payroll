package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Payroll_History {
	
	static JDialog dialog;
	static JLabel nameL,dateL,payrollHisL;
	static JComboBox<String> employee;
	static JTextField stMnthT,stDayT,stYrT,endMnthT,endDayT,endYrT;
	
	protected static JDialog createFrame() throws Exception{
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		
		System.out.println("Creating Dialog Box");
		 
		 
		 JButton loadB = new JButton("Load");
		 
		 
		 setLabels();
		 dialog.setSize(220, 220);
	    	dialog.setLayout(new GridBagLayout());
		 
	    	GridBagConstraints b1 = new GridBagConstraints();
	    	b1.gridx = 1;
	    	b1.gridy = 0;
		/*what is needed here for gui:
		 * labels: Name, Date, payroll history 
		 * Textfields for beginning and end dates
		 */
		
		
		
		
		
		return dialog;
	}
	
	 private static void setLabels() {
		 payrollHisL = new JLabel("<HTML><U> Payroll History </U></HTML>");
		 nameL = new JLabel("Name: ");
		 dateL = new JLabel("Date:" );
		}
	
	
	
	
	
	
};