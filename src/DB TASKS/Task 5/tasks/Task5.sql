
CREATE TABLE doctors
(
    doctor_id NUMBER CONSTRAINT doctors_pk PRIMARY KEY,
    name      VARCHAR2(100) NOT NULL,
    salary    NUMBER(10, 2)
);

CREATE TABLE patients
(
    patient_id NUMBER CONSTRAINT patients_pk PRIMARY KEY,
    name       VARCHAR2(100) NOT NULL,
    age        NUMBER
);

CREATE TABLE doctor_patients
(
    doctor_id  NUMBER,
    patient_id NUMBER,
    CONSTRAINT doctor_patients_pk PRIMARY KEY (doctor_id, patient_id),
    CONSTRAINT dp_doctor_fk FOREIGN KEY (doctor_id)
        REFERENCES doctors(doctor_id),
    CONSTRAINT dp_patient_fk FOREIGN KEY (patient_id)
        REFERENCES patients(patient_id)
);

