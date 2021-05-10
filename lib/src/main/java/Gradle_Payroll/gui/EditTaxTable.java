package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Tax;
import Gradle_Payroll.fileIO.Config;

public class EditTaxTable {
	
	static int taxNum = 0;

	static JDialog dialog;
	static JLabel taxNameL,taxIDL,taxTypeL,taxFedExemptL,taxSateExemptL,taxStatePAExemptL,taxSSCExemptL,taxMedicareExemptL,taxLocalExemptL,exemptionsL,YTDL;
	static JComboBox<String> taxType;
	static JPanel panel;
	static Tax tax;
	static ResultSet rs;
	
	
	
	protected static void createDialog(int empID) throws Exception{
		
		String dollar = "$";
		String percent = "%";
		
		
		
		
		dialog = new JDialog(null,Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(800,800);
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		SQLPullTax(empID);
		taxNum = SQLTaxNum(empID);
		
		
		List<JTextField> Ammount = new ArrayList<JTextField>();
		List<JTextField> Name = new ArrayList<JTextField>();
		List<JComboBox<String>> TaxType = new ArrayList<JComboBox<String>>();
		List<JCheckBox> FederalExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> StateExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> StatePAExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> SSCExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> MedicareExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> LocalExempt = new ArrayList<JCheckBox>();
		
<<<<<<< HEAD
=======
		setLabels();
//		GridBagConstraints d = new GridBagConstraints();
//		int tmp = 0;
//		d.gridx = tmp;
//		d.gridy = 0;
//	
//		for(int j = 0; j<10; j++) {
//			panel.add(taxNameL,d);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//			panel.add(YTDL);
//			tmp++;
//			d.gridx = tmp;
//		}
		panel.add(taxNameL);
		panel.add(taxTypeL);
		
		panel.add(taxFedExemptL);
		panel.add(taxSateExemptL);
		panel.add(taxStatePAExemptL);
		panel.add(taxSSCExemptL);
		panel.add(taxMedicareExemptL);
		panel.add(taxLocalExemptL);
	
>>>>>>> 4f01ea190bab8d42f7af4b20407b0ac50f863d83
		
		int autoX = 0;
<<<<<<< HEAD
		int autoY = 0;
		for(int i = 0; i < taxNum;i++) {
			rs.next();
=======
		int autoY = 1;
		for(int i = 0; i <= taxNum;i++) {
>>>>>>> 4f01ea190bab8d42f7af4b20407b0ac50f863d83
			
			c.gridx = autoX;
			c.gridy = autoY;
			panel.add(new JLabel(String.valueOf(i)),c);
			autoX++;
			c.gridx = autoX;
			
			//Name of the tax
			JTextField name = new JTextField();
			name.setText(rs.getString("taxname"));
			name.setColumns(3);
			panel.add(name,c);
			Name.add(name);
			autoX++;
			c.gridx = autoX;
			
			//Type of the tax
			JComboBox<String> taxtype = new JComboBox<String>();
			taxtype.addItem(dollar);
			taxtype.addItem(percent);
			if(rs.getString("taxtype") == "%") {
				taxtype.setSelectedIndex(1);
			}else {
				taxtype.setSelectedIndex(0);
			}
			panel.add(taxtype,c);
			TaxType.add(taxtype);
			autoX++;
			c.gridx = autoX;
			
			//Amount of the tax
			JTextField ammnt = new JTextField();
			ammnt.setColumns(3);
			ammnt.setText(String.valueOf(rs.getDouble("ammount")));
			panel.add(ammnt,c);
			Ammount.add(ammnt);
			autoX++;
			c.gridx = autoX;
			
			//Federal Exempt
			JCheckBox fedExempt = new JCheckBox();
			fedExempt.setSelected(rs.getBoolean("fedTaxExempt"));
			panel.add(fedExempt,c);
			FederalExempt.add(fedExempt);
			autoX++;
			c.gridx = autoX;
			
			//State Exempt
			JCheckBox stateExempt = new JCheckBox();
			stateExempt.setSelected(rs.getBoolean("stateTaxExempt"));
			panel.add(stateExempt,c);
			StateExempt.add(stateExempt);
			autoX++;
			c.gridx = autoX;
			
			//State PA Exempt
			JCheckBox statePAExempt = new JCheckBox();
			statePAExempt.setSelected(rs.getBoolean("statePATaxExempt"));
			panel.add(statePAExempt,c);
			StatePAExempt.add(statePAExempt);
			autoX++;
			c.gridx = autoX;
			
			//SSC Exempt
			JCheckBox sscExempt = new JCheckBox();
			sscExempt.setSelected(rs.getBoolean("SSCTaxExempt"));
			panel.add(sscExempt,c);
			SSCExempt.add(sscExempt);
			autoX++;
			c.gridx = autoX;
			
			//Medicare Exempt
			JCheckBox medicareExempt = new JCheckBox();
			medicareExempt.setSelected(rs.getBoolean("medicareTaxExempt"));
			panel.add(medicareExempt,c);
			MedicareExempt.add(medicareExempt);
			autoX++;
			c.gridx = autoX;
			
			//Local Exempt
			JCheckBox localExempt = new JCheckBox();
			localExempt.setSelected(rs.getBoolean("localTaxExempt"));
			panel.add(localExempt,c);
			LocalExempt.add(localExempt);
			
			autoX = 0;
			autoY++;
			
		}
		
		dialog.add(panel);
		
		dialog.setVisible(true);
		
		return;
	}
	
	//This will take all the current taxes for the selected employee and return them as a ResultSet...
	//It will also set taxNum to the ammount of taxes for that employee in order to dynamically create the tax table
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
<<<<<<< HEAD
=======
	private static void setLabels() {
		taxNameL = new JLabel("Tax Name	");
		taxIDL = new JLabel("Tax I.D.	");
		
		taxTypeL = new JLabel("Tax Type");
		taxFedExemptL = new JLabel("Federal Exemption");
		taxSateExemptL = new JLabel("Ohio Exemption");
		taxStatePAExemptL = new JLabel("Pennsylvania Exemption");
		taxSSCExemptL = new JLabel("SSC Exemption");
		taxMedicareExemptL = new JLabel("Medicare Exemption");
		taxLocalExemptL = new JLabel("Local Exemption");
		exemptionsL = new JLabel("Exemptions");
	}
>>>>>>> 4f01ea190bab8d42f7af4b20407b0ac50f863d83

	
}
