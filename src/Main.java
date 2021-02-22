import macros.IOMacros;

import java.io.IOException;
import data.GeneralData;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		GeneralData genData = new GeneralData();
		
		
		try {
			genData = IOMacros.readGeneral();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println(GeneralData.numOfEmployees);
//		MyFrame main_menu = new MyFrame("ACI");
//		
//		while (main_menu.isVisible() == false) {
//			
//			System.out.println("Main Menu Closed");
//			
//		}
		
		
		
		
		
		
		try {
			IOMacros.saveGeneral(genData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
