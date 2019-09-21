CREATE TABLE user (
  id        BIGINT       NOT NULL AUTO_INCREMENT,
  login     VARCHAR(255) NOT NULL UNIQUE ,
  firstname VARCHAR(100) NOT NULL,
  lastname  VARCHAR(255) NOT NULL,
  age       INT(10)      NOT NULL,
  PRIMARY KEY (id)
);