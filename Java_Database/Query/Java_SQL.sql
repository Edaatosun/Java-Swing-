
use java;

create table users(
 uyelikNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username varchar(255),
 name varchar(255),
 lastname varchar(255),
  email varchar(255),
 password varchar(255)
);
select * from users;

ALTER TABLE users AUTO_INCREMENT = 1000;


INSERT INTO users (username, password, email, phoneNumber) 
VALUES ('kullanici1', 'sifre123', 'ornek@email.com', 12345671);


select * from users;
SELECT * FROM internationalFlights WHERE point_of_departure ="İzmir" AND point_of_destination = "Almanya" AND departure_date = "2024-06-03";
DROP TABLE users;
/*uluslararası*/
CREATE TABLE internationalFlights (
    flight_id INT PRIMARY KEY,
    point_of_departure VARCHAR(100),
    point_of_destination VARCHAR(100),
    departure_date DATE,
    departure_time TIME,
    destination_time TIME
); 


INSERT INTO internationalFlights (flight_id, point_of_departure, point_of_destination, departure_date, departure_time, destination_time)
VALUES 
(1, 'Ankara', 'Fransa', '2024-05-21', '09:00:00', '12:00:00'),
(2, 'Ankara', 'İngiltere', '2024-05-22', '10:00:00', '13:00:00'),
(3, 'Ankara', 'İtalya', '2024-05-23', '11:00:00', '14:00:00'),
(4, 'İstanbul', 'Fransa', '2024-05-28', '16:00:00', '19:00:00'),
(5, 'İstanbul', 'İngiltere', '2024-05-29', '17:00:00', '20:00:00'),
(6, 'İstanbul', 'İtalya', '2024-05-30', '18:00:00', '21:00:00'),
(7, 'İzmir', 'Almanya', '2024-06-03', '22:00:00', '01:00:00'),
(8, 'İzmir', 'Fransa', '2024-06-04', '23:00:00', '02:00:00'),
(9, 'Almanya', 'İstanbul', '2024-06-09', '08:00:00', '11:00:00'),
(10, 'Fransa', 'Ankara', '2024-06-10', '09:00:00', '12:00:00'),
(11, 'İngiltere', 'İzmir', '2024-06-11', '10:00:00', '13:00:00'),
(12, 'İtalya', 'Antalya', '2024-06-12', '11:00:00', '14:00:00'),
(13, 'İspanya', 'Trabzon', '2024-06-13', '12:00:00', '15:00:00'),
(14, 'Rusya', 'İstanbul', '2024-06-14', '13:00:00', '16:00:00'),
(15, 'Çin', 'Ankara', '2024-06-15', '14:00:00', '17:00:00'),
(16, 'Almanya', 'İzmir', '2024-06-16', '15:00:00', '18:00:00'),
(17, 'Fransa', 'Antalya', '2024-06-17', '16:00:00', '19:00:00'),
(18, 'İngiltere', 'Trabzon', '2024-06-18', '17:00:00', '20:00:00');





drop table internationalFlights;
/*ulusal*/
CREATE TABLE nationalFlights (
    flight_id INT PRIMARY KEY,
    point_of_departure VARCHAR(100),
    point_of_destination VARCHAR(100),
    departure_date DATE,
    departure_time TIME,
    destination_time TIME
); 

SELECT * FROM nationalFlights WHERE point_of_departure = "İstanbul" AND point_of_destination = "Ankara" AND departure_date = "2024-05-20";

-- Ulusal Uçuşlar
INSERT INTO nationalFlights (flight_id, point_of_departure, point_of_destination, departure_date, departure_time, destination_time) 
VALUES(22, 'Ankara', 'İstanbul', '2024-05-22', '10:00:00',  '12:00:00'),
 (21, 'İstanbul', 'Ankara', '2024-05-20', '15:00:00',  '17:30:00'),
