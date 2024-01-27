# 项目员工I

# Write your MySQL query statement below

select p.project_id, round(sum(e.experience_years) / count(*), 2) as average_years
from Project p
    left join Employee e on e.employee_id = p.employee_id
where e.experience_years is not null
group by p.project_id
;

SELECT
    project_id,
    ROUND(AVG(e.experience_years),2)  AS average_years
FROM
    Project as p
LEFT JOIN
    Employee as e
ON
    p.employee_id = e.employee_id
GROUP BY
    p.project_id
;

select distinct p.project_id, round(avg(e.experience_years) over (partition by p.project_id), 2) as average_years
from Project p
    left join Employee e on p.employee_id = e.employee_id
;
