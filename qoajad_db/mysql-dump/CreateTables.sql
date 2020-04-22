-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-04-22 20:53:19.738

-- tables
-- Table: Appointment
CREATE TABLE AppointmentLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The id of the user which makes the request',
    state int NOT NULL COMMENT 'Whether the request was valid or not',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The ID of the request',
    date datetime NOT NULL COMMENT 'Appointment''''s date and time',
    mdDocument int UNSIGNED NOT NULL COMMENT 'The doctor''''s document',
    CONSTRAINT Appointment_pk PRIMARY KEY (id)
);

-- Table: Login
CREATE TABLE LoginLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    state int NOT NULL COMMENT 'Whether the request was valid or not',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT Login_pk PRIMARY KEY (id)
);

-- Table: Logout
CREATE TABLE LogoutLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT Logout_pk PRIMARY KEY (id)
);

-- Table: MedicalHistory
CREATE TABLE MedicalHistoryLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    state int NOT NULL COMMENT 'Whether the request was valid or not',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT MedicalHistory_pk PRIMARY KEY (id)
);

-- Table: User
CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    document int UNSIGNED NOT NULL,
    pw varchar(20) NOT NULL,
    UNIQUE INDEX unique_document (document),
    UNIQUE INDEX unique_username (username),
    CONSTRAINT id PRIMARY KEY (id)
);

-- foreign keys
-- Reference: MedicalHistory_User (table: Medical_History)
ALTER TABLE MedicalHistory ADD CONSTRAINT MedicalHistory_User FOREIGN KEY Clinical_History_User (user_id)
    REFERENCES User (id);

-- Reference: Appointment_User (table: Appointment)
ALTER TABLE Appointment ADD CONSTRAINT Appointment_User FOREIGN KEY Appointment_User (user_id)
    REFERENCES User (id);

-- Reference: Login_User (table: Login)
ALTER TABLE Login ADD CONSTRAINT Login_User FOREIGN KEY Login_User (user_id)
    REFERENCES User (id);

-- Reference: Logout_User (table: Logout)
ALTER TABLE Logout ADD CONSTRAINT Logout_User FOREIGN KEY Logout_User (user_id)
    REFERENCES User (id);

-- End of file.

