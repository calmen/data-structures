package erg3;

import java.io.*;

public class BST implements WordCounter {
	
	private class TreeNode{
		WordFreq item;
		TreeNode left;
		TreeNode right;
		int subtreeSize;
		public TreeNode(WordFreq item) {
			this.item = item;
			subtreeSize += 1;
		}
	}
	
	private TreeNode head;
	public List<String> stopWords;
	
	private int totalWords;
	private int distinctWords;
	private WordFreq maxFreq;
	private WordFreq[] array;
	
	public void insert(String w) {
		totalWords += 1;
		if(head == null) { //if root is empty -> fill it
			WordFreq word = new WordFreq(w);
			head = new TreeNode(word);
			distinctWords +=1 ;
			maxFreq = head.item;
		}
		else { //if root is not empty -> search for match
			TreeNode current = head;
			
			while(true) {
				
				//if found increase frequency and return
				if(current.item.key().compareTo(w)==0) {
					current.item.increase();
					return;
				}
				//if key of current node is less than w
				//search the right subtree
				if(current.item.key().compareTo(w) < 0) {
					
					if(current.right == null) {//if empty fill it
						WordFreq word = new WordFreq(w);
						current.right = new TreeNode(word);
						distinctWords += 1;
						current.subtreeSize += 1; 
						return;
					}
					else {//if not empty search the subtree (recursion)
						current = current.right;
					}
				}
				//else search the left subtree
				else {
					if(current.left == null) {//if empty fill it
						WordFreq word = new WordFreq(w);
						current.left = new TreeNode(word);
						distinctWords += 1;
						current.subtreeSize += 1; 
						return;
					}
					else {//if not empty search the subtree (recursion)
						current = current.left;
					}
				}
				if(maxFreq.get_frequency() < current.item.get_frequency()) { //find node with the maximum frequency
					maxFreq = current.item;
				}
			}
		}
	}
	
	private WordFreq searchR(TreeNode h,String w) {
		if(h == null) {
			return null;
		}
		if(h.item.key().compareTo(w) == 0) {
			return h.item;
		}
		if(h.item.key().compareTo(w) < 0) {
			return searchR(h.right,w);
		}
		else {
			return searchR(h.left,w);
		}
	}
	
	public WordFreq search(String w) {
		return searchR(head,w);
	}
	
	private TreeNode rotR(TreeNode h) {
		TreeNode x = h.left; 
		h.left = x.right;
		x.right = h; 
		return x;
		}
	
	private TreeNode rotL(TreeNode h) {
		TreeNode x = h.right; 
		h.right = x.left; 
		x.left = h; 
		return x; 
		}
	
	private TreeNode partR(TreeNode h,int k) {
		int t = (h.left == null) ? 0 : h.left.subtreeSize;
		if (t > k) {
			h.left = partR(h.left, k);
			h = rotR(h); 
		}
		if (t < k) {
			h.right = partR(h.right, k-t-1);
			h = rotL(h);
		}
		return h; 
	}
	
	private TreeNode joinLR(TreeNode a,TreeNode b) {
		if(b == null) {
			return a;
		}
		b = partR(b,0);
		b.left = a;
		return b;
	}
	
	private TreeNode removeR(TreeNode h,String v) {
		if(h == null) {
			return null;
		}
		String w = h.item.key();
		if(v.compareTo(w)<0) {
			h.left = removeR(h.left,v);
		}
		if(w.compareTo(v)<0) {
			h.right = removeR(h.right,v);
		}
		if(v.compareTo(w)==0) {
			h = joinLR(h.left,h.right);
		}
		h.subtreeSize--;
		return h;
	}
	
	public void remove(String w) {
		head = removeR(head,w);
	}
	
	public void load(String filename) {

	    File f = null;
	    BufferedReader reader = null;
	    String line;
	    String final_string = "";


	    //open text
	    try { 
	        f = new File(filename);
	    }catch(NullPointerException e) {
	        System.err.println("File not found!");
	    }

	    //Checking if the text file has opened correctly for reading.
	    try{
	        reader = new BufferedReader(new FileReader(f));
	    }catch(FileNotFoundException e) {
	        System.err.println("Error opening file!");
	    }

	    try {

	        line = reader.readLine(); //start reading lines
	        
	        while(line != null) { //read lines one by one
	        	
	        	line = line.trim();
	        	line = line.replaceAll("[^A-Za-z ]", "");
	            String []trimline = line.toLowerCase().split(" ");
	            
	            for(String word : trimline) {
	            	
	            	final_string = word.replaceAll("[^A-Za-z]", "");
	                
	                try {
	                	if(!final_string.equals("") && !final_string.equals("")){ //&& !stopWords.custom_is_inside(final_string)
		                	insert(final_string);                                 //error because of list
		                }
	                }catch(NullPointerException e) {}
	                
	               
	            }
	            final_string = "" ;
	            line = reader.readLine() ; //next line
	        }

	        reader.close(); //close the file

	    }catch(IOException e){
	        System.out.println ("Error reading line!");
	    }

	}
	
