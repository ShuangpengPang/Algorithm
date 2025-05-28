# Write your MySQL query statement below（确认率）

select s.user_id, round(if(c.user_id is null, 0, sum(if(c.action = 'confirmed', 1, 0)) / count(*)), 2) as confirmation_rate
from Signups s
    left join Confirmations c on s.user_id = c.user_id
group by s.user_id
;

select s.user_id, round(ifnull(avg(c.action = 'confirmed'), 0), 2) as confirmation_rate
from Signups s
    left join Confirmations c on s.user_id = c.user_id
group by s.user_id
;
