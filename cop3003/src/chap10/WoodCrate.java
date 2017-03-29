/**
 * Class:3003 OOP
 * FileName: WoodCrate.java
 * Date last modified: September 19, 2016
 * Notes: This class extends Crate class and implements the cost method.
 * 		  This is a complete class, so it is not abstract.
 */
package chap10;

public class WoodCrate extends Crate{
	
	// Method calculates the cost
	@Override
	public double cost() {
		return 1.4 * weight;
	}

}
