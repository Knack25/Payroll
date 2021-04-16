package Gradle_Payroll.fileIO;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Config {

	public static String[] SQLConfig() throws Exception {
		
		Scanner sc = new Scanner(new File("C:\\Users\\natha\\Desktop\\SQLConfig.txt"));
		
		String[] output = {null,null,null,null,null};
		int i = 0;
		
		while (sc.hasNextLine()) {
			output[i] = sc.nextLine();
			i++;
			//System.out.println(sc.nextLine());
		}
		
		sc.close();
		return output;
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
