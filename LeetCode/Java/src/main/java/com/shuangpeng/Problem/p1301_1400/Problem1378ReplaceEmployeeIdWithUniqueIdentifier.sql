# Write your MySQL query statement below（使用唯一标识码替换员工ID）

select u.unique_id, e.name
from Employees e
    left join EmployeeUNI u on u.id = e.id
;
