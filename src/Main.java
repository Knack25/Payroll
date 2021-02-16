import gui.MyFrame;
import gui.main_menu;
import data.Employee;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		MyFrame main_menu = new MyFrame("ACI");
		
		while (main_menu.isVisible() == false) {
			
			System.out.println("Main Menu Closed");
			
		}
		
		
	}

}
