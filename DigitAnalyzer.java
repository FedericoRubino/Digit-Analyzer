/*
  Federico Rubino
  frubino
  federico.rubino8@gmail.com
  DigitAnalyzer.java
  Assignment#5 Looking Out for Number One
  working/ tested
*/
import java.lang.StringBuilder;

/*
  DigitAnalyzer
  class that imitates a linked List, with all the appropriate functions
  uses class LinkNode to create a list of linked Nodes
*/
public class DigitAnalyzer{

    //nodes for the linkedList DigitAnalyzer    
    private class LinkNode{
	private int data;
	private LinkNode next;
	
	//constructor w/o parameter
	public LinkNode(){
	    next = null;
	}
	
	//constructor w/ a value for data
	//not necessary but seems smoother
	public LinkNode(int data){
	    next = null;
	    this.data = data;
	}
    }
    
    private LinkNode front; // first node in the list
    
    // constructs a new list
    public DigitAnalyzer(){
	LinkNode front = new LinkNode();	
    }

    // returns the number of nodes in the list
    public int getSize(){
	int count = 0;
	LinkNode temp = front;
	while (temp != null){
	    count++;
	    temp = temp.next;
	}
	return count;
    }

    // checks if list is empty
    public boolean isEmpty(){
	return front == null;
    }
    
    /*
      returns an integer array of size 10 that contains the number of values
      from the List that begin with that initial digit (zero through nine.)
    */
    public int[] getStartingDigits(){
	int[] array = new int[10];
	LinkNode temp = front;
	int digit;
	while(temp != null){
	    digit = temp.data;
	    while(digit > 9) {
		digit = digit / 10;
	    }
	    array[digit]++;
	    temp = temp.next;
	}
	return array;
    }

    // counts and removes zero entries
    public int countAndRemoveZeroEntries(){
	int counter = 0;
	while( !(isEmpty()) && front.data == 0){
	    counter++;
	    front = front.next;
	}
	if(isEmpty()){ return counter;}
	LinkNode temp = front.next;
	LinkNode tempPrev = front;
	while(temp != null){
	    if (temp.data == 0){
		counter++;
		tempPrev.next = temp.next;
		temp = temp.next;
	    } else {
		tempPrev = temp;
		temp = temp.next;
	    }
	} 
	if(tempPrev.data == 0){
	    tempPrev.next = null;
	}
	return counter;
    }

    // inserts an integer into the list, in other words creates another Node
    public void insert(int num){
	LinkNode temp =	new LinkNode(num);
	temp.next = front;
	front = temp;
    }

    /*
      returns a string with each integer in the list in order, starting from
      the first, with one entry per line.
    */
    public String toString(){
	StringBuilder builtString = new StringBuilder();
	LinkNode temp = front;
	while(temp != null){
	    builtString.append(temp.data);
	    builtString.append("\n");
	    temp = temp.next;
	}
	return builtString.toString();
    }
}


