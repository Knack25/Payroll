package Gradle_Payroll.gui;

import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Print_Reports {
	static JDialog dialog;

	protected static JDialog createFrame() throws Exception {
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);

		// dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setSize(420, 125);
		dialog.setLocation(50, 50);

		JButton RegisterB = new JButton("Register");
		// addB.addActionListener(addBListener);

		JButton w2B = new JButton("W2 Forms");
		// addB.addActionListener(addBListener);

		JButton payJournalB = new JButton("Pay Journal & Tax Summary");
		// addB.addActionListener(addBListener);

		dialog.setLayout(new FlowLayout());

		JPanel fields = new JPanel();

		dialog.setLayout(new FlowLayout());
		JLabel report = new JLabel("Select Report");

		// TODO: after selecting report, figure out what employee it is, and other
		// credentials that would be needed. Ex. date

		dialog.add(report);
		fields.add(RegisterB);
		fields.add(w2B);
		fields.add(payJournalB);

		dialog.add(fields, FlowLayout.CENTER);

		dialog.setVisible(true);

		return dialog;
	}

};