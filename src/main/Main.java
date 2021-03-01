package main;

import java.sql.Connection;

import javax.swing.JFrame;

import sql.*;
import gui.MainMenu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Connect to the database
		Connection conn = MySQL.Connect();
		
		System.out.println("Successful Connectin to DB!");
		
		JFrame mm = MainMenu.createMainMenu();
		
		while (mm.isActive()) {
			
		}
		
		
		//Close the connection
		MySQL.CloseConnection(conn);
	}

}
