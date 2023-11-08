create database movie_ticket;

use movie_ticket;

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name_role VARCHAR(255) NOT NULL
);

CREATE TABLE accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    id_role INT NOT NULL,
	FOREIGN KEY (id_role) REFERENCES roles (id)
);

CREATE TABLE customers (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(10) NOT NULL,
  birthday DATE,
  id_card VARCHAR(12),
  address VARCHAR(255),
  account_id INT,
  FOREIGN KEY (accounts_id) REFERENCES accounts (id)
);


CREATE TABLE employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(10) NOT NULL,
  birthday DATE,
  id_card VARCHAR(12),
  address VARCHAR(255),
  account_id INT,
  FOREIGN KEY (accounts_id) REFERENCES accounts (id)
);

CREATE TABLE categories (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name_category VARCHAR(255) NOT NULL
);

CREATE TABLE movies (
 id INT PRIMARY KEY AUTO_INCREMENT,
 title VARCHAR(255) NOT NULL,
 description TEXT NOT NULL,
 release_date DATE NOT NULL,
 category_id INT NOT NULL,
 director VARCHAR(255) NOT NULL,
 avatar VARCHAR(255) NOT NULL,
 banner VARCHAR(255) NOT NULL,
 FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE show_time (
  id INT PRIMARY KEY AUTO_INCREMENT,
  movie_id INT NOT NULL,
  show_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  room_number INT NOT NULL,
  price FLOAT NOT NULL,
  employee_support_id INT NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movies(id),
  FOREIGN KEY (employee_support_id) REFERENCES employees (id)
);

CREATE TABLE bookings (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT NOT NULL,
  showtime_id INT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (showtime_id) REFERENCES showtimes(id)
);

CREATE TABLE seat_booking (
	booking_id INT NOT NULL,
    seat VARCHAR(3) NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);

INSERT INTO roles (name_role) VALUES ('Admin');
INSERT INTO roles (name_role) VALUES ('User');

INSERT INTO accounts (username, password, id_role) VALUES ('admin', 'admin123', 1);
INSERT INTO accounts (username, password, id_role) VALUES ('user1', 'user123', 2);

INSERT INTO customer (full_name, email, phone_number, birthday, id_card, address, account_id) 
VALUES ('Nguyễn Văn A', 'nguyenvana@gmail.com', '0123456789', '1990-01-01', '123456789012', 'Hà Nội', 2);

INSERT INTO employee (full_name, email, phone_number, birthday, id_card, address, account_id) 
VALUES ('Nguyễn Văn B', 'nguyenvanb@gmail.com', '0987654321', '1995-02-02', '987654321012', 'Hồ Chí Minh', 1);

INSERT INTO categories (name_category) VALUES ('Action');
INSERT INTO categories (name_category) VALUES ('Comedy');

INSERT INTO movies (title, description, release_date, category_id, director, avatar, banner)
VALUES ('Movie 1', 'Description 1', '2023-01-01', 1, 'Director 1', 'avatar1.jpg', 'poster1.jpg');
INSERT INTO movies (title, description, release_date, category_id, director, avatar, banner)
VALUES ('Movie 2', 'Description 2', '2023-02-02', 2, 'Director 2', 'avatar2.jpg', 'poster2.jpg');

INSERT INTO show_time (movie_id, show_date, start_time, end_time, room_number, price, employee_support_id)
VALUES (1, '2023-01-01', '10:00:00', '12:00:00', 1, 100000.0, 1);
INSERT INTO show_time (movie_id, show_date, start_time, end_time, room_number, price, employee_support_id)
VALUES (2, '2023-02-02', '14:00:00', '16:00:00', 2, 150000.0, 1);

INSERT INTO bookings (customer_id, showtime_id)
VALUES (1, 1);
INSERT INTO bookings (customer_id, showtime_id)
VALUES (1, 2);


INSERT INTO seat_booking (booking_id, seat)
VALUES (1, 'A1');
INSERT INTO seat_booking (booking_id, seat)
VALUES (1, 'B2');