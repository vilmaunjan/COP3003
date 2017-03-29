package paint;

/**
 * Class:3003 OOP
 * FileName: MyShape.java
 * Date last modified: November 2, 2016
 * Comments: interface that defines the shapes methods
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public interface MyShape {

	//Unimplemented methods
	public abstract void draw(Graphics g);//draw shapes
	
	public abstract void setColor(Color color);//sets the color

	public abstract void setFilled(boolean filled);//determines if drawing a filled shape
	
	public abstract boolean isSelected(int x, int y);//determines if shape is selected
	
	public abstract void move(int dx, int dy);//moves shape to new location


}
