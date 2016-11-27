CREATE TABLE users(
	user_id INT NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR (20),
	email_address VARCHAR(40) UNIQUE,
	password VARCHAR(45),
	gender TINYINT(1),
	date_of_birth DATE,
	state_id INT,
	PRIMARY KEY (user_id)
);

CREATE TABLE states(
	state_id INT,
	name VARCHAR(30),
	country_id INT,
	PRIMARY KEY (state_id)
);

CREATE TABLE country(
	country_id INT,
	shortname VARCHAR(3),
	name VARCHAR(50),
	PRIMARY KEY(country_id)
);

CREATE TABLE survey_details(
	survey_id INT,
	survey_tile VARCHAR(128),
	survey_description VARCHAR(512),
	logo_location VARCHAR(11),
	last_modification_time DATETIME,
	publish_time DATETIME,
	closing_time DATETIME,
	PRIMARY KEY(survey_id)
);

CREATE TABLE participation(
	survey_id INT,
	user_id INT,
	survey_time DATETIME
);

CREATE TABLE questions(
	question_id INT,
	question VARCHAR(100),
	survey_id INT,
	PRIMARY KEY(question_id)
);

CREATE TABLE options(
	option_id INT,
	option_desc VARCHAR(100),
	question_id INT,
	option_order TINYINT,
	PRIMARY KEY(option_id)
);

CREATE TABLE user_selected_option(
	user_id INT,
	question_id INT,
	option_id INT
);