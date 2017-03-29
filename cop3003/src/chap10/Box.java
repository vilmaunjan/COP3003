/**
 * Class:3003 OOP
 * FileName: Box.java
 * Date last modified: September 19, 2016
 * Notes: Box class that implements Package. Also cost and input method are implemented.
 */
package chap10;

import java.util.Scanner;

public class Box implements Package {
	
	private double weight; // has the value of weight entered by the user
	
	//cost method returns the cost calculated based on the weight
	@Override
	public double cost() {
		return 1.2*weight;
	}

	//receives the weight from the keyboard
	@Override
	public void input(Scanner scanner) {
		System.out.print("Please input the weight for the Box (lbs) : ");
		weight = Double.parseDouble(scanner.nextLine()); //scans the string and then parse it as a double
	}

}
