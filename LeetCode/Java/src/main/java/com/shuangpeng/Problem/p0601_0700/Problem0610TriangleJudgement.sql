# 判断三角形

# Write your MySQL query statement below

select x, y, z,
    case
        when greatest(x, y, z) * 2 < x + y + z then 'Yes'
        else 'No'
    end as triangle
from Triangle
;
