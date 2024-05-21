package java_project;

import javax.swing.*;

import com.mysql.cj.protocol.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class help extends JFrame {
	
	DatabaseConnect database = new DatabaseConnect();
	
	 private JLabel navbarLabel = new JLabel("                                          YARDIM");
     private JLabel passengerLabel = new JLabel("Yolcu Numarası");
     private JTextField passenger = new JTextField(10); 
     private JButton ara = new JButton("ARA");

    public help() {
        setTitle("Uçuş Bilgi Paneli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        panel.setLayout(new BorderLayout());

        JPanel ucusPanel = new JPanel();
        ucusPanel.add(passengerLabel);
        ucusPanel.add(passenger);
        ucusPanel.add(ara);
        panel.add(ucusPanel, BorderLayout.CENTER);
      
        
        ara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int passenger_no = Integer.parseInt(passenger.getText());
                    ArrayList<String> flightDetails = getFlightDetailsFromDatabase(passenger_no);
                    
                    JOptionPane.showMessageDialog(null, flightDetails, "Uçuş Detayları", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçersiz yolcu numarası!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(panel);
       
        
        setSize(500, 300);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
 
    public ArrayList<String> getFlightDetailsFromDatabase(int passenger_no) {
    	ArrayList<String> flightDetails = new ArrayList<>();

        String queryNationalPassenger = "SELECT flight_id FROM nationalPassenger WHERE passenger_no = " + passenger_no;
        try {
            ResultSet resultSet = database.baglanti(queryNationalPassenger);
            while (resultSet.next()) {
            	
                String flightID = resultSet.getString("flight_id");
                System.out.println("uçak no :"+flightID);
                String queryNationalFlights = "SELECT * FROM nationalFlights WHERE flight_id = " + flightID;
                
                ResultSet flightResultSet = database.baglanti(queryNationalFlights);
                
                while (flightResultSet.next()) {
                	
                    String pointOfDeparture = flightResultSet.getString("point_of_departure");
                    String pointOfDestination = flightResultSet.getString("point_of_destination");
                    String departureDate = flightResultSet.getString("departure_date");
                    String departureTime = flightResultSet.getString("departure_time");
                    String destinationTime = flightResultSet.getString("destination_time");
                    
                    flightDetails.add("Flight ID: " + flightID +  
                    		", Nereden: " + pointOfDeparture + ", Nereye: " + pointOfDestination + ", Kalkış Tarihi: " + departureDate +
                    		", Kalkış saati: " + departureTime + ", Varış saati: " + destinationTime); 
                }
            }

            String queryInternationalPassenger = "SELECT flight_id FROM internationalPassenger WHERE passenger_no = " + passenger_no;
            ResultSet resultSet2 = database.baglanti(queryInternationalPassenger);
            while (resultSet2.next()) {
                String flightID = resultSet2.getString("flight_id");

                String queryInternationalFlights = "SELECT * FROM internationalFlights WHERE flight_id = " + flightID;
                ResultSet flightResultSet = database.baglanti(queryInternationalFlights);
                while (flightResultSet.next()) {
                    String pointOfDeparture = flightResultSet.getString("point_of_departure");
                    String pointOfDestination = flightResultSet.getString("point_of_destination");
                    String departureDate = flightResultSet.getString("departure_date");
                    String departureTime = flightResultSet.getString("departure_time");
                    String destinationTime = flightResultSet.getString("destination_time");
                    flightDetails.add("Uçuş Numarası: " + flightID +  
                    		", Nereden: " + pointOfDeparture + ", Nereye: " + pointOfDestination + ", Kalkış Tarihi: " + departureDate +
                    		", Kalkış saati: " + departureTime + ", Varış saati: " + destinationTime);     
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return flightDetails;
    }

    
   
}
