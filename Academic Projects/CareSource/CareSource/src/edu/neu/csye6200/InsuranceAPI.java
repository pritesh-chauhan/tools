package edu.neu.csye6200;

public interface InsuranceAPI {
	//Read all the elements of .csv file
	void read();
	
	//Search the .csv file from the ID
	void search(String inpt);
	
	//Copy the the elements of .csv to a list
	//Search for duplicate elements and remove them from list
	void duplicate();
	
	//Re-write all the elements of the list to .csv file
	void write();
}
