package paint;

/**
 * Class:3003 OOP
 * FileName: MyRectangle.java
 * Date last modified: November 2, 2016
 * Comments: Class that draw rectangles
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.IOException;

public class MyRectangle extends Rectangle implements MyShape {
	
	private Point2D point1, point2;//points used when mouse is pressed and released
	boolean filled = false;//determines if shape is filled or not
	boolean selected = false;//determines if shape is selected
	public Color color = Color.BLACK;//sets default color of shape to black
	int x,y,width, height;//declare variables to draw shape
	
	//CONSTRUCTOR
	public MyRectangle(Point2D point1, Point2D point2, Color color, boolean filled) {
		super();
		this.point1 = point1;
		this.point2 = point2;
		this.color = color;
		this.filled = filled;
		
		int x1 = (int) point1.getX(); //saves x coordinate as int
		int y1 = (int) point1.getY(); //saves y coordinate as int
		int x2 = (int) point2.getX(); //saves x coordinate as int
		int y2 = (int) point2.getY(); //saves y coordinate as int
		x = Math.min(x1, x2); //finds the minimum value for x
		y = Math.min(y1, y2); //finds the minimum value for y
		width = Math.abs(x2-x1); //calculates the width for the shape
		height = Math.abs(y2-y1); //calculates the height for the shape
	}
	//draw method draws the shapes
	@Override
	public void draw(Graphics g) {
		g.setColor(color); //set the color defined my the comboBox
		if(filled == true) //if checkBox is selected fill rectangle
			g.fillRect(x,y,width, height);
		else if (filled == false)
			g.drawRect(x,y,width, height);
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
		System.out.println("xs: " + xs + " ys: " + ys + " x: " + x + " y: " + y +" width: " + width + "height: " + height);
		MyRectangle rect = new MyRectangle(point1, point2, color, filled);
		if(rect.contains(xs, ys)) {
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
	
	public String toString(){
		return String.format("x=%d, y=%d, width=%d, height=%d\n", x,y,width, height);
	}

}
