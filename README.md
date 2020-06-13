# JPA BootStrap POC

This POC shows how to implement JPA programmatically without configuration files or making use Dependency Injection (DI).

This is based on tutorial in [baeldung tutorial](https://www.baeldung.com/java-bootstrap-jpa).

### MySQL Setup:

1- Download and install MySQL Server from [Download MySQL](https://dev.mysql.com/downloads/)

2- Login with Root account and create the following database. 
```
CREATE DATABASE appadevdb;
```

3- Create a User and grant it all Privileges on the database.
```
CREATE USER 'dbuser'@'%' IDENTIFIED BY 'dbpassword';

GRANT ALL PRIVILEGES ON appadevdb.* TO 'dbuser'@'%';
```

4- Create the following table (EVENTS):
```
drop table if exists EVENTS;
create table EVENTS(
   SEQ_ID INT NOT NULL AUTO_INCREMENT,
   TRAN_ID VARCHAR(100) NOT NULL,
   EVT_ID INT NOT NULL,
   MESSAGE_TXT VARCHAR(400),
   TRAN_TS TIMESTAMP NOT NULL,
   INSERT_TS TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY ( SEQ_ID )
);
```

## Authors

* **Soufiane Berouel**
