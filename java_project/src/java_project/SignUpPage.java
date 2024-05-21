package java_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpPage extends JFrame{
	
	DatabaseConnect database = new DatabaseConnect();
    private JTextField name = new JTextField();
    private JTextField lastName = new JTextField();
    private JTextField email = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton signUpButton = new JButton("Kayıt Ol");
    private JTextField username = new JTextField();
	private JLabel username_label = new JLabel("Kullanıcı Adı: ");

    public SignUpPage() {
    	
        setTitle("THY Kayıt Ekranı");
        setSize(500, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel username_label = new JLabel("Kullanıcı Adı: ");
        username_label.setBounds(50,20,80,30);
        JLabel nameLabel = new JLabel("Adı:");
        nameLabel.setBounds(50, 60, 80, 30);
        JLabel lastNameLabel = new JLabel("Soyadı:");
        lastNameLabel.setBounds(50, 100, 80, 30);
        JLabel emailLabel = new JLabel("E-posta:");
        emailLabel.setBounds(50, 140, 80, 30);
        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setBounds(50, 180, 80, 30);

        signUpButton = new JButton("Kayıt Ol");
        signUpButton.setBounds(150, 290, 100, 25);
        
        
        username.setBounds(260,20,165,30);
        name.setBounds(260, 60, 165, 30);
        lastName.setBounds(260, 100, 165, 30);
        email.setBounds(260, 140, 165, 30);
        password.setBounds(260, 180, 165, 30);
        
        panel.add(username_label);
        panel.add(username);
        panel.add(nameLabel);
        panel.add(name);
        panel.add(lastNameLabel);
        panel.add(lastName);
        panel.add(emailLabel);
        panel.add(email);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(new JLabel()); // Boş etiket ekleyerek butonların altta ortalanmasını sağlar
        panel.add(signUpButton);
        
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String username2 = username.getText();
                String name2 = name.getText();
                String lastName2 = lastName.getText();
                String email2 = email.getText();
                String password2 = new String(password.getPassword());

                String query = "INSERT INTO users (username, name, lastName, email, password) VALUES ('" + 
                                username2 + "', '" + name2 + "', '" + lastName2 + "', '" + email2 + "', '" + password2 + "')";

                // Veritabanına sorguyu gönder
                database.islemler(query);

                JOptionPane.showMessageDialog(SignUpPage.this, "Kayıt işlemi başarılı!");
                dispose();
                Login login = new Login();
                
            }
        });

        add(panel);

        setVisible(true);
    }

   
}

