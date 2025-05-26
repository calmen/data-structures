package erg1;

import java.io.*;

public class ReadTxt {
	
	 IntQueueImpl newQ = new IntQueueImpl() ;
	 
	 public void Read_Txt(String data) {
		 String move ;
		 int shares ;
		 int price ;
		 int sumshares = 0 ;
		 int result = 0 ;
		 int restshares = 0 ;
		 int restprice = 0 ;
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
	    
	        	while (line != null) { 	//read lines one by one

	        		String [] text = line.split(" ") ;
	        		move = text[0] ;	//type of transaction buy or sell
	        		shares = Integer.parseInt(text[1]) ;	//
	        		price = Integer.parseInt(text[3]) ;
	        		if (move.equalsIgnoreCase("buy")) {
	        			sumshares = sumshares + shares ;
	        			
	        			
	        			newQ.put(shares);  
	        			newQ.put(price);
	        			
	        		//	System.out.println("you buy " + shares +" shares" + " for "+ price+ " price") ;
	        			
	        		}
	        		
	        		
	        		if(move.equalsIgnoreCase("sell") ){
	        			sumshares = sumshares - shares ;
	        			if(sumshares <0) {
	        				System.out.println("you cant sell...you dont have shares!") ;
	        				break;
	        			}
	        			
	        			else {
	        			System.out.println("you sell " + shares +" shares" + " for "+ price+ " price") ;
	        			 int i = shares ; //
	        			
	        			 while(i > 0  ) {                      
	        				if( restshares == 0) {              // if i dont have rest from previous sell
	        					int a = newQ.peek () ;	//number of shares last inserted in queue
	        				
	        					if(i >= a) { //check if the number of shares i want to sell is bigger than the number of shares i own
	        						newQ.get() ;	//get and remove number of shares
	        						int b = newQ.peek() ; //next couple of sell
	        						newQ.get() ;
	        				
	        						result += a*(price - b)  ;
	        					
	        						i = i - a ;
	        					}
	        					else {
	        						newQ.get() ;
	        						int b = newQ.peek() ; // price of shares last inserted in queue (matches a)
	        						newQ.get()  ;
	        				
	        						result = result + i*(price-b) ; 
	        						restshares = a-i ;
	        						restprice = b;
	        						i =0 ;
	        					
	        					}
	        				}
	        			
	        				if (i >= restshares && restshares!=0) {                     // rest 
	        				 
	        					 result = result + restshares*(price-restprice) ; 
	        					 i = shares - restshares ;
	        					 restshares = 0 ;
	        				 }
	        				 if (i < restshares && restshares!=0) {
	        					 result = result + i*(price-restprice) ;
	        					 restshares = restshares - i ;
	        					 i = 0 ;
	        					 
	        				 }   
	        				 
	        			 }
	        			
	        			 System.out.println("the result of sale is " + result) ;
	        			}
	        			
	        		}
	        		
	        		line = reader.readLine() ;
	        	}
	        	
	        	
	        	reader.close() ;
	        	
	        	
	        	
	        }
	        catch (IOException e) {
				System.out.println ("Error reading line!");
	        }
	        
	 }

	
}

