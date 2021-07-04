USE occultexpress;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	userid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username CHAR(64) UNIQUE NOT NULL,
    name CHAR(64),
    surname CHAR(64),
    birthday DATE
);

INSERT INTO users (username, name, surname)
VALUES ('test_username', 'test_name', 'test_surname')
