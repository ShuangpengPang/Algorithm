# Write your MySQL query statement below（2020年最后一次登录）

select user_id, max(time_stamp) as last_stamp
from Logins
where time_stamp >= '2020-01-01' and time_stamp < '2021-01-01'
group by user_id
;

select user_id, max(time_stamp) as last_stamp
from Logins
where year(time_stamp) = '2020'
group by user_id
;
