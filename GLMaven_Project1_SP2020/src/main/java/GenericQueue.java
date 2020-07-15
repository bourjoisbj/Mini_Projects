
public class GenericQueue<I> extends GenericList<I> {

	// Constructor for GenericQueue with head as 
	// parameter and set the length of nodes to 1
	GenericQueue( Node<I> valHead) {
		this.setHead(valHead);
		this.setLength(1);
	}
	
	
	@Override
	void add(I data) {
		Node<I> newNode = new Node<I>();
		newNode.data = data;
		newNode.next = null;
		Node<I> current = this.getHead();
		
		// If the queue is empty do
		if( current == null) {
			this.setHead( newNode);
			this.setLength(1);
			return;
		}
		
		// Traverse the list to get to the end
		while( current != null) {
			current = current.next;
		}
		
		// Add the new node to the back of the list
		current = newNode;
		this.setLength(getLength()+1);
		
	}
	
	
	// Enqueue function - add element parameter to the end of the queue
	public void enqueue(I data) {
		this.add(data);
	}
	
	
	// Dequeue function - return and delete last element of the queue
	public I dequeue() {
		return this.delete();
	}

}
