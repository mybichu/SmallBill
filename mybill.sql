CREATE TABLE config (
  id int AUTO_INCREMENT,
  myKey varchar(255) ,
  myValue varchar(255) ,
  PRIMARY KEY (id)
)  DEFAULT CHARSET=utf8;

CREATE TABLE category (
  id int AUTO_INCREMENT,
  name varchar(255) ,
  PRIMARY KEY (id)
)  DEFAULT CHARSET=utf8;

CREATE TABLE record (
  id int AUTO_INCREMENT,
  spend int,
  cid int,
  comment varchar(255) ,
  myDate Date,
  PRIMARY KEY (id),
  CONSTRAINT `fk_record_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
)  DEFAULT CHARSET=utf8;