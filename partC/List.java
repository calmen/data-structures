package erg3;

import erg3.EmptyListException;

/**
 * Single-link List. Uses {@link Node} for list nodes.
 */
public class List<T> implements ListInterface<T> {

    private Node<T> head = null;
    private Node<T> tail = null;

    /**
     * Default constructor
     */
    public List() {
    }

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    @Override
    public void insertAtFront(T data) {
        Node<T> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
    }


    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    @Override
    public T removeFromFront() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        T data = head.getData();

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();

        return data;
    }

    
    //method to iterate through list
    public boolean custom_is_inside(T value) {
    	if(head == null) {
    		return false; //if list empty return false
    	}
    	else {
    		Node<T> current = head; //start from head
    		while(current != null) {
    			if(current.data.equals(value)) { //if value found return true
    				return true;
    			}
    			current = current.next; //check next node
    		}
    		return false; //if value not found return false
    	}
    }
    
    public void custom_remove (T value){
    	if(head == null) {//if list empty throw exception
    		throw new IndexOutOfBoundsException("List is Empty!");
    	}
    	Node<T> current = new Node<T>(value); //node to hold parameter value
    	if(!custom_is_inside(current.data)) {//if value is not inside the list print message
    		System.out.println(value+" is not inside the list!");
    		return;
    	}
    	if(head.data.equals(current.data)) {
    		head = head.getNext();//if its the head unlink it
    		return;
    	}
    	else { //iterate through list
    		Node<T> current_1 = head; //start iterate from node next to head(head already tested)
    		
    		while(current_1 != null) {
    			if(current_1.next != null && current_1.next.data.equals(current.data)) {
    				current_1.next = current_1.next.next; //if its a match then unlink it
    				return;
    			}
    			
    			current_1 = current_1.next; //check next node
    		}
    	}
    }

    /**
     * Returns list as String
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "List is empty :(";
        }

        Node<T> current = head;

        StringBuilder ret = new StringBuilder();

        // while not at end of list, output current node's data
        ret.append("HEAD -> ");

        while (current != null) {
            ret.append(current.data.toString());

            if (current.getNext() != null)
                ret.append(" -> ");

            current = current.next;
        }

        ret.append(" <- TAIL");

        return ret.toString();
    }
}
