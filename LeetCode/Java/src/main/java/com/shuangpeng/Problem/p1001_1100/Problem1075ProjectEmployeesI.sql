# 项目员工I

# Write your MySQL query statement below

select p.project_id, round(sum(e.experience_years) / count(*), 2) as average_years
from Project p
    left join Employee e on e.employee_id = p.employee_id
where e.experience_years is not null
group by p.project_id
;
