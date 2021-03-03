package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import sql.MySQL;

public class ConfigMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056683418265814502L;

	public static void createConfigScreen() throws Exception {
		int PeriodValue = 0;
		
		
		final String SERVERNAME = "localhost";
		final String DATABASENAME = "payroll";
		final String DATABASE_URL = "jdbc:mysql://" + SERVERNAME + "/" + DATABASENAME;
		
		String username = "root";
		String password = "anatominen1399";
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,username,password);
		
		
		Statement stmt = conn.createStatement();
			
		ResultSet rs = stmt.executeQuery("select * from generalconfig");
		
		while(rs.next()) {
			PeriodValue = rs.getInt("data_value");
			System.out.println(PeriodValue);
		}
		
		
		
		
		conn.close();
		
		//Convert value of PeriodValue to a string
		String SPeriodValue = String.valueOf(PeriodValue);
		
		JFrame Config = new JFrame("ACI Payroll");
		ImageIcon icon = new ImageIcon("lib/ACI.png");
		
		
		JPanel Period = new JPanel();
		JLabel PeriodValueL = new JLabel("Pay Period:");
		JTextArea PeriodValue_TA = new JTextArea();
		PeriodValue_TA.setText(SPeriodValue);
		
		JPanel Table = new JPanel(new BorderLayout());
		JLabel PeriodTable = new JLabel();
		PeriodTable.setText("<html>Pay Period Values:<br>Annually: 1<br>Quarterly: 4<br>Monthly: 12<br>4-Weekly: 13<br>Semi_Monthly: 24<br>Bi-Weekly: 26/27<br>Weekly: 52/53<html>");
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(SubmitBListener);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(CancelBListener);
		
		
		Config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Config.setSize(800,800);
		Config.setIconImage(icon.getImage());
		
		Table.add(PeriodTable);
		Period.add(PeriodValueL,BorderLayout.WEST);
		Period.add(PeriodValue_TA,BorderLayout.EAST);
		
		Config.add(Table);
		Config.add(Period,BorderLayout.AFTER_LAST_LINE);
		Config.add(Submit,BorderLayout.EAST);
		Config.add(Cancel,BorderLayout.EAST);
		
		
		
		Config.setVisible(true);
	}


	//When the Submit button is Pressed
		static ActionListener SubmitBListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String str = event.getActionCommand();
				System.out.println("Clicked = " + str);
			}
		};
	//When the Cancel button is Pressed
			static ActionListener CancelBListener = new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					String str = event.getActionCommand();
					System.out.println("Clicked = " + str);
					
				}
			};	
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

}
