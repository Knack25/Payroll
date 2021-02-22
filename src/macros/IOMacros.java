package macros;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.Employee;
import data.GeneralData;

public class IOMacros {
		
	public static int saveEmployee(Employee Employee,String fileName) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(fileName+".obj");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		
		oos.writeObject(Employee);
		
		oos.close();
		
		return 0;
	}
	
	public static Employee readEmployee(String fileName) throws IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream(fileName+".obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Employee emp = (Employee) ois.readObject();
		
		
		ois.close();
		
		return emp;
	}
	
	
	
	
public static int saveGeneral(Object General) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("general.obj");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		
		oos.writeObject(General);
		
		oos.close();
		
		return 0;
	}
	
	
public static GeneralData readGeneral() throws IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream("general.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		GeneralData gen = (GeneralData) ois.readObject();
		
		
		ois.close();
		
		return gen;
	}
	
}


