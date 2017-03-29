package chap14;

/**
 * Class:3003 OOP
 * FileName: ScreenSaver.java
 * Date last modified: October 14, 2016
 * Notes: The ScreenSaver class is a subclass of JPanel.
 * 		  this program can draw a growing spiral. Composed 
 * 		  of 6 straight edges that grow every second.
 * 		  the delay in the timer is adjusted by scrolling
 * 		  up and down to increase or decrease the delay.
 */

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenSaver extends JPanel implements ActionListener, MouseWheelListener {
	
	private int x[]=new int[60];//x coordinate for the line
	private int y[]=new int[60];//y coordinate for the line
	private int numOfPoints=0;//goes up to 60
	private int radius=10;// radius that the polygon will have
	private int delay=1000;//used to delay the timer
	private Timer timer=null;// javax.swing.timer 
	
	//constructor
	public ScreenSaver(){
		timer=new Timer(1000, this); // the interval is 1000 milliseconds
		timer.start();
		addMouseWheelListener(this);
	}
	
	//event handling
	public void actionPerformed(ActionEvent e){
		//Update x,y,numOfPoints
		addPoint();
		repaint();//refreshes the window
	}
	
	//draw edges
	public void paintComponent(Graphics g){
		//clear the background and draw the lines
		g.clearRect(0,0,(int)getSize().getWidth(),(int)getSize().getHeight());
		g.drawPolyline(x, y, numOfPoints);	
	}
	
	//changes delay in timer
	public void mouseWheelMoved(MouseWheelEvent e){ 
		 
	   /*  e.getWheelRotation() returns the number of "clicks" the mouse wheel was rotated.
		*  Negative values if the mouse wheel was rotated up/away from the user,
		*  and positive values if the mouse wheel was rotated down/ towards the user
		*/
		int scroll = e.getWheelRotation();
				 
		delay = delay + scroll*10;
		
		if(delay>10) {
			timer.setDelay(delay);
		}
	}
	
	//get the coordinates to draw the next line
	private void addPoint() {
		int centerX = (int) (getSize().getWidth()/2);//find the center of the panel
		int centerY = (int) (getSize().getWidth()/2);//find the center of the panel
		
		//to find the next point in the spiral
		double X = centerX + Math.cos(numOfPoints*Math.PI/3)* radius;
		double Y = centerY + Math.sin(numOfPoints*Math.PI/3)* radius;
		
		this.x[numOfPoints] = (int)X;
		this.y[numOfPoints] = (int)Y;
		numOfPoints ++;
		
		//increase radius
		radius +=3;
		
		if(numOfPoints == 60) {
			numOfPoints = 0;
			radius = 10;
		}	
	}
}
