

CREATE TABLE phones
(
    phone_id     NUMBER CONSTRAINT phones_pk PRIMARY KEY,
    phone_number VARCHAR2(20) NOT NULL UNIQUE
);

CREATE TABLE employees_lec5
(
    employee_id NUMBER CONSTRAINT employees_l5_pk PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL,
    age         NUMBER,
    phone_id    NUMBER NOT NULL UNIQUE,
    CONSTRAINT employee_phone_fk FOREIGN KEY (phone_id)
        REFERENCES phones(phone_id)
);


