CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    document int UNSIGNED NOT NULL,
    pw TINYTEXT NOT NULL,
    UNIQUE INDEX unique_document (document),
    UNIQUE INDEX unique_username (username),
    CONSTRAINT id PRIMARY KEY (id)
);

INSERT INTO `User` (`username`,`document`,`pw`) VALUES ('juan.2114@hotmail.com','1144099495','juan546');