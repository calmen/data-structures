package erg1;

public class TagMatching {
    public static void main(String args[])
    {	
    	String path = args[0];
        LoadFile load = new LoadFile();
        load.Load_File(path);
    }

}