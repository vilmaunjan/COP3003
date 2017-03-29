package paint;

/**
 * Class:3003 OOP
 * FileName: MyEllipse.java
 * Date last modified: November 2, 2016
 * Comments: Class that draw ellipses
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class MyElipse extends Ellipse2D.Float implements MyShape {

	private Point2D point1, point2; //points used when mouse is pressed and released
	boolean filled = false; //determines if shape is filled or not
	boolean selected = false;//determines if shape is selected
	public Color color = Color.BLACK; //sets default color of shape to black
	int x,y,width, height;//declare variables to draw shape
	
	//CONSTRUCTOR
	public MyElipse(Point2D point1, Point2D point2, Color color, boolean filled) {
		super();
		this.point1 = point1;
		this.point2 = point2;
		this.color = color;
		this.filled = filled;
		
		int x1 = (int) point1.getX();//finds the minimum value for x
		int y1 = (int) point1.getY(); //finds the minimum value for y
		int x2 = (int) point2.getX();//calculates the width for the shape
		int y2 = (int) point2.getY();//saves y coordinate as int
			
		x = Math.min(x1, x2);  //calculates the width for the shape
		y = Math.min(y1, y2); //saves y coordinate as int
		width = Math.abs(x2-x1); //calculates the width for the shape
		height = Math.abs(y2-y1);  //calculates the height for the shape
	}

	//draw method draws the shapes
	@Override
	public void draw(Graphics g) {
		g.setColor(color);//set the color defined my the comboBox
		if(filled == true)//if checkBox is selected fill rectangle
			g.fillOval(x, y, width, height);	
		else
			g.drawOval(x,y,width, height);
	}

	//setColor method sets the color chose in the comboBox
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	//setFilled sets if the shape is filled or not
	@Override
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	//If the shape is selected, method returns true
	@Override
	public boolean isSelected(int xs, int ys) {
							
		MyElipse ellip = new MyElipse(point1, point2, color, filled);
		if(ellip.contains(xs, ys)) {
			selected = true;
		}else {
			selected = true;
		}
		return selected;
	}

	//move method sets the new coordinates were the shape is going to be redrawn
	@Override
	public void move(int dx, int dy) {
		x = x + dx;
		y = y + dy;		
	}
}
