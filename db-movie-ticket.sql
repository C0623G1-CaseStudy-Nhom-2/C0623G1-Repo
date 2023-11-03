CREATE DATABASE movie_ticket;

USE movie_ticket;

CREATE TABLE categorys (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name_category VARCHAR(255) NOT NULL
);

CREATE TABLE movies (
 id INT PRIMARY KEY AUTO_INCREMENT,
 title VARCHAR(255) NOT NULL,
 description TEXT NOT NULL,
 release_date DATE NOT NULL,
 duration INT NOT NULL,
 id_cateogry INT NOT NULL,
 director VARCHAR(255) NOT NULL,
 avatar_url VARCHAR(255) NOT NULL,
 poster_url VARCHAR(255) NOT NULL,
 FOREIGN KEY (id_cateogry) REFERENCES categorys(id)
);

CREATE TABLE showtimes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  movie_id INT NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  room_number INT NOT NULL,
  price FLOAT NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE seats (
  id INT PRIMARY KEY AUTO_INCREMENT,
  showtime_id INT NOT NULL,
  seat_number INT NOT NULL,
  is_booked BOOL NOT NULL DEFAULT false,
  FOREIGN KEY (showtime_id) REFERENCES showtimes(id)
);

CREATE TABLE accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    activeCode VARCHAR(255)
);

CREATE TABLE customers (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  birthday DATE,
  id_card VARCHAR(12),
  address VARCHAR(255),
  accounts_id INT,
  FOREIGN KEY (accounts_id) REFERENCES accounts (id)
);

CREATE TABLE employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  birthday DATE,
  id_card VARCHAR(12),
  address VARCHAR(255),
  accounts_id INT,
  FOREIGN KEY (accounts_id) REFERENCES accounts (id)
);

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name_role VARCHAR(255) NOT NULL
);

CREATE TABLE account_roles (
    account_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (account_id, role_id),
    FOREIGN KEY (account_id)
        REFERENCES accounts (id),
    FOREIGN KEY (role_id)
        REFERENCES roles (id)
);

CREATE TABLE bookings (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT NOT NULL,
  showtime_id INT NOT NULL,
  seat_id INT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (showtime_id) REFERENCES showtimes(id),
  FOREIGN KEY (seat_id) REFERENCES seats(id)
);

DELIMITER $$
CREATE TRIGGER auto_insert_seats
    AFTER INSERT
    ON showtimes FOR EACH ROW
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 50
    DO INSERT INTO seats (showtime_id, seat_number) 
    VALUES (NEW.id,i); 
    SET i = i + 1; 
    END WHILE;
END $$
DELIMITER ;