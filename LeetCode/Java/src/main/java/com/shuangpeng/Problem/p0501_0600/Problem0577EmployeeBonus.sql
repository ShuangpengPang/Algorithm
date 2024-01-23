# 员工奖金

# Write your MySQL query statement below

select e.name, b.bonus
from Employee e
left join Bonus b on e.empId = b.empId
where b.bonus is null or b.bonus < 1000
;

select e.name, (select b.bonus from Bonus b where b.empId = e.empId && b.bonus < 1000) as bonus
from Employee e
where e.empId not in (
    select empId
    from Bonus
    where bonus >= 1000
)
;
