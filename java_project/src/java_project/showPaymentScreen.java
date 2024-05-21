package java_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class showPaymentScreen extends JFrame{
	
	  private JLabel navbarLabel = new JLabel("      Adım3:ÖDEME");
	  private JLabel cardNumberLabel = new JLabel("Kart Numarası:");
      private JTextField cardNumberField = new JTextField();
      JLabel amountLabel = new JLabel("Ödenecek Tutar:");
      JLabel amountLabel2 = new JLabel();
      JLabel cvvLabel = new JLabel("CVV:");
      JPasswordField cvvField = new JPasswordField();
	
	public showPaymentScreen(int price) {
		
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ekranı ortala
        setLayout(new BorderLayout());
        
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new GridLayout(2,0));
        navbarLabel.setFont(new Font("Arial", Font.BOLD, 18));
        navbarLabel.setForeground(Color.WHITE);
        
        navbarPanel.add(navbarLabel);
        
        JLabel bos5 = new JLabel("AAA");
        bos5.setFont(new Font("Arial", Font.BOLD, 20));
        bos5.setForeground(Color.decode("#2C2966"));
        navbarPanel.add(bos5);
        navbarPanel.setBackground(Color.decode("#2C2966"));
        add(navbarPanel,BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);

        panel.add(cvvLabel);
        panel.add(cvvField);

        amountLabel2.setText(String.valueOf(price));
        Font boldFont = new Font("Arial", Font.BOLD, 14);
        amountLabel2.setForeground(Color.red);
        // Font'u amountField'a ayarlayın
        amountLabel2.setFont(boldFont);
        amountLabel2.setEnabled(false);
        panel.add(amountLabel);
        panel.add(amountLabel2);
        
        JPanel esasPanel = new JPanel();
        esasPanel.setLayout(new BorderLayout());
        Border border = BorderFactory.createLineBorder(Color.decode("#318082"),5);
        esasPanel.setBorder(border);
        esasPanel.add(panel,BorderLayout.CENTER);
        
        
        JButton payButton = new JButton("Ödemeyi Tamamla");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "Rezervasyonunuz onaylanmıştır. iyi günler dileriz :)");
                dispose();
            }
        });
        esasPanel.setBackground(Color.decode("#2C2966"));
        esasPanel.add(payButton,BorderLayout.SOUTH);
        add(esasPanel,BorderLayout.CENTER);
        JLabel bos = new JLabel("AAA");
        bos.setFont(new Font("Arial", Font.BOLD, 30));
        bos.setForeground(Color.decode("#2C2966"));
        JLabel bos2 = new JLabel("AAA");
        bos2.setFont(new Font("Arial", Font.BOLD, 30));
        bos2.setForeground(Color.decode("#2C2966"));
        JLabel bos3 = new JLabel("AAA");
        bos3.setFont(new Font("Arial", Font.BOLD, 30));
        bos3.setForeground(Color.decode("#2C2966"));
        add(bos,BorderLayout.SOUTH);
        add(bos2,BorderLayout.WEST);
        add(bos3,BorderLayout.EAST);
        getContentPane().setBackground(Color.decode("#2C2966"));
        setVisible(true);
	}

	

}
