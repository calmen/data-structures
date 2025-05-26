package erg1;

public class Node {
    protected Object data;
    protected Node next = null;

    
    Node (Object data) {
        this.data = data;
   
    }
    
    
    public Object getData() {
        
        return data;
    }

   
     
    public Node getNext() {
        
        return next;
    }


    
    public void setNext(Node next) {
        this.next = next;
    }
}
