package Gradle_Payroll.gui;

import java.awt.Dialog;

import javax.swing.JDialog;

public class Dialog_Template {

	// https://docs.oracle.com/javase/8/docs/api/javax/swing/JDialog.html#JDialog-java.awt.Dialog-

	// Create a new internal frame.
	protected static JDialog createDialog() {
		JDialog dialog = new JDialog();

		return dialog;
	}

	protected static JDialog createModalDialog() {
		JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		return dialog;
	}

}
