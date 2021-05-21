package Gradle_Payroll.gui;

import javax.swing.JInternalFrame;

public class Frame_Template {

	// Create a new internal frame.
	protected static JInternalFrame createFrame() {
		JInternalFrame frame = new JInternalFrame();
		frame.setVisible(true); // necessary as of 1.3

		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		return frame;
	}
}
