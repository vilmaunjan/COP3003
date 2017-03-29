/**
 * Class:3003 OOP
 * Date last modified: September 5, 2016
 * Notes: This class can represent 40-digit integers. 
 * 		  In the HugeInteger class, a 40-element int array is used. Each element stores a digit.
 * 		  Other methods are implemented to make comparisons and also to make calculations.
 */

package chap8;

public class HugeInteger {
	
	 private static final int NUM_DIGITS = 40; //constant variable, size of the HugeInteger
	 
	 private int[] digits = new int[NUM_DIGITS]; // 40 integers
	 
	 private boolean positive; //sign of the integers
	 
	 //Constructor
	 public HugeInteger (String num) {
		 
		 int len=num.length(); // length of num
		 
		 int start=0; // where the first digit starts
		 
		 if(num.charAt(0)=='-'){ //if in the first position is a negative sign
			 start=1;
			 positive=false;
		 } else {
			 positive=true;
		 }
		 
		 //The least significant digit (LSD) is at digits[NUM_DIGITS-1] 
		 for(int i=len-1,j=0;i>=start;i--,j++){
			 digits[NUM_DIGITS-1-j]=num.charAt(i)-(int)'0';
		 } 
	 }
	
	//Methods for comparison
	 public boolean isEqualTo(HugeInteger hi) {
		 
		boolean result = false; //returns if it is equal to or not
		
		for(int i = 0; i <= NUM_DIGITS-1; i++){
			//determines if the sign of the first one is the same as the sign of the second number
			if(positive != hi.positive) {
				break;
				
			} else {
				//determines if the value of the first number is the same as the values of the second number
				if(digits[i] == hi.digits[i])
					result = true;
				else {
					result = false;
					break;
				}
			}
		}
		return result;		
	 }
	 
	 //determines if the first number is greater than the second number
	 public boolean isGreaterThan(HugeInteger hi) {
		 boolean result = false; // returns if it is greater than or not
		 
		 for(int i = 0; i <= NUM_DIGITS-1; i++){
			//positive = true means it is positive, positive = false means negative
			 if (positive == true && hi.positive == false) {
				 result = true;
				 break;
			 } else if (positive == false && hi.positive == true) {
				 result = false;
				 break; 
			 } else if (positive == false && hi.positive == false) {
				if(digits[i] < hi.digits[i]){
					result = true;
					break;
				} else if (digits[i] > (hi.digits[i])){
					result = false;
					break;
				}
			 } else if (positive == true && hi.positive == true) {
				 if(digits[i] > hi.digits[i]){
						result = true;
						break;
					} else if (digits[i] < (hi.digits[i])){
						result = false;
						break;
					}
			 }
		 }
		 return result;
	 }
	 //determines if two numbers are different
	 public boolean isNotEqualTo(HugeInteger hi) {
		  boolean result = false;
		  
		  if (isEqualTo(hi) == false)
			  result = true;
		  
		  return result;
	 }
	 
	 //determines if the first number is less than the second number
	 public boolean isLessThan(HugeInteger hi) {
		  boolean result = false;
		  
		  if(isGreaterThan(hi) == false && isEqualTo(hi) == false)
			  result = true;
		  
		   return result;
	 }
	 
	 //determines if the first number is greater than or equal to the second number
	 public boolean isGreaterThanOrEqualTo(HugeInteger hi) {
		  boolean result = false;
		  
		  if (isGreaterThan(hi) == true || isEqualTo(hi) == true)
				  result = true;
		  
		  return result;
	 }
	 
	 //determines if the first number is less than or equal to the second number
	 public boolean isLessThanOrEqualTo(HugeInteger hi) {
		  boolean result = false;
		 
		  if (isLessThan(hi) == true || isEqualTo(hi) == true)
				  result = true;
		  
		  return result;
	 }
	 
	 //Methods for calculation
	 //adds the array digits without considering the sign or which number is bigger
	 	 private static int[] addArrayDigits(int [] array1, int [] array2) {
	 		 
		 int[] results=new int[NUM_DIGITS]; // array that holds the answer
		 
		 int carry = 0; //carry used when the number is greater than 9
		 
		 for(int i = NUM_DIGITS-1; i >= 0; i--){
			 
				results[i] = array1[i] + array2[i] + carry;
				
				//test if carry is 0 or 1
				if(results[i] > 9) {
					results[i] %= 10;
					carry = 1;
				} else {
					carry = 0;
				}
			}
		 return results; 
	 }
	 	 
