/**
 * Class:3003 OOP
 * FileName: Crate.java
 * Date last modified: September 19, 2016
 * Notes: Class  Crate implements package, and input method is implemented.
 * 		  It is considered an abstract class since one of the methods is 
 * 		  not implemented.
 */
package chap10;

import java.util.Scanner;

abstract public class Crate implements Package {

	protected double weight; // protected variable to be read in the subclasses

	//Reads the input from keyboard
	@Override
	public void input(Scanner scanner) {
		//getClass used to identify the type of class, so the users knows what type of package is given
		System.out.print("Please input the weight for the " + this.getClass().getSimpleName() + " (lbs) : ");
		weight = Double.parseDouble(scanner.nextLine());
	}
}
