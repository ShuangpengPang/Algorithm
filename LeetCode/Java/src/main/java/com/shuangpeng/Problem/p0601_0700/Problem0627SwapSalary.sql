# 变更性别

# Write your MySQL query statement below

update Salary set sex = (case when sex = 'f' then 'm' else 'f' end)
;
