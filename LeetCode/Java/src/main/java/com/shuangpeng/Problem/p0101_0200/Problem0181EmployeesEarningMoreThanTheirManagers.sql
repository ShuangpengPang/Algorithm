# 超过经理收入的员工
# Write your MySQL query statement below

SELECT
    name AS Employee
FROM
    Employee e
WHERE
    e.managerId IS NOT NULL AND EXISTS (SELECT 1 FROM Employee e1 WHERE e1.id = e.managerId AND e.salary > e1.salary)
;

SELECT
    name AS Employee
FROM
    Employee e
WHERE
    e.managerId IS NOT NULL AND e.salary > (SELECT salary FROM Employee e1 WHERE e1.id = e.managerId)
;

SELECT
    e.name AS Employee
FROM
    Employee e
JOIN
    Employee e1
ON e.managerId = e1.id AND e.salary > e1.salary
;
