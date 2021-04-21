package Gradle_Payroll.gui;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ConfigMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056683418265814502L;
	final static String SERVERNAME = "localhost";
	final static String DATABASENAME = "payroll";
	final static String DATABASE_URL = "jdbc:mysql://" + SERVERNAME + "/" + DATABASENAME;
	
	static String username = "root";
	static String password = "anatominen1399";
	
	static boolean PeriodChange = false;
	static int PeriodValue = 0;
	
	
	static String[] PeriodTable = {"Annually: 1","Quarterly: 4","Monthly: 12","4-Weekly: 13","Semi_Monthly: 24","Bi-Weekly: 26/27","Weekly: 52/53"};

	static JComboBox<?> PeriodValueCB = new JComboBox<Object>(PeriodTable);
	

	
	static JLayeredPane Config = new JLayeredPane();
	
	
	
	
	
	
	
	
	public static JLayeredPane createConfigScreen() throws Exception {
		
		
		
		
		
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
		
		
		
		//ImageIcon icon = new ImageIcon("lib/ACI.png");
		
		
		JPanel Period = new JPanel();
		JLabel PeriodValueL = new JLabel("Pay Period:");
		
		PeriodValueCB.setSelectedIndex(PeriodValue);
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(SubmitBListener);
		
		
		
		
	
		Period.add(PeriodValueL,BorderLayout.WEST);
		Period.add(PeriodValueCB,BorderLayout.EAST);
		Config.add(Period,BorderLayout.AFTER_LAST_LINE);
		Config.add(Submit,BorderLayout.EAST);
		
		
		
		Config.setVisible(true);
		return Config;
	}


	//When the Submit button is Pressed
		static ActionListener SubmitBListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String str = event.getActionCommand();
				System.out.println("Clicked = " + str);
				
				
				//if the Period Value was changed, flag it
				if(PeriodValueCB.getSelectedIndex() != PeriodValue) {
					PeriodChange = true;
				}
				
				
				//If something on the screen has changed, we will update either the DB or the local config file
				if(PeriodChange) {
					Connection conn;
					try {
						conn = DriverManager.getConnection(DATABASE_URL,username,password);
						
						Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						
						ResultSet rs = stmt.executeQuery("select * from generalconfig");
						
						while(rs.next()) {
							rs.updateInt("data_value", PeriodValueCB.getSelectedIndex());
							rs.updateRow();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				Config.setVisible(false);
			}
		};
	//When the Cancel button is Pressed
	/*		static ActionListener CancelBListener = new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					String str = event.getActionCommand();
					System.out.println("Clicked = " + str);
					Config.dispose();
					
				}
			};	
	*/

		@Override
		public void actionPerformed(ActionEvent e) {
		}

}
