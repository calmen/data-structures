package erg3;

public class WordFreq {
	private String word;
	private int frequency;
	
	public WordFreq(String word) {
		this.word = word;
		this.frequency += 1;
	}
	
	public String key() {
		return this.word;
	}
	
	public void increase() {
		this.frequency +=1;
	}
	
	public String toString() {
		return "word = "+this.word+" , frequency = "+this.frequency;
	}
	
	public int get_frequency() {
		return this.frequency;
	}
}
