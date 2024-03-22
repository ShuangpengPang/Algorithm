# Write your MySQL query statement below（上级经理已离职的公司员工）

select employee_id
from Employees
where salary < 30000 and manager_id is not null and manager_id not in (
    select employee_id
    from Employees
)
order by employee_id
;
