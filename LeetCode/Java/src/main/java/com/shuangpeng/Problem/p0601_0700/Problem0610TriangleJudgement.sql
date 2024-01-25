# 判断三角形

# Write your MySQL query statement below

select x, y, z,
    case
        when greatest(x, y, z) * 2 < x + y + z ten 'Yes'
        else 'No'
    end as triangle
from Triangle
;

SELECT
    x,
    y,
    z,
    CASE
        WHEN x + y > z AND x + z > y AND y + z > x THEN 'Yes'
        ELSE 'No'
    END AS 'triangle'
FROM
    triangle
;
