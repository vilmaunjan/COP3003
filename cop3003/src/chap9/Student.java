/**
 * Class:3003 OOP
 * Date last modified: September 11, 2016
 * Notes: This class  Student reads the student data from the console using the readData method
 * 		  called by the constructor and then prints the Data using the print method. This is a 
 * 		  parent class
 */

package chap9;

import java.util.Scanner;

public class Student {
	
	Scanner input  = new Scanner(System.in);

	//Instance variables
	private String name;
	
	private int ssn;
	
	private int numOfCourses;
	
	private  Date birthDate;
	
	protected char gender; //all classes that are related (inherited) can see this variable
	
	//Constructor
	public Student () {
		readData();
		
	}
	
	//methods
	//Reads data from the keyboard
	private void readData() {
		
		System.out.print("Please input the name: ");
		name = input.nextLine();
		System.out.print("Please input the ssn: ");
		ssn = input.nextInt();
		System.out.print("Male/Female (m/f): ");
		gender = input.next().trim().charAt(0);
		if(gender == 'f') {
			System.out.print("How many courses she is taking: ");
			numOfCourses = input.nextInt();
			System.out.println("Please input her birth date:");
			birthDate = new Date();
			
		} else if (gender == 'm') {
			System.out.print("How many courses he is taking: ");
			numOfCourses = input.nextInt();
			System.out.println("Please input his birth date:");
			birthDate = new Date(); // creates a Date object
			
		}
	}
	
	// Prints the data entered by keyboard
	public void print() {
		System.out.println("The information you input was");
		System.out.println(name + "'s ssn is " + ssn + ".");
		if (gender == 'f') {
			System.out.println("She is taking " + numOfCourses + " courses.");
			System.out.println("Her birth date is " + birthDate);
		} else if (gender == 'm') {
			System.out.println("He is taking " + numOfCourses + " courses.");
			System.out.println("His birth date is " + birthDate);
		}
		
	}
	
}
