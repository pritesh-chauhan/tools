package edu.neu.csye6200;

import java.io.*;
import java.util.*;
import com.opencsv.*;


public class InsuranceWrite implements InsuranceAPI {
	
	String file = "Insurance.csv";
		
	List<String> list = new ArrayList<String>();
	public void read() {
		 try { 
	        
			 FileReader filereader = new FileReader(file); 
			  
		        CSVReader csvReader = new CSVReader(filereader); 
		        String[] nextRecord; 
		  
		        while ((nextRecord = csvReader.readNext()) != null) { 
		            for (String cell : nextRecord) { 
		                System.out.print(cell + "\t"); 
		                
		            } 
		            System.out.println(); 
		        } 
		        csvReader.close();
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
		 
	}
	public void search(String inpt) {
		try { 
			
			String resultRow = null;
		    BufferedReader br = new BufferedReader(new FileReader("Insurance.csv"));
		    String line;
		    while ( (line = br.readLine()) != null ) 
		    {
		        String[] values = line.split(",");
		        
		       values[0]= values[0].replace("\"", "");
		       
		        if(values[0].equals(inpt))
		        {
		            resultRow = line.replace("\"", "");
		            break;
		        }
		    }
		    br.close();
		    System.out.println("\nThe search result");
		    System.out.println(resultRow);
		}catch (Exception e) { 
	        e.printStackTrace(); }
	}
	
	public void duplicate() {
		try { 

			CSVReader csvReader = new CSVReader(new FileReader("Insurance.csv"));
		    String[] line;
		    while ( (line = csvReader.readNext()) != null ) 
		    {
		    	String data = String.join(",", line);
		    list.add(data);	
		    }
		   csvReader.close();
		    
		    for (int i = 1; i < list.size(); i++) {
		    	  for (int j = i+1; j < list.size(); j++) {
		    	    if(list.get(i).equals(list.get(j))) {
		    	    	System.out.println("DUPLICATE FOUND..... AND REMOVED");
		    	    	
		    	    	list.remove(j);
		    	    	write();
		    	    	break;
		    	    }
		    	    
		    	}
		    }System.out.println("No duplicates found");
		   
		    
		   
		    
		}catch (Exception e) { 
	        e.printStackTrace(); }
	  }
	
		public void write() {
			
		    try { 
		        FileWriter outputfile = new FileWriter(file); 
		  
		        CSVWriter writer = new CSVWriter(outputfile); 
		        
		        
		        for(String x: list)
		        {	String[] data = x.split(",");
		        	 
		        	writer.writeNext(data); 
		        }
		        // closing writer connection 
		        writer.close(); 
		    } 
		    catch (IOException e) { 
		        // TODO Auto-generated catch block 
		        e.printStackTrace(); 
		    } 
		} 
		
	
	
	public static void Demo(String inpt) throws IOException {
		InsuranceWrite ins = new InsuranceWrite();
		ins.duplicate();
		ins.search(inpt);
		
	}
	
}




