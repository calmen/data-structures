package erg1;

import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue {
	
	public IntQueueImpl() {
		
	}
	
	private Node head = null ;
	private Node tail = null ;
	private int i = 0 ;
	@Override
	
	public boolean isEmpty() {
		return head == null ;
	}

	@Override
	public void put(int item) {
		Node newNode = new Node(item) ;
		if(isEmpty()) {
			
			head = newNode ;
			tail = newNode ;
			
		}
		else {
			 newNode.setNext(head); 
			 head = newNode ;
		}
		
		i++ ;
		
	}

	@Override
	public int get() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Queue!!!") ;
		}
		int data = (int) tail.getData() ;
		
		if ( head==tail ) {
			head=tail=null ;
			i-- ;
		}
		else {
				Node iterator = head ;
				while (iterator.getNext() != tail)
					iterator = iterator.getNext() ;
				iterator.setNext(null);
				tail = iterator ;
				i-- ;
			
		     }
	
		
		return data;
	}

	@Override
	public int peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Queue!!!") ;
		}
		int data = (int) tail.getData() ;
		
		return data;
	}

	@Override
	public void printQueue(PrintStream stream) {
		if (isEmpty()) { throw new NoSuchElementException("Empty stack");
		
		
		}else{
			
			Node current = head;
			
			while (current!=null)
			{				
				System.out.printf("%s",current.data);
				current = current.next;
				
			}
			System.out.println("\n");
					
		
		}
		
	}

	@Override
	public int size() {
		
		return i;
	}

}
