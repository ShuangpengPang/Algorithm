package
SELECT d.name    AS Department,
       e1.name   AS Employee,
       e1.salary AS Salary
FROM Employee e1
         JOIN
     Department d ON e1.departmentId = d.id
WHERE (SELECT COUNT(DISTINCT e2.salary)
       FROM Employee e2
       WHERE e1.departmentId = e2.departmentId
         AND e2.salary > e1.salary
      ) < 3
;

SELECT d.name AS Department, te.name AS Employee, te.salary AS Salary
FROM (SELECT CASE
                 WHEN @pre = e.departmentId AND @pre_salary > e.salary AND ((@pre_salary := e.salary) OR TRUE)
                     THEN @rank := @rank + 1
                 WHEN @pre = e.departmentId THEN @rank
                 WHEN ((@pre := e.departmentId) OR TRUE) AND ((@pre_salary := e.salary) OR TRUE) THEN @rank := 1
                 END AS rk,
             e.departmentId,
             e.salary,
             e.name
      FROM (SELECT @pre := null, @rank := 0, @pre_salary := -1) pr,
           (SELECT *
            FROM Employee
            ORDER BY departmentId ASC, salary DESC
           ) e
     ) te
         INNER JOIN Department d ON d.id = te.departmentId
WHERE te.rk <= 3
;

SELECT d.name AS Department, e.name AS Employee, e.salary AS Salary
FROM (SELECT name,
             departmentId,
             salary,
             DENSE_RANK() OVER(PARTITION BY departmentId ORDER BY salary DESC) AS rk
      FROM Employee
     ) e
         JOIN
     Department d ON e.departmentId = d.id
WHERE e.rk <= 3
;

INSERT INTO Employee (id, name, salary, departmentId)
VALUES (17, "sep", 82, 4);
INSERT INTO Employee (id, name, salary, departmentId)
VALUES (19, "ktemsdf", 59, 4);
INSERT INTO Employee (id, name, salary, departmentId)
VALUES (7, "sznproml", 46, 4);
INSERT INTO Employee (id, name, salary, departmentId)
VALUES (18, "kwb", 42, 4);
INSERT INTO Employee (id, name, salary, departmentId)
VALUES (8, "uancfhf", 37, 4);
INSERT INTO Employee (id, name, salary, departmentId)
VALUES (10, "hpusuz", 35, 4);
INSERT INTO Employee (id, name, salary, departmentId)
VALUES (1, "rhdnq", 7, 4);
