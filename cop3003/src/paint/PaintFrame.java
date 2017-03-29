package paint;
/**
 * Class:3003 OOP
 * FileName: PaintFrame.java
 * Date last modified: November 2, 2016
 * Notes: The PaintFrame class is a subclass of JFrame.
 * 		  It is used to create all the gui components. Also there is an inner private class
 * 		  called drawPanel that extends JPanel and is where the shapes are drawn
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chap14.ScreenSaver;

public class PaintFrame extends JFrame {
	
	int shapesIndex=0; // Used with the shapeCombo to determine what shape was selected
	int colorsIndex=0; //Used with colorCombo to determine what color was selected
	boolean filled = false; //boolean to know if the drawing should be filled or not
	static Point pointStart = null; // point where the mouse was pressed
	static Point pointEnd = null; //points where the mouse was last dragged and released
		
	private JButton undo; //undo one shape
	private JButton clear; //clear the drawPanel
	
	private JComboBox colorCombo;
	Color shapeColor = Color.BLACK;
	private static final String[] colorNames = {"Red","Green","Blue","Black"}; //used for the comboBox
	private static final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK};
	
	
	private JComboBox shapeCombo;
	private static final String[] shapeNames = {"Rectangle","Oval","Edge"};//used for the comboBox
	private static final MyShape[] shapes = new MyShape[100];
	int numOfShapes = 0;
	
	private JCheckBox filledCheckBox;
	private JPanel buttonPanel; //panel to hold buttons, comboBox and checkBox
	private JPanel drawPanel;
	private JLabel statusBar;
	
	
	public PaintFrame() {
		super("Paint");
		
		//Create Buttons
		undo = new JButton("Undo");
		clear = new JButton("Clear");
		
		//Create comboBox
		colorCombo = new JComboBox(colorNames);//create with colorNames
		colorCombo.setMaximumRowCount(4);//display the 4 colors		
		
		shapeCombo = new JComboBox(shapeNames);
		shapeCombo.setMaximumRowCount(3);//display the 3 geometrical shapes
		
		//Create checkBox
		filledCheckBox = new JCheckBox("Filled");
		
		//Create button panel
		buttonPanel = new JPanel();//set up panel
		buttonPanel.setLayout(new FlowLayout());
		
		//Add component to buttonPanel
		buttonPanel.add(undo);
		buttonPanel.add(clear);
		buttonPanel.add(colorCombo);
		buttonPanel.add(shapeCombo);
		buttonPanel.add(filledCheckBox);
		add(buttonPanel, BorderLayout.NORTH);
		
		PaintPanel drawPanel = new PaintPanel();//creates a PaintPanel object
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel, BorderLayout.CENTER);
			
		statusBar = new JLabel("Mouse outsided the panel");
		add(statusBar, BorderLayout.SOUTH);
		
		//Register itemListener for comboBoxes
		colorCombo.addItemListener(new ItemListener () {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					colorsIndex = colorCombo.getSelectedIndex();
					System.out.println(colorCombo.getSelectedItem());
					System.out.println(colorCombo.getSelectedIndex());	
				}
			}
		});
		
		shapeCombo.addItemListener(new ItemListener () {
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					shapesIndex = shapeCombo.getSelectedIndex();
					System.out.println(shapeCombo.getSelectedItem());
					System.out.println(shapeCombo.getSelectedIndex());
				}
			}
		});
		
		//Register itemListener for checkBox
		filledCheckBox.addItemListener( new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(filledCheckBox.isSelected()) {
					filled = true;
					System.out.println(filledCheckBox.isSelected());
					System.out.println(filled);
				}else {
					filled = false;
					System.out.println(filledCheckBox.isSelected());
					System.out.println(filled);
				}
			}
		});
		
		//Register actionListener for buttons
		undo.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("you clicked undo");
				numOfShapes = numOfShapes - 1;
				repaint();
				validate();
				
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("you clicked clear");
				numOfShapes = 0;
				repaint();
				validate();
			}
		});	
	}
	
	private class PaintPanel extends JPanel implements MouseListener, MouseMotionListener{
	
		boolean shapePressed = false;
	
		private PaintPanel () {
			//Create and register mouse listener
			addMouseListener(this);
			addMouseMotionListener(this);
		}
			//Gives the coordinates where the mouse was moved
			@Override
			public void mouseMoved(MouseEvent e) {
				statusBar.setText(String.format("Moved at (%d, %d)",e.getX(),e.getY()));
			}

			//Gives the coordinates where the mouse was clicked
			@Override
			public void mouseClicked(MouseEvent e) {
				statusBar.setText(String.format("Clicked at (%d, %d)",e.getX(),e.getY()));
			}

			//Gives the coordinates where the mouse was entered
			@Override
			public void mouseEntered(MouseEvent e) {
				statusBar.setText(String.format("Entered at (%d, %d)",e.getX(),e.getY()));
			}

			//Gives the coordinates where the mouse was exited
			@Override
			public void mouseExited(MouseEvent e) {
				statusBar.setText("Mouse is out");
			}

			/*Gives the coordinates where the mouse was pressed
			 * If the user press left click in the mouse, it draws the selected shape
			 * If the user press right click in the mouse, it moves the last drawn shape
			 * 
			 */
			@Override
			public void mousePressed(MouseEvent e) {
			
				MyShape shape = null;
			
				shapes[numOfShapes] = shape;
				pointStart = new Point(e.getX(), e.getY());
				pointEnd = pointStart;
			
				if(e.isMetaDown()) {
								
					if (numOfShapes !=0 && shapes[numOfShapes-1].isSelected((int)e.getX(), (int)e.getY())) {
						System.out.println("shape is selected" );
						shapes[numOfShapes-1].move(0, 0);
						shapePressed = true;
						repaint();
					} else {
						System.out.println("was not selected");
					}
				
				} else if (e.isAltDown()) {
					System.out.println("you clicked in the middle");
				} else {
					switch (shapesIndex) {// define shapes[numOfShapes] depending on the selections in the comboBoxes
						case 0:
							shapes[numOfShapes] = new MyRectangle(pointStart,pointEnd,colors[colorsIndex],filled);
							break;
					
						case 1:
							shapes[numOfShapes] = new MyElipse(pointStart,pointEnd,colors[colorsIndex],filled);
							break;
				
						case 2:
							shapes[numOfShapes] = new MyLine(pointStart,pointEnd,colors[colorsIndex]);
							break;
					}
		
					numOfShapes++;//adds the number of Shapes, index where next shape is going to be drawn
					repaint();
					setVisible(true);
				}
			statusBar.setText(String.format("Pressed at (%d, %d)",e.getX(),e.getY()));
		}
		
		/*Gives the coordinates where the mouse was dragged
		 * If the user drags with left click in the mouse, it draws the selected shape
		 * If the user drags with right click in the mouse, it moves the last drawn shape
		 * 
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			
			int dx,dy;
			// update shapes[numOfShapes-1]
			pointEnd = new Point(e.getX(), e.getY());
			
			//If clicks right with the mouse
			if(e.isMetaDown()) {
				if (shapePressed) {
					dx = (int) ( e.getX()-pointStart.getX()); //set the distance to where the shape will move
					dy = (int) ( e.getY()-pointStart.getY()); //set the distance to where the shape will move
					
					shapes[numOfShapes-1].move(dx,dy);//calls move method
					repaint();
					pointStart = pointEnd;
					
				}
			//If clicks in the middle with the mouse	
			} else if (e.isAltDown()) {
				System.out.println("you clicked in the middle");
			//If clicks left with the mouse updates the shapes to the new height and width
			} else {			
				switch (shapesIndex) {// define shapes[numOfShapes] depending on the selections in the comboBoxes
					case 0:
						shapes[numOfShapes-1] = new MyRectangle(pointStart,pointEnd,colors[colorsIndex],filled);
						break;
				
					case 1:
						shapes[numOfShapes-1] = new MyElipse(pointStart,pointEnd,colors[colorsIndex],filled);
						break;
				
					case 2:
						shapes[numOfShapes-1] = new MyLine(pointStart,pointEnd,colors[colorsIndex]);
						break;
				}				
			}
			
			repaint();
			setVisible(true);
			
			statusBar.setText(String.format("Dragged at (%d, %d)",e.getX(),e.getY()));
			//System.out.println("When dragged start: "+pointStart + "end: " + pointEnd);
		}

		/*Gives the coordinates where the mouse was released
		 * If the user releases left click in the mouse, it draws the selected shape
		 * If the user releases right click in the mouse, it moves the last drawn shape
		 * 
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
									
			// Get the final x & y position after the mouse is dragged
			pointEnd = new Point(e.getX(), e.getY());
			int dx,dy;
			if(e.isMetaDown()) {
				if (shapePressed) {
					dx = (int) ( e.getX()-pointStart.getX());
					dy = (int) ( e.getY()-pointStart.getY());
					
					shapes[numOfShapes-1].move(dx,dy);
					repaint();
					pointStart = pointEnd;
					
				}
			} else if (e.isAltDown()) {
				System.out.println("you clicked in the middle");
			} else {
				switch (shapesIndex) {// define shapes[numOfShapes] depending on the selections in the comboBoxes
					case 0:
						shapes[numOfShapes-1] = new MyRectangle(pointStart,pointEnd,colors[colorsIndex],filled);
						break;
				
					case 1:
						shapes[numOfShapes-1] = new MyElipse(pointStart,pointEnd,colors[colorsIndex],filled);
						break;
			
					case 2:
						shapes[numOfShapes-1] = new MyLine(pointStart,pointEnd,colors[colorsIndex]);
						break;
				}
			}
		
			repaint();
			setVisible(true);
				
			statusBar.setText(String.format("Released at (%d, %d)",e.getX(),e.getY()));
		}				
		
		//sets the Panel to white and calls the draw method for each shape in the array
		@Override
		public void paintComponent(Graphics g) {

			g.setColor(Color.WHITE);
			g.fillRect(0,0,getWidth(),getHeight());

			for(int i= 0; i<numOfShapes; i++) {
				shapes[i].draw(g);
			
			}	
		}
	}	
}
