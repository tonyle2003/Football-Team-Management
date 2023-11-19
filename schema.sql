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
	id VARCHAR(255) PRIMARY KEY FOREIGN KEY REFERENCES person(id),
	id_club VARCHAR(255) FOREIGN KEY REFERENCES club(id),
	height DOUBLE,
	weight DOUBLE,
	number INT
);

CREATE TABLE manager(
	id VARCHAR(255) PRIMARY KEY FOREIGN KEY REFERENCES person(id),
	id_club VARCHAR(255) FOREIGN KEY REFERENCES club(id)
);

CREATE TABLE football_competition(
	id VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255),
	quantity_of_club INT,
	nationality VARCHAR(255)
);

CREATE TABLE match(
    id VARCHAR(255) PRIMARY KEY,
    id_home VARCHAR(255) FOREIGN KEY REFERENCES club(id),
    id_away VARCHAR(255) FOREIGN KEY REFERENCES club(id),
    id_competition VARCHAR(255) FOREIGN KEY REFERENCES football_competition(id),
    _date DATE,
    result VARCHAR(255),
    stadium VARCHAR(255)
);

CREATE TABLE goal(
    id VARCHAR(255) PRIMARY KEY,
    id_match VARCHAR(255) FOREIGN KEY REFERENCES match(id),
    id_player VARCHAR(255) FOREIGN KEY REFERENCES player(id),
    goal_type VARCHAR(255)
);