# Write your MySQL query statement below（查找具有有效序列号的产品）
select *
from products
where description regexp '.*SN([0-9]){4}-([0-9]){4}($|[^0-9].*)'
order by product_id;

select *
from products
where description regexp 'SN([0-9]){4}-([0-9]){4}($|[^0-9])'
order by product_id;
