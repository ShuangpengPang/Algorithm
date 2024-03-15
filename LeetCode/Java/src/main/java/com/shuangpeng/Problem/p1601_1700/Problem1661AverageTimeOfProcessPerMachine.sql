# Write your MySQL query statement below（每台机器的进程平均运行时间）

select machine_id, round(sum(if(activity_type = 'start', -timestamp, timestamp)) / (count(1) / 2), 3) as processing_time
from Activity
group by machine_id
;
