package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gradle_Payroll.data.Tax;

public class EditTaxTable {
	
	//TODO: This will have to be grabbed from SQL for the ammount of tax entries corresponding with the given Employee ID
	static int taxNum = 0;

	static JDialog dialog;
	static JLabel taxNameL,taxIDL,taxTypeL,taxFedExemptL,taxSateExemptL,taxStatePAExemptL,taxSSCExemptL,taxMedicareExemptL,taxLocalExemptL,YTDL;
	static JComboBox<String> taxType;
	static JPanel panel;
	static Tax[] taxes;
	
	protected static JDialog createDialog() throws Exception{
		
		String dollar = "$";
		String percent = "%";
		
		dialog = new JDialog(null,Dialog.ModalityType.APPLICATION_MODAL);
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		
		List<JTextField> Ammount = new ArrayList<JTextField>();
		List<JTextField> Name = new ArrayList<JTextField>();
		List<JComboBox<String>> TaxType = new ArrayList<JComboBox<String>>();
		List<JCheckBox> FederalExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> StateExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> StatePAExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> SSCExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> MedicareExempt = new ArrayList<JCheckBox>();
		List<JCheckBox> LocalExempt = new ArrayList<JCheckBox>();
		
		int autoX = 0;
		int autoY = 0;
		for(int i = 0; i < taxNum;i++) {
			c.gridx = (autoX % 5);
			c.gridy = autoY;
			if((autoX % 5) == 4) {
				autoY++;
			}
			panel.add(new JLabel(String.valueOf(i)),c);
			//Name of the tax
			JTextField name = new JTextField();
			panel.add(name);
			Name.add(name);
			//Type of the tax
			JComboBox<String> taxtype = new JComboBox<String>();
			taxtype.addItem(dollar);
			taxtype.addItem(percent);
			panel.add(taxtype);
			TaxType.add(taxtype);
			//Ammount of the tax
			JTextField ammnt = new JTextField();
			panel.add(ammnt);
			Ammount.add(ammnt);
			//Federal Exempt
			JCheckBox fedExempt = new JCheckBox();
			panel.add(fedExempt);
			FederalExempt.add(fedExempt);
			//State Exempt
			JCheckBox stateExempt = new JCheckBox();
			panel.add(stateExempt);
			StateExempt.add(stateExempt);
			//State PA Exempt
			JCheckBox statePAExempt = new JCheckBox();
			panel.add(statePAExempt);
			StatePAExempt.add(statePAExempt);
			//SSC Exempt
			JCheckBox sscExempt = new JCheckBox();
			panel.add(sscExempt);
			SSCExempt.add(sscExempt);
			//Medicare Exempt
			JCheckBox medicareExempt = new JCheckBox();
			panel.add(medicareExempt);
			MedicareExempt.add(medicareExempt);
			//Local Exempt
			JCheckBox localExempt = new JCheckBox();
			panel.add(localExempt);
			LocalExempt.add(localExempt);
			
			
			autoX++;
		}
		
		dialog.add(panel);
		
		dialog.setVisible(true);
		
		return null;
	}
	

	
}
