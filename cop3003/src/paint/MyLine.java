package paint;

/**
 * Class:3003 OOP
 * FileName: MyLine.java
 * Date last modified: November 2, 2016
 * Comments: Class that draw lines
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MyLine extends Line2D.Float implements MyShape {
	
	private Point2D point1, point2; //points used when mouse is pressed and released
	boolean selected = false;//determines if shape is selected
	public Color color = Color.BLACK;//sets default color of shape to black
	int x1,y1,x2,y2;//declare variables to draw shape
	private final float EPSILON = 2f;
	
	//CONSTRUCTOR
	public MyLine(Point2D point1, Point2D point2, Color color) {
		super();
		this.point1 = point1;
		this.point2 = point2;
		this.color = color;
		x1 = (int) this.point1.getX();//gets x value
		y1 = (int) this.point1.getY();//gets y value
		x2 = (int) this.point2.getX();//gets x value
		y2 = (int) this.point2.getY();//gets y value

	}
	
    //draw the shape
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x1,y1,x2,y2);
	}

	//sets color for the shape
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	//Empty because is not needed
	@Override
	public void setFilled(boolean filled) {
	}

	//checks if the shape was selected or not
	@Override
	public boolean isSelected(int xs, int ys) {
			
		MyLine line = new MyLine(point1, point2, color);
		if(line.contains(xs, ys)) {
	
			selected = true;
			
		}else {
			selected = true;
			
		}
		return selected;
		
	/*	//Not sure why doesnt work, but I think is the correct logic 
	 	//to get identify if the point is in the line 
		if (xs > Math.min(x1, x2) && xs < Math.max(x1, x2)){
			if(ys > Math.min(y1, y2) && ys < Math.max(y1, y2)){
				float m = Math.round(y2 - y1) / (x2 - x1);	// slope
				float b = y1 - (m * x1);				// constant
				
				if ((Math.abs(ys - (m*xs + b))) < EPSILON){
					System.out.println( "On the Line!");
					selected = true;
					System.out.println("true");
				}else{
					selected = false;
					System.out.println("1");
				}	
			}else{
				selected = false;
				System.out.println("2");
			}
		}else{
			selected = false;
			System.out.println("3");
		}
		System.out.println(selected);
		return selected;
	*/	
	}

	//updates the coordinates to redraw the shape
	@Override
	public void move(int dx, int dy) {
		x1 = x1 + dx;
		y1 = y1 + dy;
		x2 = x2 + dx;
		y2 = y2 + dy;
	}
}
