package erg3;

public class Main {

	public static void main(String[] args) {
		BST tree = new BST();
		String file = args[0];
		tree.load(file);
		tree.printTreeAlphabetically(System.out);
		System.out.println("-----------------------");
		tree.print‘reeByFrequency(System.out);
	}

}
