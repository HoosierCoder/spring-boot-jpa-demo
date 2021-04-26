use mydb;

CREATE TABLE Player (
     id int NOT NULL AUTO_INCREMENT,
     firstname VARCHAR(20) NOT NULL,
     lastname VARCHAR(20) NOT NULL,
     number int,
     PRIMARY KEY (id)
);

CREATE TABLE positions (
	id int NOT NULL AUTO_INCREMENT,
    position VARCHAR(15),
    player_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (player_id) REFERENCES player(id)
);