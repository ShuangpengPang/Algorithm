# Write your MySQL query statement below（员工的直属部门）

select employee_id, department_id
from Employee e
where primary_flag = 'Y' or (select count(1) from Employee where employee_id = e.employee_id) = 1
;
