package java_project;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;

public class PlanAndBook extends JFrame {
	
	
	DatabaseConnect database = new DatabaseConnect();

    // rezervasyon için
    private JRadioButton internationalRadio = new JRadioButton("Yurt dışı");
    private JRadioButton nationalRadio = new JRadioButton("Yurt içi");
    private JLabel fromLabel = new JLabel("Nereden");
    private JLabel toLabel = new JLabel("Nereye");
    private JLabel dateLabel1 = new JLabel("Kalkış Tarihi");
    private JLabel dateLabel2 = new JLabel("Dönüş Tarihi");
    // Uluslararası havalimanları için
    String[] internationalAirports = {"Almanya", "Fransa", "İngiltere", "İtalya", "İspanya", "Rusya", "Çin"};
    // Yurt içi havalimanları için
    String[] nationalAirports = {"İstanbul", "Ankara", "İzmir","Antalya", "Trabzon", "Bursa","Adana"};
    private JComboBox<String> from = new JComboBox<>(nationalAirports);
    private JComboBox<String> to = new JComboBox<>();
    String[] dates = {"2024-05-13","2024-05-14", "2024-05-15", "2024-05-16", "2024-05-17", "2024-05-18", "2024-05-19", "2024-05-20", "2024-05-21", "2024-05-22", "2024-05-23"};
    private JComboBox departure_date = new JComboBox(dates);
    private JComboBox destination_date = new JComboBox(dates);

