package hw4;

/**
 * Class:3003 OOP
 * Date last modified: November 24, 2016
 * Notes: Class FileIO creates an object of type FileStats, calls printDitionary, compress
 * 		  and decompress methods.
 * 		  Class FileStats reads the file passed and looks for the 4 word with most frequency
 * 		  Class FileCompressor has two methods that compress and decompress files based on
 * 		  dictionary created in FileStats class.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class FileStats{
	private Scanner input;
	private ArrayList <String> wordList=new ArrayList<String>();
	private HashSet <String> wordSet=new HashSet<String>();
	private ArrayList <Entry<String>> entryList=new ArrayList<Entry<String>>();
	private Map <String, Character> dictionary=new HashMap<String, Character>();
	private Map <Character, String> reversedDictionary = new HashMap<Character, String>();

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
	 * 3. construct the dictionary that four key-value pairs. The keys
	 *    are the most frequent words and the values are the characters,
	 *    used to represent the words.
	 */
	private void count() {
		for(String word : wordSet) {
			if (word != null) {
				//Finds the frequency of a word in wordList
				int freq = Collections.frequency(wordList, word);
				
				/*Saves the frequency and word in the entry list
				 *and adds values to entryList
				 */		
				entryList.add(new Entry(word,freq)); 	
				Collections.sort(entryList);
			}
		}
		char code[] = {'%','$','#','*'};
		for(int i=0;i<4;i++){//prints the 4 most frequent words
			dictionary.put(entryList.get(i).word, code[i]);
			reversedDictionary.put(code[i], entryList.get(i).word);
				System.out.println(entryList.get(i).word + " appears "+
								entryList.get(i).frequency+" time(s).");	
		}
	}

	//gets dictionary that its key is the word an the value is code
	public Map<String, Character> getDictionary(){
		return dictionary;
	}
	
	//gets dictionary that its key is the code an the value is word
	public Map<Character, String> getReversedDictionary(){
		return reversedDictionary;
	}

	//Prints dictionary
	public void printDictionary() {
		System.out.println("Dictionary " + dictionary);
	}
}

/*This class has two methods
 * compress(inputFile, outputFile, dictionary) transform the 4 most common 
 * words to codes
 * decompress(inputFile, outputFile, dictionary) transform the codes saved 
 * in the dictionary to words. */
class FileCompressor{
	
	/* Compress method rescans the file look for the words
	 * that are in the dictionary. */
	public static void compress(String src, String dest,
			Map<String, Character> dictionary) throws IOException{
		
		Scanner input = null;
		File f = new File(dest);
		
	
		try{
			input = new Scanner(new File(src)); //Rescan file
		} catch (FileNotFoundException e){
			System.out.println("Error openning file..");
			System.exit(1);
		}
		
		try{
			String line, word; // used when reading the "scr" file
			char code; //holds the code saved in the map
			
			while((line = input.nextLine()) != null){ //while next line is not empty
				StringTokenizer st = new StringTokenizer(line);
				
				try{
					
					FileWriter fw = new FileWriter(f,true);
					
				
					while (st.hasMoreTokens()) {
						String symbol = null;
						word = st.nextToken().toLowerCase();//converts word to lowerCase
						
						if(!word.matches("[a-zA-Z]+")){
							symbol = word.substring(word.length()-1, word.length());
							word = word.substring(0, word.length()-1);
						}
							
						
						if(dictionary.get(word)!=null){
							code = dictionary.get(word); //get the code
							if(symbol != null){
								fw.write(code + symbol + " "); //replace word for code
							} else {
								fw.write(code + " "); //replace word for code
							}
							
						} else {
							if(symbol != null){
								fw.write(word + symbol + " "); //replace word for code
							} else {
								fw.write(word + " "); //replace word for code
							}
							
						}
					}
				
					fw.write("\r\n");
					fw.close(); //close file
				
				}catch(Exception e) {
					System.err.println("Failed compressing");
				}
			}
	
		} catch (NoSuchElementException e){
			// no more lines in the file
			// no handler is necessary
		}
	}

	/* Decompress method looks for the codes in the reverseDictionary
	 * and change them for the words(the initial words) */
	public static void decompress(String src, String dest,
			Map<Character, String> reversedDictionary){

		Scanner input = null; //to scan input file
		File f = new File(dest); //create file for output textfile

		try{
			input = new Scanner(new File(src)); //Rescan file
		} catch (FileNotFoundException e){
			System.out.println("Error openning file..");
			System.exit(1);
		}
		
		try{
			String line, word, code;
			
			//while next line is not empty
			while((line = input.nextLine()) != null){ 
				StringTokenizer st = new StringTokenizer(line);
				
				try{
					//true so it does not erase was was written previously
					FileWriter fw = new FileWriter(f,true);
				
					while (st.hasMoreTokens()) {
						word = st.nextToken().toLowerCase();
						
						if(reversedDictionary.get(word.charAt(0)) != null){
							code = reversedDictionary.get(word.charAt(0));//get the code

							fw.write(code + " ");//replace code for word
						} else {
							fw.write(word + " ");//copy word
						}
					}
					fw.write("\r\n");
					fw.close(); //close file
				
				}catch(Exception e) {
					System.err.println("Failed decompressing");
				}
			}
		} catch (NoSuchElementException e){
			// no more lines in the file
			// no handler is necessary
		}
	}
}

/* Test class, creates object of type FileStats that reads a text file and
 * then calls methods compress and decompress in class FileCompressor */
public class FileIO {
	public static void main(String args[]) throws IOException{
		FileStats fs=new FileStats("basketball.txt");
		fs.printDictionary(); //prints dictionary to the console

		/* Compress file, calls compress method in FileCompressor class */
		Map <String, Character> m1=fs.getDictionary();
		FileCompressor.compress("basketball.txt", "compressed.txt",m1);

		/* Create another dictionary for decompress and name it m2 
		 * Compress file, calls compress method in FileCompressor class */
		Map <Character, String> m2=fs.getReversedDictionary();
		FileCompressor.decompress("compressed.txt", "decompressed.txt",m2);
	}
}