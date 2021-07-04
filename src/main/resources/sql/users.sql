USE occultexpress;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	userid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username CHAR(64) UNIQUE NOT NULL,
    name CHAR(64),
    surname CHAR(64),
    password CHAR(64) NOT NULL,
    birthday DATE
);

INSERT INTO users (username, name, surname, password)
VALUES ('test_username', 'test_name', 'test_surname', 'password');
