package erg2;

public class Processor implements Comparable<Processor> {
	
		int id = 0; //processor id
		int active_time = 0; //time the processor has run
		int time_difference = 0; //used to compare active times of different processors
		public String task_times = "";
		int x;
		
		
		List<Task> processedTasks = new List<Task>(); //empty list to store tasks , task id store from back and task time stored from front
	
		public Processor(int id) {
			this.id = id;
		}
		
		public int getActiveTime() {
			if(processedTasks.isEmpty()) {
				return active_time;
			}
			else {
				try {
					while(!processedTasks.isEmpty()) {
						x = processedTasks.removeFromFront().time;
						active_time += x;
						task_times += " "+Integer.toString(x);
					}
				}
				catch(EmptyListException ex) {}
				return active_time;
			}
		}
		
		
		public int compareTo(Processor y){
			this.time_difference = this.getActiveTime() - y.getActiveTime();
			if(time_difference == 0) {
				if(this.id > y.id) {
					return -1;
				}
				else {
					return 1;
				}
			}
			if(time_difference>0) {
				return -1;
			}
			else {
				return 1;
			}
		}
		
		
		
		
}
