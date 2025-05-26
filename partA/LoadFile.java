package erg1;

import java.io.*;

public class LoadFile {
	 StringStackImpl newStack = new StringStackImpl();
	 
	 public void Load_File (String data) {
		 File f = null;
	     BufferedReader reader = null;
	     String line;
	    
	     try {   //open text
	            f = new File(data);	
	        } catch (NullPointerException e) {
	            System.err.println("File not found!");
	        }

			//Checking if the text file has opened correctly for reading.	
	        try {
	            reader = new BufferedReader(new FileReader(f));
	        } catch (FileNotFoundException e) {
	            System.err.println("Error opening file!");
	        }
	        
	        try {
	        	line = reader.readLine() ;
	        	
	        	while (line != null) {
	        	
	        			
	        			if (line.trim().contains("<"))   {                                //true if have <
	        				int open = line.indexOf("<");                                    // open position
	        				if (line.charAt(open + 1) != '/' ) {                            //not close         
	        					String newS = line.substring(open);                        // open string
	        					int close = newS.indexOf('>');                            // close position
	        					String requiredString = newS.substring( 1, close); // save string
	        					newStack.push(requiredString);                          // push in stack
	        					line = newS.substring(close+1);                        //new line
	        				}
	                   else if (line.charAt(open + 1) == '/' ) {
	                    	    String newS = line.substring(open+2);                        // open string
		                        int close = newS.indexOf('>');                              // close position
		                        String requiredString = newS.substring(0, close);
		                      
		                        if(newStack.size() > 0 && newStack.peek().equals(requiredString)) {             // check first close tag if is equals with the first in stack
		                        	newStack.pop();                                      //remove
		                        }
		                        else{
		                        	newStack.push(requiredString);  
                                }
		                        line = newS.substring(close+1);                          // new line
	        				}
	        			} 
	        			else {
       					line = reader.readLine() ;	
	        			}
	        	}
	        	reader.close() ;
	        	
	        	if(!newStack.isEmpty())
	        		System.out.println("Tags are not closed correct!!!") ;
	        	else
	        		System.out.println("Tags are closed correct!!!") ;
	        	
	        	
	        }
	        catch (IOException e) {
				System.out.println ("Error reading line!");
	        }
	        
	 }

	
}