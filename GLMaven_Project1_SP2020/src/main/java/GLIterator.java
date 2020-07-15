import java.util.Iterator;

public class GLIterator<I> implements Iterator<I> {
	
	private GenericList<I>.Node<I> iter;
	
	// Constructor that points to the head of the list
	//
	GLIterator(GenericList<I>.Node<I> head) {
		iter = head;
	}
	
					
	// Checks if there is another value in the data structure and returns true or false
	//
	@Override
	public boolean hasNext() {
		
		if( iter == null)
			return false;
		return true;
	}
	
	// Returns the current value in the data structure and advances to the next item
	//
	@Override
	public I next() {
		
		I value = iter.data;
		iter = iter.next;
		return value;
		
	}


}
