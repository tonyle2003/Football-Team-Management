DROP DATABASE IF EXISTS football_management;

CREATE DATABASE football_management;

USE football_management;

CREATE TABLE person(
	id VARCHAR(255) PRIMARY KEY,
	name varchar(255),
	nationality varchar(255),
	date_of_birth DATE
);

CREATE TABLE club(
	id VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255),
	stadium VARCHAR(255)
);

CREATE TABLE player(
	id VARCHAR(255) PRIMARY KEY,
	id_club VARCHAR(255),
	height DOUBLE,
	weight DOUBLE,
	number INT,
	CONSTRAINT FOREIGN KEY (id) REFERENCES person(id),
	CONSTRAINT FOREIGN KEY (id_club) REFERENCES club(id)
);

CREATE TABLE manager(
	id VARCHAR(255) PRIMARY KEY,
	id_club VARCHAR(255),
	CONSTRAINT FOREIGN KEY (id) REFERENCES person(id),
	CONSTRAINT FOREIGN KEY (id_club) REFERENCES club(id)
);

CREATE TABLE football_competition(
	id VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255),
	quantity_of_club INT,
	nationality VARCHAR(255)
);

CREATE TABLE _match(
    id VARCHAR(255) PRIMARY KEY,
    id_home VARCHAR(255),
    id_away VARCHAR(255),
    id_competition VARCHAR(255),
    _date DATE,
    result VARCHAR(255),
    stadium VARCHAR(255),
	CONSTRAINT FOREIGN KEY (id_home) REFERENCES club(id),
	CONSTRAINT FOREIGN KEY (id_away) REFERENCES club(id),
	CONSTRAINT FOREIGN KEY (id_competition) REFERENCES football_competition(id)
);

CREATE TABLE goal(
    id VARCHAR(255) PRIMARY KEY,
    id_match VARCHAR(255),
    id_player VARCHAR(255),
    goal_type VARCHAR(255),
	CONSTRAINT FOREIGN KEY (id_match) REFERENCES _match(id),
	CONSTRAINT FOREIGN KEY (id_player) REFERENCES player(id)
);