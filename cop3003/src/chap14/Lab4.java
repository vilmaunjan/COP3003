package chap14;
/**
 * Class:3003 OOP
 * FileName: Lab4.java
 * Date last modified: October 14, 2016
 * Notes: The Lab4 class is a subclass of JFrame.
 * 		  It creates a screensaver object in a 
 * 		  frame.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class Lab4 extends JFrame{

	//constructor
	public Lab4(){
		super("Lab 4");
		ScreenSaver saver=new ScreenSaver();
		setLayout(new BorderLayout());	
		add(saver, BorderLayout.CENTER);
	}
	
	public static void main(String args[]){
		Lab4 lab4=new Lab4();
		lab4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		lab4.setSize( 600, 600 );// set frame size
		lab4.setResizable(true);
		lab4.setVisible( true );// display frame
	}
	
}