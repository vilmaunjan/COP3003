/**
 * Class:3003 OOP
 * FileName: TestLab3.java
 * Date last modified: September 19, 2016
 * Notes: This is the Test class. No changes have been made from the actual document.
 * 		  Only two imports have been added.
 */
package chap10;

import java.util.Random;
import java.util.Scanner;

public class TestLab3 {

	public static Package load_a_package(Scanner scanner){
		Package pack=null;
		int rand=(new Random()).nextInt(4); //generates a random number between 0 and 3
		
		switch(rand){ //takes the random values generated and decides what case to use
		case 0:
			pack=new Box(); // object Box is created
			break;
			
		case 1:
			pack=new Letter(); // object Letter is created
			break;
			
		case 2: 
			pack=new MetalCrate(); // object MetalCrate is created
			break;
			
		case 3: 
			pack=new WoodCrate(); // object WoodCrate is created
			
			break;
		}
		
		pack.input(scanner); // input method is called. Polymorphism is used to do so
		return pack; // returns what the user entered
			
	}
	
	
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		Package pack=null;
		
		for(int i=0;i<5;i++){
			System.out.printf("\n**** package %d ****\n",i);
			pack=load_a_package(scanner); // method is called and what is returned is assigned to pack
			System.out.printf("The cost of this package is $%.2f\n",pack.cost()); // cost is printed
		}
		scanner.close();
	}
	
}
