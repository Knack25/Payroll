module Payroll {
	exports data;
	exports fileIO;
	exports gui;
	exports main;
	exports sql;

	requires java.desktop;
	requires java.sql;
	requires mysql.connector.java;
	requires org.apache.poi.poi;
}