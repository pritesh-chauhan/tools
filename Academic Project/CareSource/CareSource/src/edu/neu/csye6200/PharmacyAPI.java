package edu.neu.csye6200;

public interface PharmacyAPI {
	
	//Take all the data from the .csv file and add it to the list, 
	//and add the input received from the user to the same list 
	void writeToList();
	
	//Copy the contents of the list to .csv file
	void write();
	
	
	//Search the .csv file using the name
	String search(String inpt);
}
