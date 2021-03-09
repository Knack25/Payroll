package gui;

 import java.awt.BorderLayout;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.sql.Connection;

 import javax.swing.*;

 import sql.MySQL;
 
 
 public class CreateEmployee extends JDialog implements ActionListener{
	 
	 
	 public static void CreateMenu() {
		 JDialog create = new JDialog();
		 create.setLayout(new GroupLayout(null));
		 JLabel create_EmployeeL = new JLabel("Create Employee");
		 JLabel employeeName = new JLabel("Employee Name:");
		 JButton createB = new JButton("Create");
		 JButton deleteB = new JButton("Delete");
		 JTextField enterHere = new JTextField(" ");
		 
		 
		 
		 
		 
		 return;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	 
	 
	 
	 
	 
 }