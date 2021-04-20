package Gradle_Payroll.fileIO;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;




public class Config {
	
	public static int PushSQLConfig(String[] input) {
		
		FileOutputStream outstream;
		try {
			outstream = new FileOutputStream("SQLConfig.txt");
			ObjectOutputStream obOutStream = new ObjectOutputStream(outstream);
			obOutStream.writeObject(input);
			obOutStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		return 0;
	}

	public static String[] PullSQLConfig() throws Exception {
		
		String[] output = {null,null,null,null,null};
		
		
		try{
			
		System.out.println("Opening Object Input Stream...");
		ObjectInputStream objinstream = new ObjectInputStream(new FileInputStream("SQLConfig.txt"));  
		byte[] bt = new byte[22];
		
		
		System.out.println("Reading in input stream.");
		objinstream.readFully(bt);
		String input = new String(bt);
		
		System.out.println(input);
		
		System.out.println("Splitting input String");
		output = input.split(input);
		
		for(int i = 0; i < output.length;i++) {
			
			System.out.println(output[i]);
			
		}
		
		/*
		String fileName = "SQLConfig.txt";
		public InputStream getResourceAsStream(String fileName);
		//Scanner sc = new Scanner(new File("C:\\Users\\natha\\Desktop\\SQLConfig.txt"));
		Scanner sc = new Scanner(new File("~/Gradle_Payroll/SQLConfig.txt"));
		
		
		int i = 0;
		
		while (sc.hasNextLine()) {
			output[i] = sc.nextLine();
			i++;
			//System.out.println(sc.nextLine());
		}
		
		sc.close();*/
		objinstream.close();
		return output;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
		
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
