# Write your MySQL query statement below（每台机器的进程平均运行时间）

select machine_id, round(sum(if(activity_type = 'start', -timestamp, timestamp)) / (count(1) / 2), 3) as processing_time
from Activity
group by machine_id
;

with tmp_diff as (
    select a1.machine_id, (a2.timestamp - a1.timestamp) as diff_time
    from Activity a1
        left join Activity a2 on a2.machine_id = a1.machine_id and a2.process_id = a1.process_id and a2.activity_type = 'end'
    where a1.activity_type = 'start'
)
select machine_id, round(sum(diff_time) / count(1), 3) as processing_time
from tmp_diff
group by machine_id
;
