/**
 * Class:3003 OOP
 * FileName: Package.java
 * Date last modified: September 19, 2016
 * Notes: Package is an interface with two methods declaration Cost and Input
 */
package chap10;

import java.util.Scanner;

public interface Package {

	//scanner object
	Scanner scanner  = new Scanner(System.in);
	
	//Methods
	public abstract double cost(); // calculates cost; no implementation
	
	public abstract void input(Scanner scanner); // gets input from scanner as a parameter; no implementation
		
}
