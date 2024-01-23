# 订单最多的客户

# Write your MySQL query statement below

with order_statistic as (
    select count(1) as order_count, customer_number
    from Orders
    group by customer_number
)
select customer_number
from order_statistic
where order_count = (select max(order_count) from order_statistic)
;
