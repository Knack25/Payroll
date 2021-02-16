package gui;

import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	public MyFrame(String title){
		
		JFrame frame = new JFrame();
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(420,420);
		this.setVisible(true);
		
		frame.getContentPane().setBackground(new Color(0,100,255));
	}
	
}
