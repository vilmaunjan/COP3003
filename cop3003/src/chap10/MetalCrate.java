/**
 * Class:3003 OOP
 * FileName: MetalCrate.java
 * Date last modified: September 19, 2016
 * Notes: This class extends Crate class and implements the cost method.
 * 		  This is a complete class, so it is not abstract.
 */
package chap10;

public class MetalCrate extends Crate{
	
	// Method calculates the cost
	@Override
	public double cost() {
		return 1.3 * weight;
	}
}
