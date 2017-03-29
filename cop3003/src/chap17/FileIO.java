package chap17;

/**
 * Class:3003 OOP
 * Date last modified: November 17, 2016
 * Notes: Class FileIO creates an object of type FileStats
 * 		  Class FileStats reads the file passed and looks for the 4 word with most frequency
 */

import java.io.*;
import java.util.*;

class FileStats{
	private Scanner input;
	private ArrayList <String> wordList=new ArrayList<String>();
	private HashSet <String> wordSet=new HashSet<String>();
	private ArrayList <Entry<String>> entryList=new ArrayList<Entry<String>>();

	private class Entry <T> implements Comparable<Entry<T>>{
		public T word;
		public int frequency;
		public Entry(T word, int f){
			this.word=word;
			frequency=f;
		}
				
		//Sort elements, compares frequency value stored with actual frequency of value
		public int compareTo(Entry<T> e){
			return e.frequency-frequency;		
		}		
	}

	//Constructor
	public FileStats(String path) {
		
		/* open the file, named path */
		try{
			input=new Scanner(new File(path)); //Scan file
		} catch (FileNotFoundException e){
			System.out.println("Error openning file..");
			System.exit(1);
		}
			
		try{
			String line, word;
			
			while((line = input.nextLine()) != null){//while next line is not empty
				StringTokenizer st = new StringTokenizer(line);
				
				while (st.hasMoreTokens()) {
					word = st.nextToken().toLowerCase();
					
					//if last character is not a letter
					if(!Character.isLetter(word.charAt(word.length()-1))) {
						word = word.substring(0, word.length()-1);
					}
					wordList.add(word); //adds the word to the list
					wordSet.add(word); //adds the word to the set
				} 
			}
		} catch (NoSuchElementException e){
			// no more lines in the file
			// no handler is necessary
		}
		count();		
	}

	/*
	 * This method is supposed to
	 * 1. find the frequency of each word in the file.
	 * 2. display the four most frequent words and their frequencies.
	 */
	private void count() {
		for(String word : wordSet) {
			if (word != null) {
				//Finds the frequency of a word in wordList
				int freq = Collections.frequency(wordList, word);
				
				//saves the frequency and word in the entry list and adds values to entryList	
				entryList.add(new Entry(word,freq)); 	
				Collections.sort(entryList);
			}
		}
		
		for(int i=0;i<4;i++){//prints the 4 most frequent words
				System.out.println(entryList.get(i).word + " appears "+ entryList.get(i).frequency+" time(s).");
		}
	}
}

public class FileIO {
	public static void main(String args[]) throws IOException{
		FileStats fs=new FileStats("basketball.txt");
	}
}