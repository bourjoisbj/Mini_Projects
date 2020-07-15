
public class GenericStack<I> extends GenericList<I> {
	
	// Constructor for GenericStack with head as 
	// parameter and set the length of nodes to 1
	GenericStack( Node<I> valHead) {
		this.setHead(valHead);
		this.setLength(1);
	}
	
	
	@Override
	void add(I data) {   
		Node<I> newNode = new Node<I>();
		newNode.data = data;
		newNode.next = null;
		
		this.setHead( newNode);
		this.setLength( this.getLength()+1);	
	}
	
	
	// Push function - add element parameter as top element of stack
	public void push(I data) {
		this.add(data);
	}
	
	
	// Pop function - return and delete top element
	public I pop() {
		return this.delete();
	}
	
}
