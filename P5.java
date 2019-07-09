/*
  Federico Rubino
  frubino
  federico.rubino8@gmail.com
  P5.java
  Assignment#5 Looking Out for Number One
  working/ tested
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/*
  Program that analyses the integers in a text by
  printing the quantity of starting digits
*/
public class P5{

    //function that takes info from STDIN and gives it to fillList
    public static void dealWithInput(DigitAnalyzer list){
	Scanner input = new Scanner(System.in);
	String inputLine;
	while(input.hasNextLine()){
	    inputLine = input.nextLine();
	    if(inputLine.length() != 0) {
		fillList(list, inputLine);
	    }
	}
    }

    //function that takes info from a file and gives it to fillList
    public static void dealWithFile(DigitAnalyzer list, String filename){
	String fileLine;
	try{
            Scanner fl = new Scanner(new File(filename));
            while(fl.hasNextLine()){
                fileLine = fl.nextLine();
		if(fileLine.length() != 0) {
		    fillList(list, fileLine);
		}
            }
        } catch (FileNotFoundException e){
            System.err.println(filename+ ": File not found!");
        }
    }

    // fills the list with the numbers found in the line out of the file/ input
    public static void fillList(DigitAnalyzer list, String line){
	String[] arrayList = line.split(" ");
	int begOfCom = 0; int endOfCom = 0; int myNumber = 0;
	boolean foundANum = false;
	for(int i = 0; i < arrayList.length; i++){
	    if(arrayList[i].equals("(*")) {
		begOfCom = i;
	    } if(arrayList[i].equals("*)")) {
		endOfCom = i;
	    }
	}       
	for(int i = 0; i < arrayList.length; i++){
	    if(i <= begOfCom || i > endOfCom){
		if(isInteger(arrayList[i])){
		    myNumber = Integer.parseInt(arrayList[i]);
		    foundANum = true;
		}
	    }
	}
	if(foundANum){
	    list.insert(myNumber);
	}
    }

    /* 
       Split the String to only find the integers that aren't
       closed in by  "(*" and end with "*)" 
    */
    public static boolean isInteger(String wouldBeInt){
	try {
	    Integer.parseInt(wouldBeInt);
	    return true;
	}
	catch(Exception e) {
	    return false;
	}
    }
 
    /*   
	 formating the output
	 displays (in a table) the total number of entries
	 the number of entries that begin with each digit, and the percentage
    */
    public static void printTable(DigitAnalyzer list){
	if(list.isEmpty()) { return; }
	int[] arrayOfDigits = list.getStartingDigits();
	double totalDigits = 0.0;
	for(int i = 0; i < arrayOfDigits.length; i++){
	    totalDigits += (double) arrayOfDigits[i];
	}
	System.out.printf("%.0f entries.%n", totalDigits);
	System.out.printf("digit \t count \t %% %n");
	for(int i = 0; i < arrayOfDigits.length; i++){
	    System.out.printf("%ds \t %d \t %.1f%% %n" , i, arrayOfDigits[i], ( arrayOfDigits[i] / totalDigits) * 100);
	}
    }

    //main uses the deal with functions and printTable for I/O
    public static void main(String args[]){
	DigitAnalyzer list = new DigitAnalyzer(); // linked list for storing the data
	if(args.length != 0){
	    dealWithFile(list, args[0]);
	} else {
	    dealWithInput(list);
	}
	printTable(list);
	int amountOfZeros = list.countAndRemoveZeroEntries();
	if(amountOfZeros != 0){
	System.out.printf("%nNumber of zeros removed: %d%n", amountOfZeros); 
	printTable(list);
	}
    }
}

