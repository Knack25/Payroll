package Gradle_Payroll.gui.Employee;

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
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Tax;
import Gradle_Payroll.fileIO.Config;
import Gradle_Payroll.sql.MySQL;

public class Edit_Tax_Table {

	static int taxNum = 0;
	final static String dollar = "$";
	final static String percent = "%";

	static int EMPID;

	static JDialog dialog;
	static JLabel taxNameL, taxIDL, taxTypeL, taxFedExemptL, taxSateExemptL, taxStatePAExemptL, taxSSCExemptL,
			taxMedicareExemptL, taxLocalExemptL, YTDL;
	static JComboBox<String> taxType;
	static JPanel panel;
	static Tax tax;
	static ResultSet rs;
	static List<JTextField> Ammount;
	static List<JTextField> Name;
	static List<JComboBox<String>> TaxType;
	static List<JCheckBox> FederalExempt;
	static List<JCheckBox> StateExempt;
	static List<JCheckBox> StatePAExempt;
	static List<JCheckBox> SSCExempt;
	static List<JCheckBox> MedicareExempt;
	static List<JCheckBox> LocalExempt;
	static List<JLabel> ID;
	static Calendar cal;

	protected static void createDialog(int empID) throws Exception {

		EMPID = empID;
		cal = Calendar.getInstance();

		JButton saveB = new JButton("Save");
		saveB.addActionListener(saveBListener);

		JButton addB = new JButton("Add");
		addB.addActionListener(addBListener);

		JButton removeB = new JButton("Remove");
		removeB.addActionListener(removeBListener);

		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);

		dialog.setLocation(50, 50);
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		SQLPullTax(empID);
		taxNum = MySQL.SQLTaxNum(empID) + 1;
		dialog.setSize(1400, 700);

		Ammount = new ArrayList<JTextField>();
		Name = new ArrayList<JTextField>();
		TaxType = new ArrayList<JComboBox<String>>();
		FederalExempt = new ArrayList<JCheckBox>();
		StateExempt = new ArrayList<JCheckBox>();
		StatePAExempt = new ArrayList<JCheckBox>();
		SSCExempt = new ArrayList<JCheckBox>();
		MedicareExempt = new ArrayList<JCheckBox>();
		LocalExempt = new ArrayList<JCheckBox>();
		ID = new ArrayList<JLabel>();

