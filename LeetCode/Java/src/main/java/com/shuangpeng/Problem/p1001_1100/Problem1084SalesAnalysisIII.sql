# 销售分析III

# Write your MySQL query statement below

with Tmp as (
    select distinct p.product_id, p.product_name
    from Product P
        left join Sales s on s.product_id = p.product_id
    where s.sale_date < '2019-01-01' or s.sale_date > '2019-03-31'
)
select distinct p.product_id, p.product_name
from Product p
    left join Sales s on s.product_id = p.product_id
where not exists(select 1 from Tmp t where t.product_id = p.product_id)
    and s.sale_date >= '2019-01-01' and s.sale_date <= '2019-03-31'
;

select product_id, product_name
from Product
where product_id not in (
    select product_id
    from Sales
    where sale_date < '2019-01-01' or sale_date > '2019-03-31'
) and product_id in (
    select product_id
    from Sales
)
;