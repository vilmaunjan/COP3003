package chap14;
/**
 * Class:3003 OOP
 * Date last modified: October 17, 2016
 * Notes: This program converts from arabic numerals to roman numerals and viceversa
 * 		  as soon as the key is released the conversion appears in the other texField.
 * 		  Also inputs are validated. In the arabic textField only numbers are allowed
 * 		  to be typed and only from 1 until 3999.
 * 		  In the Roman numeral textField, only the roman digits are allow, not numbers
 * 		  or any other letters. 
 */
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JConverterFrame extends JFrame {

	private JLabel arabicLbl;//label
	
	private JTextField arabicTxt;//arabic numbers are written here
	
	private JLabel  romanLbl;//label
	
	private JTextField romanTxt;//roman numbers are written here
	
	//Used to convert from arabic to roman
	private static int[] arabic= {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9 ,5, 4, 1};
	//Used to convert from arabic to roman
	private static String[] roman= {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX" ,"V", "IV", "I"};
	
	private String str = "";//used to store arabic numerals
	
	private String word = "";//used to store Roman numerals
	
	int num = 0;//holds arabic number when a key is pressed
	
	/*Constructor: Title is passed as a parameter.
	 * 			   Layout is set as gridLayout.
	 * 			   textFields and labels are created and added to the frame.
	 * 			   Anonymous inner classes are used in the key Listeners for each textField.
	 */
	private JConverterFrame(String title){
		super(title); //sets the title
		setLayout(new GridLayout(2, 2, 0, 7)); //set frame layout
		
		arabicLbl =  new JLabel("Arabic Numeral");
		add(arabicLbl);//add label to frame
		
		arabicTxt = new JTextField(4);
		add(arabicTxt);//add label to frame
		
		romanLbl =  new JLabel("Roman Numeral");
		add(romanLbl);//add label to frame
		
		romanTxt = new JTextField(15);
		add(romanTxt);//add label to frame
		
		arabicTxt.addKeyListener(new KeyListener() { //anonymous inner class

		//Unnecessary method
		@Override
		public void keyPressed(KeyEvent e) { 
		}

		//this method handles the key events
		@Override
		public void keyReleased(KeyEvent e) {
		
			int keyCode = e.getKeyCode();//keyCode
			//when backspace is pressed
			if (keyCode == (char) KeyEvent.VK_BACK_SPACE) {
				//if the string length is greater than 1, erase the last key pressed
				if (str.length()>1){
					str = str.substring (0,str.length()-1);
				}else if(str.length()==1){ // else set to blank the strings and the textField
					word = ""; //set blank the Roman string
					str = ""; //set blank the arabic string
					romanTxt.setText(""); //set blank the textField
				}
			//if the values entered are from 0 until 9, are saved in the str string
			}else if (keyCode >=48 && keyCode <=57){
				num =Integer.parseInt(KeyEvent.getKeyText(keyCode));
				str = str + num;
			} else {//else the key presses is erased from the textField and never added to the string
				arabicTxt.setText(arabicTxt.getText().substring (0,str.length()));
			}
			//if the arabic string is between 1 and 3999, is converted to roman numeral				
			if((Integer.parseInt(str) >=1 && Integer.parseInt(str)<=3999) ) {
				word = convertToRoman(Integer.parseInt(str));
				romanTxt.setText(word);
			} else if(str == ""){
				word = "";
				romanTxt.setText(word);
			}else {	
				str = str.substring (0,str.length()-1);
				arabicTxt.setText(str);
			}
		}

		//Unnecessary method
		@Override
		public void keyTyped(KeyEvent e) {
		}
		}); //end of anonymous inner class
		
		romanTxt.addKeyListener(new KeyListener() {//anonymous inner class used for romanTxt

			//unnecessary method
			@Override
			public void keyPressed(KeyEvent e) {
			}
			//when the key is released, event handling occurs
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();//gets key released

				if (keyCode == (char) KeyEvent.VK_BACK_SPACE) {	
					//if word length is greater than 1, erased last key pressed
					if (word.length()>1){
						word = word.substring (0,word.length()-1);
					}else if(word.length()==1){ //else set everything to blank
						word = "";//set roman string to blank
						str = "";//set arabic string to blank
						arabicTxt.setText("");//set textField to blank
					}				
				//else if keyCode are I,V,X,L,C,D,M save in the word string 
				} else if(keyCode == 73 || keyCode == 86 ||
						keyCode == 88 || keyCode == 76 || keyCode == 67 ||
						keyCode == 68 || keyCode == 77) {
					String s = KeyEvent.getKeyText(keyCode);
					word = word + s;
				} else {
					romanTxt.setText(romanTxt.getText().substring (0,word.length()));
				}
				//only convert to Arabic if the word length is greater than 0
				if(word.length()>0 ) {
					str = toString(convertToArabic(word));
					arabicTxt.setText(str);
				} else if(word == ""){ //else set to blank
					romanTxt.setText("");
				}
			}
			//used to pass the int value to string received from convertToArabic method
			private String toString(int n) {
				String s="";
				s = s + n;
				return s;
			}
			
			//Unnecessary method
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});
		
	}
	//method converts to roman
	private static String convertToRoman(int num) {
		String letters = "";//roman numerals will be stored here
		int N = num;
		
		for(int i = 0; i< arabic.length; i++) {
			while(N>=arabic[i]){//while number is greater that arabic value in array
				letters += roman[i];//add roman numeral to letters
				N -= arabic[i];//subtract arabic numeral from number
			}
		}
		return letters;
	}
	
	//method converts to arabic
	private static int convertToArabic(String num) {
         
         int i = 0;//position of the string number
         int arabic = 0;  // Arabic numeral that has been converted so far
         //while i is less than the num length
         while (i < num.length()) {
         
            char letter = num.charAt(i);        // Letter at current position in string.
            int number = letterToNumber(letter);  // Numerical equivalent of letter.
            
            if (number < 0)
               throw new NumberFormatException("Illegal character " + letter + " in roman numeral.");
               
            i++;  // Move on to next position in the string
            
            if (i == num.length()) {//it is the last letter of the string, therefore only adds
            						//the number to the single letter to arabic
               arabic += number;
            } else {
                  /* Else it looks at the next letter in the string.  If it has a larger Roman numeral
                   * equivalent than number, then the two letters are counted together as
                   * a Roman numeral with value (nextNumber - number).*/
               int nextNumber = letterToNumber(num.charAt(i));
               if (nextNumber > number) {
                  //Combines the two letters to get one value
                  arabic += (nextNumber - number);
                  i++;//moves to the next position
               }
               else {
                  arabic += number; // Adds the value of the one letter onto the number.
               }
            }
            
         }// end while
         
         if (arabic > 3999)
            throw new NumberFormatException("Roman numeral must have value 3999 or less.");
            
         return arabic;
	}
	
	/*finds the integer valus that conresponds to the roman numeral and returns -1 if
	 * it is not a legal roman numeral.
	*/
	private static int letterToNumber(char letter) {
		switch (letter) {
        	case 'I':  return 1;
        	case 'V':  return 5;
        	case 'X':  return 10;
        	case 'L':  return 50;
        	case 'C':  return 100;
        	case 'D':  return 500;
        	case 'M':  return 1000;
        	default:   return -1;
		}
	}
	
	//main method
	public static void main( String[] args ) {
		JConverterFrame converterFrame = new JConverterFrame("Roman <--> Arabic");
		converterFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		converterFrame.setSize( 400, 100 ); // set frame size
		converterFrame.setVisible( true ); // display frame
		converterFrame.setResizable(false);// it is not resizable
		
	}//end of main
}//end of class