		int autoX = 0;
		int autoY = 0;
		c.weightx = 0.09;
		for (int i = 0; i <= taxNum; i++) {

			// On the first run, build the labels
			if (i == 0) {
				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel idL = new JLabel("#");
				panel.add(idL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel nameL = new JLabel("Name");
				panel.add(nameL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel taxTypeL = new JLabel("Type");
				panel.add(taxTypeL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel ammountL = new JLabel("Ammount");
				panel.add(ammountL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel fedExemptL = new JLabel("Federal");
				panel.add(fedExemptL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel stateExemptL = new JLabel("State");
				panel.add(stateExemptL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel statePAExemptL = new JLabel("State 2");
				panel.add(statePAExemptL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel SSCExemptL = new JLabel("SSC");
				panel.add(SSCExemptL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel medicareExemptL = new JLabel("Medicare");
				panel.add(medicareExemptL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel localExemptL = new JLabel("Local");
				panel.add(localExemptL, c);
				autoX++;
				c.gridx = autoX;

				c.fill = GridBagConstraints.HORIZONTAL;
				JLabel sysID = new JLabel("ID");
				sysID.setVisible(false);
				panel.add(sysID, c);

				autoX = 0;
				autoY++;
			}
			// On the last run, add the save button
			else if (i == taxNum) {
				autoX = 15;
				c.gridx = autoX;
				c.gridy = autoY;

				panel.add(saveB, c);
				autoX++;
				c.gridx = autoX;
				panel.add(addB, c);
				autoX++;
				c.gridx = autoX;
				panel.add(removeB, c);

				autoX++;
				autoY++;
			}
			// On all the middle runs, build the table
			else {
				rs.next();

				c.gridx = autoX;
				c.gridy = autoY;
				panel.add(new JLabel(String.valueOf(i)), c);
				autoX++;
				c.gridx = autoX;

				// Name of the tax
				c.fill = GridBagConstraints.HORIZONTAL;
				JTextField name = new JTextField();
				name.setText(rs.getString("taxname"));
				name.setColumns(3);
				panel.add(name, c);
				Name.add(name);
				autoX++;
				c.gridx = autoX;

				// Type of the tax
				JComboBox<String> taxtype = new JComboBox<String>();
				taxtype.addItem(dollar);
				taxtype.addItem(percent);
				if (rs.getInt("taxtype") == 1) {
					taxtype.setSelectedIndex(1);
				} else {
					taxtype.setSelectedIndex(0);
				}
				panel.add(taxtype, c);
				TaxType.add(taxtype);
				autoX++;
				c.gridx = autoX;

				// Amount of the tax
				JTextField ammnt = new JTextField();
				ammnt.setColumns(3);
				ammnt.setText(String.valueOf(rs.getDouble("ammount")));
				panel.add(ammnt, c);
				Ammount.add(ammnt);
				autoX++;
				c.gridx = autoX;

				// Federal Exempt
				JCheckBox fedExempt = new JCheckBox();
				fedExempt.setSelected(rs.getBoolean("fedTaxExempt"));
				if (autoY < 7) {
					fedExempt.setVisible(false);
				} else {
					fedExempt.setVisible(true);
				}
				panel.add(fedExempt, c);
				FederalExempt.add(fedExempt);
				autoX++;
				c.gridx = autoX;

				// State Exempt
				JCheckBox stateExempt = new JCheckBox();
				stateExempt.setSelected(rs.getBoolean("stateTaxExempt"));
				if (autoY < 7) {
					stateExempt.setVisible(false);
				} else {
					stateExempt.setVisible(true);
				}
				panel.add(stateExempt, c);
				StateExempt.add(stateExempt);
				autoX++;
				c.gridx = autoX;

				// State PA Exempt
				JCheckBox statePAExempt = new JCheckBox();
				statePAExempt.setSelected(rs.getBoolean("state2TaxExempt"));
				if (autoY < 7) {
					statePAExempt.setVisible(false);
				} else {
					statePAExempt.setVisible(true);
				}
				panel.add(statePAExempt, c);
				StatePAExempt.add(statePAExempt);
				autoX++;
				c.gridx = autoX;

				// SSC Exempt
				JCheckBox sscExempt = new JCheckBox();
				sscExempt.setSelected(rs.getBoolean("SSCTaxExempt"));
				if (autoY < 7) {
					sscExempt.setVisible(false);
				} else {
					sscExempt.setVisible(true);
				}
				panel.add(sscExempt, c);
				SSCExempt.add(sscExempt);
				autoX++;
				c.gridx = autoX;

				// Medicare Exempt
				JCheckBox medicareExempt = new JCheckBox();
				medicareExempt.setSelected(rs.getBoolean("medicareTaxExempt"));
				if (autoY < 7) {
					medicareExempt.setVisible(false);
				} else {
					medicareExempt.setVisible(true);
				}
				panel.add(medicareExempt, c);
				MedicareExempt.add(medicareExempt);
				autoX++;
				c.gridx = autoX;

				// Local Exempt
				JCheckBox localExempt = new JCheckBox();
				localExempt.setSelected(rs.getBoolean("localTaxExempt"));
				if (autoY < 7) {
					localExempt.setVisible(false);
				} else {
					localExempt.setVisible(true);
				}
				panel.add(localExempt, c);
				LocalExempt.add(localExempt);
				autoX++;
				c.gridx = autoX;

				JLabel taxID = new JLabel(rs.getString("id"));
				taxID.setVisible(false);
				panel.add(taxID, c);
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
				taxNum = MySQL.SQLTaxNum(EMPID);
				SQLPushTable();
			} catch (Exception SQLPush) {
				SQLPush.printStackTrace();
			}

		}

		private void SQLPushTable() throws Exception {
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int result = 0;
			int result2 = 0;

			System.out.println("Querrying DB for selected Employee");

			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);
			Connection connytd = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String insertStatement = "update tax set taxname = ?, taxtype = ?, ammount = ?, fedTaxExempt = ?, stateTaxExempt = ?,"
					+ "state2TaxExempt = ?, SSCTaxExempt = ?, medicareTaxExempt = ?, localTaxExempt = ? where id = ?";

			String insertStatementytd = "update tax_ytd set name = ? where id = ?";

			PreparedStatement pstmt = conn.prepareStatement(insertStatement);
			PreparedStatement pstmtytd = connytd.prepareStatement(insertStatementytd);

			for (int i = 0; i < taxNum; i++) {
				// Tax Name
				System.out.println(Name.get(i).getText());
				pstmt.setString(1, Name.get(i).getText());
				// Tax Type
				pstmt.setInt(2, TaxType.get(i).getSelectedIndex());
				// Tax Ammount
				pstmt.setDouble(3, Double.parseDouble(Ammount.get(i).getText()));
				// Federal Tax Exempt
				pstmt.setBoolean(4, FederalExempt.get(i).isSelected());
				// State Tax Exempt
				pstmt.setBoolean(5, StateExempt.get(i).isSelected());
				// State PA Tax Exempt
				pstmt.setBoolean(6, StatePAExempt.get(i).isSelected());
				// SSC Tax Exempt
				pstmt.setBoolean(7, SSCExempt.get(i).isSelected());
				// Medicare Tax Exempt
				pstmt.setBoolean(8, MedicareExempt.get(i).isSelected());
				// Local Tax Exempt
				pstmt.setBoolean(9, LocalExempt.get(i).isSelected());
				// Tax ID
				pstmt.setInt(10, Integer.parseInt(ID.get(i).getText()));

				result += pstmt.executeUpdate();

				pstmtytd.setString(1, Name.get(i).getText());
				pstmtytd.setInt(2, Integer.parseInt(ID.get(i).getText()));

				result2 += pstmtytd.executeUpdate();
			}

			System.out.println("Updated " + result + " Entries.");
			conn.close();
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

			Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String insertStatement = "INSERT INTO "
					+ "tax(employee_id,taxname,taxtype,ammount,fedTaxExempt,stateTaxExempt,state2TaxExempt,SSCTaxExempt,medicareTaxExempt,localTaxExempt,primaryTax)"
					+ "Values(?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(insertStatement);

			pstmt.setInt(1, empID);
			pstmt.setString(2, "Name");
			pstmt.setInt(3, 0);
			pstmt.setDouble(4, 00.00);
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);
			pstmt.setBoolean(7, false);
			pstmt.setBoolean(8, false);
			pstmt.setBoolean(9, false);
			pstmt.setBoolean(10, false);
			pstmt.setBoolean(11, false);

			result = pstmt.executeUpdate();

			System.out.println("Added " + result + "entry(s) to tax table.");

			Connection conn2 = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

			String insertStatement2 = "INSERT INTO " + "tax_ytd(employee_id,name,ammount,year)" + "Values(?,?,?,?)";

			PreparedStatement pstmt2 = conn2.prepareStatement(insertStatement2);

			pstmt2.setInt(1, EMPID);
			pstmt2.setString(2, "Name");
			pstmt2.setDouble(3, 00.00);
			pstmt2.setDouble(4, cal.get(Calendar.YEAR));

			result = pstmt2.executeUpdate();

			System.out.println("Added " + result + "entry(s) to tax_ytd table.");

			System.out.println("Refreshing GUI...");
			dialog.dispose();
			createDialog(empID);
		}
	};

	static ActionListener removeBListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Tax_Table_Remove.createDialog(EMPID);
				dialog.dispose();
				createDialog(EMPID);
			} catch (Exception SQLTaxRemove) {
				SQLTaxRemove.printStackTrace();
			}
		}
	};

	// This will take all the current taxes for the selected employee and return
	// them as a ResultSet...
	private static void SQLPullTax(int empID) throws Exception {

		String[] SQL;
		SQL = Config.PullSQLConfig();

		System.out.println("Querrying DB for selected Employee");

		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];

		Connection conn = DriverManager.getConnection(DATABASE_URL, SQL[3], SQL[4]);

		String insertStatement = "select * from tax where employee_id = ?";

		PreparedStatement pstmt = conn.prepareStatement(insertStatement);

		pstmt.setInt(1, empID);

		rs = pstmt.executeQuery();

		return;
	}
}
