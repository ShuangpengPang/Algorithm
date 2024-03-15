# Write your MySQL query statement below（每位经理的下属员工数量）

select m.employee_id, m.name, count(1) as reports_count, round(avg(e.age)) as average_age
from Employees m
    left join Employees e on m.employee_id = e.reports_to
where e.employee_id is not null
group by m.employee_id
order by m.employee_id asc
;
