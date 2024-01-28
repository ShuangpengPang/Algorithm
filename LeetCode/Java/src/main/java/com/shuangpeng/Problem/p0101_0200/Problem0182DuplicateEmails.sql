# 查找重复的电子邮箱

# Write your MySQL query statement below

select email
from Person
group by email
having count(*) > 1
;
