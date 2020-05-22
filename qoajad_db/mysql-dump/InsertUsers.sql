use qoajad_users;

INSERT INTO HealthPromotingEntity(id, name) VALUES (1, "Colsanitas"), (2, "SURA");
insert into User (username, document, pw, health_promoting_entity_id) VALUES ('juan.2114@hotmail.com', 111, '{bcrypt}$2y$12$zLsOtIVbQdsABDPOJOl1DutQObb4SER2Pc0onp33dHRRH6INavDUu', 1);
