# Write your MySQL query statement below（每个产品在不同商店的价格）

with stores as (
    select 'store1' as store
    union all
    select 'store2' as store
    union all
    select 'store3' as store
), tmp as (
    select p.product_id, s.store,
        case
            when s.store = 'store1' then p.store1
            when s.store = 'store2' then p.store2
            when s.store = 'store3' then p.store3
        end as price
    from Products p
        left join stores s on true
)
select *
from tmp
where price is not null
;

select product_id, 'store1' as store, store1 as price
from Products
where store1 is not null
union all
select product_id, 'store2' as store, store2 as price
from Products
where store2 is not null
union all
select product_id, 'store3' as store, store3 as price
from Products
where store3 is not null
;
