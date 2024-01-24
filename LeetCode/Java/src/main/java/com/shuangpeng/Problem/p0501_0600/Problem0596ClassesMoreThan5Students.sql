# 超过5名学生的课

# Write your MySQL query statement below

select class
from Courses
group by class
having count(1) >= 5
;
