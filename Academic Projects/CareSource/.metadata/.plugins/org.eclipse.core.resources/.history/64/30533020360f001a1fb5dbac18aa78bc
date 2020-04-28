package edu.neu.csye6200;

/**
 * Validating Login User credentials
 * @author Trivedh Audurthi
 *
 */
public class LoginController {
	
	public LoginController() {
	}
	
	/**
	 * Check for entered credentials
	 * @param id userID
	 * @param password user password
	 * @return boolean
	 */
	public boolean check(String id,String password){
		LoginModel lm = new LoginModel();
		if(lm.csvRead(id, password)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks performed while registering new user
	 * @param id creating userID
	 * @param pwd creating password
	 * @param cpwd password checking
	 * @return message regarding registration
	 */
	public String register(String id,String pwd,String cpwd) {
		if(check(id,pwd))
			return "This user id is already taken";
		if(!pwd.equals(cpwd))
			return "Please enter same password in both feilds";
		else {
			LoginModel lm = new LoginModel();
			lm.csvData(id,pwd);
			return "Registration is successful";
		}
	}
}
