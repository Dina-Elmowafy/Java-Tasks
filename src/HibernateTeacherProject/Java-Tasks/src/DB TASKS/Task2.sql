CREATE TABLE Employees11 (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Department VARCHAR(50),
    Salary INT
);

INSERT ALL
    INTO Employees11 VALUES (101, 'John1', 'Doe1', 'HR', 20000)
    INTO Employees11 VALUES (102, 'John2', 'Doe2', 'IT', 50000)
    INTO Employees11 VALUES (103, 'John3', 'Doe3', 'CS', 40000)
    INTO Employees11 VALUES (104, 'John4', 'Doe4', 'IT', 10000)
    INTO Employees11 VALUES (105, 'John5', 'Doe5', 'ZX', 30000)
SELECT * FROM dual;

UPDATE Employees11
SET Salary = 600000
WHERE EmployeeID = 101;

DELETE FROM Employees11
WHERE EmployeeID = 101;

SELECT *
FROM Employees11
WHERE Department = 'IT';

SELECT FirstName || ' ' || LastName AS FullName
FROM Employees11;
