package paint;

/**
 * Class:3003 OOP
 * FileName: PaintDemo.java
 * Date last modified: November 2, 2016
 * Comments: Class that launches the app
 */

import javax.swing.JFrame;

public class PaintDemo extends JFrame {
	
	//Main method
	public static void main(String[] args) {
		
		PaintFrame paintFrame = new PaintFrame();
		paintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paintFrame.setSize(600,700); //set frame size
		paintFrame.setVisible(true);
	}//end main
}//end class PaintDemo




