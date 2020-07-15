import java.util.ArrayList;
import java.util.Iterator;

public abstract class GenericList<I> implements CreateIterator<I>{
//	
	private Node<I> head;
	private int length;
	
	
	public class Node<T> {
		
		public T data;
		public Node<T> next;
		
	}

	//
	// prints the items of the list
	// 
	void print() {
		
		// Check if empty and print empty list
		if( head == null)
			System.out.println("Empty list");
		
		else {   // Not empty
			Node<I> current = this.head;
			
			while( current != null) {
				System.out.println( current.data + " ");
				current = current.next;
			}
		}
	}
	
	
	//
	// adds the value to the list - abstract function
	// implementation is overrided in the inherited class
	//
	abstract void add( I data);
	
	
	//
	// Returns the first value of the list and deletes the node.
	//
	public I delete() {
		I result = null;       // Initializing the result
		
		if( head != null) {
			result = head.data;
			head = head.next;
			length--;
			return result;
		}
		
		return result;        // Return null when the object is empty
	}
	
	
	//
	// Stores all the values in the List into an ArrayList and return it
	// 
	public ArrayList<I> dumpList() {
		ArrayList<I> result = new ArrayList<I>();
		
		if( head == null)        // Return empty arraylist for empty list
			return result;
		
		Node<I> current = head;
		while( current != null) {
			result.add( current.data);
			current = current.next;
		}
		
		return result;           // Return the arraylist from the list
	}
	
	
	public int getLength() {
		return this.length;
	}
	
	
	public void setLength( int length) {
		this.length = length;
	}
	
	
	public Node<I> getHead() {
		return this.head;
	}
	
	
	public void setHead( Node<I> head) {  
		// Check if there is an existing head for proper link
		if( this.head != null )
			head.next = this.head;
		
		this.head = head;
	}

	
	@Override
	public Iterator<I> createIterator() {
		return new GLIterator<I>(head);
	}
	
}