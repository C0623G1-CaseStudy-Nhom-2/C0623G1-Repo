create database movie_ticket;

use movie_ticket;

CREATE TABLE categorys (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name_categorys VARCHAR(255) NOT NULL
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

INSERT INTO movies (title, description, release_date, duration, avatar_url, poster_url) VALUES ('Avengers: Endgame', 'After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos'' actions and restore balance to the universe.', '2019-04-26', 181, 'avatar_url_1', 'poster_url_1');
INSERT INTO movies (title, description, release_date, duration, avatar_url, poster_url) VALUES ('The Dark Knight', 'Batman raises the stakes in his war on crime. With the help of Lieutenant Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the city streets.', '2008-07-18', 152, 'avatar_url_2', 'poster_url_2');

INSERT INTO showtimes (movie_id, start_time, end_time, room_number, price) VALUES (1, '2023-10-25 19:00:00', '2023-10-25 21:00:00', 1, 10.00);
INSERT INTO showtimes (movie_id, start_time, end_time, room_number, price) VALUES (1, '2023-10-26 15:30:00', '2023-10-26 17:30:00', 2, 10.00);

INSERT INTO accounts (username, password, activeCode) VALUES ('user1', 'password123', 'ABC123');
INSERT INTO accounts (username, password, activeCode) VALUES ('user2', 'password456', 'DEF456');
INSERT INTO accounts (username, password, activeCode) VALUES ('user3', 'password789', 'DEF456');
INSERT INTO accounts (username, password, activeCode) VALUES ('user4', 'password012', 'DEF456');

INSERT INTO customers (full_name, email, phone_number, birthday, id_card, address, accounts_id) VALUES ('John Doe', 'johndoe@example.com', '123456789', '1990-01-01', '1234567890', '123 Main St', 1);
INSERT INTO customers (full_name, email, phone_number, birthday, id_card, address, accounts_id) VALUES ('Jane Smith', 'janesmith@example.com', '987654321', '1992-02-02', '0987654321', '456 Elm St', 2);

INSERT INTO employees (full_name, email, phone_number, birthday, id_card, address, accounts_id) VALUES ('Tom Hanks', 'tomhanks@example.com', '555-1234', '1980-03-03', '1357924680', '789 Oak St', 3);
INSERT INTO employees (full_name, email, phone_number, birthday, id_card, address, accounts_id) VALUES ('Emma Watson', 'emmawatson@example.com', '555-5678', '1985-04-04', '2468135790', '321 Pine St', 4);

INSERT INTO roles (name_role) VALUES ('Admin');
INSERT INTO roles (name_role) VALUES ('Staff');

INSERT INTO account_roles (account_id, role_id) VALUES (1, 1);
INSERT INTO account_roles (account_id, role_id) VALUES (2, 2);

INSERT INTO bookings (customer_id, showtime_id, seat_id) VALUES (1, 1, 1);
INSERT INTO bookings (customer_id, showtime_id, seat_id) VALUES (2, 2, 2);