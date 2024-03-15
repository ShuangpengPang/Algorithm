# Write your MySQL query statement below（查找每个员工花费的总时间）

select event_day as day, emp_id, sum(out_time - in_time) as total_time
from employees
group by event_day, emp_id
;
