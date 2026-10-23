INSERT INTO dina.student (id, name)
VALUES (1, 'Ahmed');

INSERT INTO dina.student (id, name)
VALUES (2, 'Sara');

SELECT *
FROM dina.student;

UPDATE dina.student
SET name = 'Ahmed Ali'
WHERE id = 1;

SELECT *
FROM dina.student;

DELETE FROM dina.student
WHERE id = 2;

SELECT *
FROM dina.student;

COMMIT;