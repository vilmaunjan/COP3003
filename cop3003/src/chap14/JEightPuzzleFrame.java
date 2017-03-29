package chap14;
/**
 * Class:3003 OOP
 * FileName: JEightPuzzleFrame.java
 * Date last modified: October 10, 2016
 * Notes: The JEightPuzzleFrame class is a subclass of JFrame.
 * 		  This class gets an image from the main method and extracts
 * 		  the image content to be placed in button array. A 2d array
 * 		  called board is used to organize the buttons, and provide
 * 		  the index of the buttons. 
 */

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JEightPuzzleFrame extends JFrame {
	
	private JButton buttons[] = new JButton[8]; //array of buttons is created
	
	private int board[][] = {{0,1,2},{5,6,3},{4,7,8}}; //the board has the positions of the buttons
	
	private JPanel emptyButton;// will be used to have an empty space in the frame
	
	private static int width; //width of the total image

	private static int height; // height of the total image
	
	// where to start extracting the icon in the image
	private static int leftTopX = 0;
	private static int leftTopY = 0;

	// the size of the icon
	private static int widthIcon = 0;
	private static int heightIcon = 0;
	
	//this method reads the image and uses the sizes to create an icon
	private Icon extractIcon(String path) {
		// reads the image into a BufferedImage object
		BufferedImage image=null;
		try{
			image = ImageIO.read(this.getClass().getResource(path));
		
		}catch(IOException e){
			System.err.println("Image not found");
			System.exit(1);
		}
		
		width = image.getWidth();//gets the width of the total image
		height = image.getHeight(); //gets the height of the total image
		
		widthIcon = width/3; //width that the icon is going to have
		heightIcon = height/3; //height that the icon is going to have
		
		// allocates another BufferedImage object whose size is
		// the same as the one of the wanted icon
		BufferedImage part = new BufferedImage(	widthIcon, heightIcon, BufferedImage.TYPE_4BYTE_ABGR);

		// copies the data from "image" to "part"		
		for(int x=0;x<widthIcon;x++){
			for(int y=0;y<heightIcon;y++){
				part.setRGB(x,y, image.getRGB(x+leftTopX, y+leftTopY));
			}
		}

		// creates an icon whose content is already in "part"
		ImageIcon icon = new ImageIcon();
		icon.setImage(part);

		// returns to the caller
		return icon;
		
	}//end of extractIcon
	
	private JEightPuzzleFrame(String title, String imagePath){
		super(title); //sets the title
		
		setLayout(new GridLayout(3, 3, 0, 0)); //set frame layout
		
		emptyButton = new JPanel();	// creates an empty button adding an empty JPanel
		
		ButtonHandler handler = new ButtonHandler(); // creates button handler
		
		// used to determine leftTopX and leftTopY
		for (int i = 0; i < 8; i++){
			int j = 0,k = 0;
                    if (i < 3) {
                    	j = 0;
                    	k = i;
                    }
                    else if (i > 2 && i < 6) {
                    	j = 1;
                    	k = i - 3;
                    }
                    else if (i > 5) {
                    	j = 2;
                    	k = i - 6;
                    }
                    
		leftTopX = k*widthIcon; //x coordinate that indicates starting point for image
		leftTopY = j*heightIcon; //y coordinate that indicates starting point for image
        
		buttons[i] = new JButton(extractIcon(imagePath)); //creates a button with the icon
		buttons[i].addActionListener(handler); // register event handlers
		}
		
		//add emptyButton and buttons to the frame
		for(int r=0; r<3; r++) {
			for(int c=0; c<3; c++) {
				if (r==0 && c==0)
					add(emptyButton);
				else
					add(buttons[board[r][c]-1]); //adds buttons to the frame
			}
		}
	
	} //end of constructor
	
	//private inner class for event handling
	private class ButtonHandler implements ActionListener {
	
		int rowB = 0; //rowBoard will hold the row in which the clicked button is in the board
		int colB = 0; //columnBoard will hold the column in which the clicked button is in the board
	
		//process buttons events
		@Override
		public void actionPerformed(ActionEvent event) {
			checkButton(event); //calls the method checkButton
			checkSolution(); //calls the method checkSolution
		}
		
		//checks if the button is movable or not
		private void checkButton(ActionEvent event) {
		
			boolean foundEmptyButton = false; //sets to true when emptyButton is found

			//get the index of the clicked Button
			JButton button = (JButton) event.getSource();
			int buttonIndex = Arrays.asList(buttons).indexOf(button);
			int boardValue = buttonIndex + 1;
		
			//gets the row and column of the button in the board
			for(int r=0; r<3; r++) {
				for(int c=0; c<3; c++) {
					if(boardValue == board[r][c]) {
						rowB = r;
						colB = c;
					}
				}
			}

			//look up the element '0' in the board and swap it with the selected button
			try {
				//left neighbor
				if(colB > 0 && foundEmptyButton == false) {
					if (board[rowB][colB-1] == 0){
						board[rowB][colB-1] = boardValue;
						foundEmptyButton = true;
					}
				}
			
				//right neighbor
				if(colB < 2 && foundEmptyButton == false) {
					if (board[rowB][colB+1] == 0){
						board[rowB][colB+1] = boardValue;
						foundEmptyButton = true;
					}
				}
			
				//top neighbor
				if(rowB > 0 && foundEmptyButton == false) {
					if (board[rowB-1][colB] == 0){
						board[rowB-1][colB] = boardValue;
						foundEmptyButton = true;
					}
				}
			
				//bottom neighbor
				if(rowB < 2 && foundEmptyButton == false) {
					if (board[rowB+1][colB] == 0) {
						board[rowB+1][colB] = boardValue;
						foundEmptyButton = true;
					}	
				}
			
				//if the emptyButton was found
				if (foundEmptyButton == true){
					board[rowB][colB] = 0;
					updateButtons();
				}
			
			} catch (Exception e) {
				System.err.println("Is not movable");
			}

		}//end of checkButton
	
		//this method removes all the components from the JFrame and adds them in a different order
		private void updateButtons() {

			remove(emptyButton); // removes the JFrame

			//removes all the buttons
			for (JButton btn : buttons) {
				remove(btn);
			}
		
			for(int r=0; r<3; r++) {
				for(int c=0; c<3; c++) {
					if (board[r][c]==0)
						add(emptyButton); //adds the JPanel
					else
						add(buttons[board[r][c]-1]); //adds buttons to the frame
				}
			}
			validate(); //validates
		} //end of update button    
	 
		//check if the buttons are in the same order as the array solution
		private void checkSolution() {
		
			int solution[][] = {{1,2,3},{4,5,6},{7,8,0}}; //has the desired solution of the puzzle
   
			//compares the elements of the array
			if (Arrays.deepEquals(board, solution)){
				JOptionPane.showMessageDialog(null, "Congratulations",null, JOptionPane.INFORMATION_MESSAGE);
				shuffle(board); //calls the shuffle method to reorganize the board
			}
    	}//end of checkSolution
		
		//gives a random order to an array
		private void shuffle(int[][] array) {
		
	    	Random random = new Random();

	    	for (int i = array.length - 1; i >= 0; i--) {
	        	for (int j = array[i].length - 1; j >= 0; j--) {
	            	int m = random.nextInt(i + 1);
	            	int n = random.nextInt(j + 1);

	            	int temp = array[i][j];
	            	array[i][j] = array[m][n];
	            	array[m][n] = temp;
	        	}
	    	}
	    	updateButtons();// call updateButtons to add them to the JFrame
		}//end of shuffle
	}// end of buttonHandler
	
	public static void main( String[] args ) {
		//JEightPuzzleFrame puzzleFrame = new JEightPuzzleFrame("Eight Puzzle","FGCU_logo.png");
		JEightPuzzleFrame puzzleFrame = new JEightPuzzleFrame("Eight Puzzle","bunny.jpg");
		puzzleFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		puzzleFrame.setSize( width+10, height+40 ); // set frame size
		puzzleFrame.setVisible( true ); // display frame
		puzzleFrame.setResizable(false);// it is not resizable
	}//end of main

}//end of class JEightPuzzleFrame
