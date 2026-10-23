

CREATE TABLE languages
(
    language_id NUMBER CONSTRAINT languages_pk PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL UNIQUE
);

CREATE TABLE teachers
(
    teacher_id  NUMBER CONSTRAINT teachers_pk PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL,
    salary      NUMBER(10, 2),
    language_id NUMBER NOT NULL,
    CONSTRAINT teacher_language_fk FOREIGN KEY (language_id)
        REFERENCES languages(language_id)
);


