USE fixapp;
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------
-- fixapp.users
-- ------------------------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT NOT NULL,
    role VARCHAR(20),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    PRIMARY KEY (id),
    UNIQUE (username)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO users (id, role, username, password, first_name, last_name, email, phone)
VALUES (1, 'MASTER', 'admin', 'admin', 'John', 'Smith', 'admin@fix.com', '+38067-777-77-77'),
(2, 'MANAGER', 'manager', 'manager', 'Jane', 'Richardson', 'manager@fix.com', '+38093-333-33-33'),
(3, 'CUSTOMER', 'alex777', 'user', 'Tony', 'Black', 'user@user.com', '+38098-475-21-30'),
(4, 'CUSTOMER', 'johny', '1234', 'John', 'Snow', 'user@user.com', '+38098-475-21-30');

-- ------------------------------------------
-- fixapp.items
-- ------------------------------------------
DROP TABLE IF EXISTS items;
CREATE TABLE IF NOT EXISTS items (
    id INT AUTO_INCREMENT NOT NULL,
    item_type VARCHAR(100),
    brand VARCHAR(100),
    item_name VARCHAR(100),
    PRIMARY KEY (id)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO items (id, item_type, brand, item_name)
VALUES (1, 'phone', 'Nokia', 'lumia 720'),
(2, 'tv', 'Philips', 'HD200'),
(3, 'car', 'ZAZ', 'Lanos'),
(4, 'microwave', 'Samsung', 'HG100');

-- ------------------------------------------
-- fixapp.contacts
-- ------------------------------------------
DROP TABLE IF EXISTS reviews;
CREATE TABLE IF NOT EXISTS reviews (
    id INT AUTO_INCREMENT NOT NULL,
    review_status VARCHAR(20),
    review_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    cost BIGINT DEFAULT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO reviews (id, review_status, cost)
VALUES (1, 'ACCEPTED', 10000),
(2, 'ACCEPTED', 40000),
(3, 'DECLINED', null);

-- ------------------------------------------
-- fixapp.repair_requests
-- ------------------------------------------
DROP TABLE IF EXISTS repair_requests;
CREATE TABLE IF NOT EXISTS repair_requests (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    item_id INT NOT NULL,
    review_id INT,
    status VARCHAR(20),
    creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(250),
      PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id)
      ON DELETE CASCADE,
    FOREIGN KEY (review_id) REFERENCES reviews (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO repair_requests (id, user_id, item_id, review_id, status, creation_time, description)
VALUES (1, 3, 1, 1, 'DONE', '2019-05-12 22:31:18', 'not charging'),
(2, 3, 2, 2, 'REVIEWED', '2019-05-15 21:14:01', 'black stripes on the screen'),
(3, 3, 3, 3, 'REVIEWED', '2019-05-22 14:15:10', 'bad breaks'),
(4, 3, 4, null, 'NEW', '2019-05-23 08:00:54', 'spinning but not heating');

-- ------------------------------------------
-- fixapp.feedback
-- ------------------------------------------
DROP TABLE IF EXISTS feedback;
CREATE TABLE IF NOT EXISTS feedback (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    text VARCHAR(255),
    creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO feedback (id, user_id, text)
VALUES (1, 3, 'Thank you for quick repair.'),
(2, 4, 'Вы просто огонь! Спасибо что так качественно починили мой телефон!');


SET FOREIGN_KEY_CHECKS = 1;
