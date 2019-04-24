USE fixapp;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    userType VARCHAR(20),
    email VARCHAR(100),
    password VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO users (id, firstName, lastName, userType, email, password)
VALUES (1, 'Admin', 'Admin', 'MANAGER', 'admin@gmail.com', 'admin'),
(2, 'John', 'Smith', 'CUSTOMER', 'john-smith@gmail.com', 'pass'),
(3, 'Master', 'Master', 'MASTER', 'master@gmail.com', 'master');

DROP TABLE IF EXISTS repairs;

CREATE TABLE IF NOT EXISTS repairs (
    id BIGINT NOT NULL,
    userId INT NOT NULL,
    status VARCHAR(100),
    item VARCHAR(100),
    description VARCHAR(250),
    pictureUrl VARCHAR(100),
    price BIGINT,
    managerNote VARCHAR(250),
    userFeedback VARCHAR(250),
    PRIMARY KEY (id)
);

INSERT INTO repairs (id, userId, status, item, description, pictureUrl, price, managerNote, userFeedback)
VALUES (1, 2, 'NEW', 'Phone', 'not charging', NULL, NULL, NULL, NULL),
(2, 2, 'APPROVED', 'Tv', 'black stripes on the screen', NULL, NULL, 'Need to change matrix', NULL),
(3, 2, 'DECLINED', 'Car', 'bad breaks', NULL, NULL, 'Sorry, we don''t fix cars', NULL),
(4, 2, 'DONE', 'Microwave', 'spinning but not heating', '', 1000,
'Some problems with electronic components', 'Thank you for speed and quality.');
