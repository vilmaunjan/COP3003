/**
 * Class:3003 OOP
 * Date last modified: September 11, 2016
 * Notes: This class, GradTA is a subclass of GradStudent. The constructor calls the GradStudent class
 * 		  constructor and also reads data from its readData method. Then it prints the Data.
 * 		  The scanner is commented because is not needed, because it uses the one declared in Student,
 * 		  however, it might not work when trying to input one of the input values. I have it as a comment
 * 	  	  just in case.
 */
package chap9;

//import java.util.Scanner;

public class GradTA extends GradStudent {
	
	//Scanner input  = new Scanner(System.in);

	//instance variables
	private String taCourse;
	
	private String taInstructor;
	
	private Date hireDate;
	
	
	//Constructor
	public GradTA () {
		super();
		readData(); // calls the readData method
	}
	
	//methods
	//Reads data from the keyboard
	private void readData() {
		if(gender == 'f') {
			System.out.print("Please input her TA course: ");
			taCourse = input.nextLine();
			System.out.print("Please input her TA instructor: ");
			taInstructor = input.nextLine();
			System.out.println("Please input her birth date:");
		} else if (gender == 'm') {
			System.out.print("Please input his TA course: ");
			taCourse = input.nextLine();
			System.out.print("Please input his TA instructor: ");
			taInstructor = input.nextLine();
			System.out.println("Please input his hire date:");
		}
		hireDate = new Date(); // creates a Date object
		System.out.println();
		
	}
	
	// Prints the data entered by keyboard
	public void print() {
		super.print(); // calls the print method from GradStudent class
		if(gender == 'f') {
			System.out.println("Her TA course is " + taCourse + ".");
			System.out.println("Her TA instructor is " + taInstructor + ".");
			System.out.println("Her hire date is " + hireDate);
		} else if (gender == 'm') {
			System.out.println("His TA course is " + taCourse + ".");
			System.out.println("His TA instructor is " + taInstructor + ".");
			System.out.println("His hire date is " + hireDate);
		}
	}
}
