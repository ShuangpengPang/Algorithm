# Write your MySQL query statement below（查找拥有有效邮箱的用户）

select user_id, name, mail
from Users
where mail regexp '^[a-zA-Z][a-zA-Z0-9_\\-\\.]*@leetcode\\.com$'
;
