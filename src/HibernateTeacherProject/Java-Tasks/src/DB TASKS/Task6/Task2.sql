

SELECT *FROM jobs
NATURAL JOIN job_history;



SELECT *FROM jobs JOIN job_history USING (job_id);


SELECT *FROM jobs j
JOIN job_history jh
ON j.job_id = jh.job_id;


SELECT *FROM jobs j
INNER JOIN job_history jh
ON j.job_id = jh.job_id;


SELECT *FROM jobs j
LEFT JOIN job_history jh
ON j.job_id = jh.job_id;


SELECT *FROM jobs j
RIGHT JOIN job_history jh
ON j.job_id = jh.job_id;



SELECT *FROM jobs j
FULL JOIN job_history jh
ON j.job_id = jh.job_id;