	 //adds the array digits without considering the sign or which number is bigger
	 private static int[] subtractArrayDigits(int [] array1, int [] array2) {
		 
		int[] results=new int[NUM_DIGITS]; // array that holds the answer
		 
		int borrow = 10; // value that needs to be borrowed from the digit at the left

   		int temp = 0; // value that holds digit after

   		int left = 0; //can take 1 or 0 value depending on the previous digit

   		for(int i = NUM_DIGITS-1; i >= 0; i--){
   			//if the first number is less than the second number
     		if ((array1[i] - left )< array2[i]){
     				temp = array1[i] + borrow - left;
          			left = 1;
         	} else {
               		temp = array1[i] - left;
               		left = 0;
            }
     		results[i] = temp - array2[i];
      	}
   		return results;
    }
	 
	// adds the value in “hi” to the current object 
	 public void add(HugeInteger hi) {

		 int[] num1=new int[NUM_DIGITS]; // array used to place the number is going to be added first
		 
		 int[] num2=new int[NUM_DIGITS]; // array used to place the number is going to be added second
		 
		 num1 = digits; // assigned to current value
		 
		 num2 = hi.digits; // assigned to hi object
		 		 
		 //(+h1)+(+h2)
		 if (positive == true && hi.positive == true) {
			 digits = addArrayDigits(num1, num2); // answer will be placed in this array
			 		 
		//(-h1)+(-h2)=-h1-h2
		 } else if (positive == false && hi.positive == false) {
			 digits = addArrayDigits(num1, num2);
		
		//(+h1)+(-h2)=h1-h2
		 } else if (positive == true && hi.positive == false) {
			 
			 hi.negate(); // negate in order to compare the absolute values
			 
			 if (isLessThan(hi) == true){
					num1 = hi.digits;
					num2 = digits;				
					negate(); // negate because the answer is false
					digits = subtractArrayDigits(num1, num2);
					hi.negate(); //negate to return to the original sign
					
			 } else {
					num1 = digits;
					num2 = hi.digits;
					digits = subtractArrayDigits(num1, num2);
					hi.negate(); //negate to return to the original sign
				}
			 				
		 //(-h1)+(h2)=-h1+h2
		 } else if (positive == false && hi.positive == true) {
              negate();
			  if (isLessThan(hi) == true){ 
				  	num1 = hi.digits;
				  	num2 = digits;	
			  } else {
				  	num1 = digits;
				  	num2 = hi.digits;
				  	negate();
			  }
			  digits = subtractArrayDigits(num1, num2);
		 }
	 }
	 
	// subtracts the value in “hi” from the current object
	 public void subtract(HugeInteger hi) {
		 
		 int[] num1=new int[NUM_DIGITS]; // array used to place the number is going to be subtracted first
		 
		 int[] num2=new int[NUM_DIGITS]; // array used to place the number is going to be subtracted second
		 
		 //(+h1)-(+h2) = h1-h2	
		 if (positive == true && hi.positive == true) {
			if (isLessThan(hi) == true){
				num1 = hi.digits;
				num2 = digits;
				negate();
				digits = subtractArrayDigits(num1, num2);
			} else {
				num1 = digits;
				num2 = hi.digits;
				digits = subtractArrayDigits(num1, num2);
			}
			 
		 //(+h1)-(-h2)=h1+h2	 
		 } else if (positive == true && hi.positive == false) {
			 num1 = digits;
			 num2 = hi.digits;
			 digits = addArrayDigits(num1, num2);
			 
		 //(-h1)-(+h2) = -h1-h2, add the numbers and keep the sign from h1	 
		 } else if (positive == false && hi.positive == true) {
			 num1 = digits;
			 num2 = hi.digits;
			 digits = addArrayDigits(num1, num2);
			 
		 //(-h1)-(-h2)=-h1+h2
		 } else if (positive == false && hi.positive == false) {
			//negate values to be able to compare
			 negate();
			 hi.negate();
			 if (isLessThan(hi) == true){
				 	num1 = hi.digits;
					num2 = digits;
					digits = subtractArrayDigits(num1, num2);
					hi.negate();
					
				} else {
					num1 = digits;
					num2 = hi.digits;
					negate();
					digits = subtractArrayDigits(num1, num2);
					hi.negate();
				}
		 }
	 }
	
