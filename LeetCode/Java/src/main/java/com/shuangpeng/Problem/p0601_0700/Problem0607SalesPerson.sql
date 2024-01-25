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

select name
from SalesPerson
where sales_id not in (
    select s.sales_id
    from Company c
        left join Orders o on c.com_id = o.com_id
        left join SalesPerson s on o.sales_id = s.sales_id
    where c.name = 'RED'
        and s.sales_id is not null
)
;

select name
from SalesPerson
where sales_id not in (
    select o.sales_id
    from Orders o
        left join Company c on c.com_id = o.com_id
    where c.name = 'RED'
)
;