    private JRadioButton goAndBackRadio = new JRadioButton("Gidiş-Dönüş");
    private JRadioButton justGoRadio = new JRadioButton("Tek yön");
    
    
    private JPanel flightsPanel; 

   
    public PlanAndBook() {
    	     //bütün nesneleri içine alacak panel
    	 JPanel anapanel = new JPanel();
         anapanel.setLayout(new BoxLayout(anapanel, BoxLayout.Y_AXIS));
          //Yurt içi yurt dışı group butonları
    	 JPanel radioPanel = new JPanel();
         radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

         ButtonGroup group = new ButtonGroup();
         group.add(internationalRadio);
         group.add(nationalRadio);
         internationalRadio.setFont(new Font("Arial", Font.BOLD, 14));
         nationalRadio.setFont(new Font("Arial", Font.BOLD, 14));
         
         radioPanel.add(internationalRadio);
         radioPanel.add(nationalRadio);
         anapanel.add(radioPanel);
         
  
         nationalRadio.setSelected(true);
         if(nationalRadio.isSelected()) {
        	 updateAirportComboBoxes(false);
         }
         else {
        	 updateAirportComboBoxes(true);
         }
         // Uluslararası ve yurt içi seçeneklerine göre havalimanlarını güncelle
         internationalRadio.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 updateAirportComboBoxes(true);
             }
         });

         nationalRadio.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 updateAirportComboBoxes(false);
             }
         });
         
         //gidiş dönüş tek yön butonları
    	 JPanel goOrBackPanel = new JPanel();
    	 goOrBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

         ButtonGroup goOrBackGroup = new ButtonGroup();
         goOrBackGroup.add(goAndBackRadio);
         goOrBackGroup.add(justGoRadio);
         goAndBackRadio.setFont(new Font("Arial", Font.BOLD, 14));
         justGoRadio.setFont(new Font("Arial", Font.BOLD, 14));
         
         goAndBackRadio.setSelected(true);
         destination_date.setEnabled(true); 
         
      // Gidiş-Dönüş veya Tek Yön seçeneklerine göre dönüş tarihini kontrol etme
         goAndBackRadio.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 destination_date.setEnabled(true); 
             }
         });

         justGoRadio.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 destination_date.setEnabled(false); 
             }
         });

         
         goOrBackPanel.add(goAndBackRadio);
         goOrBackPanel.add(justGoRadio);
         anapanel.add(goOrBackPanel);
         
         
         

         //nereden nereye tarih kısımlarını içeren panel
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(2, 3,10,5));
         panel.setSize(150, 30);

         panel.add(fromLabel);
         panel.add(toLabel);
         panel.add(dateLabel1);
         panel.add(dateLabel2);
         
         panel.add(from);
         panel.add(to);
         panel.add(departure_date);
         panel.add(destination_date);
         // date kısımlarını uçuşa göre düzenleme
         from.addItemListener((ItemListener) new ItemListener() {
        	 public void itemStateChanged(ItemEvent e) {
      	        if (e.getStateChange() == ItemEvent.SELECTED) {
      	        	if(nationalRadio.isSelected()==true) {
      	        		updateDepartureDates(from.getSelectedItem().toString(), to.getSelectedItem().toString());
          	            updateDastinationDates(from.getSelectedItem().toString(), to.getSelectedItem().toString());
      	        	}
      	        	else {
      	        		updateDepartureDatesForInternational(from.getSelectedItem().toString(), to.getSelectedItem().toString());
         	            updateDastinationDatesForInternational(from.getSelectedItem().toString(), to.getSelectedItem().toString());
      	        	}
      	            
      	            
      	           
      	        }
      	    }
				
        	});
         
         to.addItemListener((ItemListener) new ItemListener() {
     	    public void itemStateChanged(ItemEvent e) {
     	        if (e.getStateChange() == ItemEvent.SELECTED) {
     	        	if(nationalRadio.isSelected()==true) {
     	        		updateDepartureDates(from.getSelectedItem().toString(), to.getSelectedItem().toString());
         	            updateDastinationDates(from.getSelectedItem().toString(), to.getSelectedItem().toString());
     	        	}
     	        	else {
     	        		updateDepartureDatesForInternational(from.getSelectedItem().toString(), to.getSelectedItem().toString());
        	            updateDastinationDatesForInternational(from.getSelectedItem().toString(), to.getSelectedItem().toString());
     	        	}
     	            
     	            
     	           
     	        }
     	    }
				
     	});

        	
         anapanel.add(panel);

        //UÇUŞ ARAMA KISMI
        
        // Uçuş arama düğmesi
        JButton search = new JButton("Uçuş Ara");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Uçuşları veritabanından al ve listele
                showFlightsFromDatabase();
            }
        });
        
        JPanel searchPanel = new JPanel();
        searchPanel.add(search);
        anapanel.add(searchPanel);
        
      //UÇUŞ ARAMA KISMI BİTİŞ
        
     
        
        // Uçuşların listeleneceği paneli oluştur
     // Uçuşların listeleneceği paneli oluştur
        flightsPanel = new JPanel();
        flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.Y_AXIS));

        // JScrollPane oluştur ve flightsPanel'i içine yerleştir
        JScrollPane scrollPane1 = new JScrollPane(flightsPanel);
        
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(scrollPane1.getPreferredSize().width * 8, scrollPane1.getPreferredSize().height * 80));

        // Ana panele JScrollPane'i ekle
        anapanel.add(scrollPane1);
     
     
        getContentPane().add(anapanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1100,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//CONSTURCTOR BİTİŞİ
    
    
 // Uçuşları veritabanından getir ve listele
    private void searchFlightsForInternational(String from, String to, String departureDate) {
        // SQL sorgusunu oluştur
        String query = "SELECT * FROM internationalFlights WHERE point_of_departure = '" + from + "' AND point_of_destination = '" + to + "' AND departure_date = '" + departureDate + "'";

        // Veritabanından uçuşları getir ve listele
        try {
            ResultSet rs = database.baglanti(query);

            // Eğer uçuşlar bulunamazsa
            if (!rs.next()) {
            	 JPanel noFlightPanel = new JPanel(new GridLayout(1, 1));
                 JLabel noFlightLabel = new JLabel("Bu tarihler arasında uçuş bulunamamaktadır");
                 noFlightLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Büyük puntoyla
                 noFlightLabel.setForeground(Color.RED); // Kırmızı renkte
                 noFlightPanel.add(noFlightLabel);

                 // Ana paneldeki uçuşlar paneline ekle
                 flightsPanel.add(noFlightPanel);

                // Ana paneldeki uçuşlar paneline ekle
                flightsPanel.add(noFlightPanel);
            } else {
                // Uçuş bilgilerini al ve listele
            	do{
                    // Uçuş bilgilerini al
                    int flightNumber = rs.getInt("flight_id");
                    String departureAirport = rs.getString("point_of_departure");
                    String destinationAirport = rs.getString("point_of_destination");
                    String departureTime = rs.getString("departure_time");
                    String arrivalTime = rs.getString("destination_time");

                    // Kalkış ve varış zamanlarını parse et
                    LocalTime departureLocalTime = LocalTime.parse(departureTime);
                    LocalTime arrivalLocalTime = LocalTime.parse(arrivalTime);

                    // Uçuş süresini hesapla
                    Duration duration = Duration.between(departureLocalTime, arrivalLocalTime);
                    long hours = duration.toHours();
                    long minutes = duration.toMinutes() % 60;

                    // Tahmini uçuş süresini oluştur
                    String estimatedDuration = hours + " saat " + minutes + " dakika";

                    // Uçuş bilgilerini içeren bir panel oluştur
                    JPanel flightPanel = new JPanel(new GridLayout(5, 1));
                    flightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel flightInfoLabel = new JLabel("Uçuş Numarası: " + flightNumber);
                    JLabel departureLabel = new JLabel("Nereden: " + departureAirport);
                    JLabel destinationLabel = new JLabel("Nereye: " + destinationAirport);
                    JLabel departureTimeLabel = new JLabel("Kalkış Zamanı: " + departureTime);
                    JLabel arrivalTimeLabel = new JLabel("Varış Zamanı: " + arrivalTime);
                    JLabel durationLabel = new JLabel("Tahmini Uçuş Süresi: " + estimatedDuration);

                    flightPanel.add(flightInfoLabel);
                    flightPanel.add(departureLabel);
                    flightPanel.add(destinationLabel);
                    flightPanel.add(departureTimeLabel);
                    flightPanel.add(arrivalTimeLabel);
                    flightPanel.add(durationLabel);

                    // Rezervasyon yap düğmesi
                    JButton bookButton = new JButton("Rezervasyon Yap");
                    bookButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
				            System.out.println("Uçuş No: " + flightNumber + " numaralı uçuş için"+"Rezervasyon yapılıyor... ");
				            SeatReservationForInternational frame = new SeatReservationForInternational(flightNumber);
				            dispose();
				  
				            
				        }
							
						
					}); // Hangi uçuşa rezervasyon yapılacağını belirtmek için parametre geçiyoruz
                    flightPanel.add(bookButton);
                    flightPanel.add(bookButton);

                    // Ana paneldeki uçuşlar paneline ekle
                    flightsPanel.add(flightPanel);
                } while (rs.next());
            }

            // Eklenen uçuşları yeniden çiz
            flightsPanel.revalidate();
            flightsPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Uçuşları veritabanından getir ve listele
    private void searchFlights(String from, String to, String departureDate) {
        // SQL sorgusunu oluştur
        String query = "SELECT * FROM nationalFlights WHERE point_of_departure = '" + from + "' AND point_of_destination = '" + to + "' AND departure_date = '" + departureDate + "'";

        // Veritabanından uçuşları getir ve listele
        try {
            ResultSet rs = database.baglanti(query);

            // Eğer uçuşlar bulunamazsa
            if (!rs.next()) {
            	 JPanel noFlightPanel = new JPanel(new GridLayout(1, 1));
                 JLabel noFlightLabel = new JLabel("Bu tarihler arasında uçuş bulunamamaktadır");
                 noFlightLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Büyük puntoyla
                 noFlightLabel.setForeground(Color.RED); // Kırmızı renkte
                 noFlightPanel.add(noFlightLabel);

                 // Ana paneldeki uçuşlar paneline ekle
                 flightsPanel.add(noFlightPanel);

                // Ana paneldeki uçuşlar paneline ekle
                flightsPanel.add(noFlightPanel);
            } else {
                // Uçuş bilgilerini al ve listele
            	do{
                    // Uçuş bilgilerini al
                    int flightNumber = rs.getInt("flight_id");
                    String departureAirport = rs.getString("point_of_departure");
                    String destinationAirport = rs.getString("point_of_destination");
                    String departureTime = rs.getString("departure_time");
                    String arrivalTime = rs.getString("destination_time");

           
                    LocalTime departureLocalTime = LocalTime.parse(departureTime);
                    LocalTime arrivalLocalTime = LocalTime.parse(arrivalTime);

                    // Uçuş süresini hesapla
                    Duration duration = Duration.between(departureLocalTime, arrivalLocalTime);
                    long hours = duration.toHours();
                    long minutes = duration.toMinutes() % 60;

                    // Tahmini uçuş süresini oluştur
                    String estimatedDuration = hours + " saat " + minutes + " dakika";

                    // Uçuş bilgilerini içeren bir panel oluştur
                    JPanel flightPanel = new JPanel(new GridLayout(5, 1));
                    flightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel flightInfoLabel = new JLabel("Uçuş Numarası: " + flightNumber);
                    JLabel departureLabel = new JLabel("Nereden: " + departureAirport);
                    JLabel destinationLabel = new JLabel("Nereye: " + destinationAirport);
                    JLabel departureTimeLabel = new JLabel("Kalkış Zamanı: " + departureTime);
                    JLabel arrivalTimeLabel = new JLabel("Varış Zamanı: " + arrivalTime);
                    JLabel durationLabel = new JLabel("Tahmini Uçuş Süresi: " + estimatedDuration);

                    flightPanel.add(flightInfoLabel);
                    flightPanel.add(departureLabel);
                    flightPanel.add(destinationLabel);
                    flightPanel.add(departureTimeLabel);
                    flightPanel.add(arrivalTimeLabel);
                    flightPanel.add(durationLabel);

                    // Rezervasyon yap düğmesi
                    JButton bookButton = new JButton("Rezervasyon Yap");
                    bookButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
				            System.out.println("Uçuş No: " + flightNumber + " numaralı uçuş için"+"Rezervasyon yapılıyor... ");
				            SeatReservation frame = new SeatReservation(flightNumber);
				            dispose();       
				        }
							
						
					}); // Hangi uçuşa rezervasyon yapılacağını belirtmek için parametre geçiyoruz
                    flightPanel.add(bookButton);

                    // Ana paneldeki uçuşlar paneline ekle
                    flightsPanel.add(flightPanel);
                }while (rs.next());
            }

            // Eklenen uçuşları yeniden çiz
            flightsPanel.revalidate();
            flightsPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private void searchFlights2(String from, String to, String departureDate, String destinationDate) {
    	
    	boolean foundOutboundFlights = false;
    	
        // Gidiş uçuşlarını arama sorgusu
        String query = "SELECT * FROM nationalFlights WHERE point_of_departure = '" + from + "' AND point_of_destination = '" + to + "' AND departure_date = '" + departureDate + "'";

        try {
            ResultSet rs = database.baglanti(query);

            while (rs.next()) {
                // Gidiş uçuş bilgilerini al
                int flightNumber = rs.getInt("flight_id");
                String departureAirport = rs.getString("point_of_departure");
                String destinationAirport = rs.getString("point_of_destination");
                String departureTime = rs.getString("departure_time");
                String arrivalTime = rs.getString("destination_time");

                // Kalkış ve varış zamanlarını parse et
                LocalTime departureLocalTime = LocalTime.parse(departureTime);
                LocalTime arrivalLocalTime = LocalTime.parse(arrivalTime);

                // Uçuş süresini hesapla
                Duration duration = Duration.between(departureLocalTime, arrivalLocalTime);
                long hours = duration.toHours();
                long minutes = duration.toMinutes() % 60;

                // Tahmini uçuş süresini oluştur
                String estimatedDuration = hours + " saat " + minutes + " dakika";

                // Gidiş uçuşlarını içeren bir panel oluştur
                
                JPanel flightPanel = new JPanel(new GridLayout(5, 1));
                flightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JLabel flightInfoLabel = new JLabel("Uçuş Numarası: " + flightNumber);
                JLabel departureLabel = new JLabel("Nereden: " + departureAirport);
                JLabel destinationLabel = new JLabel("Nereye: " + destinationAirport);
                JLabel departureTimeLabel = new JLabel("Kalkış Zamanı: " + departureTime);
                JLabel arrivalTimeLabel = new JLabel("Varış Zamanı: " + arrivalTime);
                JLabel durationLabel = new JLabel("Tahmini Uçuş Süresi: " + estimatedDuration);

                flightPanel.add(flightInfoLabel);
                flightPanel.add(departureLabel);
                flightPanel.add(destinationLabel);
                flightPanel.add(departureTimeLabel);
                flightPanel.add(arrivalTimeLabel);
                flightPanel.add(durationLabel);

                

                // Dönüş uçuşlarını arama sorgusu
                String query2 = "SELECT * FROM nationalFlights WHERE point_of_departure = '" + to + "' AND point_of_destination = '" + from + "' AND departure_date = '" + destinationDate + "'";
                ResultSet rs2 = database.baglanti(query2);

                JPanel returnFlightsPanel = new JPanel(); // Dönüş uçuşlarını içerecek panel

                while (rs2.next()) {
                	
                	foundOutboundFlights = true;
                	
                    // Dönüş uçuş bilgilerini al
                    int returnFlightNumber = rs2.getInt("flight_id");
                    String returnDepartureAirport = rs2.getString("point_of_departure");
                    String returnDestinationAirport = rs2.getString("point_of_destination");
                    String returnDepartureTime = rs2.getString("departure_time");
                    String returnArrivalTime = rs2.getString("destination_time");

                    // Kalkış ve varış zamanlarını parse et
                    LocalTime returnDepartureLocalTime = LocalTime.parse(returnDepartureTime);
                    LocalTime returnArrivalLocalTime = LocalTime.parse(returnArrivalTime);

                    // Uçuş süresini hesapla
                    Duration returnDuration = Duration.between(returnDepartureLocalTime, returnArrivalLocalTime);
                    long returnHours = returnDuration.toHours();
                    long returnMinutes = returnDuration.toMinutes() % 60;

                    // Tahmini uçuş süresini oluştur
                    String returnEstimatedDuration = returnHours + " saat " + returnMinutes + " dakika";

                    // Dönüş uçuşlarını içeren bir panel oluştur
                    JPanel returnFlightPanel = new JPanel(new GridLayout(5, 2));

                    JLabel returnFlightLabel = new JLabel("Dönüş Uçağı ");
                    JLabel returnFlightInfoLabel = new JLabel("Uçuş Numarası: " + returnFlightNumber);
                    JLabel returnDepartureLabel = new JLabel("Nereden: " + returnDepartureAirport);
                    JLabel returnDestinationLabel = new JLabel("Nereye: " + returnDestinationAirport);
                    JLabel returnDepartureTimeLabel = new JLabel("Kalkış Zamanı: " + returnDepartureTime);
                    JLabel returnArrivalTimeLabel = new JLabel("Varış Zamanı: " + returnArrivalTime);
                    JLabel returnDurationLabel = new JLabel("Tahmini Uçuş Süresi: " + returnEstimatedDuration);

                    returnFlightPanel.add(returnFlightLabel);
                    returnFlightPanel.add(returnFlightInfoLabel);
                    returnFlightPanel.add(returnDepartureLabel);
                    returnFlightPanel.add(returnDestinationLabel);
                    returnFlightPanel.add(returnDepartureTimeLabel);
                    returnFlightPanel.add(returnArrivalTimeLabel);
                    returnFlightPanel.add(returnDurationLabel);

                    // Rezervasyon yap düğmesi
                    JButton bookButton = new JButton("Rezervasyon Yap");
                    bookButton.setBackground(Color.GREEN);
                    bookButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
				            System.out.println("Uçuş No: " + flightNumber + " numaralı uçuş için"+"Rezervasyon yapılıyor... ");
				            SeatReservationGoBack frame = new SeatReservationGoBack(flightNumber,returnFlightNumber);
				            dispose();
				  
				            
				        }
							
						
					}); // Hangi uçuşa rezervasyon yapılacağını belirtmek için parametre geçiyoruz
                    flightPanel.add(bookButton);
                    returnFlightPanel.add(bookButton);
                    returnFlightsPanel.add(flightPanel);
                    returnFlightsPanel.add(returnFlightPanel);

                 
                    
                }
                

                flightsPanel.add(returnFlightsPanel);
                
            }
            if (!foundOutboundFlights) {
                
            	JPanel noFlightPanel = new JPanel(new GridLayout(1, 1));
                JLabel noFlightLabel = new JLabel("Bu tarihler arasında uçuş bulunamamaktadır");
                noFlightLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Büyük puntoyla
                noFlightLabel.setForeground(Color.RED); // Kırmızı renkte
                noFlightPanel.add(noFlightLabel);
                
                flightsPanel.add(noFlightPanel);
            }

            // Eklenen uçuşları yeniden çiz
            flightsPanel.revalidate();
            flightsPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void searchFlightsForInternational2(String from, String to, String departureDate, String destinationDate) {
    	
    	boolean foundOutboundFlights = false;
    	
        // Gidiş uçuşlarını arama sorgusu
        String query = "SELECT * FROM internationalFlights WHERE point_of_departure = '" + from + "' AND point_of_destination = '" + to + "' AND departure_date = '" + departureDate + "'";

        try {
            ResultSet rs = database.baglanti(query);

            while (rs.next()) {
                // Gidiş uçuş bilgilerini al
                int flightNumber = rs.getInt("flight_id");
                String departureAirport = rs.getString("point_of_departure");
                String destinationAirport = rs.getString("point_of_destination");
                String departureTime = rs.getString("departure_time");
                String arrivalTime = rs.getString("destination_time");

                // Kalkış ve varış zamanlarını parse et
                LocalTime departureLocalTime = LocalTime.parse(departureTime);
                LocalTime arrivalLocalTime = LocalTime.parse(arrivalTime);

                // Uçuş süresini hesapla
                Duration duration = Duration.between(departureLocalTime, arrivalLocalTime);
                long hours = duration.toHours();
                long minutes = duration.toMinutes() % 60;

                // Tahmini uçuş süresini oluştur
                String estimatedDuration = hours + " saat " + minutes + " dakika";

                // Gidiş uçuşlarını içeren bir panel oluştur
                
                JPanel flightPanel = new JPanel(new GridLayout(5, 2,10,10));
                flightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JLabel flightInfoLabel = new JLabel("Uçuş Numarası: " + flightNumber);
                JLabel departureLabel = new JLabel("Nereden: " + departureAirport);
                JLabel destinationLabel = new JLabel("Nereye: " + destinationAirport);
                JLabel departureTimeLabel = new JLabel("Kalkış Zamanı: " + departureTime);
                JLabel arrivalTimeLabel = new JLabel("Varış Zamanı: " + arrivalTime);
                JLabel durationLabel = new JLabel("Tahmini Uçuş Süresi: " + estimatedDuration);

                flightPanel.add(flightInfoLabel);
                flightPanel.add(departureLabel);
                flightPanel.add(destinationLabel);
                flightPanel.add(departureTimeLabel);
                flightPanel.add(arrivalTimeLabel);
                flightPanel.add(durationLabel);
                JLabel demo = new JLabel("    ");
                flightPanel.add(demo);

                

                // Dönüş uçuşlarını arama sorgusu
                String query2 = "SELECT * FROM internationalFlights WHERE point_of_departure = '" + to + "' AND point_of_destination = '" + from + "' AND departure_date = '" + destinationDate + "'";
                ResultSet rs2 = database.baglanti(query2);

                JPanel returnFlightsPanel = new JPanel(); // Dönüş uçuşlarını içerecek panel

                while (rs2.next()) {
                	
                	foundOutboundFlights = true;
                	
                    // Dönüş uçuş bilgilerini al
                    int returnFlightNumber = rs2.getInt("flight_id");
                    String returnDepartureAirport = rs2.getString("point_of_departure");
                    String returnDestinationAirport = rs2.getString("point_of_destination");
                    String returnDepartureTime = rs2.getString("departure_time");
                    String returnArrivalTime = rs2.getString("destination_time");

                    // Kalkış ve varış zamanlarını parse et
                    LocalTime returnDepartureLocalTime = LocalTime.parse(returnDepartureTime);
                    LocalTime returnArrivalLocalTime = LocalTime.parse(returnArrivalTime);

                    // Uçuş süresini hesapla
                    Duration returnDuration = Duration.between(returnDepartureLocalTime, returnArrivalLocalTime);
                    long returnHours = returnDuration.toHours();
                    long returnMinutes = returnDuration.toMinutes() % 60;

                    // Tahmini uçuş süresini oluştur
                    String returnEstimatedDuration = returnHours + " saat " + returnMinutes + " dakika";

                    // Dönüş uçuşlarını içeren bir panel oluştur
                    JPanel returnFlightPanel = new JPanel(new GridLayout(6, 3,5,5));

                    JLabel returnFlightLabel = new JLabel("Dönüş Uçağı ");
                    JLabel returnFlightInfoLabel = new JLabel("Uçuş Numarası: " + returnFlightNumber);
                    JLabel returnDepartureLabel = new JLabel("Nereden: " + returnDepartureAirport);
                    JLabel returnDestinationLabel = new JLabel("Nereye: " + returnDestinationAirport);
                    JLabel returnDepartureTimeLabel = new JLabel("Kalkış Zamanı: " + returnDepartureTime);
                    JLabel returnArrivalTimeLabel = new JLabel("Varış Zamanı: " + returnArrivalTime);
                    JLabel returnDurationLabel = new JLabel("Tahmini Uçuş Süresi: " + returnEstimatedDuration);

                    returnFlightPanel.add(returnFlightLabel);
                    returnFlightPanel.add(returnFlightInfoLabel);
                    returnFlightPanel.add(returnDepartureLabel);
                    returnFlightPanel.add(returnDestinationLabel);
                    returnFlightPanel.add(returnDepartureTimeLabel);
                    returnFlightPanel.add(returnArrivalTimeLabel);
                    returnFlightPanel.add(returnDurationLabel);

                    // Rezervasyon yap düğmesi
                    JButton bookButton = new JButton("Rezervasyon Yap");
                    bookButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
				            System.out.println("Uçuş No: " + flightNumber + " numaralı uçuş için"+"Rezervasyon yapılıyor... ");
				            SeatReservationGoBackForInternational frame = new SeatReservationGoBackForInternational(flightNumber,returnFlightNumber);
				            dispose();
				            
				        }
							
						
					}); // Hangi uçuşa rezervasyon yapılacağını belirtmek için parametre geçiyoruz
                    flightPanel.add(bookButton);
                    returnFlightPanel.add(bookButton);
                    returnFlightsPanel.add(flightPanel);
                    
                    returnFlightsPanel.add(returnFlightPanel);
                }
                flightsPanel.add(returnFlightsPanel);
            }
            if (!foundOutboundFlights) {
                
            	JPanel noFlightPanel = new JPanel(new GridLayout(1, 1));
                JLabel noFlightLabel = new JLabel("Bu tarihler arasında uçuş bulunamamaktadır");
                noFlightLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Büyük puntoyla
                noFlightLabel.setForeground(Color.RED); // Kırmızı renkte
                noFlightPanel.add(noFlightLabel);
                
                flightsPanel.add(noFlightPanel);
            }

            // Eklenen uçuşları yeniden çiz
            flightsPanel.revalidate();
            flightsPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    
    // uçuş araya basınca uçuş listesini gösteriyor
    private void showFlightsFromDatabase() {
    	// her uçuş aradığında paneli temizle
    	flightsPanel.removeAll();
    	
    	if(nationalRadio.isSelected()== true) {
    		if(justGoRadio.isSelected()== true) {
        		// Seçilen kalkış yeri, varış yeri ve kalkış tarihini al
                String selectedFrom = from.getSelectedItem().toString();
                String selectedTo = to.getSelectedItem().toString();
                String selectedDepartureDate = departure_date.getSelectedItem().toString();

                // Uçuşları veritabanından getir ve listele
                searchFlights(selectedFrom, selectedTo, selectedDepartureDate);
                flightsPanel.revalidate();
                flightsPanel.repaint();
        	}
        	
        	else if(goAndBackRadio.isSelected()==true) {
        		// Seçilen kalkış yeri, varış yeri ve kalkış tarihini al
                String selectedFrom = from.getSelectedItem().toString();
                String selectedTo = to.getSelectedItem().toString();
                String selectedDepartureDate = departure_date.getSelectedItem().toString();
                String selectedDestinationDate = destination_date.getSelectedItem().toString();

                searchFlights2(selectedFrom, selectedTo, selectedDepartureDate,selectedDestinationDate);
                flightsPanel.revalidate();
                flightsPanel.repaint();
        		
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Tek yön veya gidiş-dönüş butonlarından birini seçiniz.");
        	}
    	}
    	
    	else if(internationalRadio.isSelected()== true) {
    		
    		if(justGoRadio.isSelected()== true) {
        		// Seçilen kalkış yeri, varış yeri ve kalkış tarihini al
                String selectedFrom = from.getSelectedItem().toString();
                String selectedTo = to.getSelectedItem().toString();
                String selectedDepartureDate = departure_date.getSelectedItem().toString();

                searchFlightsForInternational(selectedFrom, selectedTo, selectedDepartureDate);
                // Eklenen uçuşları yeniden çiz
                flightsPanel.revalidate();
                flightsPanel.repaint();
        	}
        	
        	else if(goAndBackRadio.isSelected()==true) {
        		// Seçilen kalkış yeri, varış yeri ve kalkış tarihini al
                String selectedFrom = from.getSelectedItem().toString();
                String selectedTo = to.getSelectedItem().toString();
                String selectedDepartureDate = departure_date.getSelectedItem().toString();
                String selectedDestinationDate = destination_date.getSelectedItem().toString();

                // Uçuşları veritabanından getir ve listele
                searchFlightsForInternational2(selectedFrom, selectedTo, selectedDepartureDate,selectedDestinationDate);
                // Eklenen uçuşları yeniden çiz
                flightsPanel.revalidate();
                flightsPanel.repaint();
        		
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Tek yön veya gidiş-dönüş butonlarından birini seçiniz.");
        	}
    	}
    	
    	
    	
    }
    
 // Yurt içi ve yurt dışı seçimlerine göre havalimanlarını güncelleyen metot
    private void updateAirportComboBoxes(boolean isInternational) {
        
        to.removeAllItems();
        
        String[] airports = isInternational ? internationalAirports : nationalAirports;
        for (String airport : airports) {
  
            to.addItem(airport);
        }
    }
    private void updateDepartureDates(String from, String to) {
        // ComboBox'ı temizle
        departure_date.removeAllItems();

        String query = "SELECT DISTINCT departure_date FROM nationalFlights WHERE point_of_departure = '" + from + "' AND point_of_destination = '" + to + "'";
        
        try {
        	
            ResultSet rs = database.baglanti(query);

            while (rs.next()) {
                String departureDate = rs.getString("departure_date");
                departure_date.addItem(departureDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateDastinationDates(String from, String to) {
        // ComboBox'ı temizle
    	destination_date.removeAllItems();

        String query = "SELECT DISTINCT departure_date FROM nationalFlights WHERE point_of_departure = '" + to + "' AND point_of_destination = '" + from + "'";
        
        try {
        	// Sorguyu hazırla
           
            ResultSet rs = database.baglanti(query);
 
            while (rs.next()) {
                String destiantionDate = rs.getString("departure_date");
                destination_date.addItem(destiantionDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateDepartureDatesForInternational(String from, String to) {
        // ComboBox'ı temizle
        departure_date.removeAllItems();

        String query = "SELECT DISTINCT departure_date FROM internationalFlights WHERE point_of_departure = '" + from + "' AND point_of_destination = '" + to + "'";
        
        try {
        	// Sorguyu hazırla
           
            ResultSet rs = database.baglanti(query);
  
            while (rs.next()) {
                String departureDate = rs.getString("departure_date");
                departure_date.addItem(departureDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateDastinationDatesForInternational(String from, String to) {
        // ComboBox'ı temizle
    	destination_date.removeAllItems();

        
        String query = "SELECT DISTINCT departure_date FROM internationalFlights WHERE point_of_departure = '" + to + "' AND point_of_destination = '" + from + "'";
        
        try {
        	// Sorguyu hazırla
           
            ResultSet rs = database.baglanti(query);
            
            // Her bir departure_date'i combo box'a ekleme
            while (rs.next()) {
                String destiantionDate1 = rs.getString("departure_date");
                destination_date.addItem(destiantionDate1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    


    public static void main(String[] args) {
        PlanAndBook frame = new PlanAndBook();
    }
}
