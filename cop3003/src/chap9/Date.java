/**
 * Class:3003 OOP
 * Date last modified: September 11, 2016
 * Notes: This class reads the month, day, and year from the user input (when it is called by
 * 		  the other classes) and displays it in the date form mm/dd/yyyy
 */
package chap9;

import java.util.Scanner;

public class Date {
	
	Scanner input  = new Scanner(System.in);

	//Data members
	private int month;
	
	private int day;
	
	private int year;
	

	//Constructor
	public Date() {
		readData();
	}
	
	//methods
	//Reads data from the keyboard
	private void readData() {
		System.out.print("\tPlease input the month: ");
		month = Integer.parseInt(input.nextLine());
		System.out.print("\tPlease input the day: ");
		day = Integer.parseInt(input.nextLine());
		System.out.print("\tPlease input the year: ");
		year = Integer.parseInt(input.nextLine());
	}
	
	public String toString() {
		return String.format("%d/%d/%d", month,day,year);
		
	}
}
