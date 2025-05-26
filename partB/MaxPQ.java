package erg2;

public class MaxPQ implements PQInterface{
	
	public Processor[] heap; //the heap
	private int size; //current size of heap
	
	private static final int default_capacity = 5; //starting slot of PQ
	private static final float percentage = 0.75f; //percentage to resize
	
	MaxPQ(){
		heap = new Processor[default_capacity];
		this.size = 0;
	}
	
	
	private void resize() {
		Processor[] newHeap = new Processor[heap.length*2]; //new heap , double the size of current heap
		
		//copy all slots from position 1 till position size in the new heap
		for (int i = 0; i <= size; i++) {
	           newHeap[i] = heap[i];
	       }

	        heap = newHeap; // assign newHeap address to heap
	}
	
	private void swap(int x,int y) { //swap nodes
		Processor z = heap[x];
		heap[x] = heap[y];
		heap[y] = z;
	}
	
	private void swim(int k) {
		if(k==1) {
			return; //if its the root return
		}
		int parent = k / 2; //parent node position
		
		while(k>1 && heap[k].compareTo(heap[parent])>0) {
			swap(k,parent);
			k = parent;
			parent = k / 2;
			//continue till ordered or k becomes root
		}
	}
	
	private void sink(int k) {
		int left = 2*k; //left child
		int right = left + 1; //right child
		
		// if 2*i > size, node i is a leaf return
        if (left > size)
            return;

        // while haven't reached the leafs
        while (left <= size) {
            // Determine the largest child of node i
            int max = left;
            if (right <= size) {
                if (heap[left].compareTo(heap[right])<0)
                    max = right;
            }

            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (heap[k].compareTo(heap[max])>0)
                return;
            else {
                swap(k, max);
                k = max;
                left = k * 2;
                right = left + 1;
            }
        }
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void insert(Processor x) {
		
		//check size
		if((float) size / (heap.length - 1) > percentage) {
			resize();
		}
		
		//go to next empty position and place x (start from 1)
		heap[++size] = x;
		
		//restore order in the heap
		swim(size);
	}
	
	public Processor max() {
		if(size == 0) {
			return null;
		}
		
		return heap[1];
	}
	
	public Processor getmax() {
		if(size == 0) {
			return null;
		}
		
		Processor root = heap[1]; //save root
		
		// Replace root item with the one at rightmost leaf
        heap[1] = heap[size];
        size--;

        // Dispose the rightmost leaf
        // Sink the new root element
        sink(1);

        // Return the int removed
        return root;
	}
}
