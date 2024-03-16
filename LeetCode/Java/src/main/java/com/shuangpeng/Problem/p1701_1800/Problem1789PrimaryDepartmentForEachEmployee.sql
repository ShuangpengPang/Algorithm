# Write your MySQL query statement below（员工的直属部门）

select employee_id, department_id
from Employee e
where primary_flag = 'Y' or (select count(1) from Employee where employee_id = e.employee_id) = 1
;

select employee_id, department_id
from Employee
where primary_flag = 'Y' or employee_id in (
    select employee_id
    from Employee
    group by employee_id
    having count(1) = 1
)
;

with tmp as (
    select employee_id, department_id, primary_flag, count(1) over (partition by employee_id) cnt
    from Employee
)
select employee_id, department_id
from tmp
where primary_flag = 'Y' or cnt = 1
;
