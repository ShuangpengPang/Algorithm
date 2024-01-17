# 上升的温度

# Write your MySQL query statement below

select w1.id
from Weather w1
    left join Weather w2 on datediff(w1.recordDate, w2.recordDate) = 1
where w1.temperature > w2.temperature
;

select w1.id
from Weather w1
where exists(
    select 1
    from Weather w2
    where w2.recordDate = date_sub(w1.recordDate, interval 1 day)
        and w2.temperature < w1.temperature
)
;
