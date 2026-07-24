

CREATE TABLE languages
(
    language_id NUMBER CONSTRAINT languages_t3_pk PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL UNIQUE
);

CREATE TABLE teachers
(
    teacher_id  NUMBER CONSTRAINT teachers_t3_pk PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL,
    salary      NUMBER(10, 2),
    language_id NUMBER NOT NULL,

    CONSTRAINT teacher_language_t3_fk
        FOREIGN KEY (language_id)
        REFERENCES languages(language_id)
);


INSERT INTO languages VALUES (1, 'English');
INSERT INTO languages VALUES (2, 'Arabic');

INSERT INTO teachers VALUES (1, 'Ahmed', 9000, 1);
INSERT INTO teachers VALUES (2, 'Mona', 8500, 1);
INSERT INTO teachers VALUES (3, 'Sara', 8000, 2);

COMMIT;

SELECT * FROM languages;
SELECT * FROM teachers;

