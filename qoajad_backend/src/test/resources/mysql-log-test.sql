CREATE TABLE Log (
    id int NOT NULL AUTO_INCREMENT,
    activeUsername varchar(50) NULL,
    state varchar(15) NOT NULL COMMENT 'Whether the request was valid or not',
    time timestamp NOT NULL COMMENT 'The timestamp of the request',
    ip varchar(15) NOT NULL COMMENT 'The IP of the request',
    data json NOT NULL COMMENT 'The data of the request',
    requestType varchar(6) NOT NULL COMMENT 'The type of the request in REST format',
    eventType varchar(30) NOT NULL COMMENT 'The type of the event which triggers a log writing',
    CONSTRAINT Log_pk PRIMARY KEY (id)
);


CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    document bigint unsigned NOT NULL,
    pw tinytext NOT NULL,
    UNIQUE INDEX unique_document (document),
    UNIQUE INDEX unique_username (username),
    CONSTRAINT id PRIMARY KEY (id)
);

ALTER TABLE Log ADD CONSTRAINT Log_User FOREIGN KEY Log_User (activeUsername)
    REFERENCES User (username);
    
INSERT INTO `User` (`username`,`document`,`pw`) VALUES ('juan.2114@hotmail.com','1144099495','juan546');