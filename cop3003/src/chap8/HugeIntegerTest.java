/**
 * Class:3003 OOP
 * Date last modified: September 5, 2016
 * Notes: This is a test class for the HugeInteger class. A 2D array is used to put values that are going
 * 		  to represent h1 and h2. Comparisons and operations will be made with this values.
 */

package chap8;

// Test Driver for HugeInteger
	public class HugeIntegerTest {
		public static void main(String args[]){
			//test values
			String [][] testInputs = {
				{"987654321", "234567890"},
				{"987654321", "-234567890"},
				{"-987654321", "234567890"},
				{"-987654321", "-234567890"},
				{"234567890", "987654321"},
				{"234567890", "-987654321"},
				{"-234567890", "987654321"},
				{"-234567890", "-987654321"}
			};

			//using HugeInteger methods
			for(String [] ints : testInputs){
				HugeInteger h1 = new HugeInteger(ints[0]);
				HugeInteger h2 = new HugeInteger(ints[1]);

				System.out.println("h1="+h1);
				System.out.println("h2="+h2);
				if(h1.isEqualTo(h2)){
					System.out.println("h1 is equal to h2.");
				}
				if(h1.isNotEqualTo(h2)){
					System.out.println("h1 is not equal to h2.");
				}
				if(h1.isGreaterThan(h2)){
					System.out.println("h1 is greater than h2.");
				}
				if(h1.isLessThan(h2)){
					System.out.println("h1 is less than to h2.");
				}
				if(h1.isGreaterThanOrEqualTo(h2)){
					System.out.println("h1 is greater than or equal to h2.");
				}
				if(h1.isLessThanOrEqualTo(h2)){
					System.out.println("h1 is less than or equal to h2.");
				}
				h1.add(h2);
				System.out.printf("h1=%s\n",h1);
				h1.subtract(h2);
				System.out.println("h1.subtract(h2);");
				h1.subtract(h2);
				System.out.println("h1.subtract(h2);");
				System.out.printf("h1=%s\n",h1);
				h1.add(h2);
				System.out.println("h1.add(h2);");
				h1.multiply(h2);
				System.out.println("h1.multiply(h2);");
				System.out.printf("h1=%s\n\n",h1);
			}
		}
	}

