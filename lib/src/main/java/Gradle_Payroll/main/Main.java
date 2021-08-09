package Gradle_Payroll.main;




import Gradle_Payroll.gui.Main_Menu;
import Gradle_Payroll.sql.YTD_Lookup;

public class Main {

	public static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					YTD_Lookup.startupCheck();
				} catch (Exception startup) {
					startup.printStackTrace();
				}
				
				Main_Menu.createAndShowGUI();
				
			}
		});
	}
}
