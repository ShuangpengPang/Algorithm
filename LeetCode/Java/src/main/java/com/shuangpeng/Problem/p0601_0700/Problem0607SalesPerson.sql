# 销售员

# Write your MySQL query statement below

select name
from SalesPerson s
where not exists (
    select 1
    from Orders o
        left join Company c on c.com_id = o.com_id
    where o.sales_id = s.sales_id
        and c.name = 'RED'
)
;
