package Lab6;

/**
* Class:3003 OOP
* Date last modified: December 2, 2016
* Notes: Class FileIO creates an object of type FileStats, calls printDitionary, compress
* 		  and decompress methods.
* 		  Class FileStats reads the file passed and looks for the 4 word with most frequency
* 		  Class FileCompressor has two methods that compress and decompress files based on
* 		  dictionary created in FileStats class.
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ReactionArea {
	public int waitingHydrogen = 0; //number of hydrogens that are waiting
	public int waitingOxygen = 0; //number of oxygens that are waiting
	
	public synchronized void increWHydrogen(int index) throws InterruptedException{
		while(waitingHydrogen > 5){ // there are more than 5 hydrogens
			wait(); //wait and release the lock
		}
		
		waitingHydrogen +=1; //waiting hydrogens increment 1
		
		System.out.println("The " + index + "th hydrogen atom was added.");
		
		notifyAll();
	}
	
	public synchronized void increWOxygen(int index) throws InterruptedException{
		while(waitingOxygen > 2){ // there are more than 2 oxygens
			wait(); //wait and release the lock
		}
		
		waitingOxygen +=1; //waiting oxygens increment 1
		
		System.out.println("The " + index + "th Oxygen atom was added.");
		
		notifyAll();
	}
	
	public synchronized void react(int index) throws InterruptedException{
		while(waitingHydrogen < 2 || waitingOxygen == 0 ){ // There is not enough material
			wait();
		}
		
		// when water is produced, 2 hydrogens and 1 oxygen are removed from the waiting list 
		waitingHydrogen -=2; 
		waitingOxygen -=1;

		System.out.println("The " + index + "th water molecule was formed.");
				
		notifyAll();
	}
}

class Hydrogen implements Runnable {

	ReactionArea buff;
	
	public Hydrogen(ReactionArea buff){
		this.buff = buff;
	}
	
	@Override
	public void run() {
		for(int i=0; i<20; i++){
			try{
				buff.increWHydrogen(i);
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}

class Oxygen implements Runnable {

	ReactionArea buff;
	
	public Oxygen(ReactionArea buff){
		this.buff = buff;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			try{
				buff.increWOxygen(i);
				Thread.sleep(200);
			} catch(InterruptedException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}

class Reactor implements Runnable {

	ReactionArea buff;
	
	public Reactor(ReactionArea buff){
		this.buff = buff;
	}
	
	@Override
	public void run() {
		try{
			Thread.sleep(2000); //sleeps 2 secs before the loop
				for(int i=0; i<10; i++){
					buff.react(i);
					Thread.sleep(50);
				}	
		} catch(InterruptedException e){
				e.printStackTrace();
				System.exit(1);
		}
		
	}
}


public class ReactionAreaTest {

	public static void main(String[] args) {
		
		//create a newCachedThreadPool
		ExecutorService application = Executors.newCachedThreadPool();
		
		//create SynchronizedBuffer to store ints
		ReactionArea reactionArea = new ReactionArea();
		
		//Create hydrogen, oxygen and reactor objects
		Hydrogen h = new Hydrogen(reactionArea);
		Oxygen o = new Oxygen(reactionArea);
		Reactor r = new Reactor(reactionArea);
		
		application.execute(h);
		application.execute(o);
		application.execute(r);
		
		application.shutdown(); //shut it down
		
	}
}
