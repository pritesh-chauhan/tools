package edu.neu.csye6200;

public class PatientDetails {
	private String name;
	private String insId;
	private String insTermDate;
	private String dob;
	private int age;
	private String gender;
	private String ward;
	
	public PatientDetails(String name, String insId, String insTermDate, String dob, int age, String gender,
			String ward) {
		super();
		this.name = name;
		this.insId = insId;
		this.insTermDate = insTermDate;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.ward = ward;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInsId() {
		return insId;
	}
	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getInsTermDate() {
		return insTermDate;
	}
	public void setInsTermDate(String insTermDate) {
		this.insTermDate = insTermDate;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: ").append(getName());
		sb.append(", Insurance Id: ").append(getInsId());
		sb.append(", Insurance Termination Date: ").append(getInsTermDate());
		sb.append(", DOB: ").append(getDob());
		sb.append(", Age: ").append(getAge());
		sb.append(", Gender: ").append(getGender());
		sb.append(", Ward Allotted: ").append(getWard());
		
		return sb.toString();
	}

	public static void demo() {
		System.out.println("PatientDetails.demo... start");
		
		FileUtil fileobj = new FileUtil();
		fileobj.readTextFile();		
		System.out.println("PatientDetails.demo... done");
	}

}
