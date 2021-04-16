package Gradle_Payroll.main;




import Gradle_Payroll.gui.MainMenu;

public class Main {

	public static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainMenu.createAndShowGUI();
				
			}
		});
	}
}
