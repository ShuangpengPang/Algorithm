# Write your MySQL query statement below（排名靠前的旅行者）

select u.name, sum(ifnull(r.distance, 0)) as travelled_distance
from Users u
    left join Rides r on r.user_id = u.id
group by u.id
order by sum(ifnull(r.distance, 0)) desc, u.name asc
;