	public int getTotalWords() {
		return totalWords;
	}
	
	public int getDistinctWords() {
		return distinctWords;
	}
	
	public int getFrequency(String w) {
		WordFreq k = search(w);
		if(k == null) {
			return 0;
		}
		else {
			return k.get_frequency();
		}
		
	}
	
	public WordFreq getMaximumFrequency() {
		return maxFreq;
	}
	
	private double sumFrequencies(TreeNode h) {//add to sum frequency of given node and continue with left and right subtrees.
		if(h == null) {
			return 0;
		}
		double sum = 0;
		sum = h.item.get_frequency()+sumFrequencies(h.left)+sumFrequencies(h.right);
		return sum;
	}
	
	public double getMeanFrequency() {
		if(head == null) {
			return 0;
		}
		else {
			return sumFrequencies(head)/(double)distinctWords;
		}
	}
	
	public void addStopWord(String w) {
		try{
			if(stopWords.custom_is_inside(w)) {
				return;
			}
		
			stopWords.insertAtFront(w);
		}catch(NullPointerException e){}
	}
	
	public void removeStopWord(String w) {
		stopWords.custom_remove(w);
	}
	
	private void printTree(TreeNode h,PrintStream stream) {
		if(h == null) {
			return;
		}
		printTree(h.left,stream);           //we traverse the tree recursively and we print from left to right 
		stream.println(h.item.toString()); //we take advantage of the nature of the binary search tree
		printTree(h.right,stream);        //we print alphabetically given that in the insertion 
		                                 //the keys are compared based on their ASCII values
		
	}
	
	public void printTreeAlphabetically(PrintStream stream) {
		if(head == null) {
			return;
		}
		printTree(head,stream);
	}
	
	private void tree_to_list(TreeNode h,List<WordFreq> list) {
		if(h == null) {
			return;
		}
		tree_to_list(h.left,list);
		list.insertAtFront(h.item);
		tree_to_list(h.right,list);
	}
	
	private void list_to_array(List<WordFreq> list,int k) {
		try {
			this.array = new WordFreq[k];
			for(int i=0;i<k;i++) {
				this.array[i] = list.removeFromFront();
			}
			
		}
		catch(EmptyListException e){}
	}
	
	static void swap(WordFreq[] arr, int i, int j)
	{
	    WordFreq temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}
	
	static int partition(WordFreq[] arr, int low, int high)
	{
	     
	    // pivot
	    WordFreq pivot = arr[high];
	     
	    // Index of smaller element and
	    // indicates the right position
	    // of pivot found so far
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++)
	    {
	         
	        // If current element is smaller
	        // than the pivot
	        if (arr[j].get_frequency() < pivot.get_frequency())
	        {
	             
	            // Increment index of
	            // smaller element
	            i++;
	            swap(arr, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    return (i + 1);
	}
	 
	
	static void quickSort(WordFreq[] arr, int low, int high)
	{
	    if (low < high)
	    {
	         
	        // pi is partitioning index, arr[p]
	        // is now at right place
	        int pi = partition(arr, low, high);
	 
	        // Separately sort elements before
	        // partition and after partition
	        quickSort(arr, low, pi - 1);
	        quickSort(arr, pi + 1, high);
	    }
	}
	
	public void printÔreeByFrequency(PrintStream stream) {
		int k = this.distinctWords;
		List<WordFreq> list = new List<WordFreq>();
		tree_to_list(this.head,list);
		list_to_array(list,k);
		quickSort(this.array,0,k-1); //quickSort code was borrowed from https://www.geeksforgeeks.org/quick-sort/
									//due to no time to implement it ourselves , even though we do understand the theory
		for(int i=0;i<k;i++) {
			stream.println(array[i]);
		}
		
	}
	
	
}
