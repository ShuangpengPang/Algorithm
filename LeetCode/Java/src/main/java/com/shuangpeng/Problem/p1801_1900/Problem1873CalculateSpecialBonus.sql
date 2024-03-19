# Write your MySQL query statement below（计算特殊奖金）

select employee_id, if(employee_id % 2 = 1 and substring(name, 1, 1) != 'M', salary, 0) as bonus
from Employees
order by employee_id
;

select employee_id, if(employee_id % 2 = 1 and name not regexp '^M', salary, 0) as bonus
from Employees
order by employee_id
;
