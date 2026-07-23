
CREATE TABLE directors_pk
(
    director_id NUMBER
        CONSTRAINT dir_pk_did_pk PRIMARY KEY,
    first_name  VARCHAR2(50),
    last_name   VARCHAR2(50)
);

INSERT INTO directors_pk (director_id, first_name, last_name)
VALUES (1, 'Ahmed', 'Ali');

INSERT INTO directors_pk (director_id, first_name, last_name)
VALUES (2, 'Mona', 'Hassan');


CREATE TABLE directors_unique
(
    director_id NUMBER
        CONSTRAINT dir_uk_did_nn NOT NULL
        CONSTRAINT dir_uk_did_uk UNIQUE,
    first_name  VARCHAR2(50),
    last_name   VARCHAR2(50)
);

INSERT INTO directors_unique (director_id, first_name, last_name)
VALUES (1, 'Ahmed', 'Ali');

INSERT INTO directors_unique (director_id, first_name, last_name)
VALUES (2, 'Mona', 'Hassan');


COMMIT;


SELECT * FROM directors_pk;
SELECT * FROM directors_unique;



SELECT table_name, constraint_name, constraint_type
FROM user_constraints
WHERE table_name IN ('DIRECTORS_PK', 'DIRECTORS_UNIQUE')
ORDER BY table_name, constraint_type;



