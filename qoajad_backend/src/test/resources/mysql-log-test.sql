CREATE TABLE Log (
    id int NOT NULL AUTO_INCREMENT,
    active_user_id int NULL,
    state varchar(15) NOT NULL,
    time timestamp NOT NULL,
    ip varchar(15) NOT NULL,
    data json NOT NULL,
    requestType varchar(6) NOT NULL,
    eventType varchar(30) NOT NULL,
    CONSTRAINT Log_pk PRIMARY KEY (id)
);

CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    document int unsigned NOT NULL,
    pw tinytext NOT NULL,
    UNIQUE INDEX unique_document (document),
    UNIQUE INDEX unique_username (username),
    CONSTRAINT id PRIMARY KEY (id)
);

ALTER TABLE Log ADD CONSTRAINT Log_User FOREIGN KEY Log_User (active_user_id)
    REFERENCES User (id);
    
INSERT INTO `User` (`username`,`document`,`pw`) VALUES ('juan.2114@hotmail.com','1144099495','juan546');