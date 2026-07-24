-- Optional manual Oracle script.
-- Hibernate can create the table and sequence because ddl-auto=update.

CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE EMPLOYEES (
    ID NUMBER(19) PRIMARY KEY,
    NAME VARCHAR2(100 CHAR) NOT NULL,
    AGE NUMBER(3) NOT NULL,
    PHONE_NUMBER VARCHAR2(20 CHAR) NOT NULL
);
