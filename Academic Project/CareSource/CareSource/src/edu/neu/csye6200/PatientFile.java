package edu.neu.csye6200;

import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PatientFile {
	List<String> l = new ArrayList<String>();
	String file = ".\\main.csv";
	
	public void FileCopy() {
		try { 
			FileReader filereader = new FileReader(file); 
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord; 
			
		        while ((nextRecord = csvReader.readNext()) != null) 
		        { 
		        	String data = String.join(",", nextRecord);
		        	l.add(data);
		        }
		        System.out.println("List: "+l);
			csvReader.close();

		}catch (Exception e) { 
			e.printStackTrace(); }
	}
	
	public void FileCreation(String csvS) {
		PatientFileDatabase pfd = new PatientFileDatabase();
		String s = pfd.patientString(csvS);
		
		l.add(s);
		System.out.println(l);
		try { 
			FileWriter outputfile = new FileWriter(file); 
			CSVWriter writer = new CSVWriter(outputfile); 

			for(String x: l)
			{	
				String[] data = x.split(",");
				writer.writeNext(data); 
			}
			writer.close(); 
		} 
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}
		
	}

}
