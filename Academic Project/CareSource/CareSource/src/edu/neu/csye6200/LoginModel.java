package edu.neu.csye6200;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Reading logged in user database
 * @author Priyanka Maheshwari
 *
 */

public class LoginModel {
	
	/**
	 * Validating the credentials of the user while logging in
	 * @param id 
	 * @param pwd
	 * @return
	 */
	public boolean csvRead(String id,String pwd) {
		try(FileReader fr= new FileReader("validation credentials.txt");
				BufferedReader br= new BufferedReader(fr))
		{
			Scanner sc= new Scanner(br);
			while(sc.hasNext()){
				String inputLine=sc.nextLine();
				Scanner inLine= new Scanner(inputLine);
				inLine.useDelimiter(",");
				String s1=inLine.next();
				String s2=inLine.next();
				inLine.close();
				if(s1.equals(id)&&s2.equals(pwd))
					return true;
			}
			sc.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * New User Credentials added to the database
	 * @param id UserId
	 * @param pwd User password
	 */
	public void csvData(String id, String pwd) {
		try(FileWriter fw= new FileWriter("validation credentials.txt",true);
			BufferedWriter br= new BufferedWriter(fw);	){
			//br.newLine();
			String s=id+","+pwd;
			br.write(s);
			br.newLine();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
