package Gradle_Payroll.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorDialog {
	static JDialog dialog;
	static JButton confirmB;
	static JLabel message;
	
	protected static JDialog createError(String errorMessage)  throws Exception {
		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		confirmB = new JButton("OK");
		confirmB.addActionListener(confirmBListener);
		message = new JLabel(errorMessage);
		
		JPanel north,south;
		north = new JPanel();
		south = new JPanel();
		north.add(message);
		south.add(confirmB);
		dialog.add(north,BorderLayout.NORTH);
		dialog.add(south,BorderLayout.SOUTH);
		
		
		dialog.setSize(400,200);
		dialog.setVisible(true);
		 return dialog;
	}
	
	static ActionListener confirmBListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.dispose();
		}
	};

}
