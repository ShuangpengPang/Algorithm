# Write your MySQL query statement below（至少有5名直接下属的经理）

select name
from (
    select distinct id, name
    from Employee e
    where (
            select count(*)
            from Employee e1
            where e1.managerId = e.id
        ) >= 5
) t;
