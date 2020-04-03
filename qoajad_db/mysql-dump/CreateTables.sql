-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-04-03 14:50

-- tables
-- Table: API
use qoajad_users;

CREATE TABLE API (
    api_id int NOT NULL AUTO_INCREMENT,
    api_key varchar(50) NOT NULL,
    CONSTRAINT API_pk PRIMARY KEY (api_id)
);

-- Table: Appointment
CREATE TABLE Appointment (
    ap_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The id of the user which makes the request',
    ap_state int NOT NULL COMMENT 'Whether the request was valid or not',
    ap_time datetime NOT NULL COMMENT 'The timestamp of the request',
    ap_ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    ap_date datetime NOT NULL COMMENT 'Appointment''''s date and time',
    ap_mdid int NOT NULL COMMENT 'The doctor''''s id',
    CONSTRAINT Appointment_pk PRIMARY KEY (ap_id)
);

-- Table: Medical_History
CREATE TABLE Medical_History (
    mh_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    mh_state varchar(15) NOT NULL COMMENT 'Whether the request was valid or not',
    mh_time datetime NOT NULL COMMENT 'The timestamp of the request',
    mh_ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT Medical_History_pk PRIMARY KEY (mh_id)
);

-- Table: Login
CREATE TABLE Login (
    login_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    login_state int NOT NULL COMMENT 'Whether the request was valid or not',
    login_time datetime NOT NULL COMMENT 'The timestamp of the request',
    login_ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT Login_pk PRIMARY KEY (login_id)
);

-- Table: Logout
CREATE TABLE Logout (
    logout_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL COMMENT 'The ID of the user which makes the request',
    logout_time datetime NOT NULL COMMENT 'The timestamp of the request',
    logout_ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    CONSTRAINT Logout_pk PRIMARY KEY (logout_id)
);

-- Table: User
CREATE TABLE User (
    user_id int NOT NULL AUTO_INCREMENT,
    user_document int UNSIGNED NOT NULL,
    user_pw varchar(20) NOT NULL,
    CONSTRAINT id PRIMARY KEY (user_id)
);

-- foreign keys
-- Reference: Medical_History_User (table: Medical_History)
ALTER TABLE Medical_History ADD CONSTRAINT Medical_History_User FOREIGN KEY Medical_History_User (user_id)
    REFERENCES User (user_id);

-- Reference: Copy_of_Medical_History_User (table: Appointment)
ALTER TABLE Appointment ADD CONSTRAINT Copy_of_Medical_History_User FOREIGN KEY Copy_of_Medical_History_User (user_id)
    REFERENCES User (user_id);

-- Reference: Login_User (table: Login)
ALTER TABLE Login ADD CONSTRAINT Login_User FOREIGN KEY Login_User (user_id)
    REFERENCES User (user_id);

-- Reference: Logout_User (table: Logout)
ALTER TABLE Logout ADD CONSTRAINT Logout_User FOREIGN KEY Logout_User (user_id)
    REFERENCES User (user_id);

-- End of file.

