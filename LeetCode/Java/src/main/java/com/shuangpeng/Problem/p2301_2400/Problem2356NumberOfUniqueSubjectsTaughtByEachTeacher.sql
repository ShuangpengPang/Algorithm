# Write your MySQL query statement below（每位教师所教授的科目种类的数量）

select teacher_id, count(distinct subject_id) as cnt
from Teacher
group by teacher_id
;
