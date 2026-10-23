CREATE TABLE account (
    id NUMBER PRIMARY KEY,
    username VARCHAR2(100) UNIQUE NOT NULL,
    email VARCHAR2(150) UNIQUE NOT NULL,
    password VARCHAR2(100) NOT NULL
);

CREATE SEQUENCE account_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE item (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    price NUMBER,
    total_number NUMBER
);

CREATE SEQUENCE item_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE item_details (
    id NUMBER PRIMARY KEY,
    description VARCHAR2(500),
    comments VARCHAR2(500),
    item_id NUMBER UNIQUE,
    CONSTRAINT fk_item_details_item
        FOREIGN KEY (item_id)
        REFERENCES item(id)
);

CREATE SEQUENCE item_details_seq START WITH 1 INCREMENT BY 1;


SELECT * FROM account;