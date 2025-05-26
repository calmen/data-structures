package erg1;

import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack {
	
public StringStackImpl(){
		
	}
	private Node head= null ;
	private Node tail = null ;
	private int i = 0 ;

	
	public boolean isEmpty() {
		
		return head == null ;
	}

	
	public void push(String item) {
		
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

	
	public String pop() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Stack!!!") ;
		}
		
		String data = (String) head.getData() ; //einai object kai kanw kasting
		
		if(head==tail) {
			head=tail=null ;
			i--;
		}
		else {
			head = head.getNext() ;
			i--;
		}
		return data;
	}

	
	public String peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Stack!!!") ;
		}
		
		String data = (String) head.getData() ; //einai object kai kanw kasting
		
		return data ;
	}

	
	public void printStack(PrintStream stream) {
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

	
	public int size() {
		
		return i;
	}

}
