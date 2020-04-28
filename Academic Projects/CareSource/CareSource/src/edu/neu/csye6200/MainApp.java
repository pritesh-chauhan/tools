package edu.neu.csye6200;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Priyanka Maheshwari
 *
 */
public class MainApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void createAndShowGUI() throws Exception {
		new LoginView();
//		new NewPatientFormView();
	}
}