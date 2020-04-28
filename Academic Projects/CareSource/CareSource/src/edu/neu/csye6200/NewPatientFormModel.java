package edu.neu.csye6200;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writing New Patient Details to database
 * @author Priyanka Maheshwari
 */

public class NewPatientFormModel {
	
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
	String csvFile = ".\\developer.csv";
	FileWriter fileWriter;
	
	public void newPatientDatabase(String tname, String tmno, String tdate, String tmonth, String tyear, String date, String month, String year, String gender, String ward, String age) {
		try {
        	fileWriter = new FileWriter(csvFile, true);
        	fileWriter.append(tname);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(tmno);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(tdate+"/"+tmonth+"/"+tyear);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(date+"/"+month+"/"+year);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(age);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(gender);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(ward);
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
