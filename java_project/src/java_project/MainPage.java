
package java_project;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainPage extends JFrame {
	//navbar için 
	Login login = new Login();
    
    private JPanel navbarPanel = new JPanel();
    
    private ImagePanel imagePanel = new ImagePanel("C:/JavaProject/java_project/src/java_project/turkish_airlines.png");
    private JLabel label = new JLabel("TURKISH AIRLINES");
    private JButton planButton = new JButton("PLANLA&UÇ");
    private JButton helpButton = new JButton("YARDIM");
    private ImagePanel userLogo = new ImagePanel("C:/JavaProject/java_project/src/java_project/user.png");
    
    

    public MainPage(String username) {
    	// Navbar paneli düzenleme
        navbarPanel.setLayout(new FlowLayout());
        navbarPanel.setBackground(Color.decode("#2C2966"));
        
       
        // Sol kısım : label ve icon
        JPanel temp1 = new JPanel();
        temp1.setLayout(new FlowLayout());
        // iconun resmini ayarlama
        imagePanel.setPreferredSize(new Dimension(70, 50));
        temp1.add(imagePanel);
        label.setForeground(Color.WHITE);
        temp1.add(label);
        temp1.setOpaque(false); // Temp1 panelini şeffaf yap
        navbarPanel.add(temp1);

       

        // Sağ kısım: butonlar
        JPanel temp2 = new JPanel();
        temp2.setLayout(new FlowLayout());
        planButton.setBackground(Color.decode("#2C2966"));
        planButton.setForeground(Color.WHITE);
        planButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlanAndBook frame = new PlanAndBook();
				dispose();
				
			}
		});
        helpButton.setBackground(Color.decode("#2C2966"));
        helpButton.setForeground(Color.WHITE);
        
        helpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				help frame = new help();
				dispose();
			}
		});
        userLogo.setPreferredSize(new Dimension(50, 30));
        JLabel userLabel = new JLabel();
        userLabel.setText(username);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.ITALIC, 14)); 
        temp2.add(planButton);
        temp2.add(helpButton);
        temp2.add(userLogo);
        temp2.add(userLabel);
        temp2.setOpaque(false); // Temp2 panelini şeffaflaştır
        
        JLabel templabel = new JLabel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        templabel.setForeground(Color.decode("#2C2966"));
        navbarPanel.add(templabel);
        navbarPanel.add(temp2);  
        setLayout(new BorderLayout());

        add(navbarPanel,BorderLayout.NORTH);
        
        //arkaplan resmi
        Image arkaPlanImage=new ImageIcon(MainPage.class.getResource("ucak.jpg")).getImage();
		JLabel arkaPlanLabel=new JLabel();
		arkaPlanLabel.setIcon(new ImageIcon(arkaPlanImage));
		arkaPlanLabel.setSize(900,650);
		
		
		add(arkaPlanLabel,BorderLayout.CENTER);
        setSize(870, 590);
        setTitle("Main Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }

    
    
}
