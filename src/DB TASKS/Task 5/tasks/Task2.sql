
CREATE TABLE doctors
(
    doctor_id NUMBER CONSTRAINT doctors_t2_pk PRIMARY KEY,
    name      VARCHAR2(100) NOT NULL,
    salary    NUMBER(10, 2)
);

CREATE TABLE patients
(
    patient_id NUMBER CONSTRAINT patients_t2_pk PRIMARY KEY,
    name       VARCHAR2(100) NOT NULL,
    age        NUMBER
);


CREATE TABLE doctor_patients
(
    doctor_id  NUMBER NOT NULL,
    patient_id NUMBER NOT NULL,

    CONSTRAINT doctor_patients_t2_pk
        PRIMARY KEY (doctor_id, patient_id),

    CONSTRAINT dp_t2_doctor_fk
        FOREIGN KEY (doctor_id)
        REFERENCES doctors(doctor_id),

    CONSTRAINT dp_t2_patient_fk
        FOREIGN KEY (patient_id)
        REFERENCES patients(patient_id)
);


INSERT INTO doctors VALUES (1, 'Dr. Ahmed', 15000);
INSERT INTO doctors VALUES (2, 'Dr. Mona', 17000);

INSERT INTO patients VALUES (1, 'Ali', 25);
INSERT INTO patients VALUES (2, 'Sara', 30);

INSERT INTO doctor_patients VALUES (1, 1);
INSERT INTO doctor_patients VALUES (1, 2);
INSERT INTO doctor_patients VALUES (2, 1);

COMMIT;

SELECT * FROM doctors;
SELECT * FROM patients;
SELECT * FROM doctor_patients;

