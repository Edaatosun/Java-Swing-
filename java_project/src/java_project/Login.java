package java_project;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame{
	
	DatabaseConnect database = new DatabaseConnect();
	
	private JButton loginButton = new JButton("Giriş Yap");
	private JButton signUpButton = new JButton("Kayıt Ol");
	private JPanel mainPanel = new JPanel();
	private JLabel password_label = new JLabel("Şifre: ");
	private JPasswordField password = new JPasswordField();
	private JTextField username = new JTextField();
	private JLabel username_label = new JLabel("Kullanıcı Adı: ");
	

	public Login() {
		
        mainPanel.setLayout(null); // kendim belirleyeceğim hangi noktada olacaklarını
        
        
         // setbounds labelların genişlik yükseklik gibi parametrerlerini ayarlamamızı sağlıyor.
    	username_label.setBounds(10, 20, 80, 25); 
    	// x= 10 y = 20 kordinatlarından başa 80 piksel genişliğinde 25 piksel yüksekliğinde olduğunu belirt.
    	mainPanel.add(username_label);
    	
        username.setBorder(new LineBorder(Color.GRAY,3));
        username.setBackground(Color.decode("#e4eee9"));
        username.setBounds(100, 20, 165, 25);
        mainPanel.add(username);
        
        password_label.setBounds(10, 50, 80, 25);
        mainPanel.add(password_label);
        
        password.setBorder(new LineBorder(Color.GRAY,3));
        password.setBackground(Color.decode("#e4eee9"));
        password.setBounds(100, 50, 165, 25);
        mainPanel.add(password);
        
        loginButton.setBounds(40,120,100,25);
        loginButton.setBackground(Color.decode("#77dd77"));
        
        mainPanel.add(loginButton); 
        
        signUpButton.setBounds(160, 120, 100, 25);
        signUpButton.setBackground(Color.decode("#5ae258"));
        mainPanel.add(signUpButton);
        mainPanel.setBackground(Color.decode("#d6d6cd"));
   
        
        add(mainPanel, BorderLayout.CENTER); 
      
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username2 = username.getText();
                String password2 = new String(password.getPassword());

                // Veritabanı ile kontrol et
                boolean loginSuccessful = checkLogin(username2, password2);

                if (loginSuccessful) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı!");
                    
                    MainPage frame = new MainPage(username2);
                    dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Giriş bilgileriniz hatalıdır. Bilgilerini kontrol ederek tekrar giriş yapınız.");
                }
            }
        });
        
        
        signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpPage signUp = new SignUpPage();
			}
		});
        
        
	
	}
	
	public String getUsername() {
    	return username.getText();
    }
    
	// Kullanıcı adı ve şifre doğrulaması yapacak metod
    public boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        
        try {
        	ResultSet rs = database.baglanti(query);
            
            // ResultSet boş değilse, kullanıcı adı ve şifre doğrudur
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        // ResultSet boşsa, kullanıcı adı ve şifre yanlıştır
        return false;
    }
	
	public static void main(String[]args) {
		Login frame = new Login();
		frame.setSize(350,200);
		frame.setTitle("Login Page");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	  
	
	

}
