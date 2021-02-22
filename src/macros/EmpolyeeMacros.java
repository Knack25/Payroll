package macros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import data.Address;
import data.Employee;
import data.GeneralData;
import data.Name;
import data.Tax;


import macros.IOMacros;

public class EmpolyeeMacros {
	
	public int FileLineNum(String fileName) {

	    int count = 0;

	    try {
	      // create a new file object
	      File file = new File(fileName);

	      // create an object of Scanner
	      // associated with the file
	      Scanner sc = new Scanner(file);

	      // read each line and
	      // count number of lines
	      while(sc.hasNextLine()) {
	        sc.nextLine();
	        count++;
	      }
	      System.out.println("Total Number of Lines: " + count);

	      // close scanner
	      sc.close();
	    } catch (Exception e) {
	      e.getStackTrace();
	    }
	    return count;
	  }

	public Employee createEmployee(int iD, String fName,String mName,String lName, String status, String streetAddr, String cityAddr,String stateAddr,double zipAddr,
			String telnum, String email, char sex,String ssn,String jobTitle, String dOB, String dOH, String dOT, double salary, double regPay,
			double regHour, double otPay, double otHour, double ptoPay, double ptoHour, double localTaxCode,
			double addStateTax, double addFedTax, double vacationTimeUsed, double vacationTimeRemaining,
			String department, double[] yTD,Tax[] tax) throws IOException  {
	
		
		
		Address myAdderess = new Address();
		Name myName = new Name();
		
		myAdderess.setStreet(streetAddr);
		myAdderess.setCity(cityAddr);
		myAdderess.setState(stateAddr);
		myAdderess.setZip(zipAddr);
		
		myName.setFirst(fName);
		myName.setMiddle(mName);
		myName.setLast(lName);
		
		
		Employee myEmployee = new Employee(iD, myName, status, myAdderess, telnum, email, sex, ssn, jobTitle, dOB, dOH, dOT, salary, regPay, regHour, otPay,
				otHour, ptoPay, ptoHour, localTaxCode, addStateTax, addFedTax, vacationTimeUsed, vacationTimeRemaining, department, yTD, tax);
		
		
		String s =  String.valueOf(GeneralData.numOfEmployees) + 1;
		
		
		//Save the employee to a file
		IOMacros.saveEmployee(myEmployee, s);
		
		return myEmployee;
	}
	
	public Map<String, Employee> loadEmployees() throws IOException, ClassNotFoundException {
		
		
		Map<String, Employee> emp = new TreeMap<>();
		
		//FileInputStream fileName = new FileInputStream("employees.txt");
		
		Employee temp = new Employee();
		
		
		
		
		
	
		
		int i = FileLineNum("employees.txt");
		
		
		for(Integer j = 0; j<i;j++) {
			
			String s = j.toString();
			temp = IOMacros.readEmployee(s);
			
			String specific_line_text = Files.readAllLines(Paths.get("myfile.txt")).get(j);
			
			emp.put(specific_line_text,  temp);
			
		}
		return emp;
	}
	
	
	
	
	
}
