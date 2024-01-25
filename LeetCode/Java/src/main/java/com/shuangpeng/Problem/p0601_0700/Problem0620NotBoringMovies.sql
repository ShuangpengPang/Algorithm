# 有趣的电影

# Write your MySQL query statement below

select *
from Cinema
where id % 2 = 1
    and description not like '%boring%'
order by rating desc
;

select *
from cinema
where mod(id, 2) = 1 and description != 'boring'
order by rating DESC
;
