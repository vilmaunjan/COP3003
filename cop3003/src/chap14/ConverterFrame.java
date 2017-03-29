package chap14;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConverterFrame extends JFrame implements KeyListener {

	private JLabel arabicLbl;//label
	
	private JTextField arabicTxt;//arabic numbers are written here
	
	private JLabel  romanLbl;//label
	
	private JTextField romanTxt;//roman numbers are written here
	
	//Used to convert from arabic to roman
	private static int[] arabic= {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9 ,5, 4, 1};
	
	private static String[] roman= {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX" ,"V", "IV", "I"};
	
		
	private static String romanNum="";
	
	private int arabicNum=0;
	
	private ConverterFrame(String title){
		super(title); //sets the title
		setLayout(new GridLayout(2, 2, 0, 7)); //set frame layout
		
		arabicLbl =  new JLabel("Arabic Numeral");
		add(arabicLbl);
		
		arabicTxt = new JTextField(4);
		add(arabicTxt);
		
		romanLbl =  new JLabel("Roman Numeral");
		add(romanLbl);
		
		romanTxt = new JTextField(15);
		add(romanTxt);
		
		arabicTxt.addKeyListener(this);
		romanTxt.addKeyListener(this);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
				
		try {
			arabicNum = Integer.parseInt(arabicTxt.getText());
			
			if (arabicNum > 3999) {
				arabicTxt.setText(arabicTxt.getText().substring(0,arabicTxt.getText().length() - 1));
				arabicNum = Integer.parseInt(arabicTxt.getText());	
	        }
					
			convertToRoman(arabicNum);
			romanTxt.setText(romanNum);
			
		} catch (NumberFormatException exception) {
				arabicTxt.setText(arabicTxt.getText().substring(0,arabicTxt.getText().length() - 1));
				romanTxt.setText(romanTxt.getText().substring(0,romanTxt.getText().length() - 1));
		//		arabicTxt.setText("");
		//        romanTxt.setText("");
			} 
				
			try {
		
			romanNum = romanTxt.getText();
			
	//		convertToArabic(romanNum);
			
			//if(CharMatcher.anyOf("HIOSX").matchesAllOf(romanNum))
			if(romanNum.matches("[IVXLCDM]+")){
			
			

				convertToArabic(romanNum);
				//arabicTxt.setText(arabicNum.toString());
			}else{
				romanTxt.setText(romanTxt.getText().substring(0,romanTxt.getText().length() - 1));
			}
				
				
			} catch (Exception exception) {
				
			}
			

			int i = 5;
			String strI = "" + i;
			


	}		
	
		
	
	private static String convertToRoman(int num) {
		
		romanNum = "";
		if (num < 1 || num > 3999) {
            return "Invalid Roman Number Value";
        }
		
		for(int i = 0; i< arabic.length; i++) {
			while(num>=arabic[i]){
				romanNum += roman[i];
				num -= arabic[i];
				
			}
		}
		
		return romanNum;
	}
	
	private static int convertToArabic(String num) {
         
         int i = 0;//position of the string number
         int arabic = 0;  // Arabic numeral converted.
         
         while (i < num.length()) {
         
            char letter = num.charAt(i); // Letter at current position in string.
            int number = letterToNumber(letter); // Numerical equivalent of letter.
            
            if (number < 0)
               throw new NumberFormatException("Illegal character \"" + letter + "\" in roman numeral.");
               
            i++;  // Move on to next position in the string
            
            if (i == num.length()) {
                  // There is no letter in the string following the one we have just processed.
                  // So just add the number corresponding to the single letter to arabic.
               arabic += number;
            } else {
                  // Look at the next letter in the string.  If it has a larger Roman numeral
                  // equivalent than number, then the two letters are counted together as
                  // a Roman numeral with value (nextNumber - number).
               int nextNumber = letterToNumber(num.charAt(i));
               if (nextNumber > number) {
                     // Combine the two letters to get one value, and move on to next position in string.
                  arabic += (nextNumber - number);
                  i++;
               }
               else {
                     // Don't combine the letters.  Just add the value of the one letter onto the number.
                  arabic += number;
               }
            }
            
         }  // end while
         
         if (arabic > 3999)
            throw new NumberFormatException("Roman numeral must have value 3999 or less.");
            
         return arabic;

	}
	
	
	private static int letterToNumber(char letter) {
        // Find the integer value of letter considered as a Roman numeral.  Return
        // -1 if letter is not a legal Roman numeral.  The letter must be upper case.
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



	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main( String[] args ) {
		ConverterFrame converterFrame = new ConverterFrame("Roman <--> Arabic");
		converterFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		converterFrame.setSize( 400, 100 ); // set frame size
		converterFrame.setVisible( true ); // display frame
		converterFrame.setResizable(false);// it is not resizable
		
	}//end of main
}
