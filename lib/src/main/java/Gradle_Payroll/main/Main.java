package Gradle_Payroll.main;




import Gradle_Payroll.gui.Main_Menu;

public class Main {

	public static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Main_Menu.createAndShowGUI();
				
			}
		});
	}
}
