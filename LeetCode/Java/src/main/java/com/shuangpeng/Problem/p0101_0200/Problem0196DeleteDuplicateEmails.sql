# 删除重复的电子邮箱

# Write your MySQL query statement below
with ToDelete as (
    select id
    from Person s
    where exists(
        select 1
        from Person t
        where t.email = s.email
            and t.id < s.id
    )
)
delete
from Person
where id in (select id from ToDelete)
;
