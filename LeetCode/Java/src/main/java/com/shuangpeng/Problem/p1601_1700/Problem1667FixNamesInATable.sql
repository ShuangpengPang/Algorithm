# Write your MySQL query statement below（修复表中的名字）

select user_id, concat(upper(substring(name, 1, 1)), lower(substring(name, 2, char_length(name) - 1))) as name
from Users
order by user_id
;

SELECT user_id, CONCAT(UPPER(SUBSTRING(name, 1, 1)), LOWER(SUBSTRING(name, 2))) AS name
FROM Users
ORDER BY user_id;
;
