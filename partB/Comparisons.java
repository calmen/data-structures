package erg2;

import java.util.Random;
import java.io.*;

public class Comparisons {
	public static void main(String[] args) {
		
		int[] n = {100,225,400};
		int x;
		String path = "";
		double average_1 = 0;
		double average_2 = 0;
		
		for(int i = 0;i<n.length;i++) {
			for(int y =0;y<10;y++) {
				try {
					  String name = "txt"+"_"+(i+1)+"_"+(y+1)+".txt";
					  FileWriter myWriter = new FileWriter(name);
				      int processors = (int) Math.round(((Math.sqrt(n[i]))));
				      myWriter.write(Integer.toString(processors)+"\n");
				      myWriter.write(Integer.toString(n[i])+"\n");
				      x = 1;
				      
				      while(x<=n[i]) {
				    	  
				    	  Random rand = new Random();
				    	  int task_time = rand.nextInt(200);
				    	  myWriter.write(Integer.toString(x)+" "+Integer.toString(task_time)+"\n");
				    	  x++;
					      
				      }
				      
				      myWriter.close();
				      
				      }catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				      }
				
			}
		}
		
		for(int i = 0;i<n.length;i++) {
			
			for(int y =0;y<10;y++) {
				path = "txt"+"_"+(i+1)+"_"+(y+1)+".txt";
				int makespan_1 = Greedy.Algorithm_1(path);
				int makespan_2 = Sort.HeapSort(path);
				average_1 += makespan_1;
				average_2 += makespan_2;
			}
			
			System.out.println("Number of Tasks = "+n[i]);
			System.out.println("Average Makespan using Algorithm 1 = "+(average_1)/n[i]);
			System.out.println("Average Makespan using Algorithm 2 = "+(average_2)/n[i]);
			
		}
	}

}
