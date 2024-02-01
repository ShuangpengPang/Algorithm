# 平均售价

# Write your MySQL query statement below

with summary as (
    select u.product_id, round(sum(p.price * u.units) / sum(u.units), 2) as average_price
    from UnitsSold u
        left join Prices p on p.product_id = u.product_id and u.purchase_date >= p.start_date and u.purchase_date <= p.end_date
    group by u.product_id
)
select distinct p.product_id, ifnull(s.average_price, 0) as average_price
from Prices p
    left join summary s on s.product_id = p.product_id
;

select p.product_id, ifnull(round(sum(p.price * u.units) / sum(u.units), 2), 0) as average_price
from Prices p
    left join UnitsSold u on u.product_id = p.product_id and u.purchase_date >= p.start_date and u.purchase_date <= p.end_date
group by p.product_id
;
