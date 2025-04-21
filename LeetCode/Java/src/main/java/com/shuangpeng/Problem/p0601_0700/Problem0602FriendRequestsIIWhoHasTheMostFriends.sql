# Write your MySQL query statement below（好友申请II：谁有最多的好友）

with friend as (
    select accepter_id as id, count(*) as cnt
    from RequestAccepted
    group by id
    union all
    select requester_id as id, count(*) as cnt
    from RequestAccepted
    group by id
)
select id, sum(cnt) as num
from friend
group by id
order by num desc
limit 1;