(1, 'İstanbul', 'Ankara', '2024-05-20', '10:00:00',  '12:00:00'),
(2, 'Ankara', 'İzmir', '2024-05-21', '13:00:00',  '15:00:00'),
(3, 'İzmir', 'Antalya', '2024-05-22', '11:30:00',  '13:30:00'),
(4, 'Antalya', 'Trabzon', '2024-05-23', '09:00:00',  '11:00:00'),
(5, 'İstanbul', 'İzmir', '2024-05-24', '12:00:00',  '14:00:00'),
(6, 'Ankara', 'Antalya', '2024-05-25', '10:30:00', '12:30:00'),
(7, 'İzmir', 'Trabzon', '2024-05-26', '11:00:00',  '13:00:00'),
(8, 'Antalya', 'İstanbul', '2024-05-27', '14:00:00',  '16:00:00'),
(9, 'Trabzon', 'Ankara', '2024-05-28', '12:30:00',  '14:30:00'),
(10, 'İstanbul', 'Antalya', '2024-05-29', '09:30:00', '11:30:00'),
(11, 'Ankara', 'İstanbul', '2024-05-30', '15:00:00',  '17:00:00'),
(12, 'İzmir', 'Ankara', '2024-05-31', '16:30:00', '18:30:00'),
(13, 'Antalya', 'İzmir', '2024-06-01', '17:00:00',  '19:00:00'),
(14, 'Trabzon', 'Antalya', '2024-06-02', '18:00:00',  '20:00:00'),
(15, 'İstanbul', 'Trabzon', '2024-06-03', '19:00:00',  '21:00:00'),
(16, 'Ankara', 'İzmir', '2024-06-04', '20:00:00',  '22:00:00'),
(17, 'İzmir', 'İstanbul', '2024-06-05', '21:00:00',  '23:00:00'),
(18, 'Antalya', 'Ankara', '2024-06-06', '22:00:00',  '00:00:00'),
(19, 'Trabzon', 'İzmir', '2024-06-07', '23:00:00',  '01:00:00'),
(20, 'İstanbul', 'Ankara', '2024-06-08', '00:00:00',  '02:00:00');

CREATE TABLE nationalPassenger (
    passenger_no INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    passenger_type VARCHAR(255),
    passenger_price INT,
    flight_id INT,
    FOREIGN KEY (flight_id) REFERENCES nationalFlights(flight_id)
);

CREATE TABLE internationalPassenger (
    passenger_no INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    passenger_type VARCHAR(255),
    passenger_price INT,
    flight_id INT,
    FOREIGN KEY (flight_id) REFERENCES internationalFlights(flight_id)
);



CREATE TABLE nationalseats (
    seat_number INT,
    passenger_no INT,
    is_occupied BOOLEAN DEFAULT false,
    class VARCHAR(20),
    flight_id INT,
    PRIMARY KEY (seat_number,flight_id),
    FOREIGN KEY (passenger_no) REFERENCES nationalPassenger(passenger_no),
    FOREIGN KEY (flight_id) REFERENCES nationalpassenger(flight_id)
);

select * from nationalflights where flight_id = 1;
select seat_number from nationalseats where passenger_no = 107 AND flight_id = 1 ;

INSERT INTO nationalseats(seat_number,passenger_no,is_occupied,class,flight_id) VALUES (1,5,true,"eco",1);
select * from nationalseats;
select * from nationalpassenger;



CREATE TABLE internationalSeats (
    seat_number INT,
    passenger_no INT,
    is_occupied BOOLEAN,
    class VARCHAR(20),
    flight_id INT,
    PRIMARY KEY (seat_number,flight_id),
    FOREIGN KEY (passenger_no) REFERENCES internationalPassenger(passenger_no),
    FOREIGN KEY (flight_id) REFERENCES internationalpassenger(flight_id)
);


drop table nationalSeats;
drop table seats;
drop table passenger;
INSERT INTO seats (seat_number, flight_id, is_occupied, class) 
VALUES 
(1, 1, FALSE, 'economy'),
(2, 1, FALSE, 'economy'),
(3, 1, FALSE, 'economy'),
(4, 1, FALSE, 'economy'),
(1, 2, FALSE, 'economy');


select * from nationalPassenger;
/*toplam yolcu sayısı*/
SELECT passenger_no FROM nationalPassenger ORDER BY passenger_no DESC LIMIT 3;
SELECT seat_number FROM nationalseats WHERE flight_id = 2;
select * from nationalFlights;
select * from nationalpassenger;
select * from nationalseats;
select flight_id from nationalPassenger WHERE passenger_no = 113;
select seat_number,class,flight_id,point_of_departure,point_of_destination,departure_date,departure_time,destination_time from nationalSeats WHERE flight_id = 11;