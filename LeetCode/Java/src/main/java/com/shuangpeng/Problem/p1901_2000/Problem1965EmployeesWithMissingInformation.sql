# Write your MySQL query statement below（丢失信息的雇员）

select employee_id
from Employees
where employee_id not in (select employee_id from Salaries)
union all
select employee_id
from Salaries
where employee_id not in (select employee_id from Employees)
order by employee_id
;

with tmp as (
    select employee_id
    from Employees
    union all
    select employee_id
    from Salaries
)
select employee_id
from tmp
group by employee_id
having count(1) = 1
order by employee_id
;
