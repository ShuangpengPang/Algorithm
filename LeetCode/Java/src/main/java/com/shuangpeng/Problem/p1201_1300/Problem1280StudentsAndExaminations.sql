# Write your MySQL query statement below（学生们参加各科测试的次数）

select s.student_id, s.student_name, j.subject_name, sum(if(e.student_id is null, 0, 1)) as attended_exams
from Students s
    left join Subjects j on true
    left join Examinations e on e.student_id = s.student_id and e.subject_name = j.subject_name
where j.subject_name is not null
group by s.student_id, s.student_name, j.subject_name
order by student_id, subject_name
;
