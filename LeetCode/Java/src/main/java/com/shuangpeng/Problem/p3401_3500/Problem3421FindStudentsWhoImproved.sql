# Write your MySQL query statement below（查找进步的学生）

with date_info as (
    select student_id, subject, min(exam_date) first_date, max(exam_date) as latest_date
    from Scores
    group by student_id, subject
), first_exam as (
    select s.*
    from date_info d
        left join Scores s on s.student_id = d.student_id and s.subject = d.subject and s.exam_date = d.first_date
), latest_exam as (
   select s.*
       from date_info d
           left join Scores s on s.student_id = d.student_id and s.subject = d.subject and s.exam_date = d.latest_date
)
select f.student_id, f.subject, f.score as first_score, l.score as latest_score
from first_exam f
    left join latest_exam l on l.student_id = f.student_id and l.subject = f.subject
where l.score > f.score
order by f.student_id, f.subject;