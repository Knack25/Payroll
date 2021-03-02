package fileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Config {

	public static int SQLConfig() throws Exception {
		
		File file = new File("/Payroll/lib/SQLConfig");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		
		sc.close();
		return 0;
	}
	
	public static int PayrollConfig() throws FileNotFoundException {
		
		File file = new File("/Payroll/lib/PayrollConfig");
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		
		
		sc.close();
		return 0;
	}
	
}
