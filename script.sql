USE fixapp;
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------
-- fixapp.contacts
-- ------------------------------------------
DROP TABLE IF EXISTS contacts;
CREATE TABLE IF NOT EXISTS contacts (
    id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(16),
    PRIMARY KEY (id)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO contacts (id, email, phone)
VALUES (1, 'admin@fix.com', '+38067-777-77-77'),
(2, 'manager@fix.com', '+38093-333-33-33'),
(3, 'user@user.com', '+38098-475-21-30'),
(4, 'user@user.com', '+38098-475-21-30');

-- ------------------------------------------
-- fixapp.users
-- ------------------------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT NOT NULL,
    contacts_id INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (contacts_id) REFERENCES contacts (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO users (id, contacts_id, username, password, first_name, last_name, role)
VALUES (1, 1, 'admin', 'admin', 'John', 'Smith', 'MASTER'),
(2, 2, 'manager', 'manager', 'Jane', 'Richardson', 'MANAGER'),
(3, 3, 'alex777', 'user', 'Tony', 'Black', 'CUSTOMER'),
(4, 4, 'johny', 'user', 'John', 'Snow', 'CUSTOMER');

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
    user_id INT NOT NULL,
    item_id INT NOT NULL,
    status VARCHAR(20),
    creation_date DATETIME,
    description VARCHAR(250),
    cost BIGINT DEFAULT NULL,
      PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO repair_requests (id, user_id, item_id, status, creation_date, description, cost)
VALUES (1, 3, 1, 'NEW', NOW(), 'not charging', null),
(2, 3, 2, 'APPROVED', NOW(), 'black stripes on the screen', 1000),
(3, 3, 3, 'DECLINED', NOW(), 'bad breaks', null),
(4, 3, 4, 'DONE', NOW(), 'spinning but not heating', 2890);

-- ------------------------------------------
-- fixapp.feedback
-- ------------------------------------------
DROP TABLE IF EXISTS feedback; --todo add date
CREATE TABLE IF NOT EXISTS feedback (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    text VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO feedback (id, user_id, text)
VALUES (1, 3, 'Thank you for quick repair.'),
(2, 4, 'Вы просто огонь! Спасибо что так качественно починили мой телефон!');


SET FOREIGN_KEY_CHECKS = 1;
