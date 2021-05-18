package Gradle_Payroll.sql;


import java.sql.*;

import Gradle_Payroll.fileIO.Config;


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
			e.printStackTrace();
		}
		System.out.println("Successfully Closed Connection to Database.");
		return 0;
	}
	
	 public static int sqlPullEmpID(String[] name) throws Exception,SQLException{
		 int ID = 0;
		 
		 String[] SQL = Config.PullSQLConfig();
			
		final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
		Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
		 
		String requestStatement = "select * from employee where firstname = ? and lastname = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(requestStatement);
		
		if(name.length == 3) {
			pstmt.setString(1,name[0]);
			pstmt.setString(2, name[2]);
		}
		if(name.length == 2) {
			pstmt.setString(1,name[0]);
			pstmt.setString(2, name[1]);
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		ID = rs.getInt("id");
		
		 return ID;
	 }
	 
	 public static int SQLTaxNum(int empID) throws Exception {
			ResultSet Rs;
			String[] SQL;
			SQL = Config.PullSQLConfig();
			int TaxNum = 0;
			
			System.out.println("Querrying DB for selected Employee");
			
			final String DATABASE_URL = "jdbc:mysql://" + SQL[1] + "/" + SQL[2];
			
			Connection conn = DriverManager.getConnection(DATABASE_URL,SQL[3],SQL[4]);
			
			String insertStatement = "select * from tax where employee_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(insertStatement);
			
			pstmt.setInt(1, empID);
			
			Rs = pstmt.executeQuery();
			
			while(Rs.next()) {
				TaxNum++;
			}
			
			
			return TaxNum;
		}

	
}
