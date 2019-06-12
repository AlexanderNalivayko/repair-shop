DROP DATABASE IF EXISTS fixapp;

CREATE DATABASE IF NOT EXISTS fixapp;

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
VALUES (1, 'MASTER', 'master', 'pass', 'John', 'Smith', 'admin@fix.com', '+38067-777-77-77'),
(2, 'MANAGER', 'manager', 'pass', 'Jane', 'Richardson', 'manager@fix.com', '+38093-333-33-33'),
(3, 'CUSTOMER', 'alex', 'pass', 'Alex', 'Black', 'alex@user.com', '+38098-475-21-30'),
(4, 'CUSTOMER', 'johny', 'pass', 'John', 'Snow', 'user@user.com', '+38098-485-71-85');

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
(4, 'microwave', 'Samsung', 'HG100'),
(5, 'Стиральная машина', 'индезит', 'WM1234'),
(6, 'Посудомойка', 'Бош', 'DWM-400'),
(7, 'Электрогриль', 'скарлет', 'O-9000'),
(8, 'Кофеварка', 'lg', 'CM - 800'),
(9, 'Утюг', 'philips', 'I-1234'),
(10, 'Стул', 'икея', 'маммут'),
(11, 'Телефон', 'siemens', 'c75'),
(12, 'E-book', 'Amazon', 'Kindle-6');

-- ------------------------------------------
-- fixapp.contacts
-- ------------------------------------------
DROP TABLE IF EXISTS reviews;
CREATE TABLE IF NOT EXISTS reviews (
    id INT AUTO_INCREMENT NOT NULL,
    review_status VARCHAR(20),
    review_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    cost BIGINT DEFAULT NULL,
    reject_reason VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB CHARACTER SET=UTF8;
INSERT INTO reviews (id, review_status, cost, reject_reason)
VALUES (1, 'ACCEPTED', 10000, null),
(2, 'ACCEPTED', 40000, null),
(3, 'REJECTED', null, 'We don''t fix cars');

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
(4, 3, 4, null, 'NEW', '2019-05-23 08:00:54', 'spinning but not heating'),
(5, 4, 5, null, 'NEW', '2019-05-24 20:53:17', 'Не откачивает воду после стирки'),
(6, 4, 6, null, 'NEW', '2019-05-25 19:30:45', 'Не реагирует на нажатие кнопок'),
(7, 4, 7, null, 'NEW', '2019-05-26 09:01:03', 'Индикатор горит, но ничего не происходит'),
(8, 4, 8, null, 'NEW', '2019-05-26 10:00:07', 'Забились фильтры воды'),
(9, 4, 9, null, 'NEW', '2019-05-26 11:05:10', 'Не работает в режиме пара'),
(10, 4, 10, null, 'NEW', '2019-05-26 12:40:03', 'Сломалась ножка'),
(11, 4, 11, null, 'NEW', '2019-05-27 23:50:00', 'Ик порт не работает'),
(12, 3, 12, null, 'NEW', '2019-05-27 23:52:32', 'Won''t turn on');

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
(2, 4, 'Спасибо что так качественно починили мой телефон!'),
(3, 3, 'Wow. Such a nice site'),
(4, 4, 'Eah site is great'),
(5, 3, 'Ого так много функций'),
(6, 4, 'Ребята как вы смогли это сделать?! ЛУЧШИЙ САЙТ РЕМОНТНОЙ МАСТЕРСКОЙ ИЗ СУЩЕСТВУЮЩИХ!'),
(7, 3, 'Thanks !'),
(8, 4, 'Спасибо, всё супер!'),
(9, 3, 'До ремонта у меня был айфон 5, отремонтировали - стал 10!!!'),
(10, 4, 'Так дёшево ?!'),
(11, 3, 'Раньше сидел в фейсбук, но надоело... Теперь тут зависаю, гораздо круче! И самое главное - качественные ремонты техники!'),
(12, 4, 'Я 3 года ждал пока появиться такой сайт! Спасибо за починку моей микроволновки!'),
(13, 3, 'Эти ребята могут починить всё! Моя стиралкак как новая!'),
(14, 4, 'Very glad that I used your services');


SET FOREIGN_KEY_CHECKS = 1;
