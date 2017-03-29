/**
 * Class:3003 OOP
 * FileName: Letter.java
 * Date last modified: September 19, 2016
 * Notes: Letter class implements Package,the variable numOfPages is defined
 * 		  and used to calculate the cost in cost method. Cost and input 
 * 		  method are implemented.
 */
package chap10;

import java.util.Scanner;

public class Letter implements Package {

	private double numOfPages; // numOfPages is declared
	
	//calculates the cost
	@Override
	public double cost() {
		return 5 * numOfPages / 100;
	}

	//gets input from keyboard
	@Override
	public void input(Scanner scanner) {
		System.out.print("Please input the number of pages for the Letter (pgs) : ");
		numOfPages = Double.parseDouble(scanner.nextLine());
		
	}

}
