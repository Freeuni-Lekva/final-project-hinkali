USE occultexpress;

DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS pending;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username CHAR(64) UNIQUE NOT NULL,
    name CHAR(64),
    surname CHAR(64),
    password CHAR(64) NOT NULL,
    birthday DATE
);

Create table friends (
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(userid),
    FOREIGN KEY (friend_id) REFERENCES users(userid),
    PRIMARY KEY (user_id, friend_id)
);

create table pending (
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(userid),
    FOREIGN KEY (friend_id) REFERENCES users(userid),
    PRIMARY KEY (user_id, friend_id)
);

INSERT INTO users (username, name, surname, password)
VALUES ('test_username', 'test_name', 'test_surname', 'password');
