/**
 * Class:3003 OOP
 * Date last modified: September 11, 2016
 * Notes: This class, GradStudent is a subclass of Student. The constructor calls the Student class
 * 		  constructor and also reads data from the readData method. Then it prints the Data.
 */

package chap9;

import java.util.Scanner;

public class GradStudent extends Student {
	
	Scanner input  = new Scanner(System.in);

	//instance variables
	private String researchTopic;
	
	private String advisor;
	
	//Constructor
	public GradStudent() {
		super();
		readData(); // calls the readData method of this class
	}
	
	//methods
	//Reads data from the keyboard
	private void readData() {
			
		if(gender == 'f') {
			System.out.print("Please input her research topic: ");
			researchTopic = input.nextLine();
			System.out.print("Please input her research advisor: ");
			advisor = input.nextLine();
		} else if (gender == 'm') {
			System.out.print("Please input his research topic: ");
			researchTopic = input.nextLine();
			System.out.print("Please input his research advisor:");
			advisor = input.nextLine();
		}
		
	}
	// Prints the data entered by keyboard	
	public void print() {
		super.print(); // calls the print method from the student class
		if(gender == 'f') {
			System.out.println("Her research topic is " + researchTopic + ".");
			System.out.println("Her advisor is " + advisor + ".");
		} else if (gender == 'm') {
			System.out.println("His research topic is " + researchTopic + ".");
			System.out.println("His advisor is " + advisor + ".");
		}
	}
}
