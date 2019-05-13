USE fixapp;
SET FOREIGN_KEY_CHECKS=0;

-- ------------------------------------------
-- fixapp.contacts
-- ------------------------------------------
DROP TABLE IF EXISTS contacts;
CREATE TABLE IF NOT EXISTS contact_information (
    id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(16),
    PRIMARY KEY (id)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO contacts (id, email, phone)
VALUES (1, 'admin@fix.com', '+38067-777-77-77'),
(2, 'manager@fix.com', '+38093-333-33-33'),
(3, 'user@user.com', '+38098-475-21-30');

-- ------------------------------------------
-- fixapp.users
-- ------------------------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(20),
    contacts_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (contacts_id) REFERENCES contacts (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO users (id, username, password, first_name, last_name, role, contacts_id)
VALUES (1, 'admin', 'admin', 'John', 'Smith', 'ADMIN', 1),
(2, 'manager', 'manager', 'Jane', 'Richardson', 'MANAGER', 2),
(3, 'user', 'user', 'Tony', 'Black', 'USER', 3);

-- ------------------------------------------
-- fixapp.items
-- ------------------------------------------
DROP TABLE IF EXISTS items;
CREATE TABLE IF NOT EXISTS items (
    id INT AUTO_INCREMENT NOT NULL,
    product_type VARCHAR(100),
    brand VARCHAR(100),
    product_name VARCHAR(100),
    PRIMARY KEY (id)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO items (id, product_type, brand, product_name)
VALUES (1, 'phone', 'Nokia', 'lumia 720'),
(2, 'tv', 'Philips', 'HD200'),
(3, 'car', 'ZAZ', 'Lanos'),
(4, 'microwave', 'Samsung', 'HG100');

-- ------------------------------------------
-- fixapp.repair_requests
-- ------------------------------------------
DROP TABLE IF EXISTS repair_requests;
CREATE TABLE IF NOT EXISTS repair_requests (
    id INT AUTO_INCREMENT NOT NULL,
    status VARCHAR(20),
    user_id INT NOT NULL,
    item_id INT NOT NULL,
    description VARCHAR(250),
    cost BIGINT DEFAULT NULL,
      PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO repair_requests (id, status, user_id, item_id, description, cost)
VALUES (1, 'NEW', 3, 1, 'not charging', null),
(2, 'APPROVED', 3, 2, 'black stripes on the screen', 1000),
(3, 'DECLINED', 3, 3, 'bad breaks', null),
(4, 'DONE', 3, 4, 'spinning but not heating', 2890);

-- ------------------------------------------
-- fixapp.feedback
-- ------------------------------------------
DROP TABLE IF EXISTS feedback;
CREATE TABLE IF NOT EXISTS feedback (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    text VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO feedback (id, user_id, text)
VALUES (1, 3, 'Thank you for quick repair.');


SET FOREIGN_KEY_CHECKS=1;
