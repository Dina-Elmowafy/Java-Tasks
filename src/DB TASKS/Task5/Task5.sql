

CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100)
);

CREATE TABLE orders (
    order_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    CONSTRAINT fk_orders_customers
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
);



ALTER TABLE orders
DROP CONSTRAINT fk_orders_customers;



ALTER TABLE orders
ADD CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id);


ALTER TABLE orders
DISABLE CONSTRAINT fk_orders_customers;



INSERT INTO orders (order_id, customer_id)
VALUES (1, 100);



ALTER TABLE orders
ENABLE CONSTRAINT fk_orders_customers;



CREATE TABLE students (
    student_id NUMBER PRIMARY KEY,
    student_name VARCHAR2(100),
    age NUMBER,
    CONSTRAINT chk_student_age CHECK (age >= 18)
);



ALTER TABLE students
DISABLE CONSTRAINT chk_student_age;



INSERT INTO students (student_id, student_name, age)
VALUES (1, 'Ali', 15);



UPDATE students
SET age = 18
WHERE age < 18;



ALTER TABLE students
ENABLE CONSTRAINT chk_student_age;



ALTER TABLE students
DROP CONSTRAINT chk_student_age;