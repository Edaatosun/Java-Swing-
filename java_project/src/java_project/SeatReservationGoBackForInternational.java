package java_project;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ulusal uçuşlar için kullanılan

public class SeatReservationGoBackForInternational extends JFrame{
	
	
	DatabaseConnect database = new DatabaseConnect();
	private int totalPassengerCount;
	private int[] passengerNumbers;
	private int k = 0;
	private int k2 = 0;
	private int fiyat;
	
	
	 private JLabel seatLabel = new JLabel(" Gidiş uçuşu için Koltuk Seçimi");
	//koltuklar için
	    private JButton[][] seats;
	    private ArrayList<JButton> selectedSeats;
	    private ArrayList<JButton> selectedSeats2;
	    private JRadioButton economyRadio = new JRadioButton("Economy");
	    private JRadioButton businessRadio = new JRadioButton("Business");
	    private JLabel count = new JLabel("YOLCU SAYISI");
	    
	    
	
	public SeatReservationGoBackForInternational(int flightNumber,int returnFlightNumber) {
		
		int flight_id = flightNumber;
		
		JPanel anapanel = new JPanel();
        anapanel.setLayout(new BoxLayout(anapanel, BoxLayout.Y_AXIS));
        
		//KOLTUKLARIN PANEL BAŞLANGICI
        
     // koltuk sınıfları
        JPanel radioPanel2 = new JPanel();
        radioPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));

        ButtonGroup group1 = new ButtonGroup();
        group1.add(economyRadio);
        group1.add(businessRadio);
        
        economyRadio.setFont(new Font("Arial", Font.BOLD, 14));
        businessRadio.setFont(new Font("Arial", Font.BOLD, 14));

        radioPanel2.add(economyRadio);
        radioPanel2.add(businessRadio);
        economyRadio.setSelected(true);
        businessRadio.setSelected(false);
        anapanel.add(radioPanel2);
        
        JPanel passengerPanel = new JPanel();
        passengerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        count.setFont(new Font("Arial", Font.BOLD, 14));
        count.setForeground(Color.GRAY);
        passengerPanel.add(count);
        
        anapanel.add(passengerPanel);
        // Yolcu Türü için panel
        JPanel passengerTypePanel = new JPanel(new GridLayout(4, 2, 5, 5));

        JLabel adultLabel = new JLabel("Yetişkin:");
        JSpinner adultSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        JLabel childLabel = new JLabel("Çocuk:");
        JSpinner childSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        JLabel babyLabel = new JLabel("Bebek:");
        JSpinner babySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        JLabel studentLabel = new JLabel("Öğrenci:");
        JSpinner studentSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        // Stil
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = Color.BLACK;
        adultLabel.setFont(labelFont);
        adultLabel.setForeground(labelColor);
        childLabel.setFont(labelFont);
        childLabel.setForeground(labelColor);
        babyLabel.setFont(labelFont);
        babyLabel.setForeground(labelColor);
        studentLabel.setFont(labelFont);
        studentLabel.setForeground(labelColor);
        

        passengerTypePanel.add(adultLabel);
        passengerTypePanel.add(adultSpinner);
        passengerTypePanel.add(childLabel);
        passengerTypePanel.add(childSpinner);
        passengerTypePanel.add(babyLabel);
        passengerTypePanel.add(babySpinner);
        passengerTypePanel.add(studentLabel);
        passengerTypePanel.add(studentSpinner);
        
        anapanel.add(passengerTypePanel);
        //toplam yolcu sayısı paneli
        JPanel forPassenger = new JPanel();
        forPassenger.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel passengerCountLabel = new JLabel("Toplam yolcu sayısı:    ");
        passengerCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel passengerCount = new JLabel("0");
        passengerCount.setFont(new Font("Arial", Font.PLAIN, 18))
        ;
        forPassenger.add(passengerCountLabel);
        forPassenger.add(passengerCount);
        
        
        anapanel.add(forPassenger);
        
        //onayla butonu için panel
        JPanel panelButton2 = new JPanel();
        panelButton2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton completeButton2 = new JButton(" Onayla");
        
        panelButton2.add(completeButton2);
        anapanel.add(panelButton2);
        
        

        ChangeListener passengerChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Eğer öğrenci sayısı arttırıldıysa
                if ((int) studentSpinner.getValue() > 0) {
                    // Diğer yolcu türlerinin sayısını varsayılan olarak sıfıra ayarla
                    adultSpinner.setValue(0);
                    childSpinner.setValue(0);
                    babySpinner.setValue(0);
                    
                    // Diğer yolcu türlerinin arttırma düğmelerini devre dışı bırak
                    adultSpinner.setEnabled(false);
                    childSpinner.setEnabled(false);
                    babySpinner.setEnabled(false);
                } else {
                    // Öğrenci sayısı sıfıra ayarlandıysa, diğer yolcu türlerinin arttırma düğmelerini etkinleştir
                    adultSpinner.setEnabled(true);
                    childSpinner.setEnabled(true);
                    babySpinner.setEnabled(true);
                }
                
                // Toplam yolcu sayısını güncelle
                int totalPassengerCount1 = calculateTotalPassengerCount(adultSpinner, childSpinner, babySpinner, studentSpinner);
                passengerCount.setText(Integer.toString(totalPassengerCount1));
                
                totalPassengerCount = totalPassengerCount1;
            }
        };
       	
       	ChangeListener passengerChangeListener1 = new ChangeListener() {
       	    @Override
       	    public void stateChanged(ChangeEvent e) {
       	        // Eğer öğrenci sayısı arttırıldıysa
       	        if ((int) adultSpinner.getValue() > 0 || (int) childSpinner.getValue() > 0 || (int) babySpinner.getValue() > 0) {
       	            studentSpinner.setValue(0);
       	            
       	          studentSpinner.setEnabled(false);
       	        } else {
       	            // Öğrenci sayısı sıfıra ayarlandıysa, diğer yolcu türlerinin arttırma düğmelerini etkinleştir
       	           studentSpinner.setEnabled(true);
       	        }
       	        
       	  // Toplam yolcu sayısını güncelle
                int totalPassengerCount1 = calculateTotalPassengerCount(adultSpinner, childSpinner, babySpinner, studentSpinner);
                passengerCount.setText(Integer.toString(totalPassengerCount1));
                totalPassengerCount = totalPassengerCount1;
       	    }
       	};

       	// Öğrenci spinner'ına olay dinleyicisi ekle
       	adultSpinner.addChangeListener(passengerChangeListener1);
       	childSpinner.addChangeListener(passengerChangeListener1);
       	babySpinner.addChangeListener(passengerChangeListener1);
       	studentSpinner.addChangeListener(passengerChangeListener);
       	
        JPanel pricePanel = new JPanel();
        JLabel priceLabel = new JLabel("Fiyat:    ");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel priceCount = new JLabel("0");
        priceCount.setFont(new Font("Arial", Font.PLAIN, 18));
        pricePanel.add(priceLabel);
        pricePanel.add(priceCount);
     
       	
       	completeButton2.addActionListener(new ActionListener() {
       	    @Override
       	    public void actionPerformed(ActionEvent e) {
       	    	passengerNumbers = GetPassengerNumber(totalPassengerCount);
       	        System.out.println(passengerNumbers);
       	    	adultSpinner.setEnabled(false);
                childSpinner.setEnabled(false);
                babySpinner.setEnabled(false);
                studentSpinner.setEnabled(false);
       	    	
       	        int adultCount = (int) adultSpinner.getValue();
       	        int childCount = (int) childSpinner.getValue();
       	        int babyCount = (int) babySpinner.getValue();
       	        int studentCount = (int) studentSpinner.getValue();
       	        if(economyRadio.isSelected()==true) {
       	        	int adultPrice2 = 2000;
           	        int childPrice2 = 750;
           	        int babyPrice2 = 500;
           	        int studentPrice2 = 1000;
           	        
           	        fiyat = (adultCount*adultPrice2+childCount*childPrice2+babyCount*babyPrice2+studentCount*studentPrice2)*2;
           	        priceCount.setText(Integer.toString(fiyat));
           	         
        
           	        // Adult yolcuları eklemek için döngü
           	        for (int i = 0; i < adultCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('adult', " + adultPrice2 + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('adult', " + adultPrice2 + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	            
           	            
           	        }

           	        // Child yolcuları eklemek için döngü
           	        for (int i = 0; i < childCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('child', " + childPrice2 + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('child', " + childPrice2 + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	        }

           	        // Baby yolcuları eklemek için döngü
           	        for (int i = 0; i < babyCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('baby', " + babyPrice2 + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('baby', " + babyPrice2 + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	        }

           	        // Student yolcuları eklemek için döngü
           	        for (int i = 0; i < studentCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('student', " + studentPrice2 + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('student', " + studentPrice2 + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	        }
           	        
           	        
       	        }
       	        else {
       	        	int adultPrice = 5000;
           	        int childPrice = 2000;
           	        int babyPrice = 1000;
           	        int studentPrice = 2500;
           	        
           	        fiyat = (adultCount*adultPrice+childCount*childPrice+babyCount*babyPrice+studentCount*studentPrice);
        	        priceCount.setText(Integer.toString(fiyat));
        	         
      
           	        // Adult yolcuları eklemek için döngü
           	        for (int i = 0; i < adultCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('adult', " + adultPrice + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('adult', " + adultPrice + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	            
           	            
           	        }

           	        // Child yolcuları eklemek için döngü
           	        for (int i = 0; i < childCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('child', " + childPrice + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('child', " + childPrice + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	        }

           	        // Baby yolcuları eklemek için döngü
           	        for (int i = 0; i < babyCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('baby', " + babyPrice + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('baby', " + babyPrice + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	        }

           	        // Student yolcuları eklemek için döngü
           	        for (int i = 0; i < studentCount; i++) {
           	        	String query = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('student', " + studentPrice + ", " + flight_id + ");";
           	            database.islemler(query);
           	            String query2 = "INSERT INTO internationalPassenger(passenger_type, passenger_price, flight_id) VALUES ('student', " + studentPrice + ", " + returnFlightNumber + ");";
        	            database.islemler(query2);
           	        }
           	        
           	        
       	        }
       	        
       	        
       	       

       	        
       	    }
       	});

       
        // YOLCU TÜRLERİ İÇİN PANEL BİTTİ
        
        
      
        
        //Koltuk seçimi labelı için panele ekleme 
        JPanel seatSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        seatLabel.setFont(new Font("Arial", Font.BOLD, 14));
        seatLabel.setForeground(Color.GRAY);
        seatSelectionPanel.add(seatLabel);
        anapanel.add(seatSelectionPanel);

   
       selectedSeats = new ArrayList<>(); // seçilen koltukları tutmak için array

       // Koltuk düzeni için bir panel oluştur
       JPanel seatPanel = new JPanel(new GridLayout(4, 5, 10, 10));
       seats = new JButton[4][5];
       int seatNumber = 1000; 
       int[] seatNumberArray = updateSeatStatus(flight_id);
       int z = 0;
 
       // Koltukların oluşturulması
       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 5; j++) {
           	
        	   JButton seat = new JButton();
               seat.setPreferredSize(new Dimension(70, 40)); // Koltuk boyutu
               seats[i][j] = seat;
               seat.setText(Integer.toString(seatNumber));
               seatPanel.add(seat);
               seatNumber++;
               
               if(z<seatNumberArray.length) {
            	   String seatNumberStr = seat.getText();
            	   int seatNumberForUpdate = Integer.parseInt(seatNumberStr);
                   if(seatNumberForUpdate == seatNumberArray[z]) {
                   	seat.setBackground(Color.RED);
           			seat.setEnabled(false);
           			z++;
                   }
                   else {
                	   seat.setBackground(Color.GREEN); // Başlangıçta tüm koltuklar boş
                   }
               }else {
            	    seat.setBackground(Color.GREEN); // Başlangıçta tüm koltuklar boş
                    
               }
           
           
               seat.addActionListener(new ActionListener() {
                      	   
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					JButton button = (JButton) e.getSource();
					
		            if (selectedSeats.contains(button)) {
		                // Koltuk zaten seçiliyse, seçimden çıkar
		                button.setBackground(Color.GREEN);
		                selectedSeats.remove(button);
		                
       	        
		            } else {
		                // Koltuk henüz seçilmediyse, seçime ekle
		                button.setBackground(Color.RED);
		                selectedSeats.add(button);
		                int selectedSeatCount = selectedSeats.size(); 
		                
		                if (selectedSeatCount > totalPassengerCount) {
		                	JOptionPane.showMessageDialog(null, "Toplam yolcu sayısından fazla koltuk seçtiniz. Lütfen tekrar gözden geçirin.");
			       	         System.out.println("Toplam yolcu sayısı: " + totalPassengerCount);
			       	         button.setBackground(Color.GREEN);
			                 selectedSeats.remove(button);   
		       	        }
		                else {
		                	// Butonun etiketini alarak koltuk numarasını elde edin
			                System.out.println("seçilen koltuk sayısı"+selectedSeatCount);
			                String seatNumberStr = button.getText();
			                int seatNumber = Integer.parseInt(seatNumberStr);
			                System.out.println("seçilen koltuk numarası"+seatNumber);
			                int passenger = passengerNumbers[k];
			                System.out.println("k"+k);
			                System.out.println("yolcu numarası"+passenger);
			                int confirm = JOptionPane.showConfirmDialog(null, "Koltuk " + seatNumber + " numaralı yolcuya atanacaktır. Onaylıyor musunuz?");
			                // Eğer kullanıcı onaylarsa
			                if (confirm == JOptionPane.YES_OPTION) {
			                	
			                	button.setEnabled(false);
			                	 // Hangi radyo düğmesinin seçili olduğunu kontrol edin ve buna göre koltuk yerleştirme işlemini yapın
			                    if (economyRadio.isSelected()) {
			                        InsertSeat(seatNumber, passenger, true, "Economy",flight_id);
			                    } else if (businessRadio.isSelected()) {
			                        InsertSeat(seatNumber, passenger, true, "Business",flight_id);
			                    }
			                    k++;
			                }
			                else if(confirm == JOptionPane.NO_OPTION) {
			                	// Koltuk zaten seçiliyse, seçimden çıkar
				                button.setBackground(Color.GREEN);
				                selectedSeats.remove(button);
			                }
				             
		                }
		                
		            }
				}
			});
               
               
           }
       }

       anapanel.add(seatPanel);
       
       //KOLTUKLARIN PANEL BİTİŞİ Gidiş Uçuşu için
       
       //Koltuk seçimi labelı için panele ekleme 
       JPanel seatSelectionPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
       JLabel seatLabel = new JLabel(" Dönüş Uçuşu için Koltuk Seçimi");
       seatLabel.setFont(new Font("Arial", Font.BOLD, 14));
       seatLabel.setForeground(Color.GRAY);
       seatSelectionPanel2.add(seatLabel);
       anapanel.add(seatSelectionPanel2);

  
      selectedSeats2 = new ArrayList<>(); // seçilen koltukları tutmak için array

      // Koltuk düzeni için bir panel oluştur
      JPanel seatPanel2 = new JPanel(new GridLayout(4, 5, 10, 10));
      seats = new JButton[4][5];
      int seatNumber2 = 2000; 
      int[] seatNumberArray2 = updateSeatStatus(returnFlightNumber);
      int z2 = 0;
      System.out.println(seatNumberArray2[z2]);

      // Koltukların oluşturulması
      for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 5; j++) {
          	
       	   	  JButton seat2 = new JButton();
              seat2.setPreferredSize(new Dimension(70, 40)); // Koltuk boyutu
              seats[i][j] = seat2;
              seat2.setText(Integer.toString(seatNumber2));
              seatPanel2.add(seat2);
              seatNumber2++;
              
              if(z2<seatNumberArray2.length) {
           	   String seatNumberStr2 = seat2.getText();
           	   int seatNumberForUpdate2 = Integer.parseInt(seatNumberStr2);
           	   System.out.println("alınan butonun numarası"+seatNumberForUpdate2);
           	System.out.println("eşit mi diye bakılam butonun numarası"+seatNumberArray2[z2]);
                  if(seatNumberForUpdate2 == seatNumberArray2[z2]) {
                  	seat2.setBackground(Color.RED);
          			seat2.setEnabled(false);
          			System.out.println("z2.."+z2);
          			z2++;
                  }
                  else {
               	   seat2.setBackground(Color.GREEN); // Başlangıçta tüm koltuklar boş
                  }
              }else {
           	    seat2.setBackground(Color.GREEN); // Başlangıçta tüm koltuklar boş
                   
              }
          
          
              seat2.addActionListener(new ActionListener() {
                     	   
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					JButton button = (JButton) e.getSource();
					
		            if (selectedSeats.contains(button)) {
		                // Koltuk zaten seçiliyse, seçimden çıkar
		                button.setBackground(Color.GREEN);
		                selectedSeats.remove(button);
		                
      	        
		            } else {
		                // Koltuk henüz seçilmediyse, seçime ekle
		                button.setBackground(Color.RED);
		                selectedSeats2.add(button);
		                int selectedSeatCount = selectedSeats2.size(); 
		                
		                if (selectedSeatCount > totalPassengerCount) {
		                	JOptionPane.showMessageDialog(null, "Toplam yolcu sayısından fazla koltuk seçtiniz. Lütfen tekrar gözden geçirin.");
			       	         System.out.println("Toplam yolcu sayısı: " + totalPassengerCount);
			       	         button.setBackground(Color.GREEN);
			                 selectedSeats.remove(button);   
		       	        }
		                else {
		                	// Butonun etiketini alarak koltuk numarasını elde edin
			                System.out.println("seçilen koltuk sayısı2.."+selectedSeatCount);
			                String seatNumberStr2 = button.getText();
			                int seatNumber2 = Integer.parseInt(seatNumberStr2);
			                System.out.println("seçilen koltuk numarası2.."+seatNumber2);
			                int passenger = passengerNumbers[k2];
			                System.out.println("k2.."+k2);
			                System.out.println("yolcu numarası2.."+passenger);
			                int confirm = JOptionPane.showConfirmDialog(null, "Koltuk " + seatNumber2 + " numaralı yolcuya atanacaktır. Onaylıyor musunuz?");
			                // Eğer kullanıcı onaylarsa
			                if (confirm == JOptionPane.YES_OPTION) {
			                	
			                	button.setEnabled(false);
			                	 // Hangi radyo düğmesinin seçili olduğunu kontrol edin ve buna göre koltuk yerleştirme işlemini yapın
			                    if (economyRadio.isSelected()) {
			                        InsertSeat(seatNumber2, passenger, true, "Economy",returnFlightNumber);
			                    } else if (businessRadio.isSelected()) {
			                        InsertSeat(seatNumber2, passenger, true, "Business",returnFlightNumber);
			                    }
			                    k2++;
			                }
			                else if(confirm == JOptionPane.NO_OPTION) {
			                	// Koltuk zaten seçiliyse, seçimden çıkar
				                button.setBackground(Color.GREEN);
				                selectedSeats2.remove(button);
			                }
				             
		                }
		                
		            }
				}
			});
              
              
          }
      }

      anapanel.add(seatPanel2);
      
      //KOLTUKLARIN PANEL BİTİŞİ dönüş uçuşu
       
       //Boş Panel tanımı koltuklarla çok bitişik durmasın diye
       JPanel panelempty = new JPanel();
       anapanel.add(panelempty);
       
      
       anapanel.add(pricePanel);
     //tamamlama butonu için panel
       JPanel panelButton = new JPanel();
       JButton completeButton = new JButton("Rezervasyonu Tamamla");
       completeButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			showPaymentScreen frame = new showPaymentScreen(fiyat);
			dispose();
			
		}
	});
       panelButton.add(completeButton);
       anapanel.add(panelButton);
       getContentPane().add(anapanel);
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
       setSize(1100,650);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    private int calculateTotalPassengerCount(JSpinner adultSpinner, JSpinner childSpinner, JSpinner babySpinner, JSpinner studentSpinner) {
        int adultCount = (int) adultSpinner.getValue();
        int childCount = (int) childSpinner.getValue();
        int babyCount = (int) babySpinner.getValue();
        int studentCount = (int) studentSpinner.getValue();
        
        int totalPassengerCount = adultCount + childCount + babyCount + studentCount;
        
        return totalPassengerCount;
    }
    
    private void InsertSeat(int seatNumber, int passengerNo, boolean isOccupied, String seatClass, int flight_id) {
        String query = "INSERT INTO internationalseats (seat_number, passenger_no, is_occupied, class, flight_id) " +
                       "VALUES (" + seatNumber + ", " + passengerNo + ", " + isOccupied + ", '" + seatClass + "', " + flight_id + ");";
        database.islemler(query);
    }
     // Koltukların durumunu güncelleyen fonksiyon
    private int[] updateSeatStatus(int flight_id) {
        // Veritabanında ilgili koltuğun durumunu güncelle
        String updateQuery = "(SELECT seat_number FROM nationalseats WHERE flight_id = " + flight_id + " )";

        // Sorguyu veritabanında çalıştırın ve sonucu ResultSet nesnesine bağlayın
        ResultSet resultSet = database.baglanti(updateQuery);

        ArrayList<Integer> seatNumberList = new ArrayList<>(); // Dizi yerine bir liste kullanın

        try {
            // Sonuç kümesinde bir sonraki kayıt varsa
            while (resultSet.next()) {
                // Sonuç kümesinden koltuk numarasını alın ve listeye ekleyin
                int seatNumber = resultSet.getInt("seat_number");
                seatNumberList.add(seatNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (seatNumberList.isEmpty()) {
            seatNumberList.add(-1); // -1 gibi bir değer ekleyebiliriz
        }
        
  
        // Listeyi int dizisine dönüştürün
        int[] seatNumberArray = seatNumberList.stream().mapToInt(Integer::intValue).toArray();

        // Koltuk numaralarını içeren diziyi döndürün
        return seatNumberArray;
    }
    
  
    private int[] GetPassengerNumber(int totalPassenger) {
    	
        int[] passengerNumbers = new int[totalPassenger]; // Yolcu numaralarını tutmak için bir dizi tanımlayın

        // SQL sorgusunu tanımlayın
        String query = "(SELECT passenger_no FROM internationalPassenger ORDER BY passenger_no DESC LIMIT " + totalPassenger + ")";
        
        // Sorguyu veritabanında çalıştırın ve sonucu ResultSet nesnesine bağlayın
        ResultSet resultSet = database.baglanti(query);

        int index = 0; // Dizi içindeki indeksi izlemek için bir değişken tanımlayın

        try {
            // Sonuç kümesinde bir sonraki kayıt varsa
            while (resultSet.next()) {
                // Sonuç kümesinden yolcu numarasını alın ve diziye ekleyin
                passengerNumbers[index] = resultSet.getInt("passenger_no");
                index++; // Dizi içindeki bir sonraki boş hücreye geçin
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        // Yolcu numaralarını içeren diziyi döndürün
        return passengerNumbers;
    }
  


}
