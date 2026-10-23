
SELECT *FROM locations
NATURAL JOIN countries;


SELECT *FROM locations JOIN countries
USING (country_id);

SELECT *FROM locations l JOIN countries c
ON l.country_id = c.country_id;



SELECT *FROM locations l INNER JOIN countries c
ON l.country_id = c.country_id;


SELECT *FROM locations l LEFT JOIN countries c
ON l.country_id = c.country_id;



SELECT *FROM locations l
RIGHT JOIN countries c
ON l.country_id = c.country_id;


SELECT *FROM locations l
FULL JOIN countries c
ON l.country_id = c.country_id;