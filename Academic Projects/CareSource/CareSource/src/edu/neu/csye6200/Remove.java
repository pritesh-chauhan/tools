package edu.neu.csye6200;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Remove {
	String file = ".\\developer1.csv";
	String org;	
	List<String> pharma = new ArrayList<>();
	void read() {
		try { 
			FileReader filereader = new FileReader(file); 
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord; 
		        
		        while ((nextRecord = csvReader.readNext()) != null) 
		        { 
		        	String data = String.join(",", nextRecord);
		        	pharma.add(data);
		        	
		        } 
		        csvReader.close();
	    } 
	    catch (Exception e) { e.printStackTrace(); } 
		}
	void write() {
		try { 
		    FileWriter outputfile = new FileWriter(file); 
		    CSVWriter writer = new CSVWriter(outputfile); 
		        
		        for(String x: pharma)
		        {	
		        	String[] data = x.split(",");
		        	
		        	writer.writeNext(data); 
		        	System.out.println("list updated");
		        }
		        writer.close(); 
		        
		    } 
		    catch (IOException e) { e.printStackTrace();} 
		    
	}
	
	String search(String name) {
		try {
			FileReader filereader = new FileReader(file); 
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord; 
			while ((nextRecord = csvReader.readNext()) != null){	
				if(nextRecord[0].equals(name)){
					org = String.join(",", nextRecord);
					int ind = pharma.indexOf(org);
					pharma.remove(ind);
					write();
					break;
					}
				else {
					return "Patient not found";
				}
			}
			csvReader.close();
		}
		catch (Exception e){ 
			e.printStackTrace(); 
		}
		return "";
	}
	
	public static String demo(String name) {
		Remove r = new Remove();
		r.read();
		String s = r.search(name);
		System.out.println(s);
		return s;
	}
}
	
	