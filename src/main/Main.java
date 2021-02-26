package main;

import java.sql.Connection;

import sql.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Connect to the database
		Connection conn = MySQL.Connect();
		
		System.out.println("Successful Connectin to DB!");
		
		
		
		
		//Close the connection
		MySQL.CloseConnection(conn);
	}

}
