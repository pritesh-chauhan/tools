package edu.neu.csye6200;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AssignPharmacy implements AssignPharmacyAPI {
	String file = "Pharmacy.csv";
	String org;	
	String dest;
	List<String> pharma = new ArrayList<>();
	
	public void copy() {
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
		    catch (Exception e) { 
		        e.printStackTrace(); 
		    } 
	}
	
	@Override
	public void read() 
	{
		try {
				FileReader filereader = new FileReader(file); 
				CSVReader csvReader = new CSVReader(filereader);
				String[] nextRecord; 
		        
		        while ((nextRecord = csvReader.readNext()) != null) 
		        { 
		            for (String cell : nextRecord) 
		            { 
		                System.out.print(cell+"\t" ); 
		            } 
		            System.out.println(); 
		        } 
		        csvReader.close();
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 

	}

	@Override
	public void change() {
		try { 
			    FileWriter outputfile = new FileWriter(file); 
			    CSVWriter writer = new CSVWriter(outputfile); 
			        
			        for(String x: pharma)
			        {	
			        	String[] data = x.split(",");
			        	writer.writeNext(data); 
			        }
			        writer.close(); 
			    } 
			    catch (IOException e) { 
			        // TODO Auto-generated catch block 
			        e.printStackTrace();} 
			    
		}
	
	@Override
	public String assign(String str, int dose) {
		String val = "";
		try {
			
			CSVReader csvReader = new CSVReader(new FileReader(file));
		    String[] line;
		    while ((line = csvReader.readNext()) != null)
		    {
		       if(line[0].equals(str))
		       {   
		    	   org = String.join(",", line);
		    	   int NStock= Integer.parseInt(line[1]);
		    	   
		    	   if(NStock==0)
		    	   {
		    		   System.out.println("\nStock empty");
		    		   val += "Medicine: "+str+"Stock Empty...";
		    		   break;
		    	   }
		    	   
		    	   if(NStock<=10)
		    	   {
		    		   System.out.println("\nMedicine: "+ str+ " reserved only for emergency");
		    		   val += "Medicine: "+ str+ " Reserved only for Emergency...";
		    		 break;
		    	   }
		    	   
		    	   else 
		    	   {   System.out.println("\nMedicine assigned: "+ str);
		    		   NStock= NStock-dose;
		    	   	   line[1]= Integer.toString(NStock);
		    		   System.out.println("In stock: "+ NStock);
		    		   dest = String.join(",", line);
		    		   Collections.replaceAll(pharma,org, dest);
		    	   }
		    	}
	          }
	        csvReader.close(); 
//	       return true;
	    }catch (Exception e) { 
	        e.printStackTrace(); }
		return val;
	}
	
	String assign(String medname, String meddose, int j) {
		System.out.println(medname+", "+meddose+", "+j);
		String[] csvmed = medname.split(",");
		String[] csvdose = meddose.split(",");
		int x = 0;
		String val = "";
		for(int i=0;i<j;i++) 
		{
		String med = csvmed[i];
		
		int dose = Integer.parseInt(csvdose[i]);
		val += AssignPharmacy.Demo(med,dose);
		if(!val.contains("reserved"))
			x = 1;
		}
		if(x == 1)
			return val;
		return val;
	}

	public static String Demo(String med, int dose )
	{
		AssignPharmacy b = new AssignPharmacy();
		b.copy();
		
			String val = b.assign(med,dose);
			b.change();
			
			return val;
		
	}
}