	// multiplies the current object by the value in "hi", cannot call add or substract
	 public void multiply(HugeInteger hi) {
		 
		 // stores the values of digits or hi.digits
		 int[] h1 = new int[NUM_DIGITS];
		 
		 int[] h2 = new int[NUM_DIGITS];
		 
		 int carry = 0; //used as a carry when multiplying
		 
		 int carrySum = 0; //used as a carry for the partial sums
		 
		 int lenh1 = 0; //used to calculate the actual size of the numbers
		 
		 int lenh2 = 0; //used to calculate the actual size of the numbers
		 
		 int[] answer = new int[NUM_DIGITS]; // results will be added with the other results
		 		 
		 //determines the sign of the multiplication
		 if(positive == true && hi.positive == true){
			 positive = true;
		 } else if (positive == true && hi.positive == false) {
			 positive = false;
		 } else if (positive == false && hi.positive == true) {
			 positive = false;
		 } else if (positive == false && hi.positive == false) {
			 positive = true;
		 }
		 
		 //calculates the actual size for h1
		 for(int i = 0; i <= NUM_DIGITS-1; i++) {
				//breaks when it gets to the first non 0 digit
				if(digits[i] != 0) {
					lenh1 = NUM_DIGITS - i;
					break;
				}
			}
		 
		 //calculates the actual size for h2
		 for(int i = 0; i <= NUM_DIGITS-1; i++) {
				//break when it gets to the first non 0 digit
				if(hi.digits[i] != 0) {
					lenh2 = NUM_DIGITS - i;
				//System.out.println("lenh2 " + lenh2+"i= "+i);
					break;
				}
			}
		 
		 //assigns h1 and h2 depending on their lengths
		 if (lenh1 <= lenh2){
			h1=digits; //the shortest number
			h2=hi.digits; // the longest number
		 } else {
			h1=hi.digits; //the shortest number
			h2=digits;    // the longest number
		 }
		 
		 for (int i = 0; i < NUM_DIGITS; ++i) // Using for loop we are initializing answer to zero
		 {
		     answer[i] = 0;
		 }

		 for(int i = NUM_DIGITS-1; i >= NUM_DIGITS-lenh1; i--){ //for the shortest number
			 
			 int k = i;
			 
			 for(int j =  NUM_DIGITS-1; j >= NUM_DIGITS-lenh2-1; j--){ // for the longest number
				 
				 int result = h1[i] * h2[j] + carry; // result holds the actual value of the multiplication
				 
				 //if the value is greater than 9
				 if (result > 9){
					 int temp = result;
					 result %= 10;
					 carry = (temp-result) / 10;
				 } else{
					 carry = 0;
				 }
				
				 answer[k] = result + answer[k] + carrySum; //each digit in the same position is going to be added
				 
				 //if the sum of the digits is greater than 9
				 if( answer[k] > 9) {
				     answer[k] %= 10;
				     carrySum = 1; // carry goes to the digit on the left
				 } else {
				     carrySum = 0;
				 }
				 k = k -1; // the partial sum need to be in a stair form, k is in charge of that
			 }
		 }
		 //the last carry
		 //answer [0] = carry;
		 digits = answer; // holds the final answer
		 
	 }
	 
	// flip the sign 
	 public void negate() {
		 positive = !positive;
	 }	 
		 
	// the returned string does not include the leading zeros 
	 public String toString() {
		 
		String str =  ""; // will hold the digits
		
		int i; //counter for the loop
		
		for(i = 0; i <= NUM_DIGITS-1; i++) {
			//break when it gets to the first non 0 digit
			if(digits[i] != 0)
				break;
		}
		
		//puts together the rest of the digits as a String
		for(int j = NUM_DIGITS-1; j >= i ; j--){			
					str = digits[j] + str;
		}
				
		//if it is a negative number
		if(positive==false) {
			str = '-' + str; //put a negative sign in the first position
		}
		return str;	
	} 	  
}