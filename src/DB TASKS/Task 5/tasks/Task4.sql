
CREATE TABLE phones
(
    phone_id     NUMBER CONSTRAINT phones_t4_pk PRIMARY KEY,
    phone_number VARCHAR2(20) NOT NULL UNIQUE
);

CREATE TABLE employees_lec5
(
    employee_id NUMBER CONSTRAINT employees_t4_pk PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL,
    age         NUMBER,
    phone_id    NUMBER NOT NULL UNIQUE,

    CONSTRAINT employee_phone_t4_fk
        FOREIGN KEY (phone_id)
        REFERENCES phones(phone_id)
);


INSERT INTO phones VALUES (1, '01012345678');
INSERT INTO phones VALUES (2, '01198765432');

INSERT INTO employees_lec5 VALUES (1, 'Ahmed', 28, 1);
INSERT INTO employees_lec5 VALUES (2, 'Mona', 26, 2);

COMMIT;

SELECT * FROM phones;
SELECT * FROM employees_lec5;

