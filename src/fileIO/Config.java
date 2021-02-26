package fileIO;

import java.io.File;
import java.util.Scanner;

public class Config {

	public static int SQLConfig() throws Exception {
		
		File file = new File("/Payroll/lib/SQLConfig");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		
		return 0;
	}
	
}
