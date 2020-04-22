-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-04-22 20:53:19.738

-- tables
-- Table: Appointment
use qoajad_users;

CREATE TABLE AppointmentLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The id of the user which makes the request',
    state int NOT NULL COMMENT 'Whether the request was valid or not',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The ID of the request',
    date datetime NOT NULL COMMENT 'Appointment''''s date and time',
    mdDocument int UNSIGNED NOT NULL COMMENT 'The doctor''''s document',
    CONSTRAINT AppointmentLog_pk PRIMARY KEY (id)
);

-- Table: Login
CREATE TABLE LoginLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    state int NOT NULL COMMENT 'Whether the request was valid or not',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT LoginLog_pk PRIMARY KEY (id)
);

-- Table: Logout
CREATE TABLE LogoutLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT LogoutLog_pk PRIMARY KEY (id)
);

-- Table: MedicalHistory
CREATE TABLE MedicalHistoryLog (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    state int NOT NULL COMMENT 'Whether the request was valid or not',
    time datetime NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT MedicalHistoryLog_pk PRIMARY KEY (id)
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
-- Reference: MedicalHistoryLog_User (table: MedicalHistoryLog)
ALTER TABLE MedicalHistory ADD CONSTRAINT MedicalHistoryLog_User FOREIGN KEY MedicalHistoryLog_User (user_id)
    REFERENCES User (id);

-- Reference: AppointmentLog_User (table: AppointmentLog)
ALTER TABLE AppointmentLog ADD CONSTRAINT AppointmentLog_User FOREIGN KEY AppointmentLog_User (user_id)
    REFERENCES User (id);

-- Reference: LoginLog_User (table: LoginLog)
ALTER TABLE LoginLog ADD CONSTRAINT LoginLog_User FOREIGN KEY LoginLog_User (user_id)
    REFERENCES User (id);

-- Reference: LogoutLog_User (table: LogoutLog)
ALTER TABLE LogoutLog ADD CONSTRAINT LogoutLog_User FOREIGN KEY LogoutLog_User (user_id)
    REFERENCES User (id);

-- End of file.

