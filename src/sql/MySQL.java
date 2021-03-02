package sql;

import java.sql.*;


public class MySQL {

	public static Connection Connect() {
		//final String DRIVER_NAME = "org.gjt.mm.mysql.Driver";
		//Array Of Connection Data
		
		//Driver, IP,DB Name
		final String SERVERNAME = "localhost";
		final String DATABASENAME = "payroll";
		final String DATABASE_URL = "jdbc:mysql://" + SERVERNAME + "/" + DATABASENAME;
		
		String username = "root";
		String password = "anatominen1399";
		
		try(Connection connection = DriverManager.getConnection(DATABASE_URL,username,password);){
			return connection;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		
		return null;
	}
	
	public static int CloseConnection(Connection conn) {
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Closed Connection to Database.");
		return 0;
	}
	
	
	public static int LoadEmployeeTable() {
		
		
		
		return 0;
	}

	
}
