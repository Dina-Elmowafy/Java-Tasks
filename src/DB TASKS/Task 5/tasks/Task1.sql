
CREATE TABLE players
(
    id   NUMBER CONSTRAINT player_id_nn NOT NULL
                CONSTRAINT player_id_uk UNIQUE,
    name VARCHAR2(100) CONSTRAINT player_name_uk UNIQUE,
    age  NUMBER
);


CREATE TABLE managers_composite
(
    id     NUMBER CONSTRAINT manager_id_nn NOT NULL,
    name   VARCHAR2(100),
    salary NUMBER(10, 2),
    CONSTRAINT manager_id_name_uk UNIQUE (id, name)
);


CREATE TABLE managers_pk
(
    id   NUMBER CONSTRAINT manager_pk PRIMARY KEY,
    name VARCHAR2(100),
    age  NUMBER
);


INSERT INTO players VALUES (1, 'Ahmed', 22);
INSERT INTO managers_composite VALUES (1, 'Ali', 10000);
INSERT INTO managers_composite VALUES (1, 'Mona', 12000);
INSERT INTO managers_pk VALUES (1, 'Sara', 30);

COMMIT;

