package edu.neu.csye6200;


public class NW {
	    private static final int MAX = 20;
	    private static NW[] instances = new NW[MAX];
	    static {
	        for (int i = 0; i < MAX; i++) {
	            instances[i] = new NW(i);
	        }
	    }
	    private int count;

	    private static int counter = 0; 

	    private NW(int count) {
	        this.count = count;
	    }

	    public static synchronized NW getInstance() {
	        if (counter < MAX) {
	            return instances[counter++];
	        }
	        throw new RuntimeException("Out of new instances.");
	    }
	}
