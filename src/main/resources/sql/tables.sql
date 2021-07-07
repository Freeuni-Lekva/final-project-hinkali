USE occultexpress;

DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS stats;
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

CREATE TABLE stats(
    userid INT NOT NULL,
    games_played INT DEFAULT 0,
    wins INT DEFAULT 0,
    draws INT DEFAULT 0,
    losses INT DEFAULT 0,
    points INT DEFAULT 0,
    FOREIGN KEY (userid) REFERENCES users(userid)
);

Create table friends (
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(userid),
    FOREIGN KEY (friend_id) REFERENCES users(userid),
    PRIMARY KEY (user_id, friend_id)
);

create table pending (
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES users(userid),
    FOREIGN KEY (receiver_id) REFERENCES users(userid),
    PRIMARY KEY (sender_id, receiver_id)
);

INSERT INTO users (username, name, surname, password)
VALUES ('test_username', 'Test_name', 'Test_surname', 'password'),
		('multiTest', 'TestName2', 'TestSurname2', 'password'),
        ('looooooooongUseeeername', 'LoooooooooooooongName', 'Wolfeschlegelsteinhausenbergerdorff',
        'password');
-- long values for display testing

INSERT INTO stats (userid) VALUES (1), (2), (3);
