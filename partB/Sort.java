package erg2;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sort {
	public static void main(String[] args) {
		
		String file = args[0];
		int makespan = HeapSort(file);
		//print Makespan
		System.out.println("Makespan = "+makespan);
        
	}

public static int HeapSort(String data) {
	
	 
	 MaxPQ newQueue = new MaxPQ() ;
	 File f = null;
     BufferedReader reader = null;
     String line;
     int numberofprocessors = 0;
     int total_tasks = 0;
 	 int tasks_counter = 0; 
 	 int id ;
 	 int time ;
 	 int countline = 0 ;
 	 Task[] sort = null ;
 	 Processor max_processor = new Processor(0);
 	  
 	 //open text
 	 try { 
 		f = new File(data);	
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
    	 
    	 line = reader.readLine() ; //start reading lines
    	 
    	 while(line != null) { //read lines one by one
    		 
    		
    		 
    		 String [] text = line.split(" ") ;
    		 
    		 if(countline == 0){ //first line is number of processors
    			 numberofprocessors = Integer.parseInt(text[0]) ;
    			 for(int x =  1; x<=numberofprocessors;x++) {
        	    	 Processor processor = new Processor(x); //give ids to processors via constructor
        	    	 newQueue.insert(processor); //insert processor in PQ
        			 }
    			
        			 }
        	   
        	 
    		 
    		 if (countline == 1) { //second line is number of total tasks	
    			 total_tasks = Integer.parseInt(text[0]) ; 
    			 sort = new Task[total_tasks];
        	 }	
    		 
    		 if(countline > 1){
    			 
    			 id = Integer.parseInt(text[0]) ;
	        	 time = Integer.parseInt(text[1]);
	        		
	        	 Task Task = new Task(id,time) ; //create task
	        	 sort[tasks_counter]= Task;
	        	 
	        	 tasks_counter += 1;
	        	 
	        	 }
	        			
        		 countline +=1;
        		 line = reader.readLine() ; //next line
        		 
        		}
    	 
    	 reader.close(); //close the file
    	 
     }catch(IOException e){
    	 System.out.println ("Error reading line!") ;
     }
     
     
     int n = sort.length;
     
     // Build max heap
     for (int i = n / 2 - 1; i >= 0; i--) {
       heapify(sort, n, i);
     }
 
     // Heap sort
     for (int i = n - 1; i >= 0; i--) {
       Task temp = sort[0];
       sort[0] = sort[i];
       sort[i] = temp;
       
       heapify(sort, i, 0);
       
     }
   
 	
 	for(int i=total_tasks-1; i>=0; i--) {
 		
 		Task Task = sort[i]; //create task
 		 max_processor = newQueue.getmax();
   	 
 		max_processor.processedTasks.insertAtFront(Task);
        
        newQueue.insert(max_processor);
	}
 	
 	 if(total_tasks != tasks_counter) {
		 System.err.println("The number of tasks in the file does not match the number of tasks declared in second line.");
	 }
     else {
		 if(total_tasks<50) {
			 //print processors in increasing active time order
			 while(!newQueue.isEmpty()){
			  
				 max_processor = newQueue.getmax();
				 System.out.println("id "+Integer.toString(max_processor.id)+", load = "+max_processor.active_time+" : "+max_processor.task_times);
    		 
			 }
		 }
		 else {
			 while(!newQueue.isEmpty()) {
				 max_processor = newQueue.getmax();
			 }
		 }
		 
     }
     
 	 return max_processor.active_time;
}
     
    
      
     
     static void heapify(Task[] arr, int n, int i) {
         // Find largest among root, left child and right child
         int largest = i;
         int l = 2 * i + 1;
         int r = 2 * i + 2;
     
         if (l < n && arr[l].time > arr[largest].time)
           largest = l;
     
         if (r < n && arr[r].time > arr[largest].time)
           largest = r;
     
         
         if (largest != i) {
           Task swap = arr[i];
           arr[i] = arr[largest];
           arr[largest] = swap;
     
           heapify(arr, n, largest);
         }
         
         
       }

    

}

