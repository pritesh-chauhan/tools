package edu.neu.csye6200;

import java.io.*;
import java.util.*;
import com.opencsv.*;


public class PharmacyMed implements PharmacyAPI{
	String file = "Pharmacy.csv";
	List<String> pharma = new ArrayList<String>();

	String org;
	String dest;

	public void newData(String str) {
		pharma.add(str);
	}
	public void writeToList() {

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

		}catch (Exception e) { 
			e.printStackTrace(); }
	}

	public void write(){
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
			e.printStackTrace(); 
		} 
	}


	public String search(String details) {
		String val = "";
		try { 
			
			FileReader filereader = new FileReader(file); 
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord; 
			String[] data = details.split(",");
			boolean res= false;
			while ((nextRecord = csvReader.readNext()) != null)
			{	
			if(nextRecord[0].equals(data[0]))
				{
				res = true;
				writeToList();
				org = String.join(",", nextRecord);
				
				int NStock = Integer.parseInt(nextRecord[1]);
				NStock= Integer.parseInt(nextRecord[1])+Integer.parseInt(data[1]);
				data[1] = Integer.toString(NStock);

				String newDetails=String.join(",", data);
				Collections.replaceAll(pharma, org, newDetails);
				
				System.out.println("Stock updated: "+newDetails);
				val += "Stock updated: "+newDetails; 
				write();
				break;

				}
			}
			if (res==false)
			{
				System.out.println("New record updated with: "+details);
				val += "New record updated with: "+details;
				writeToList();
				newData(details);
				write();
			}
			csvReader.close();
		
		}catch (Exception e) { e.printStackTrace(); }
		return val;
	}


	public static String Demo(String details) throws IOException {
		PharmacyMed ph = new PharmacyMed();
		return ph.search(details);
		
		}

	}

