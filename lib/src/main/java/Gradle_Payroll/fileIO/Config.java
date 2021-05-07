package Gradle_Payroll.fileIO;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;




public class Config {
	
	public static int PushSQLConfig(String[] input) {
		
		FileOutputStream outstream;
		try {
			//outstream = new FileOutputStream("C://Program Files//Payroll//SQLConfig.txt");
			outstream = new FileOutputStream("SQLConfig.txt");
			ObjectOutputStream obOutStream = new ObjectOutputStream(outstream);
			obOutStream.writeObject(input);
			obOutStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		return 0;
	}

	public static String[] PullSQLConfig() throws Exception {
		
		String[] output = {null,null,null,null,null};
		
		Scanner sc = new Scanner(new File("SQLConfig.txt"));
		
		
		int i = 0;
		
		while (sc.hasNextLine()) {
			output[i] = sc.nextLine();
			i++;
			
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
