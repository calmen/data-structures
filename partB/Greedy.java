package erg2;

import java.io.*;

public class Greedy {

	public static void main(String[] args) {
		
		String file = args[0];
		int makespan = Algorithm_1(file);
		//print Makespan
	    System.out.println("Makespan = "+makespan);
	}

	
	public static int Algorithm_1(String data) {
		
		 MaxPQ newQueue = new MaxPQ() ;//create priority queue object
		 File f = null;
	     BufferedReader reader = null;
	     String line;
	     int numberofprocessors = 0;
	     int total_tasks = 0;
	 	 int tasks_counter = 0; 
	 	 int id ;
	 	 int time ;
	 	 int countline = 0 ;
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
	        	 }	
	    		 
	    		 if(countline > 1){
	    			 
	    			 id = Integer.parseInt(text[0]) ;
		        	 time = Integer.parseInt(text[1]);
		        		
		        	 Task Task = new Task(id,time) ; //create task
		        	 
		        	 max_processor = newQueue.getmax();
		        	
		             max_processor.processedTasks.insertAtFront(Task); //store task time in front of list in processor object
		             //max_processor.processedTasks.insertAtBack(Task.id);//store task id in back of list in processor object
		             
		             newQueue.insert(max_processor);
		             
		        	 tasks_counter += 1;
		        		 
		        	 }
		        			
	        		 countline +=1;
	        		 line = reader.readLine() ; //next line
	        		 
	        		}
	    	 
    		 
	    	 reader.close(); //close the file
	    	 
	     }catch(IOException e){
	    	 System.out.println ("Error reading line!");
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
	
	
}

