package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Tax;
import Gradle_Payroll.fileIO.Config;

public class Edit_YTD {
	

	/*Everything needed for the YTD
	 * name of YTD
	 * Amount 
	 * tax table
	 * */
	
	static int taxNum = 0;

	static int EMPID;

	static JDialog dialog;
	static JLabel NameL,IDL,amountL;
	static JComboBox<String> taxType;
	static JPanel panel;
	static Tax tax;
	static ResultSet rs;
	static List<JTextField> Ammount;
	static List<JTextField> Name;
	static List<JLabel> ID;
	
	
	
	protected static void createDialog(int empID) throws Exception{
		
		EMPID = empID;
		
		JButton saveB = new JButton("Save");
		saveB.addActionListener(saveBListener);
		
		
		dialog = new JDialog(null,Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(800,800);
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		SQLPullTax(empID);
		taxNum = SQLTaxNum(empID) + 1;
		
		
		Ammount = new ArrayList<JTextField>();
		Name = new ArrayList<JTextField>();
		TaxType = new ArrayList<JComboBox<String>>();
		ID = new ArrayList<JLabel>();
		
		
		
		int autoX = 0;
		int autoY = 0;
		for(int i = 0; i <= taxNum;i++) {
			
			//On the first run, build the labels
			if(i == 0) {
				
				JLabel idL = new JLabel("#");
				panel.add(idL,c);
				autoX++;
				c.gridx = autoX;
				
				JLabel nameL = new JLabel("Name");
				panel.add(nameL,c);
				autoX++;
				c.gridx = autoX;
			
				
				JLabel amountL = new JLabel("Amount");
				panel.add(amountL,c);
				autoX++;
				c.gridx = autoX;
				//c.weightx
				
				
				JLabel sysID = new JLabel("ID");
				panel.add(sysID,c);
				
				
				autoX = 0;
				autoY++;
			}
			//On the last run, add the save button
			else if(i == taxNum) {
				autoX = 15;
				c.gridx = autoX;
				c.gridy = autoY;
				
				panel.add(saveB,c);
				
				
				autoY++;
			}
			//On all the middle runs, build the table
			else{
				rs.next();
				
				c.gridx = autoX;
				c.gridy = autoY;
				panel.add(new JLabel(String.valueOf(i)),c);
				autoX++;
				c.gridx = autoX;
				
				//Name of the tax
				JTextField name = new JTextField();
				name.setText(rs.getString("taxname"));
				name.setColumns(3);
				name.setEditable(false);
				panel.add(name,c);
				Name.add(name);
				autoX++;
				c.gridx = autoX;
				
				//Amount of the tax
				JTextField ammnt = new JTextField();
				ammnt.setColumns(3);
				name.setEditable(false);
				ammnt.setText(String.valueOf(rs.getDouble("ammount")));
				panel.add(ammnt,c);
				Ammount.add(ammnt);
				autoX++;
				c.gridx = autoX;
				
				
				JLabel taxID = new JLabel(rs.getString("id"));
				panel.add(taxID,c);
				ID.add(taxID);			
				autoX = 0;
				c.gridx = autoX;
				autoY++;
			}
			
		}
		
		dialog.add(panel);
		
		dialog.setVisible(true);
		
		return;
	}
	
	
	static ActionListener saveBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SQLPushTable();
			} catch (Exception SQLPush) {
				SQLPush.printStackTrace();
			}
			
		}

		private void SQLPushTable() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int result = 0;
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String insertStatement = "update tax set taxname = ?, taxtype = ?, ammount = ?, fedTaxExempt = ?, stateTaxExempt = ?,"
					+ "statePATaxExempt = ?, SSCTaxExempt = ?, medicareTaxExempt = ?, localTaxExempt = ? where id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(insertStatement);
			
			for(int i = 0; i < taxNum-1; i++) {
				//Tax Name
				System.out.println(Name.get(i).getText());
				pstmt.setString(1,Name.get(i).getText());
				//Tax Type
				pstmt.setInt(2, TaxType.get(i).getSelectedIndex());
				//Tax Amount
				pstmt.setDouble(3, Double.parseDouble(Ammount.get(i).getText()));
				//Tax ID
				pstmt.setInt(10, Integer.parseInt(ID.get(i).getText()));

				result += pstmt.executeUpdate();
				
				dialog.dispose();
			}
			
			System.out.println("Updated " + result + " Entries.");
			
			
		}
	};
		
		
	static ActionListener addBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SQLAddTax(EMPID);
			} catch (Exception SQLTaxAdd) {
				SQLTaxAdd.printStackTrace();
			}
			
		}

		private void SQLAddTax(int empID) throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int result = 0;
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String insertStatement = "INSERT INTO "
					+ "tax(name,type,ammout,fedTaxExempt,stateTaxExempt,statePATaxExempt,SSCTaxExempt,medicareTaxExempt,localTaxExempt)"
					+ "Values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(insertStatement);
			
			pstmt.setString(1, "Name");
			pstmt.setInt(2, 0);
			pstmt.setDouble(3, 00.00);
			pstmt.setBoolean(4, false);
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);
			pstmt.setBoolean(7, false);
			pstmt.setBoolean(8, false);
			pstmt.setBoolean(9, false);
			
			result = pstmt.executeUpdate();
			
			System.out.println("Added " + result + "entry(s) to table.");
		}
	};
	
	static ActionListener removeBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SQLRemoveTax();
			} catch (Exception SQLTaxRemove) {
				SQLTaxRemove.printStackTrace();
			}
			
		}

		private void SQLRemoveTax() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int result = 0;
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String insertStatement = "Delete from tax where id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(insertStatement);
			
			
			
		}
	};
	
	//This will take all the current taxes for the selected employee and return them as a ResultSet...
	private static void SQLPullTax(int empID) throws Exception {
		
		String[] SQL;
		SQL = Config.PullSQLConfig();
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		String insertStatement = "select * from tax where employee_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(insertStatement);
		
		pstmt.setInt(1, empID);
		
		rs = pstmt.executeQuery();		
		
		return;
	}
	
	//This will set taxNum to the ammount of taxes for that employee in order to dynamically create the tax table
private static int SQLTaxNum(int empID) throws Exception {
		ResultSet Rs;
		String[] SQL;
		SQL = Config.PullSQLConfig();
		int TaxNum = 0;
		
		System.out.println("Querrying DB for selected Employee");
		
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
		
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		
		String insertStatement = "select * from tax where employee_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(insertStatement);
		
		pstmt.setInt(1, empID);
		
		Rs = pstmt.executeQuery();
		
		while(Rs.next()) {
			TaxNum++;
		}
		
		
		return TaxNum;
	}

}
