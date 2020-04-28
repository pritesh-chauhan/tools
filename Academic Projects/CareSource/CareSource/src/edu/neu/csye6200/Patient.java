package edu.neu.csye6200;


public class Patient extends Person {

	private int Age;
	private String gender;
	private String DOB;
	private int insurance;
	
	public Patient(int age, String gender, String dOB, int insurance) {
		super();
		Age = age;
		this.gender = gender;
		DOB = dOB;
		this.insurance = insurance;
	}
	
	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}
	
}
