package sql;

import java.sql.*;


public class MySQL {

	public static int Connect() {
		final String DRIVER_NAME = "org.gjt.mm.mysql.Driver";
		final String SERVERNAME = "localhost";
		final String DATABASENAME = "payroll";
		final String DATABASE_URL = "jdbc:mysql://" + SERVERNAME + "/" + DATABASENAME;
		
		String username = "root";
		String password = "anatominen1399";
		
		try(
				
			Connection connection = DriverManager.getConnection(DATABASE_URL,username,password);
				
				
				){
			
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
//		Connection con = null;
//	      try {
//	         con = DriverManager.
//	         getConnection("jdbc:mysql://localhost:3306/payroll?useSSL=false", "Knack25", "Night_Streak25");
//	         System.out.println("Connection is successful !!!!!");
//	      } catch(Exception e) {
//	         e.printStackTrace();
//	      }
		
		return 0;
	}
	
	
	public static int LoadEmployeeTable() {
		
		
		return 0;
	}
	
}
