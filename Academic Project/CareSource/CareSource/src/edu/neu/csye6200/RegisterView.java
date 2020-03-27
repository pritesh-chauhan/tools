package edu.neu.csye6200;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * UI for New User Registration
 * @author Pritesh Chauhan
 *
 */
public class RegisterView extends JFrame implements ActionListener {
	private Container c; 
    private JLabel title; 
    private JLabel name; 
    private JLabel res;
    private JTextField tname; 
    private JLabel pass; 
    private JPasswordField tpass; 
    private JLabel cpass; 
    private JPasswordField tcpass;
    private JButton register; 
    private JButton back; 
    
	public RegisterView() {
		setTitle("Welcome to CareSource"); 
        setBounds(500, 150, 900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        // Title Label
        title = new JLabel("Login to CareSource"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        c.add(title); 
  
        // Name Label
        name = new JLabel("UserName"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20)); 
        name.setSize(180, 20); 
        name.setLocation(275,150); 
        c.add(name); 
  
        // Name TextArea
        tname = new JTextField(); 
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 20); 
        tname.setLocation(480, 150); 
        c.add(tname); 
  
        // Password Label
        pass = new JLabel("Create Password"); 
        pass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        pass.setSize(300, 20); 
        pass.setLocation(275, 200); 
        c.add(pass); 
  
        // Password TextArea
        tpass = new JPasswordField(); 
        tpass.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tpass.setSize(190, 20); 
        tpass.setLocation(480, 200); 
        c.add(tpass); 
        
        // Check Password Label
        cpass = new JLabel("Confirm Password"); 
        cpass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        cpass.setSize(300, 20); 
        cpass.setLocation(275, 250); 
        c.add(cpass); 
  
        // Check Password TextArea
        tcpass = new JPasswordField(); 
        tcpass.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tcpass.setSize(190, 20); 
        tcpass.setLocation(480, 250); 
        c.add(tcpass); 
  
        // Registration Button
        register = new JButton("Register"); 
        register.setFont(new Font("Arial", Font.PLAIN, 15)); 
        register.setSize(100, 20); 
        register.setLocation(350, 300); 
        register.addActionListener(this); 
        c.add(register); 
        
        // Control to Previous UI Button
        back = new JButton("Go Back"); 
        back.setFont(new Font("Arial", Font.PLAIN, 15)); 
        back.setSize(100, 20); 
        back.setLocation(480, 300); 
        back.addActionListener(this); 
        c.add(back); 
        
        res = new JLabel(""); 
        res.setFont(new Font("Arial", Font.PLAIN, 20)); 
        res.setSize(500, 25); 
        res.setLocation(275, 350); 
        c.add(res); 
  
        setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == register) {
			
            LoginController val = new LoginController();
            String rval = "";
            String[] data = tname.getText().split("\\W+");
            String[] data1 = tname.getText().split("\\W+");
            String[] data2 = tname.getText().split("\\W+");
            if(!data[0].equals("") || !data2[0].equals("") || !data1[0].equals("")) {
            	rval = val.register(tname.getText(), tpass.getText(), tcpass.getText()); 
            }
            System.out.println(rval+" "+rval.contains("successful"));
            if(rval.contains("successful")){
            	setVisible(false);
            	new PatientCheckView();
            }
            else {
            	res.setText("Please check password");
            }
		}
		else if (e.getSource() == back) {
			setVisible(false);
			new PharmacyMed();
		}
		
	}
	
}
